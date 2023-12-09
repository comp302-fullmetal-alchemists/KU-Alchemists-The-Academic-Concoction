
package system.ui.panels;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import system.domain.controllers.AuthenticationController;
import system.domain.interfaces.Observer;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.DefaultComboBoxModel;


import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuthenticationPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private JTextField username1;
	private JTextField username2;
    private JComboBox token1;
    private JComboBox token2;
	private AuthenticationController authController;

	/**
	 * Create the panel.
	 */
	public AuthenticationPanel() {
	
		setBackground(new Color(58, 77, 108));
		setLayout(null);
		setSize(1200, 800);
		
		this.authController = new AuthenticationController();
        authController.setObserver(this);
		
		JTextArea txtWelcome = new JTextArea();
		txtWelcome.setEditable(false);
		txtWelcome.setForeground(new Color(255, 255, 255));
		txtWelcome.setFont(new Font("Monotype Corsiva", Font.BOLD, 60));
		txtWelcome.setBackground(new Color(58, 77, 108));
		txtWelcome.setText("WELCOME TO KUALCHEMISTS!");
		txtWelcome.setBounds(304, 37, 920, 82);
		txtWelcome.setEditable(false);

		add(txtWelcome);
		
		JTextArea txtInformMessage = new JTextArea();
		txtInformMessage.setText("Please enter usernames and choose tokens to start the game!");
		txtInformMessage.setForeground(Color.WHITE);
		txtInformMessage.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 25));
		txtInformMessage.setBackground(new Color(58, 77, 108));
		txtInformMessage.setBounds(356, 129, 975, 46);
		txtInformMessage.setEditable(false);
		add(txtInformMessage);

    JTextArea txtrPlayer1Username = new JTextArea();
		txtrPlayer1Username.setText("Player 1 username:\n");
		txtrPlayer1Username.setForeground(Color.WHITE);
		txtrPlayer1Username.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 20));
		txtrPlayer1Username.setBackground(new Color(58, 77, 108));
		/*txtrPlayer1Username.setBounds(270, 212, 212, 32);*/
		txtrPlayer1Username.setBounds(267, 214, 166, 32);
    txtrPlayer1Username.setEditable(false);
		add(txtrPlayer1Username);

		JTextArea txtrPlayer2Username = new JTextArea();
		txtrPlayer2Username.setText("Player 2 username:\n");
		txtrPlayer2Username.setForeground(Color.WHITE);
		txtrPlayer2Username.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 20));
		txtrPlayer2Username.setBackground(new Color(58, 77, 108));
		/*txtrPlayer2Username.setBounds(905, 212, 141, 32);*/
		txtrPlayer2Username.setBounds(267, 337, 141, 32);
    txtrPlayer2Username.setEditable(false);
		add(txtrPlayer2Username);

        JTextArea txtrPlayer1TokenChoose = new JTextArea();
		txtrPlayer1TokenChoose.setText("Player 1 choose a token:\n");
		txtrPlayer1TokenChoose.setForeground(Color.WHITE);
		txtrPlayer1TokenChoose.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 20));
		txtrPlayer1TokenChoose.setBackground(new Color(58, 77, 108));
		/*txtrPlayer1TokenChoose.setBounds(258, 397, 332, 32);*/
		txtrPlayer1TokenChoose.setBounds(815, 214, 166, 32);
    txtrPlayer1TokenChoose.setEditable(false);
		add(txtrPlayer1TokenChoose);

		JTextArea txtrPlayer2TokenChoose = new JTextArea();
		txtrPlayer2TokenChoose.setText("Player 2 choose a token:\n");
		txtrPlayer2TokenChoose.setForeground(Color.WHITE);
		txtrPlayer2TokenChoose.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 20));
		txtrPlayer2TokenChoose.setBackground(new Color(58, 77, 108));
		/*txtrPlayer2TokenChoose.setBounds(880, 397, 296, 32);*/
    txtrPlayer2TokenChoose.setBounds(815, 337, 166, 32);
    txtrPlayer2TokenChoose.setEditable(false);
		add(txtrPlayer2TokenChoose);

    username1 = new JTextField();
    username1.setFont(new Font("Tahoma", Font.PLAIN, 21));
    username1.setBounds(286, 242, 166, 53);
    /*username1.setBounds(270, 276, 166, 53);*/
    add(username1);
    username1.setColumns(10);

    username2 = new JTextField();
    username2.setFont(new Font("Tahoma", Font.PLAIN, 21));
    username2.setBounds(286, 368, 166, 53);
    /*username2.setBounds(905, 276, 166, 53);*/
    username2.setColumns(10);
    add(username2);

    token1 = new JComboBox();
    token1.setBounds(246, 439, 348, 268);
    token1.setBackground(new Color(58, 77, 108));
    token1.setModel(loadImages());
    token1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            token1.getSelectedItem();
        }
    });
    add(token1);
    token1.setVisible(true);

    token2 = new JComboBox();
    token2.setBounds(873, 439, 351, 268);
    token2.setBackground(new Color(58, 77, 108));
    token2.setModel(loadImages());
    token2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            token2.getSelectedItem();
        }
    });
    add(token2);
    token2.setVisible(true);

    JButton loginButton = new JButton("LOGIN");
    loginButton.setFont(new Font("Tahoma", Font.PLAIN, 25));

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	                String player1username = username1.getText();
	                String player2username = username2.getText();
	                Icon player1token = (Icon) token1.getSelectedItem();
	                Icon player2token = (Icon) token2.getSelectedItem();
	                authController.login(player1username, player1token, player2username, player2token);
	            
			}
		});
		/*loginButton.setBounds(1105, 747, 166, 46);*/
		loginButton.setBounds(889, 527, 166, 46);
		add(loginButton);
		
		JTextArea txtrAGameBy = new JTextArea();
		txtrAGameBy.setText("A game by FullMetal Alchemists\n");
		txtrAGameBy.setForeground(Color.WHITE);
		txtrAGameBy.setFont(new Font("Luminari", Font.ITALIC, 25));
		txtrAGameBy.setBackground(new Color(58, 77, 108));
		/*txtrAGameBy.setBounds(1004, 803, 468, 32);*/
		txtrAGameBy.setBounds(938, 710, 241, 32);
    txtrAGameBy.setEditable(false);
		add(txtrAGameBy);
	}

    //load images to the combobox   
    private DefaultComboBoxModel<Icon> loadImages(){
        DefaultComboBoxModel<Icon> images = new DefaultComboBoxModel<Icon>();
        images.addElement(new ImageIcon(getClass().getResource("/resources/token1.png")));
        images.addElement(new ImageIcon(getClass().getResource("/resources/token2.png")));
        images.addElement(new ImageIcon(getClass().getResource("/resources/token3.png")));
        images.addElement(new ImageIcon(getClass().getResource("/resources/token4.png")));

        return images;
    }
    
    

	@Override
	public void update(String msg) {
		 if (!msg.equals("VALID")) {
	            JOptionPane.showMessageDialog(this, msg);
	        }
	}
}
