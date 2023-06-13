package server;

import inner_client.Client;

public class InnerClientThread extends Thread{
    @Override
    public void run() {
        Client client = new Client();
        client.doWhileTrue();
    }
}
