package system.ui.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import system.domain.controllers.GameBoardController;
import system.domain.controllers.WelcomeController;
import system.ui.frame.Gameboard;

public class HostingScreen extends JPanel{
    JButton startGameButton;
    JButton backButton;
    private WelcomeController controller;

    public HostingScreen(Gameboard gameBoard) {
        this.startGameButton = new JButton("Start Game");
        this.backButton = new JButton("Back");
        this.controller = GameBoardController.getInstance().getWelcomeController();

        //if waiting screen called by when we click host game in the welcome page,we will put a start gamebutton and
        // also a text saying waiting for other player to join
        //I want to write a text including my ip address and port number here
        JTextArea  waitingText = new JTextArea("Waiting for other player to join");
        waitingText.setBounds(100,100,100,100);
        this.add(waitingText);

        JLabel ipAddress = new JLabel("Your ip address is: " + controller.learnIP());
        add(ipAddress);
        
        startGameButton.setBounds(100,100,100,100);
        backButton.setBounds(100,100,100,100);

        //if waiting screen called by when we click join game in the welcome page,we will put a text saying waiting for
        // host to start the game

        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.authentication();
				//gameBoard.showAuthenticationPanel();
                //server ın bir şey demesi gerekiyor burada aslında
			}
			
		});

        this.add(startGameButton);
        this.add(backButton);

    }
}
