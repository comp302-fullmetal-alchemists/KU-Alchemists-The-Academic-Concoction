package system.ui.panels;

import system.domain.interfaces.Observer;
import system.network.OnlineClient;
import system.ui.frame.Gameboard;
import system.domain.IngredientCard;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.Player;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerDashboard extends JPanel implements Observer {
    
    private Player player;
    private Inventory inventory;
    private JLabel playerLabel;
    private JTextArea gameLogDisplayText;
    private JScrollPane gameLogDisplay;
    private JTextField userInputField;
    private JLabel lblReputation;
    private JLabel lblSickness;
    private JLabel lblToken;

    public PlayerDashboard(Player player) {
        super();
        setBackground(new Color(58, 77, 108));
        setLayout(null);

        this.setBounds(150, 150, 410, 700);
        this.player = player;
        this.playerLabel = new JLabel(player.getName());
        playerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setBounds(118, 5, 88, 33);
        add(playerLabel);

        this.inventory = new Inventory(player.getInventory());
        inventory.setBounds(22, 63, 420, 480);
        add(inventory);
        
        JLabel lblNewLabel = new JLabel("Player name:");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(20, 5, 125, 33);
        add(lblNewLabel);

        lblReputation = new JLabel("Reputation: " + player.getReputation());
        lblReputation.setForeground(Color.WHITE);
        lblReputation.setBounds(128, 41, 88, 16);
        add(lblReputation);

        lblSickness = new JLabel("Sickness: "  + player.getSickness()  );
        lblSickness.setForeground(Color.WHITE);
        lblSickness.setBounds(225, 41, 88, 16);
        add(lblSickness);

        lblToken = new JLabel("");
        lblToken.setBounds(332, 5, 60, 60);
        lblToken.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/token" + player.getTokenIndex() + ".png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));
        add(lblToken);
  
        /*JLabel lblInventory = new JLabel("Inventory");
        lblInventory.setForeground(Color.LIGHT_GRAY);
        lblInventory.setBounds(22, 50, 88, 13);
        add(lblInventory);*/
        
        if (GameBoardController.getInstance().getClientAdapter().getMode().equals("Online")) {
            //USER IN TEXT GİRDİĞİ YER
            userInputField = new JTextField("write message");
            userInputField.setBounds(22, 466, 88, 25); // Adjust the position and size as needed
            userInputField.setFont(new Font("Arial", Font.PLAIN, 20));
            userInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Get the user's input;
                    String userInput = userInputField.getText();
                    String message = player.getName() + ": " + userInput + "\n";
                    GameBoardController.getInstance().getClientAdapter().send("CHAT:" + message);
                }
            }
        });
            add(userInputField);
        }
        

        gameLogDisplayInit();
        add(gameLogDisplay);
        
        JLabel lblGameLog = new JLabel("Game Log");
        lblGameLog.setForeground(Color.WHITE);
        lblGameLog.setBounds(24, 552, 88, 15);
        add(lblGameLog);

        player.setPlayerUI(this);

    }

    public Player getPlayer(){
        return this.player;
    }


    public void appendToGameLog(String text) {
        gameLogDisplayText.append(text); //append to the scrollable textbox in players dashboard
    }

    public void gameLogDisplayInit(){ //creation of scrollable textbox as gamelog
        this.gameLogDisplayText = new JTextArea(5,35);
        gameLogDisplayText.setEditable(false);
        this.gameLogDisplay = new JScrollPane(gameLogDisplayText);
        gameLogDisplay.setBounds(24, 574, 380, 103);

        gameLogDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void update(String msg) {//Observer messager
        if (msg.contains("GAMELOG")) {
        	appendToGameLog(msg.split(":")[1]);
        }
        else if (msg.contains("REPUTATION:")) {
     //   else if (msg.contains("REPUTATION")) {
            lblReputation.setText("Reputation: " + player.getReputation());
        }
    }
}   
