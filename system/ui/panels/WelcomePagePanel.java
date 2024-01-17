package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import system.domain.controllers.GameBoardController;
import system.domain.controllers.WelcomeController;
import system.ui.frame.Gameboard;
import system.network.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class WelcomePagePanel extends JPanel { //class ismi değiştir
 
    JButton startGameButton;
    JComboBox<Integer> numberOfPlayers;
    JButton hostGameButton;
    JButton joinGameButton;
    private WelcomeController controller;


    public WelcomePagePanel(Gameboard gameboard) {
    	setBackground(new Color(58, 77, 108)); //isimleri değiştirdim tek
        this.controller = GameBoardController.getInstance().getWelcomeController();
        setLayout(null);
        // Offline Section
        JLabel offlineLabel = new JLabel("OFFLINE GAME MODE");
        offlineLabel.setForeground(new Color(255, 255, 255));
        offlineLabel.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 20));
        offlineLabel.setBounds(23, 173, 283, 39);
        add(offlineLabel);
        numberOfPlayers = new JComboBox<>(new Integer[]{2, 3, 4});
        numberOfPlayers.setBounds(307, 224, 64, 27);
        add(numberOfPlayers);
        startGameButton = new JButton("Start Game");
        startGameButton.setBounds(386, 223, 112, 29);
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.offlineHostingMode((Integer) numberOfPlayers.getSelectedItem());
                //GameBoardController.getInstance().setServer(server);
                //gameboard.showAuthenticationPanel();
            }
        });
        add(startGameButton);

        // Online Section
        JLabel onlineLabel = new JLabel("ONLINE GAME MODE\n");
        onlineLabel.setForeground(new Color(255, 255, 255));
        onlineLabel.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 20));
        onlineLabel.setBounds(23, 319, 256, 16);
        add(onlineLabel);

        JTextField port = new JTextField("Port: ",10);
        port.setBounds(105, 401, 130, 26);
        add(port);

        JTextField ip = new JTextField("IP: ",10);
        ip.setBounds(297, 401, 130, 26);
        add(ip);

        
        hostGameButton = new JButton("Host Game");
        hostGameButton.setBounds(508, 401, 113, 29);
        hostGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameboard.showHostingScreen();
                Integer portno= Integer.parseInt(port.getText());
                controller.onlineHostingMode(portno);
                
            }
        });
        add(hostGameButton);


        joinGameButton = new JButton("Join Game");
        joinGameButton.setBounds(508, 439, 107, 29);
        joinGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameboard.showWaitingScreen();
                Integer portno= Integer.parseInt(port.getText());
                controller.onlineJoiningMode(ip.getText(),portno);
            }
        });
        add(joinGameButton);
        
        JLabel lblNewLabel = new JLabel("WELCOME TO KUALCHEMISTS!");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 35));
        lblNewLabel.setBounds(84, 6, 568, 77);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Please select offline or online mode to start the game!\n");
        lblNewLabel_1.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 15));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(141, 114, 582, 16);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Please choose the number of players:");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 14));
        lblNewLabel_2.setBounds(33, 229, 273, 16);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Please enter your port and IP");
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 14));
        lblNewLabel_3.setBounds(33, 370, 224, 16);
        add(lblNewLabel_3);
        
		
		JLabel lblNewLabel_4 = new JLabel("A game by FullMetal Alchemists");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Luminari", Font.BOLD | Font.ITALIC, 19));
		lblNewLabel_4.setBounds(400, 523, 306, 27);
		add(lblNewLabel_4);
    }
}
