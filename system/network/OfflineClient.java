package system.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.Player;

public class OfflineClient implements IClientAdapter {

    private OfflineServer server;
    private List<Player> players;
    private int playerNum;
    private int currentPlayer;
    
    public OfflineClient(OfflineServer server) {
        this.server = server;
        this.players = new ArrayList<Player>();
    }

    @Override 
    public void connectToServer() {
        // in offline there is no need for this method
    }
    
    @Override
    public void startAuthentication() {
        GameBoardController.getInstance().startAuthentication();
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    // for checking in authentication
    @Override
    public boolean validateUserChoices(String username) {
        for (Player p: players) {
            if (p.getName().equals(username)) return false;
        }
        return true;
    }

    // after check, register the players
    @Override
    public void registerPlayer(Player p) {
        // get ingredients from server and add them to the player here
        p.getInventory().initializeIngredients(server.drawIngredient(), server.drawIngredient());
        players.add(p);
        if (players.size() == playerNum) {
            server.initializeGame();            
        }
        else {
            GameBoardController.getInstance().startAuthentication();
        }
    }

    @Override
    public void initialize() {
        GameBoardController.getInstance().initializeTheBoard();
        Collections.shuffle(players);
    }

    @Override
    public void authorize() {
        GameBoardController.getInstance().setPlayer(players.get(currentPlayer));
        players.get(currentPlayer).changeTurn();
        GameBoardController.getInstance().authorizePlayer();
    }

    @Override
    public void deauthorize() {
        players.get(currentPlayer).changeTurn();
        GameBoardController.getInstance().deauthorizePlayer();    
    }

    @Override
    public void endPlayerTurn() {
        server.changePlayer();
    }
    
    public void changePlayer() {
        currentPlayer += 1;
        if (currentPlayer == playerNum) {
            currentPlayer = 0;
            server.newRound();
        }
    }
    
    @Override   
    public boolean ingPileIsEmpty() {
        return server.ingPileIsEmpty();
    }
    @Override
    public IngredientCard drawIngredient() {
        return server.drawIngredient();
    }
    @Override
    public boolean artifactPileIsEmpty() {
        return server.artifactPileIsEmpty();
    }
    @Override
    public ArtifactCard drawArtifact() {
        return server.drawArtifact();
    }



}
