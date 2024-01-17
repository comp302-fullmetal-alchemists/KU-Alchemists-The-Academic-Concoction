package system.network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import system.domain.controllers.GameBoardController;

import java.util.ArrayList;

public class OnlineServer extends Thread implements IServerAdapter {

    private final ServerSocket serverSocket;
    private int playerNum;
    private int rounds = 0;
    private final ExecutorService clientExecutor;
    private List<ClientHandler> clients;
    private int currentClient = 0;
    private static final int MAX_CLIENTS = 4;
    private BufferedReader fromServer;
    private List<String> usernames;
    private List<Integer> ingredientPile;
    private GameBoardController gameBoardController;
    private volatile boolean running = true;

    public OnlineServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.clientExecutor = Executors.newCachedThreadPool();
        this.clients = Collections.synchronizedList(new ArrayList<ClientHandler>());
        fromServer = new BufferedReader(new InputStreamReader(System.in));
        usernames = new ArrayList<String>();
        ingredientPile = new ArrayList<Integer>();
        for (int i = 0; i < 24; i++) {
            ingredientPile.add(i % 8);
        }
        Collections.shuffle(ingredientPile);
    }

    public void stopServer() {
        running = false;
        try {
            for (ClientHandler client: clients) {
                client.clientSocket.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (running) {
            try {
                if (clients.size() < MAX_CLIENTS) {
                    System.out.println("[SERVER] Waiting for clients on port " + serverSocket.getLocalPort() + "...");
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("[SERVER] Client connected: " + clientSocket);
                    ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                    clients.add(clientHandler);
                    //gameBoardController.getWelcomeController().setPlayer
                    clientExecutor.execute(clientHandler);
                }
            } catch (IOException e) {
                if (!running) {
                    System.out.println("[SERVER] Server stopped.");
                    break;
                }
                e.printStackTrace();
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
                    currentClient += 1;
                    if (currentClient == clients.size()) {
                        currentClient = 0;
                        System.out.println("[SERVER] All clients authenticated.");
                        initializeGame();
                    }
                    else {
                        startAuthentication();
                    }
                }
                else if (message.contains("validateUsername")) {
                    String username = message.split(":")[1];
                    if (usernames.contains(username)) {
                        writer.writeUTF("duplicateUsername");
                    }
                    else {
                        usernames.add(username);
                        writer.writeUTF("validUsername");
                    }
                }
                else if (message.contains("change_player")) {
                    changePlayer();
                }
                else if (message.contains("request_ingredient")) {
                    requestIngredient();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public DataOutputStream getWriter() {
            return writer;
        }
    }

    @Override
    public void setPlayerNumber(int playerNum) {
        this.playerNum = clients.size();
    }

    @Override
    public void startAuthentication() {
        try {
            System.out.println("[SERVER] Sending authentication request to client " + currentClient);
            clients.get(currentClient).getWriter().writeUTF("authentication");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initializeGame() {
        List<Integer> alchemyIndex = assignRandomAlchemy();
        Collections.shuffle(clients);
        for (int i = 0; i < clients.size(); i++) {
            try {
                System.out.println("[SERVER] Sending initialize request to client " + i);
                clients.get(i).getWriter().writeUTF("SetAlchemy:"+alchemyIndex.toString());
                clients.get(i).getWriter().writeUTF("initialize");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[SERVER] All clients initialized."); 
        authorizeClient();
    }

    public List<Integer> assignRandomAlchemy() {
        ArrayList<Integer> alchemyIndex = new ArrayList<Integer>();
        for (int i = 0; i < 8; i++) {
            alchemyIndex.add(i);
        }
        Collections.shuffle(alchemyIndex);
        return alchemyIndex;
    }

    @Override
    public void authorizeClient() {
        try {
            clients.get(currentClient).getWriter().writeUTF("authorize");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setNextClient() {
        currentClient += 1;
        if (currentClient == playerNum) {
            newRound();
        }
    }

    @Override
    public void deauthorizeClient() {
        try {
            clients.get(currentClient).getWriter().writeUTF("deauthorize");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changePlayer() {
        //first remove the old player
        deauthorizeClient();
        //then find new player, change round number if needed
        setNextClient();
        //finally, give permission to new player
        authorizeClient();
    }

    @Override
    public void newRound() {
        currentClient = 0;
        rounds += 1;
    }

    @Override
    public void requestIngredient() {
        try {
            if (ingredientPile.isEmpty()) {
                clients.get(currentClient).getWriter().writeUTF("empty_ingredient_pile");
            }
            else {
                clients.get(currentClient).getWriter().writeUTF("ingredient:"+ingredientPile.remove(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
  
    }
}
