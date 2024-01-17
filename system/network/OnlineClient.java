package system.network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import system.domain.Alchemy;
import system.domain.Theory;
import system.domain.controllers.AuthenticationController;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.Player;
import system.domain.util.IngredientFactory;

public class OnlineClient extends Thread implements IClientAdapter {
    private Socket socket;
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private BufferedReader fromUser;
    
    private static final String HOST = "127.0.0.1"; 

    public OnlineClient(String ip, int port) throws IOException {
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
                else if (message.equals("duplicateUsername")) {
                    AuthenticationController.getInstance().invalidUsername();
                }
                else if (message.equals("validUsername")) {
                    AuthenticationController.getInstance().validUsername();
                }
                else if (message.contains("SetAlchemy")) {
                    // Extracting the part after the colon
                    String alchemyIndex = message.split(":")[1];
                
                    // Removing the square brackets
                    alchemyIndex = alchemyIndex.replace("[", "").replace("]", "");
                
                    List<Integer> alchemyMap = new ArrayList<Integer>();
                
                    // Splitting the string by comma and trimming to avoid white space issues
                    for (String s : alchemyIndex.split(",")) {
                        alchemyMap.add(Integer.parseInt(s.trim()));
                    }
                
                    System.out.println("[CLIENT] Alchemy map: " + alchemyMap);
                    setAlchemyMap(alchemyMap);
                }
                else if (message.equals("initialize")) {
                    initialize();
                }
                else if (message.equals("authorize")) {
                    authorize();
                }
                else if (message.equals("deauthorize")) {
                    deauthorize();
                }
                else if (message.equals("empty_ingredient_pile")) {
                    emptyPile();
                }
                else if (message.contains("ingredient")) {
                    int index = Integer.parseInt(message.split(":")[1]);
                    takeIngredientIndex(index);
                }
                else if (message.contains("publish")) {
                    addPublishTheory(message);
                }
                else if (message.contains("debunk")) {
                    addDebunkTheory(message);
                }
                else if (message.contains("endorse")) {
                    addEndorsedTheory(message);
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
 
    @Override
    public void startAuthentication() {
        // Authentication logic goes here
        GameBoardController.getInstance().startAuthentication();
    }

    @Override
    public void validateUserChoices(String username) {
        try {
            toServer.writeUTF("validateUsername:"+username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerPlayer(Player p) {
        System.out.println("[CLIENT] Registering player");
        GameBoardController.getInstance().setPlayer(p);
        try {
            toServer.writeUTF("authentication_done");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAlchemyMap(List<Integer> alchemyIndex) {
        IngredientFactory.getInstance().setAlchemyMap(alchemyIndex);
    }

    @Override
    public void initialize() {
        Random random = new Random();
        Player p = GameBoardController.getInstance().getPlayer();
        p.getInventory().initializeIngredients(IngredientFactory.getInstance().createIngredient(random.nextInt(8)), IngredientFactory.getInstance().createIngredient(random.nextInt(8)));
        GameBoardController.getInstance().getGameLog().GameLogControllerInitPlayer(p);
        GameBoardController.getInstance().initializeTheBoard();
        GameBoardController.getInstance().initializePlayer();
    }

    @Override
    public void authorize() {
        // Authorization logic
        GameBoardController.getInstance().getPlayer().changeTurn();
        GameBoardController.getInstance().authorizePlayer();
    }

    @Override
    public void deauthorize() {
        // Deauthorization logic
        GameBoardController.getInstance().getPlayer().changeTurn();
        GameBoardController.getInstance().deauthorizePlayer();   
    }

    @Override
    public void endPlayerTurn() {
        // Notify server that player turn has ended
        try {
            toServer.writeUTF("change_player");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void requestIngredient() {
        try {
            toServer.writeUTF("request_ingredient");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void emptyPile() {
        GameBoardController.getInstance().getIngredientStorageController().emptyPileError();
    }

    @Override
    public void takeIngredientIndex(int index) {
        GameBoardController.getInstance().getIngredientStorageController().takeIngredient(IngredientFactory.getInstance().createIngredient(index));
    }

    @Override
    public void reportPuplishTheroryToServer(Theory a) {
        //I need to find theory's alchmey's index and ingredient
        try {
            toServer.writeUTF("publish_theory:"+a.getAlchemy().toString()+":"+a.getIngredient()+":"+a.getOwner());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void reportDebunkTheoryToServer(Theory a) {
        try {
            toServer.writeUTF("debunk_theory:"+a.getAlchemy().toString()+":"+a.getIngredient()+":"+a.getOwner());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void reportEndorseTheoryToServer(Theory a) {
        try {
            toServer.writeUTF("endorse_theory:"+a.getIngredient() + a.getEndorser());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addPublishTheory(String message) {
        Alchemy alchemy = Alchemy.findAlchemy(message.split(":")[1]);
        String ingredient = message.split(":")[2];
        Theory theory = new Theory(alchemy, ingredient, message.split(":")[3]);
        if (!GameBoardController.getInstance().getPlayer().getName().equals(message.split(":")[3])) {
            GameBoardController.getInstance().getTheoryController().getTheories().add(theory);
            GameBoardController.getInstance().getPublicationAreaController().serverUpdateUIForPublishedd(theory);
        } 
    }

    private void addEndorsedTheory(String message) {
        for (Theory theory : GameBoardController.getInstance().getTheoryController().getTheories()) {
            if (theory.getIngredient() == message.split(":")[1] & !theory.getEndorser().equals(message.split(":")[2])) {
                theory.endorsed(message.split(":")[2]);
                GameBoardController.getInstance().getPublicationAreaController().serverUpdateUIForEndorsedd(theory);
            }
        }
    }

    private void addDebunkTheory(String message) {
        for (Theory theory : GameBoardController.getInstance().getTheoryController().getTheories()) {
            if (theory.getIngredient() == message.split(":")[1] & !theory.getOwner().equals(message.split(":")[3])) {
                theory.setDebunked(true);
                theory.setOwner(message.split(":")[3]);
                theory.setAlchemy(Alchemy.findAlchemy(message.split(":")[2]));
                GameBoardController.getInstance().getPublicationAreaController().serverUpdateUIForDebunkedd(theory);
            }
        }
    }
}
