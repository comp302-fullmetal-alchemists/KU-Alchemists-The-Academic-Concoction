package system.network;

import system.domain.controllers.GameBoardController;
import system.domain.controllers.Player;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class OfflineServer implements IServerAdapter {

    private GameBoardState gbState;
    private int playerNo = 3;
    private int currPlayer = 0;
    private int rounds = 0;
    private List<Player> players;

    public OfflineServer() {
        this.players = new ArrayList<Player>();
        this.gbState = new GameBoardState();
    }

    // host decides the number of players
    public void setNoPlayers(int playerNo) {
        this.playerNo = playerNo;
    }

    public void startAuthentication() {
        GameBoardController.getInstance().setServer(this);
        GameBoardController.getInstance().startAuthentication();
    }

    // for checking in authentication
    public boolean validateUserChoices(String username) {
        for (Player p: players) {
            if (p.getName().equals(username)) return false;
        }
        return true;
    }

    // after check, register the players
    public void registerPlayer(Player p) {
        players.add(p);
        if (players.size() == playerNo) {
            initializeGame();            
        }
        else {
            GameBoardController.getInstance().startAuthentication();
        }
    }

    public void initializeGame() {
        Collections.shuffle(players);
        for (Player p: players) {
            p.getInventory().initializeIngredients(gbState.getIngredientPile().remove(0), gbState.getIngredientPile().remove(0));
        }

        GameBoardController.getInstance().initializeTheBoard();
        authorizePlayer();
    }

    public void changePlayer() {
        //first remove the old player
        deauthorizePlayer();
        //then find new player, change round number if needed
        setNextPlayer();
        //finally, give permission to new player
        authorizePlayer();
    }


    public void deauthorizePlayer() {
        //remove players connection to mediator.
        players.get(currPlayer).changeTurn();
        GameBoardController.getInstance().deauthorizePlayer();        
    }

    public void authorizePlayer() {
        GameBoardController.getInstance().setPlayer(players.get(currPlayer));
        players.get(currPlayer).changeTurn();
        GameBoardController.getInstance().authorizePlayer();
    }

    public void setNextPlayer() {
        currPlayer += 1;
        if (currPlayer == playerNo) {
            rounds += 1;
            currPlayer = 0;
            // if round is 3 finish the game.
        }
    }

}
