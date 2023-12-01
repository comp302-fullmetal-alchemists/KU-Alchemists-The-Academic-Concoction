package system.ui.panels;

import system.domain.controllers.AuthenticationController;
import system.domain.Player;
import system.ui.frame.Gameboard;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AuthenticationPanel extends JPanel{
    private Gameboard game;
    private JButton loginButton;
    private JTextField usernameField1;
    private JTextField usernameField2;
    private JTextField tokenField1;
    private JTextField tokenField2;
    private JLabel usernameLabel;
    private JLabel tokenLabel;
    private AuthenticationController authController;

    public AuthenticationPanel(Gameboard game){
        super();
        this.game = game;
        this.authController = new AuthenticationController();
        this.usernameLabel = new JLabel("Usernames");
        add(usernameLabel);
        this.usernameField1 = new JTextField(23);
        add(usernameField1);
        this.usernameField2 = new JTextField(23);
        add(usernameField2);
        this.tokenLabel = new JLabel("Tokens");
        add(tokenLabel);
        this.tokenField1 = new JTextField(10);
        add(tokenField1);
        this.tokenField2 = new JTextField(10);
        add(tokenField2);
        this.loginButton = new JButton("Login");
        add(loginButton);
        
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String username1 = usernameField1.getText();
                String username2 = usernameField2.getText();
                String token1 = new String(tokenField1.getText());
                String token2 = new String(tokenField2.getText());
                String response = authController.login(username1, token1, username2, token2);
                if (response == "Welcome to the KuAlchemists"){
                    //game.initializeTheBoard();
                    
                }
                else{
                    JOptionPane.showMessageDialog(AuthenticationPanel.this, response);
                }
                
            }
    });


}

}
