package system.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import system.domain.Alchemy;
import system.domain.controllers.AuthenticationController;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.GameLogController;
import system.domain.controllers.Player;
import system.domain.util.IngredientFactory;

public class OnlineClient extends Thread implements IClientAdapter {
    private Socket socket;
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private GameLogController gamelog;
    
    private static final String LOCALHOST = "127.0.0.1"; 

    public OnlineClient(String ip, int port) {
        this.gamelog = GameBoardController.getInstance().getGameLog();
        if (ip.equals("")) {
            ip = LOCALHOST;
        }
        try {
            this.socket = new Socket(ip, port);
            this.fromServer = new DataInputStream(socket.getInputStream());
            this.toServer = new DataOutputStream(socket.getOutputStream());
            System.out.println("[CLIENT] Connected to server on port " + port);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Can't connect to the server");
            throw new RuntimeException("Can't connect to the server");
        }
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
                else if (message.equals("exit_game")) {
                    closeResources();
                    System.exit(0);
                }
                else if (message.equals("calculate_final_score")) {
                    calculateMyScore();
                }
                else if (message.contains("show_endgame_screen")) {
                    showEndgameScreen(message);
                }
                else if (message.contains("CHAT:")) {
                    gamelog.recordchat(message);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }

    private void showEndgameScreen(String message) {
        GameBoardController.getInstance().showEndgameScreen(message);
    }

    private void calculateMyScore() {
        Player p =  GameBoardController.getInstance().getPlayer();
        String score = String.format("my_score:%s:%d", p.getName(), GameBoardController.getInstance().calculateFinalScore(p));
        try {
            toServer.writeUTF(score);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void closeResources() {
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
    public void reportPublishTheoryToServer(Alchemy alchemy, String ingredient, String playerName) {
        try {
            toServer.writeUTF("publish_theory:" + alchemy.toString() + ":" + ingredient + ":" + playerName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void reportEndorseTheoryToServer(String ingredient, String playerName, String ownerName) {
        try {
            toServer.writeUTF("endorse_theory:" + ingredient + ":" + playerName + ":" + ownerName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void reportDebunkTheoryToServer(Alchemy alchemy, String ingredient, String playerName, String ownerName) {
        try {
            toServer.writeUTF("debunk_theory:" + alchemy.toString() + ":" + ingredient + ":" + playerName + ":" + ownerName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addPublishTheory(String message) {
        String[] pack = message.split(":");
        Alchemy alchemy = Alchemy.findAlchemy(pack[1]);
        String ingredient = pack[2];
        String authorName = pack[3];
        Player p = GameBoardController.getInstance().getPlayer();
        if (!p.getName().equals(authorName)) {
            GameBoardController.getInstance().getTheoryController().publishTheory(alchemy, ingredient, authorName);
            gamelog.recordLog(p, "Academy", p.getName(), String.format("%s published the Theory with %s and %s!", authorName, ingredient, alchemy.toString()), 2);
        }
    }

    private void addEndorsedTheory(String message) {
        String[] pack = message.split(":");
        String ingredient = pack[1];
        String endorserName = pack[2];
        String ownerName = pack[3];
        Player p = GameBoardController.getInstance().getPlayer();
        if (!p.getName().equals(endorserName)) {
            GameBoardController.getInstance().getTheoryController().endorseTheory(ingredient, endorserName);
            if (p.getName().equals(ownerName)) {
                gamelog.recordLog(p, "Academy", p.getName(), String.format("%s paid 1 gold to endorse your theory!", endorserName), 2);
                p.getInventory().updateGold(1);
            }
            else {
                gamelog.recordLog(p, "Academy", p.getName(), String.format("%s endorsed the Theory of %s about %s!", endorserName, ownerName, ingredient), 2);
            }
        }
    }

    private void addDebunkTheory(String message) {
        String[] pack = message.split(":");
        Alchemy alchemy = Alchemy.findAlchemy(pack[1]);
        String ingredient = pack[2];
        String debunkerName = pack[3];
        String ownerName = pack[4];
        Player p = GameBoardController.getInstance().getPlayer();
        if (!p.getName().equals(debunkerName)) {
            GameBoardController.getInstance().getTheoryController().debunkTheory(alchemy, ingredient, debunkerName);
            if (p.getName().equals(ownerName)) {
                gamelog.recordLog(p, "Academy", p.getName(), String.format("%s debunked your Theory about %s!", debunkerName, ingredient), 0);
                p.updateReputation(-1);
            }
            else {
                gamelog.recordLog(p, "Academy", p.getName(), String.format("%s endorsed the Theory of %s about %s!", debunkerName, ownerName, ingredient), 2);
            }
        }
    }

    @Override
    public void reportExitGameToServer() {
        try {
            toServer.writeUTF("exit_game");
        } catch (IOException e) {
        }
    }

    @Override
    public void send(String msg) {
        try {
            toServer.writeUTF(msg);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String getMode() {
        return "Online";
    }
}
