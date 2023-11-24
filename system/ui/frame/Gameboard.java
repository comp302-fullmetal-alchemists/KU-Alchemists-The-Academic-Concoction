package system.ui.frame;

import javax.swing.JFrame;

import java.awt.GridLayout;

public class Gameboard extends JFrame{

    PlayerContentPane playerPane;
    GameContentPane gamePane;
    
    public Gameboard() {
        super();
        setLayout(new GridLayout(1, 2));
        this.playerPane = new PlayerContentPane();
        this.gamePane = new GameContentPane();
        add(gamePane);
        add(playerPane);
        
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }

}
