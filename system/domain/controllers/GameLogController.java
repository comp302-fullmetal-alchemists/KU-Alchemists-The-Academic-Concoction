package system.domain.controllers;

import java.util.List;
import java.util.Map;

import system.domain.GameAction;
import system.domain.Player;

public class GameLogController {

    //	showGameLog(player) prints the game log. To show, UI elements need to be implemented


    private Map<Player, List<GameAction>> gameActions;
    
    public GameLogController GameLogController(Player player1, Player player2){
        gameAction startAction1 = new gameAction("Game", player1.getName(), "GameLog Start", 0);
        gameAction startAction2 = new gameAction("Game", player2.getName(), "GameLog Start", 0);

        gameActions.put(player1, startAction1);
        gameActions.put(player1, startAction2);

    }

    public void showGameLog(Player player) {
        List<E> tail = gameActions.get(player).subList(Math.max(l.size() - 3, 0), l.size()); //gets the players list and then takes the last 3 elements as tail.
        
        for (i = 0; i < tail.size(); i++) 
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
