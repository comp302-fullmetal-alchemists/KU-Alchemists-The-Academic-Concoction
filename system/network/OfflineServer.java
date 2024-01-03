package system.network;

import system.domain.controllers.GameBoardController;
import system.domain.controllers.Player;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class OfflineServer implements IServerAdapter {

    private GameBoardState gbState;
    private int playerNo;
    private int currPlayer = 0;
    private List<Player> players;

    public OfflineServer() {
        this.players = new ArrayList<Player>();
        this.gbState = new GameBoardState();
    }

    // host decides the number of players
    public void setNoPlayers(int playerNo) {
        this.playerNo = playerNo;
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
            ///start playing the game.
        }
    }

    public void initializeGame() {
        GameBoardController.getInstance().initializeTheBoard();
        Collections.shuffle(players);
        for (Player p: players) {
            p.getInventory().addIngredient(gbState.getIngredientPile().remove(0));
            p.getInventory().addIngredient(gbState.getIngredientPile().remove(0));
        }
        changePlayer();
    }

    public void changePlayer() {
        players.get(currPlayer).changeTurn();
        GameBoardController.getInstance().getMediator().connectPlayer(players.get(currPlayer));
    }


    
    
    // TODO: Add class members and methods here
    


    
    // TODO: Implement the functionality of the OfflineServer class
    
    // TODO: Add constructors, getters, and setters if needed
    
    // TODO: Add any additional methods required by the OfflineServer class
}
