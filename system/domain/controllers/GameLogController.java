package system.domain.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import system.domain.GameAction;

public class GameLogController {

    private Map<Player, List<GameAction>> gameActions = new HashMap<>(); //there is only one gamelog per game, so it stores the players game actions in a Map.

    public  GameLogController(){
    } //creation without players


    public void GameLogControllerInit(Player player){
        gameActions.put(player,  new ArrayList<>());
    }//initalizing the gameActions map with players inputted.

    public void recordLog(Player player, GameAction gameAction) {
        gameActions.get(player).add(gameAction); //gets the players GameAction list, adds the game action to there.
        player.appendToGameLog(gameAction.toString()); //adds the gameLog to the player dashboard so it shows in UI.
        return;
    }

    public void recordLogSilent(Player player, GameAction gameAction) {
        gameActions.get(player).add(gameAction); //gets the players GameAction list, adds the game action to there.
        return;
    }


}