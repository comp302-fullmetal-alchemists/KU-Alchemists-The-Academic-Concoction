package system.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    //this will be our server for TCP/IP connections
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    //Socket clientSocket = serverSocket.accept();
    //serverSocket.setSoTimeout(10000);
    }

    public void run() {
        while(true) {
            try {
                System.out.println("Waiting for players on port " + serverSocket.getLocalPort() + "...");
                Socket player1 = serverSocket.accept();
                System.out.println("Player 1 connected.");

                Socket player2 = serverSocket.accept();
                System.out.println("Player 2 connected.");

                BufferedReader fromPlayer1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
	            PrintWriter toPlayer1 = new PrintWriter(player1.getOutputStream(), true);
	            BufferedReader fromPlayer2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
	            PrintWriter toPlayer2 = new PrintWriter(player2.getOutputStream(), true);
    
                toPlayer1.println("Player 1");
                toPlayer2.println("Player 2");

                String player1Name = fromPlayer1.readLine();
                String player2Name = fromPlayer2.readLine();

                System.out.println("Player 1 name: " + player1Name);
                System.out.println("Player 2 name: " + player2Name);

                toPlayer1.println(player2Name);
                toPlayer2.println(player1Name);

                

            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
        }
    }


    
}
