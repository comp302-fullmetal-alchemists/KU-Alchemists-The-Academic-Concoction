package system.network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import javax.xml.crypto.Data;

import system.domain.IngredientCard;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.Player;

public class OnlineClient extends Thread implements IClientAdapter {
    private Socket socket;
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private BufferedReader fromUser;
    
    private static final String HOST = "127.0.0.1"; 

    public OnlineClient(int port) throws IOException {
        this.socket = new Socket(HOST, port);
        this.fromServer = new DataInputStream(socket.getInputStream());
        this.toServer = new DataOutputStream(socket.getOutputStream());
        this.fromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("[CLIENT] Connected to server on port " + port);
    }

    public void run() {
        try {
            while (true) { 
                handleServerMessage(fromServer.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    public String readFromServer() {
        try {
            return fromServer.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void handleServerMessage(String message) {
        // Handle different types of messages from the server
        System.out.println("[CLIENT] Server says: " + message);

        // Example: if it's the player's turn, read input and send to server
            try {
                if(message.equals("authentication")) {
                    startAuthentication();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        
        // Add other message handling logic here
    }

    private void closeResources() {
        try {

            if (socket != null) {
                socket.close();
            }
            if (fromServer != null) {
                fromServer.close();
            }
            if (toServer != null) {
                toServer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* 
    public static void main(String[] args) {
        try {
            OnlineClient client = new OnlineClient(6060);
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */


    @Override
    public void connectToServer() {
        // Connection is already established in the constructor
    }

    @Override
    public void startAuthentication() {
        // Authentication logic goes here
        GameBoardController.getInstance().startAuthentication();
    }

    @Override
    public boolean validateUserChoices(String username) {
        try {
            toServer.writeUTF("validateUsername:"+username);

            if (fromServer.readUTF().equals("validUsername")) {
                System.out.println("[CLIENT] Username is valid");
                return true;
            }
            else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void initialize() {
        // Initialize client-side game elements
    }

    @Override
    public void authorize() {
        // Authorization logic
    }

    @Override
    public void deauthorize() {
        // Deauthorization logic
    }

    @Override
    public void endPlayerTurn() {
        // Notify server that player turn has ended
    }

    @Override
    public IngredientCard drawIngredient() {
        // Request an ingredient card from the server
        return null; // Placeholder return value
    }

    @Override
    public void registerPlayer(Player p) {
        System.out.println("[CLIENT] Registering player");
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerPlayer'");
    }

    @Override
    public void setAlchemyMap(List<Integer> alchemyIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAlchemyMap'");
    }


}
