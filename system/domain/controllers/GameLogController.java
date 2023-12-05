package system.domain.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import system.domain.GameAction;
import system.domain.Player;
import system.ui.panels.PlayerDashboard;

public class GameLogController {

    //	showGameLog(player) prints the game log. To show, UI elements need to be implemented


    private Map<Player, List<GameAction>> gameActions = new HashMap<>();
    private  PlayerDashboard playerDashboardUI;

    public void setPlayerDashboard(PlayerDashboard playerDashboard){
        this.playerDashboardUI = playerDashboard;
    }
    
    public  GameLogController(Player player1, Player player2){
        //GameAction startAction1 = new GameAction("Game", player1.getName(), "GameLog Start", 0);
        //GameAction startAction2 = new GameAction("Game", player2.getName(), "GameLog Start", 0);

        gameActions.put(player1,  new ArrayList<>());
        gameActions.put(player2,  new ArrayList<>());
    }

    public void recordLog(Player player, GameAction gameAction) {
        gameActions.get(player).add(gameAction); //gets the players GameAction list, adds the game action to there.
        for (int i = 0; i<2; i++){
            if(player == GameBoardController.getPlayer(i)){
                playerDashboardUI.appendToGameLog(gameAction.toString());
            }
        }
        return;
    }

}