package system.ui.panels;

import system.domain.interfaces.Observer;
import system.domain.IngredientCard;
import system.domain.controllers.Player;

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

        this.setBounds(150, 150, 410, 700);
        this.player = player;
        this.playerLabel = new JLabel(player.getName());
        playerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setBounds(118, 5, 88, 33);
        add(playerLabel);

        this.inventory = new Inventory(player.getInventory());
        inventory.setBounds(22, 63, 420, 619);
        add(inventory);
        
        JLabel lblNewLabel = new JLabel("Player name:");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(20, 5, 125, 33);
        add(lblNewLabel);

        lblReputation = new JLabel("Reputation: " + player.getReputation());
        lblReputation.setForeground(Color.WHITE);
        lblReputation.setBounds(76, 41, 88, 16);
        add(lblReputation);

        lblSickness = new JLabel("Sickness: "  + player.getSickness()  );
        lblSickness.setForeground(Color.WHITE);
        lblSickness.setBounds(194, 41, 88, 16);
        add(lblSickness);
        
        //Scrollable GameLog inside players dashboard
        gameLogDisplayInit();
        add(gameLogDisplay);
        
        JLabel lblGameLog = new JLabel("Game Log");
        lblGameLog.setForeground(Color.WHITE);
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
     //   else if (msg.contains("REPUTATION")) {
            lblReputation.setText("Reputation: " + player.getReputation());
        }
    }
}   
