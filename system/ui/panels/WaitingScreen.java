package system.ui.panels;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import system.domain.controllers.GameBoardController;
import system.domain.controllers.WelcomeController;
import system.ui.frame.Gameboard;

public class WaitingScreen extends JPanel{

    JButton backButton;
    private WelcomeController controller;

    public WaitingScreen(Gameboard gameBoard) {
        this.backButton = new JButton("Back");
        this.controller = GameBoardController.getInstance().getWelcomeController();
        
        JLabel message = new JLabel("Waiting for host to start the game");
        backButton.setBounds(100,100,100,100);
        add(message);

        //if waiting screen called by when we click join game in the welcome page,we will put a text saying waiting for
        // host to start the game
        this.add(backButton);

    }
}
