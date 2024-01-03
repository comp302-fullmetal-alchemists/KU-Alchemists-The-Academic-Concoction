package system.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private BufferedReader fromClient;
    private PrintWriter toClient;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        toClient = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {

            while (true) {
                String input = fromClient.readLine();
                if (input.equals("Hello")) {
                    toClient.println(Server.getRandomNumber());
                }
                else {
                    toClient.println("Error");
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Client disconnected.");
        }
        finally {
            try {
                fromClient.close();
                toClient.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
