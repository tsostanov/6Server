package inner_client;

import data.CommandData;
import data.ResultData;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class WebDispatcher {
    private SocketChannel socketChannel;
    private ByteBuffer readBuf = ByteBuffer.allocate(8192);
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private int writeBytes = 0;
    private int objectLength = 0;
    public boolean isConnected = false;
    private Message messageComponent;
    private Warning warningComponent;

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public WebDispatcher(Message messageComponent, Warning warningComponent){
        this.messageComponent = messageComponent;
        this.warningComponent = warningComponent;
    }

    public SocketChannel connect(String host, int port) throws Exception{
            InetSocketAddress address = new InetSocketAddress(host, port);

            //try to connect
            while (!isConnected) {
                try {
                    socketChannel = SocketChannel.open(address);
                    isConnected = socketChannel.isConnected();
                    messageComponent.connectionSuccess();
                } catch (ConnectException e) {
                    System.out.println("Try to connect ... ");
                    Thread.sleep(3000);
                }
            }

            socketChannel.configureBlocking(false);


        return socketChannel;
    }

    public void sendCommandDataToExecutor(CommandData commandData) throws IOException {
        if (commandData.command.isClientCommand()){
            sendCommandToClient(commandData);
        }
        else{
            sendCommandToServer(commandData);
        }

    }

    private void sendCommandToServer(CommandData commandData) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(commandData);

        //add 4 to write length
        ByteBuffer buffer = ByteBuffer.allocate(4 + byteArrayOutputStream.size());
        buffer.putInt(0, byteArrayOutputStream.size());
        buffer.put(4, byteArrayOutputStream.toByteArray());
        buffer.position(0);
        socketChannel.write(buffer);
    }

    private void sendCommandToClient(CommandData commandData){
        commandData.client.execute(commandData);
    }

    public ResultData getResultDataFromServer() throws IOException, ClassNotFoundException {
        int r = socketChannel.read(readBuf);
        if (r == -1) {
            socketChannel.close();
            warningComponent.warningMessage("Connection was ended.");
            return null;
        }
        if(r == 0){
            return null;
        }
        System.out.println("Get " + r + " bytes from server");

        readBuf.flip();
        //if this record is first
        if (writeBytes == 0){
            if (readBuf.remaining() > 4){
                objectLength = readBuf.getInt();
                int canRead = Math.min(readBuf.remaining(), (objectLength - writeBytes));
                byte[] newBytes = new byte[canRead];
                readBuf.get(newBytes);
                byteArrayOutputStream.write(newBytes);
                writeBytes += canRead;
            }
            else{
                warningComponent.warningMessage("Not enough bytes for object");
                return null;
            }
        }
        //if this record is second+
        else{
            int canRead = Math.min(readBuf.remaining(), (objectLength - writeBytes));
            byte[] newBytes = new byte[canRead];
            readBuf.get(newBytes);
            byteArrayOutputStream.write(newBytes);
            writeBytes += canRead;
        }
        //if object was not ended
        if (writeBytes < objectLength){
            messageComponent.printText("Still have to read something");
        }

        // compact instead of clear?
        readBuf.flip();
        readBuf.clear();

        ResultData resultData = transformToResultData();
        if (resultData != null){
            readBuf = ByteBuffer.allocate(8192);
            byteArrayOutputStream = new ByteArrayOutputStream();
            writeBytes = 0;
            objectLength = 0;
        }
        return resultData;
    }

    private ResultData transformToResultData() throws IOException, ClassNotFoundException {
        byte[] usefulBytes = byteArrayOutputStream.toByteArray();
        if (usefulBytes.length == objectLength){
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(usefulBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            ResultData resultData = (ResultData) objectInputStream.readObject();
            return resultData;
        }
        return null;
    }
}
