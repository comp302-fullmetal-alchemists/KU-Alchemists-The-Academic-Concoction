package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import system.domain.controllers.GameBoardController;
import system.network.OfflineServer;
import system.ui.frame.Gameboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePagePanel extends JPanel {

    JButton offlineButton;
    JTextArea onlineGameOptions;
    JButton hostGameButton;
    JButton joinGameButton;


    public WelcomePagePanel(Gameboard gameboard) {
        
        // I need two button offline and online
        //if offline is clicked, authPanel will show up

        offlineButton = new JButton("Offline");
        offlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OfflineServer server = new OfflineServer();
                server.startAuthentication();
                //GameBoardController.getInstance().setServer(server);
                //gameboard.showAuthenticationPanel();
            }
        });
        add(offlineButton);


        onlineGameOptions = new JTextArea("Online Game Options");
        add(onlineGameOptions);

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
