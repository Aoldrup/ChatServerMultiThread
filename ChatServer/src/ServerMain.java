import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerMain {
    public static void main(String[] args) {
        int port = 8818;
        try {

            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("hej med dig");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted" + clientSocket);

                Thread t = new Thread(){
                    @Override
                    public void run(){
                        try {
                            HandleClientSocket(clientSocket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
            }

        }  catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void HandleClientSocket(Socket clientSocket) throws IOException, InterruptedException {
        OutputStream outputStream = clientSocket.getOutputStream();
        for (int i = 0; i < 10; i++) {
            outputStream.write(("Time now is " + new Date() + "\n").getBytes());
            Thread.sleep(1000);
        }
        clientSocket.close();
    }
}