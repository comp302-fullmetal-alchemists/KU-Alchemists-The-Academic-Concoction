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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePagePanel extends JPanel {

    JButton startGameButton;
    JComboBox<Integer> numberOfPlayers;
    JButton hostGameButton;
    JButton joinGameButton;
    private WelcomeController controller;


    public WelcomePagePanel(Gameboard gameboard) {
        this.controller = GameBoardController.getInstance().getWelcomeController();
        // Offline Section
        JLabel offlineLabel = new JLabel("Oflfline Game Options");
        add(offlineLabel);
        numberOfPlayers = new JComboBox<>(new Integer[]{2, 3, 4});
        add(numberOfPlayers);
        startGameButton = new JButton("Start Game");
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
        JLabel onlineLabel = new JLabel("Online Game Options");
        add(onlineLabel);

        JTextField port = new JTextField(10);
        //port.setBounds(157, 229, 166, 53);
        add(port);
        
        hostGameButton = new JButton("Host Game");
        hostGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameboard.showWaitingScreen();
                Integer portno= Integer.parseInt(port.getText());
                controller.onlineHostingMode(portno);
                
            }
        });
        add(hostGameButton);

        joinGameButton = new JButton("Join Game");
        joinGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameboard.showWaitingScreen();
                Integer portno= Integer.parseInt(port.getText());
                controller.onlineJoiningMode(portno);
            }
        });
        add(joinGameButton);
    }
}
