package system.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import system.domain.IngredientCard;
import system.domain.controllers.AuthenticationController;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.Player;
import system.domain.util.IngredientFactory;

public class OfflineClient implements IClientAdapter {

    private OfflineServer server;
    private List<Player> players;
    private int playerNum;
    private int currentPlayer;
    private IngredientFactory ingFactory;
    
    public OfflineClient(OfflineServer server) {
        this.server = server;
        this.players = new ArrayList<Player>();
        this.ingFactory = new IngredientFactory();
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
    public void validateUserChoices(String username) {
        for (Player p: players) {
            if (p.getName().equals(username)) {
                AuthenticationController.getInstance().invalidUsername();
                return;
            }
            
        }
        AuthenticationController.getInstance().validUsername();
    }

    // after check, register the players
    @Override
    public void registerPlayer(Player p) {
        players.add(p);
        if (players.size() == playerNum) {
            server.initializeGame();            
        }
        else {
            GameBoardController.getInstance().startAuthentication();
        }
    }

    @Override
    public void setAlchemyMap(List<Integer> alchemyIndex) {
        ingFactory.setAlchemyMap(alchemyIndex);
    }

    @Override
    public void initialize() {
        for (Player p: players) {
            p.getInventory().initializeIngredients(drawIngredient(), drawIngredient());
            GameBoardController.getInstance().getGameLog().GameLogControllerInitPlayer(p);
        }
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
    public IngredientCard drawIngredient(){
        int index = server.requestIngredient();
        if (index == -1) {
            throw new NullPointerException();
        }
        return ingFactory.createIngredient(index);
    }




}
