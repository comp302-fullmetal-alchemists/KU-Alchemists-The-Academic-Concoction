package system.domain.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import system.domain.GameAction;
import system.domain.Player;
import java.util.ArrayList;
import java.util.List;

public class GameLogController {

    private Map<Player, List<GameAction>> gameActions = new HashMap<>();
    
    public  GameLogController(Player player1, Player player2){

        gameActions.put(player1,  new ArrayList<>());
        gameActions.put(player2,  new ArrayList<>());
    }

    public void recordLog(Player player, GameAction gameAction) {
        gameActions.get(player).add(gameAction); //gets the players GameAction list, adds the game action to there.
            player.appendToGameLog(gameAction.toString());
        return;
    }

}