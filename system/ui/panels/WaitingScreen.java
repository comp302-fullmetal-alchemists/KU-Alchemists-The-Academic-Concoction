package system.ui.panels;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.WelcomeController;
import system.network.IClientAdapter;
import system.ui.frame.Gameboard;
import java.awt.Color;
import java.awt.Font;

public class WaitingScreen extends JPanel{

    JButton backButton;
    private WelcomeController controller;

    public WaitingScreen(Gameboard gameBoard) {
    	setBackground(new Color(58, 77, 108));
        
        this.controller = GameBoardController.getInstance().getWelcomeController();
        this.backButton = new JButton("Back");
        this.backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                gameBoard.showWelcomePagePanel();
        	}
        });
        JLabel message = new JLabel("Waiting for host to start the game...");
        message.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
        message.setForeground(new Color(255, 255, 255));
        message.setBounds(326, 230, 530, 74);
        backButton.setBounds(467,32,129,59);
        setLayout(null);
        add(message);

        //if waiting screen called by when we click join game in the welcome page,we will put a text saying waiting for
        // host to start the game
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameBoard.showWelcomePagePanel();
                GameBoardController.getInstance().getClientAdapter().closeResources();
            }
        });
        this.add(backButton);
        
        JLabel lblNewLabel = new JLabel("Please wait while others join.");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(353, 160, 503, 53);
        add(lblNewLabel);

    }
}
