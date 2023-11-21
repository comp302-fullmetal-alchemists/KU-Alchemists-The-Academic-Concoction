package UI.GUI;

import javax.swing.JPanel;

import Domain.Controllers.AppController;
import Domain.Player;

import java.awt.GridLayout;

public class GameBoard extends JPanel {
    
    private DashboardPanel players;
    private GamePanel gamePanel;

    private Mediator mediator = new Mediator();
    private AppController app;

    public GameBoard() {
        super();
        setLayout(new GridLayout(1, 2));
        gamePanel = new GamePanel(mediator);
        add(gamePanel);
        app = new AppController();
        validate();
    }

    public void addPlayersAndInitialize(Player p1, Player p2) {
        this.players = new DashboardPanel(p1, p2, mediator);
        app.addPlayers(p1, p2);
        add(players);
        players.changeToCard((p1.isInTurn()? p1.getName(): p2.getName()));
        validate();
    }

    public void changeTurns() {
        app.changeTurns();
        players.changeTurns();
    }


}
