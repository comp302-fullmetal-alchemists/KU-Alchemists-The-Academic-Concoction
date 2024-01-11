package system.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;

public class OnlineServer extends Thread implements IServerAdapter {
    
    String hostName = "127.0.0.1";
    private ServerSocket serverSocket;
    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);
    

    public OnlineServer(int port) throws IOException {
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

      // host sets the number of players that will join the game
    public void setPlayerNumber(int playerNum) {
        //this will be get from welcompagecontroller
    }

    // after number of players is set, server starts accepting clients (local computers)
    public void acceptClients() {
        // this online mode, sockets will be used
        // to communicate with clients, we will use bufferedReader and printWriter
    }

    // after clients are accepted, authentication as players must be done
    public void startAuthentication() {
        // after pressing start button, this method will be called
    }

    // after authentication, each client (local computer) should initialize their gameboards
    public void initializeGame() {
        // after authentication, this method will be called - 
    }

    // ingredient pile is open to all clients, server holds it
    public boolean ingPileIsEmpty() {
        return false;
    }

    public IngredientCard drawIngredient() {
        return null;
    }

    // artifact pile is open to all clients, server holds it
    public boolean artifactPileIsEmpty() {
        return false;
    }
    
    public ArtifactCard drawArtifact() {
        return null;
    }

    // players are changed with the following steps
    public void changePlayer() {

    }

    // 1) current client is deauthorized
    public void deauthorizeClient() {

    }

    // 2) next client is figured out
    public void setNextClient() {

    }

    // 3) the client that is to be the next is given authorization to play
    public void authorizeClient() {

    }

    // add 1 more to the rounds
    public void newRound() {
        
    }


    @Override
    public int requestIngredient() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestIngredient'");
    }
    
}
