package system.ui.panels;

import system.domain.Player;
import system.domain.interfaces.Observer;
import system.domain.IngredientCard;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;

import java.awt.Color;

import javax.swing.JLabel;

public class PlayerDashboard extends JPanel implements Observer {
    
    private Player player;
    private Inventory inventory;
    private JLabel playerLabel;
    private JTextArea gameLogDisplayText;
    private JScrollPane gameLogDisplay;
    private JLabel lblReputation;
    private JLabel lblSickness;

    public PlayerDashboard(Player player) {
        super();
        setBackground(new Color(58, 77, 108));
        setLayout(null);

        this.setBounds(150, 150, 335, 700);
        this.player = player;
        this.playerLabel = new JLabel(player.getName());
        player.setPlayerUI(this);
        playerLabel.setForeground(Color.LIGHT_GRAY);
        playerLabel.setBounds(157, 5, 81, 16);
        add(playerLabel);

        this.inventory = new Inventory(player.getInventory());
        inventory.setBounds(22, 63, 290, 390);
        add(inventory);
        
        JLabel lblNewLabel = new JLabel("Player name:");
        lblNewLabel.setForeground(Color.LIGHT_GRAY);
        lblNewLabel.setBounds(80, 5, 110, 16);
        add(lblNewLabel);

        lblReputation = new JLabel("Reputation: \n" + player.getReputation());
        lblReputation.setForeground(Color.LIGHT_GRAY);
        lblReputation.setBounds(53, 31, 88, 16);
        add(lblReputation);

        lblSickness = new JLabel("Sickness: \n"  + player.getSickness()  );
        lblSickness.setForeground(Color.LIGHT_GRAY);
        lblSickness.setBounds(190, 31, 88, 16);
        add(lblSickness);

//GAME LOG
  
        JLabel lblInventory = new JLabel("Inventory");
        lblInventory.setForeground(Color.LIGHT_GRAY);
        lblInventory.setBounds(22, 50, 88, 13);
        add(lblInventory);
        
        gameLogDisplayInit();
        add(gameLogDisplay);
        
        JLabel lblGameLog = new JLabel("Game Log");
        lblGameLog.setForeground(Color.LIGHT_GRAY);
        lblGameLog.setBounds(22, 464, 88, 13);
        add(lblGameLog);
    }

    public Player getPlayer(){
        return this.player;
    }


    public void appendToGameLog(String text) {
        gameLogDisplayText.append(text);
    }

    public void gameLogDisplayInit(){
        this.gameLogDisplayText = new JTextArea(5,35);
        gameLogDisplayText.setEditable(false);
        this.gameLogDisplay = new JScrollPane(gameLogDisplayText);
        gameLogDisplay.setBounds(22, 477, 290, 210);
        gameLogDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void update(String msg) {
        if (msg.contains("GAMELOG")) {
        	appendToGameLog(msg.split(":")[1]);
        }
    }
}   
