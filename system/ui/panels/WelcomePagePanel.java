package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import system.ui.frame.Gameboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePagePanel extends JPanel {

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
            @Override
            public void actionPerformed(ActionEvent e) {
                gameboard.showAuthenticationPanel();
            }
        });
        add(startGameButton);

        // Online Section
        JLabel onlineLabel = new JLabel("Online Game Options");
        add(onlineLabel);


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
    }
}
