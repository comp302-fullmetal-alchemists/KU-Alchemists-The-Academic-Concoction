/*package system.ui.panels;

import system.domain.controllers.AuthenticationController;
import system.domain.Player;
import system.domain.interfaces.Observer;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AuthenticationPanel extends JPanel implements Observer{
    private JButton loginButton;
    private JTextField usernameField1;
    private JTextField usernameField2;
    private JTextField tokenField1;
    private JTextField tokenField2;
    private JLabel usernameLabel;
    private JLabel tokenLabel;
    private AuthenticationController authController;

    public AuthenticationPanel(){
        super();
        this.authController = new AuthenticationController();
        authController.setObserver(this);
        this.usernameLabel = new JLabel("Usernames");
        add(usernameLabel);
        this.usernameField1 = new JTextField(23);
        usernameField1.setText("player1");
        add(usernameField1);
        this.usernameField2 = new JTextField(23);
        usernameField2.setText("player2");
        add(usernameField2);
        this.tokenLabel = new JLabel("Tokens");
        add(tokenLabel);
        this.tokenField1 = new JTextField(10);
        add(tokenField1);
        tokenField1.setText("token1");
        this.tokenField2 = new JTextField(10);
        add(tokenField2);
        tokenField2.setText("token2");
        this.loginButton = new JButton("Login");
        add(loginButton);
        
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String username1 = usernameField1.getText();
                String username2 = usernameField2.getText();
                String token1 = new String(tokenField1.getText());
                String token2 = new String(tokenField2.getText());
                authController.login(username1, token1, username2, token2);
            }
        });
    }

    @Override
    public void update(String msg) {
        if (!msg.equals("VALID")) {
            JOptionPane.showMessageDialog(this, msg);
        }
    }


}*/
package system.ui.panels;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import system.domain.controllers.AuthenticationController;
import system.domain.interfaces.Observer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuthenticationPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private JTextField username1;
	private JTextField username2;
	private JTextField token1;
	private JTextField token2;
	private AuthenticationController authController;

	/**
	 * Create the panel.
	 */
	public AuthenticationPanel() {
	
		setBackground(new Color(51, 102, 153));
		setLayout(null);
		
		this.authController = new AuthenticationController();
        authController.setObserver(this);
		
		JTextArea txtWelcome = new JTextArea();
		txtWelcome.setForeground(new Color(255, 255, 255));
		txtWelcome.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 30));
		txtWelcome.setBackground(new Color(51, 102, 153));
		txtWelcome.setText("WELCOME TO KUALCHEMISTS!");
		txtWelcome.setBounds(190, 29, 697, 87);
        txtWelcome.setEditable(false);
		add(txtWelcome);
		
		JTextArea txtInformMessage = new JTextArea();
		txtInformMessage.setText("Please enter usernames and choose tokens to start the game!");
		txtInformMessage.setForeground(Color.WHITE);
		txtInformMessage.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 23));
		txtInformMessage.setBackground(new Color(51, 102, 153));
		txtInformMessage.setBounds(90, 140, 697, 46);
        txtInformMessage.setEditable(false);
		add(txtInformMessage);
		
		JTextArea txtrPlayer1Username = new JTextArea();
		txtrPlayer1Username.setText("Player 1 username:\n");
		txtrPlayer1Username.setForeground(Color.WHITE);
		txtrPlayer1Username.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 14));
		txtrPlayer1Username.setBackground(new Color(51, 102, 153));
		txtrPlayer1Username.setBounds(36, 214, 166, 32);
        txtrPlayer1Username.setEditable(false);
		add(txtrPlayer1Username);
		
		JTextArea txtrPlayer2Username = new JTextArea();
		txtrPlayer2Username.setText("Player 2 username:\n");
		txtrPlayer2Username.setForeground(Color.WHITE);
		txtrPlayer2Username.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 14));
		txtrPlayer2Username.setBackground(new Color(51, 102, 153));
		txtrPlayer2Username.setBounds(36, 324, 141, 32);
        txtrPlayer2Username.setEditable(false);
		add(txtrPlayer2Username);
		
		JTextArea txtrPlayer1TokenChoose = new JTextArea();
		txtrPlayer1TokenChoose.setText("Player 1 choose a token:\n");
		txtrPlayer1TokenChoose.setForeground(Color.WHITE);
		txtrPlayer1TokenChoose.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 14));
		txtrPlayer1TokenChoose.setBackground(new Color(51, 102, 153));
		txtrPlayer1TokenChoose.setBounds(442, 214, 166, 32);
        txtrPlayer1TokenChoose.setEditable(false);
		add(txtrPlayer1TokenChoose);
		
		JTextArea txtrPlayer2TokenChoose = new JTextArea();
		txtrPlayer2TokenChoose.setText("Player 2 choose a token:\n");
		txtrPlayer2TokenChoose.setForeground(Color.WHITE);
		txtrPlayer2TokenChoose.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 14));
		txtrPlayer2TokenChoose.setBackground(new Color(51, 102, 153));
		txtrPlayer2TokenChoose.setBounds(442, 324, 166, 32);
        txtrPlayer2TokenChoose.setEditable(false);
		add(txtrPlayer2TokenChoose);
		
		username1 = new JTextField();
		username1.setBounds(72, 242, 166, 53);
        username1.setText("player1");
        add(username1);
		username1.setColumns(10);
		
		username2 = new JTextField();
		username2.setColumns(10);
		username2.setBounds(72, 368, 166, 53);
        username2.setText("player2");
		add(username2);
		
		token1 = new JTextField();
		token1.setColumns(10);
		token1.setBounds(452, 242, 166, 53);
        token1.setText("token1");
		add(token1);
		
		token2 = new JTextField();
		token2.setColumns(10);
		token2.setBounds(452, 368, 166, 53);
        token2.setText("token2");
		add(token2);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	                String player1username = username1.getText();
	                String player2username = username2.getText();
	                String player1token = new String(token1.getText());
	                String player2token = new String(token2.getText());
	                authController.login(player1username, player1token, player2username, player2token);
	            
			}
		});
		loginButton.setBounds(614, 470, 166, 46);
		add(loginButton);
		
		JTextArea txtrAGameBy = new JTextArea();
		txtrAGameBy.setText("A game by FullMetal Alchemists\n");
		txtrAGameBy.setForeground(Color.WHITE);
		txtrAGameBy.setFont(new Font("Luminari", Font.ITALIC, 14));
		txtrAGameBy.setBackground(new Color(51, 102, 153));
		txtrAGameBy.setBounds(600, 532, 241, 32);
        txtrAGameBy.setEditable(false);
		add(txtrAGameBy);

	}

	@Override
	public void update(String msg) {
		// TODO Auto-generated method stub
		 if (!msg.equals("VALID")) {
	            JOptionPane.showMessageDialog(this, msg);
	        }
	}
}
