package system.ui.panels;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import system.ui.frame.Gameboard;

public class OnlineGamePanel extends JPanel{
    JButton backButton;
    JButton hostGameButton;
    JButton joinGameButton;
    JTextField ip;  
    JTextField port;


    public OnlineGamePanel(Gameboard gameBoard) {
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                gameBoard.showWelcomePagePanel();
                
            }
        });
        add(backButton);
        //give enough space for the ip address
        ip = new JTextField("IP Address");
        //give enough space for the port number
        port = new JTextField("Port");
        hostGameButton = new JButton("Host Game");

        add(ip);
        add(port);
        add(hostGameButton);        

        

    }
    
}
