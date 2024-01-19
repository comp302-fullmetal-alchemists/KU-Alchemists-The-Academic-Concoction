package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import system.domain.controllers.GameBoardController;
import system.domain.controllers.WelcomeController;
import system.ui.frame.Gameboard;

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
    	setSize(1200, 800);
    	setBackground(new Color(58, 77, 108)); //isimleri değiştirdim tek
        this.controller = GameBoardController.getInstance().getWelcomeController();
        setLayout(null);
        // Offline Section
        JLabel offlineLabel = new JLabel("OFFLINE GAME MODE");
        offlineLabel.setBounds(64, 181, 358, 39);
        offlineLabel.setForeground(new Color(255, 255, 255));
        offlineLabel.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 30));
        add(offlineLabel);
        numberOfPlayers = new JComboBox<>(new Integer[]{2, 3, 4});
        numberOfPlayers.setBounds(588, 247, 85, 63);
        add(numberOfPlayers);
        startGameButton = new JButton("Start Game");
        startGameButton.setBounds(739, 253, 165, 41);
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
        onlineLabel.setBounds(57, 330, 404, 42);
        onlineLabel.setForeground(new Color(255, 255, 255));
        onlineLabel.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 30));
        add(onlineLabel);

        JTextField port = new JTextField(10);
        port.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        port.setBounds(211, 472, 250, 53);
        add(port);

        JTextField ip = new JTextField(10);
        ip.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        ip.setBounds(211, 559, 250, 53);
        add(ip);

        
        hostGameButton = new JButton("Host Game");
        hostGameButton.setBounds(587, 480, 158, 41);
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
        joinGameButton.setBounds(587, 559, 163, 43);
        joinGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer portno= Integer.parseInt(port.getText());
                    controller.onlineJoiningMode(ip.getText(),portno);
                    gameboard.showWaitingScreen();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Server did not respond. Please try again.");
            }
        }
        });
        add(joinGameButton);
        
        JLabel lblNewLabel = new JLabel("WELCOME TO KUALCHEMISTS!");
        lblNewLabel.setBounds(257, 17, 821, 77);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 44));
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Please select offline or online mode to start the game!\n");
        lblNewLabel_1.setBounds(290, 106, 637, 27);
        lblNewLabel_1.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 23));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Please choose the number of players:");
        lblNewLabel_2.setBounds(98, 251, 495, 39);
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 23));
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("To join a game, please enter the IP address and port number provided. \n");
        lblNewLabel_3.setBounds(122, 413, 795, 27);
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 20));
        add(lblNewLabel_3);
        
		
		JLabel lblNewLabel_4 = new JLabel("A game by FullMetal Alchemists");
		lblNewLabel_4.setBounds(767, 663, 456, 53);
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Luminari", Font.BOLD | Font.ITALIC, 24));
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("If you would like to host a game, please enter your port.");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 21));
		lblNewLabel_5.setBounds(122, 375, 623, 39);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("PORT:");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_6.setBounds(142, 482, 85, 34);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("IP:");
		lblNewLabel_6_1.setForeground(Color.WHITE);
		lblNewLabel_6_1.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_6_1.setBounds(157, 569, 85, 34);
		add(lblNewLabel_6_1);
    }

}
