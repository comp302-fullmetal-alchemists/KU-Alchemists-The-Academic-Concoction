package system.ui.frame;

import system.domain.Player;
import system.ui.interfaces.PlayerMediator;

import javax.swing.JFrame;

import java.awt.GridLayout;

public class Gameboard extends JFrame{

    AuthenticationPanel authPanel;
    PlayerContentPane playerPane;
    GameContentPane gamePane;
    PlayerMediator playerMediator;
    
    public Gameboard() {
        super();
        this.authPanel = new AuthenticationPanel(this);
        add(authPanel);

        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }

    public void initializeTheBoard(Player p1, Player p2) {
        remove(authPanel);
        setLayout(new GridLayout(1, 2));
        this.playerPane = new PlayerContentPane(p1, p2);
        this.gamePane = new GameContentPane();
        add(gamePane);
        add(playerPane);
        revalidate();
}

}