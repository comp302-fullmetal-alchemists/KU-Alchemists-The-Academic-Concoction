package system.domain.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import system.domain.GameAction;
import system.domain.Player;

public class GameLogController {

    //	showGameLog(player) prints the game log. To show, UI elements need to be implemented


    private Map<Player, List<GameAction>> gameActions;
    
    public  GameLogController(Player player1, Player player2){
        gameActions = new HashMap<Player, List<GameAction>>(); //initalize the gameactions

        GameAction startAction1 = new GameAction("Game", player1.getName(), "GameLog Start", 0);
        GameAction startAction2 = new GameAction("Game", player2.getName(), "GameLog Start", 0);

        gameActions.put(player1, List.of(startAction1));
        gameActions.put(player2, List.of(startAction2));
    }

    public void showGameLog(Player player) {
        List<GameAction> tail = gameActions.get(player).subList(Math.max(gameActions.get(player).size() - 3, 0), gameActions.get(player).size()); //gets the players list and then takes the last 3 elements as tail.
        
        for (int i = 0; i < tail.size(); i++) 
        {
            tail.get(i).toString(); //iterates over the elements and prints by using string method.
        }
        return;
    }

    //Used in a while loop until recorded click(gameLog)
    public void recordLog(Player player, GameAction gameAction) {
        gameActions.get(player).add(gameAction); //gets the players GameAction list, adds the game action to there.
        return;
    }

}