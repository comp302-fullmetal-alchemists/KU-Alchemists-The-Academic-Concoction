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
        this.serverSocket = new ServerSocket(port);
    //Socket clientSocket = serverSocket.accept();
    //serverSocket.setSoTimeout(10000);
    }
    

    public void startServer() {
        try {
            while (true) {
                System.out.println("Waiting for clients on port " + serverSocket.getLocalPort() + "...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                executorService.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stopServer();
        }
    }

    public void stopServer() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static char[] getRandomNumber() {
        char[] random = new char[4];
        for (int i = 0; i < 4; i++) {
            random[i] = (char) (Math.random() * 10 + '0');
        }
        return random;
    }


    public static void main(String[] args) {
        try {
            Server server = new Server(8080);
            server.startServer();
        } catch (IOException e) {
            System.out.println("Cannot start server");
            e.printStackTrace();
        }
    }


    
}
