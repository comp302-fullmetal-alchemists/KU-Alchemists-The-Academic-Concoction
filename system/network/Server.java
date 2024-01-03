package system.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread{
    //this will be our server for TCP/IP connections
    String hostName = "127.0.0.1";
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);
    

    public Server(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    Thread t = new Thread(this);
    //Socket clientSocket = serverSocket.accept();
    //serverSocket.setSoTimeout(10000);
    }
    

    public void run() {
        while(true) {
            System.out.println("Waiting for players on port " + serverSocket.getLocalPort() + "...");
            try (Socket player1 = serverSocket.accept()) {
                System.out.println("Player 1 connected.");
                ClientHandler clientHandler = new ClientHandler(player1);
                clients.add(clientHandler);
                executorService.execute(clientHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            System.out.println("Player 1 connected.");

            
            
        }
    }


    public static char[] getRandomNumber() {
        char[] random = new char[4];
        for (int i = 0; i < 4; i++) {
            random[i] = (char) (Math.random() * 10 + '0');
        }
        return random;
    }


    
}
