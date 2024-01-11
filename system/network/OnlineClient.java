package system.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.controllers.Player;

public class OnlineClient implements IClientAdapter {
    private static final String HOST = "127.0.0.1";
    Socket socket;

    public OnlineClient(Integer Port) throws UnknownHostException, IOException {
        this.socket = new Socket(HOST, Port);
        System.out.println("Connected to server on port " + Port);
    }

    public void run () throws IOException {
        try {
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);

            String userInput;
            while ((userInput = fromUser.readLine()) != null) {
                toServer.println(userInput);
                System.out.println("Server says: " + fromServer.readLine());
            }
        } catch (IOException e) {
            System.out.println("Error in client communication");
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing the client socket");
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        try {
            Client client = new Client(8080);
            client.run();
        } catch (IOException e) {
            System.out.println("Cannot start client");
            e.printStackTrace();
        }
    }

    // client connecting to server, only required for OnlineClient
    public void connectToServer() {

    }

    // Authentication on a local computer
    public void startAuthentication() {

    }

    // clientAdapter talks to server to validate choices
     public boolean validateUserChoices(String username) {
        return false;
     }

    // clientAdapter sends authenticated player to server
    public void registerPlayer(Player p) {

    }

    // local computer is initialized for playing
    public void initialize() {

    }

    // local computer is given the authority to play
    public void authorize() {

    }
    
    // local computer is taken the authority to play
    public void deauthorize() {

    }

    // clientAdapter informs the server that player turn has ended
    public void endPlayerTurn() {

    }


    // clientAdapter talks to server to get the relevant information on gameobjects
    public boolean ingPileIsEmpty() {
        return false;}

    public IngredientCard drawIngredient() {
        return null;}

    public boolean artifactPileIsEmpty() {
        return false;}
    
    public ArtifactCard drawArtifact() {
        return null;}

    @Override
    public void setAlchemyMap(List<Integer> alchemyIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAlchemyMap'");
    }

    
}
