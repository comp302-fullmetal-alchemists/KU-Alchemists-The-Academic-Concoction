package system.network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.crypto.Data;

import java.util.ArrayList;

public class OnlineServer extends Thread implements IServerAdapter {

    private final ServerSocket serverSocket;
    private int playerNum;
    private final ExecutorService clientExecutor;
    private List<ClientHandler> clients;
    private int currentClient = 0;
    private static final int MAX_CLIENTS = 4;
    private BufferedReader fromServer;
    private List<String> usernames;

    public OnlineServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.clientExecutor = Executors.newCachedThreadPool();
        this.clients = Collections.synchronizedList(new ArrayList<ClientHandler>());
        fromServer = new BufferedReader(new InputStreamReader(System.in));
        usernames = new ArrayList<String>();
    }

    public void run() {
        while (true) {
            try {
                if (clients.size() < MAX_CLIENTS) {
                    System.out.println("[SERVER] Waiting for clients on port " + serverSocket.getLocalPort() + "...");
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("[SERVER] Client connected: " + clientSocket);
                    ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                    clients.add(clientHandler);
                    clientExecutor.execute(clientHandler);
                }
            } catch (IOException e) {
                if (!serverSocket.isClosed()) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void removeClient(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    private class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final OnlineServer server;
        private DataOutputStream writer;
        private DataInputStream reader;
                
        public ClientHandler(Socket socket, OnlineServer server) {
            this.clientSocket = socket;
            this.server = server;
        }
    
        public void run() {
            try {
                this.reader = new DataInputStream((clientSocket.getInputStream()));
                this.writer = new DataOutputStream(clientSocket.getOutputStream()); 

                System.out.println("[SERVER] ClientHandler running for client "+ clients.size()+".: " + clientSocket);

                writer.writeUTF("Welcome to the Server!");

                String clientMessage;

                while (true) {
                    clientMessage = reader.readUTF();
                    handleClientMessage(clientMessage);
                }
    
            } catch (IOException e) {
                System.out.println("Exception in ClientHandler: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException ex) {
                    System.out.println("Exception while closing client socket: " + ex.getMessage());
                    ex.printStackTrace();
                }
                server.removeClient(this);
                System.out.println("ClientHandler terminated for client: " + clientSocket);
            }
        }

        public void handleClientMessage(String message) {
            try {
                if (message.equals("authentication_done")) {
                    if (currentClient == clients.size()) {
                        currentClient = 0;
                        System.out.println("[SERVER] All clients authenticated.");
                    }
                    else {
                        startAuthentication();
                    }
                }
                if (message.contains("validateUsername")) {
                    String username = message.split(":")[1];
                    if (usernames.contains(username)) {
                        writer.writeUTF("duplicateUsername");
                    }
                    else {
                        usernames.add(username);
                        writer.writeUTF("validUsername");
                    }
                    


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public DataOutputStream getWriter() {
            return writer;
        }
    }
    /*
    public static void main(String[] args) {
        int port = 6060;
        try {
            Thread serverThread = new OnlineServer(port);
            serverThread.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

    @Override
    public void setPlayerNumber(int playerNum) {
        this.playerNum = clients.size();
    }

    @Override
    public void startAuthentication() {
        try {
            System.out.println("[SERVER] Sending authentication request to client " + currentClient);
            clients.get(currentClient).getWriter().writeUTF("authentication");
            currentClient += 1;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void initializeGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initializeGame'");
    }

    @Override
    public void changePlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changePlayer'");
    }

    @Override
    public void deauthorizeClient() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deauthorizeClient'");
    }

    @Override
    public void setNextClient() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNextClient'");
    }

    @Override
    public void authorizeClient() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authorizeClient'");
    }

    @Override
    public void newRound() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newRound'");
    }

    @Override
    public int requestIngredient() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestIngredient'");
    }

    @Override
    public void acceptClients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'acceptClients'");
    }
}
