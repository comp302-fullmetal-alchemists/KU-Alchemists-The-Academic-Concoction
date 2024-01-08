package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import system.ui.frame.Gameboard;
import system.network.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

        JTextField port = new JTextField();
        port.setBounds(157, 229, 166, 53);
        add(port);
        
        hostGameButton = new JButton("Host Game");
        hostGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameboard.showWaitingScreen();
                try {
                    
                    Integer portno= Integer.parseInt(port.getText());
                    Server server = new Server(portno);
                    server.startServer();

                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
        });
        add(hostGameButton);

        joinGameButton = new JButton("Join Game");
        joinGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Integer portno= Integer.parseInt(port.getText());
                    Client client = new Client(portno);
                    client.run();
                    
                    
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        add(joinGameButton);
    }
}
