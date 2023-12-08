package system.ui.panels;

import system.domain.Player;
import system.domain.controllers.GameLogController;
import system.domain.IngredientCard;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

public class PlayerDashboard extends JPanel {
    
    private Player player;
    private Inventory inventory;
    private JLabel playerLabel;

    public PlayerDashboard(Player player){
        super();
        setBackground(new Color(58, 77, 108));
        this.setBounds(150, 150, 486, 357);
        this.player = player;
        this.inventory = new Inventory(player.getInventory());
        inventory.setBounds(20, 33, 193, 303);
        setLayout(null);
        this.playerLabel = new JLabel(player.getName());
        playerLabel.setBounds(181, 5, 73, 16);
        add(playerLabel);
        add(inventory);
        
        JLabel lblNewLabel = new JLabel("Player name:");
        lblNewLabel.setBounds(80, 5, 110, 16);
        add(lblNewLabel);
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

