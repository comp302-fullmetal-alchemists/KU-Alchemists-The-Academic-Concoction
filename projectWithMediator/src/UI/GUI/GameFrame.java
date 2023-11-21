package UI.GUI;


import Domain.Player;
import Domain.Controllers.AppController;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;


public class GameFrame extends JFrame {
    private GameBoard gameboard;

    public GameFrame() {
        super();
        this.gameboard = new GameBoard();
        setLayout(new BorderLayout());
        add(gameboard);
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void addPlayers(Player p1, Player p2) {
        gameboard.addPlayersAndInitialize(p1, p2);
    }

    
}
