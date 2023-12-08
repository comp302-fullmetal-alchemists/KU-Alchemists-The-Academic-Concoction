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
        this.setBounds(150, 150, 486, 357);
        this.player = player;
        this.inventory = new Inventory(player.getInventory());
        inventory.setBounds(20, 40, 193, 303);
        inventory.setForeground(Color.WHITE); //
        setLayout(null);
        this.playerLabel = new JLabel(player.getName());
        player.setPlayerDashboard(this);
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        playerLabel.setBounds(22, 20, 105, 21);
        playerLabel.setBackground(new Color(58, 77, 108));
        add(playerLabel);
        add(inventory);
        gameLogDisplayInit();
        add(gameLogDisplay);
        JLabel lblNewLabel = new JLabel("Player name:");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(80, 5, 110, 16);
        add(lblNewLabel);

        lblReputation = new JLabel("Reputation: \n" + player.getReputation());
        lblReputation.setForeground(Color.WHITE);
        lblReputation.setBounds(22, 58, 88, 16);
        add(lblReputation);

        lblSickness = new JLabel("Sickness: \n"  + player.getSickness()  );
        lblSickness.setForeground(Color.WHITE);
        lblSickness.setBounds(22, 86, 88, 16);
        add(lblSickness);
    }

    public Player getPlayer(){
        return this.player;
    }
    public void takeIngredients(IngredientCard ingCard) {
        inventory.addItemToInventory(ingCard.getName(), "Ingredient");
    } 

    
    private void click(GameLogController gameLog) {
        //OR CLICK GAMELOG PANEL
	}
    
    private void get(GameLogController gameLog, Player player) {
    	player = this.player;
    	//return gameLog actions
    }
    
}   
