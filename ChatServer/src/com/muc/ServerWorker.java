package com.muc;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class ServerWorker extends Thread
{
    private final Socket clientSocket;

    public ServerWorker(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocket();
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException, InterruptedException {
        OutputStream outputStream = clientSocket.getOutputStream();
        for (int i = 0; i < 10; i++) {
            outputStream.write(("Time now is " + new Date() + "\n").getBytes());
            Thread.sleep(1000);
        }
        clientSocket.close();
    }
}
