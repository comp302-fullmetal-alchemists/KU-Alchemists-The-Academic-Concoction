package system.ui.panels;

import system.domain.interfaces.Observer;
import system.domain.IngredientCard;
import system.domain.controllers.Player;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import javafx.scene.image.Image;

import java.awt.Font;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerDashboard extends JPanel implements Observer {
    
    private Player player;
    private Inventory inventory;
    private JLabel playerLabel;
    private JTextArea gameLogDisplayText;
    private JScrollPane gameLogDisplay;
    private JLabel lblReputation;
    private JLabel lblSickness;
    private JLabel lblToken;

    public PlayerDashboard(Player player) {
        super();
        setBackground(new Color(58, 77, 108));
        setLayout(null);

        this.setBounds(150, 150, 335, 700);
        this.player = player;
        this.playerLabel = new JLabel(player.getName());
        playerLabel.setForeground(Color.LIGHT_GRAY);
        //playerLabel.setBounds(157, 5, 81, 16);
        playerLabel.setBounds(99, 5, 81, 16);
        add(playerLabel);

        this.inventory = new Inventory(player.getInventory());
        inventory.setBounds(22, 63, 290, 390);
        add(inventory);
        
        JLabel lblNewLabel = new JLabel("Player name:");
        lblNewLabel.setForeground(Color.LIGHT_GRAY);
        //lblNewLabel.setBounds(80, 5, 110, 16);
        lblNewLabel.setBounds(22, 5, 110, 16);
        add(lblNewLabel);

        lblReputation = new JLabel("Reputation: " + player.getReputation());
        lblReputation.setForeground(Color.LIGHT_GRAY);
        //lblReputation.setBounds(53, 31, 88, 16);
        lblReputation.setBounds(22, 31, 88, 16);
        add(lblReputation);

        lblSickness = new JLabel("Sickness: "  + player.getSickness()  );
        lblSickness.setForeground(Color.LIGHT_GRAY);
        //lblSickness.setBounds(190, 31, 88, 16);
        lblSickness.setBounds(140, 31, 88, 16);
        add(lblSickness);

        lblToken = new JLabel("");
        lblToken.setBounds(250, 5, 60, 60);
        lblToken.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/token" + player.getTokenIndex() + ".png")).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));
        add(lblToken);
  
        JLabel lblInventory = new JLabel("Inventory");
        lblInventory.setForeground(Color.LIGHT_GRAY);
        lblInventory.setBounds(22, 50, 88, 13);
        add(lblInventory);
        
        //Scrollable GameLog inside players dashboard
        gameLogDisplayInit();
        add(gameLogDisplay);
        
        JLabel lblGameLog = new JLabel("Game Log");
        lblGameLog.setForeground(Color.LIGHT_GRAY);
        lblGameLog.setBounds(22, 464, 88, 13);
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
        gameLogDisplay.setBounds(22, 477, 290, 210);

        gameLogDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void update(String msg) {//Observer messager
        if (msg.contains("GAMELOG")) {
        	appendToGameLog(msg.split(":")[1]);
        }
        else if (msg.contains("REPUTATION:")) {
            lblReputation.setText("Reputation: " + player.getReputation());
        }
    }
}   
