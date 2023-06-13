package server;

import data.ResultData;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Attachment {

    public ByteBuffer readingBuffer = ByteBuffer.allocate(1024);
    public int objectLength = 0;
    public int writeBytes = 0;
    public ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    public ResultData resultData;

    public byte[] getUsefulBytes(){
        return byteArrayOutputStream.toByteArray();
    }

    public boolean isFull(){
        return (getUsefulBytes().length == objectLength);
    }

}
