package system.domain;
import java.util.ArrayList;
import java.util.List;
public class GameBoard {

    //GameBoard	players: List<Players>	calculateFinalScore()
    //startGame()
    //changePlayer(player)

    private List<Player> players;

    public GameBoard() {
        this.players = new ArrayList<Player>();
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
