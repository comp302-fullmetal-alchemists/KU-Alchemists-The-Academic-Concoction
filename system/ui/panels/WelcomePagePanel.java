package system.ui.panels;

import javax.swing.JButton;
<<<<<<< HEAD
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
=======
import javax.swing.JPanel;
import javax.swing.JTextArea;
>>>>>>> 821a5bc (writing backend code for online/offlinw option)

import system.ui.frame.Gameboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePagePanel extends JPanel {

<<<<<<< HEAD
    JButton startGameButton;
    JComboBox<Integer> numberOfPlayers;
    JButton hostGameButton;
    JButton joinGameButton;

    public WelcomePagePanel(Gameboard gameboard) {

        // Offline Section
        JLabel offlineLabel = new JLabel("Oflfline Game Options");
        add(offlineLabel);
        numberOfPlayers = new JComboBox<>(new Integer[]{1, 2, 3, 4});
        add(numberOfPlayers);
        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
=======
    JButton offlineButton;
    JTextArea onlineGameOptions;
    JButton hostGameButton;
    JButton joinGameButton;


    public WelcomePagePanel(Gameboard gameboard) {
        
        // I need two button offline and online
        //if offline is clicked, authPanel will show up

        offlineButton = new JButton("Offline");
        offlineButton.addActionListener(new ActionListener() {
>>>>>>> 821a5bc (writing backend code for online/offlinw option)
            @Override
            public void actionPerformed(ActionEvent e) {
                gameboard.showAuthenticationPanel();
            }
        });
<<<<<<< HEAD
        add(startGameButton);

        // Online Section
        JLabel onlineLabel = new JLabel("Online Game Options");
        add(onlineLabel);

=======
        add(offlineButton);

        onlineGameOptions = new JTextArea("Online Game Options");
        add(onlineGameOptions);
>>>>>>> 821a5bc (writing backend code for online/offlinw option)

        hostGameButton = new JButton("Host Game");
        hostGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameboard.showOnlineGamePanel();
            }
        });
        add(hostGameButton);

        joinGameButton = new JButton("Join Game");
        joinGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameboard.showOnlineGamePanel();
            }
        });
        add(joinGameButton);
<<<<<<< HEAD
=======

>>>>>>> 821a5bc (writing backend code for online/offlinw option)
    }
}
