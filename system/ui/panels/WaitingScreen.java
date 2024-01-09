package system.ui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import system.ui.frame.Gameboard;

public class WaitingScreen extends JPanel{

    JButton startGameButton;
    JButton backButton;

    public WaitingScreen(Gameboard gameBoard) {
        this.startGameButton = new JButton("Start Game");
        this.backButton = new JButton("Back");

        //if waiting screen called by when we click host game in the welcome page,we will put a start gamebutton and
        // also a text saying waiting for other player to join

        startGameButton.setBounds(100,100,100,100);
        backButton.setBounds(100,100,100,100);

        //if waiting screen called by when we click join game in the welcome page,we will put a text saying waiting for
        // host to start the game

        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				gameBoard.showAuthenticationPanel();
			}
			
		});

        this.add(startGameButton);
        this.add(backButton);

    }


}
