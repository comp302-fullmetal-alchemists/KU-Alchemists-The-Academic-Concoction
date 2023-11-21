
import Domain.Player;
import UI.GUI.GameFrame;

public class App {
    public static void main(String[] args) throws Exception {
        
        Player p1 = new Player("özgür", "1", "2");
        Player p2 = new Player("barış", "3", "4");

        GameFrame game = new GameFrame();
        game.addPlayers(p1, p2);


    }
}
