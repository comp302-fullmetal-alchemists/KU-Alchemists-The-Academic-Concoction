package system.domain.controllers;
import java.util.ArrayList;
import java.util.List;

import system.domain.Player;
public class GameBoardController {

    //GameBoard	players: List<Players>	calculateFinalScore()
    //startGame()
    //changePlayer(player)

    private List<Player> players;

    public GameBoardController() {
        this.players = new ArrayList<Player>();
    }

    public void initializeTheBoard(Player player1, Player player2) {
        players.add(player1);
        players.add(player2);
    }
    
    public Player getPlayer(int index) {
        return players.get(index);
    }

    public double calculateFinalScore() {
    //to do: get rep, gold, artifact from player's inventory
    	int finalScore = 0;
        return finalScore;
    }

    public void startGame() {
        return;
    }

}
