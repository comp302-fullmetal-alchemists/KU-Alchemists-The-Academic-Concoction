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
import javax.swing.SwingConstants;

import system.domain.controllers.GameBoardController;
import system.domain.controllers.WelcomeController;
import system.domain.interfaces.Observer;
import system.ui.frame.Gameboard;
import java.awt.Color;
import java.awt.Font;

public class HostingScreen extends JPanel {
    JButton startGameButton;
    JButton backButton;
    private WelcomeController controller;
    JLabel playerCountLbl;
    private Integer playerCount;
    

    public HostingScreen(Gameboard gameBoard) {
    	setBackground(new Color(58, 77, 108));
        this.startGameButton = new JButton("Start Game");
        this.backButton = new JButton("Back");
        backButton.setBackground(new Color(204, 51, 204));
        this.controller = GameBoardController.getInstance().getWelcomeController();
        setLayout(null);
        this.playerCount =1;

        //if waiting screen called by when we click host game in the welcome page,we will put a start gamebutton and
        // also a text saying waiting for other player to join
        //I want to write a text including my ip address and port number here
        JTextArea  waitingText = new JTextArea("Waiting for other players to join");
        waitingText.setForeground(Color.WHITE);
        waitingText.setBackground(new Color(58, 77, 108));
        waitingText.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 18));
        waitingText.setBounds(428,143,370,59);
        this.add(waitingText);

        JLabel IpAddress = new JLabel("Your ip address is: " + controller.learnIP());
        IpAddress.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 19));
        IpAddress.setForeground(Color.WHITE);
        IpAddress.setVerticalAlignment(SwingConstants.BOTTOM);
        IpAddress.setBounds(438, 198, 319, 59);
        add(IpAddress);
        
        startGameButton.setBounds(466,562,215,49);
        backButton.setBounds(494,31,132,59);

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

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameBoard.showWelcomePagePanel();
                controller.getServer().stopServer();
            }
        });
        this.add(backButton);
        
        JLabel lblNewLabel_1 = new JLabel("Current number of players:");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 19));
        lblNewLabel_1.setBounds(444, 470, 370, 38);
        add(lblNewLabel_1);
        
        playerCountLbl = new JLabel("A player has joined!");
        playerCountLbl.setForeground(Color.WHITE);
        playerCountLbl.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 20));
        playerCountLbl.setBounds(518, 520, 151, 25);
        
    }

    /* 

    @Override
    public void update(String msg) {
        // TODO Auto-generated method stub
        
        if(msg.equals("PLAYER_JOINED")){
            playerCountLbl.setVisible(true);
        }
    }
*/

}
