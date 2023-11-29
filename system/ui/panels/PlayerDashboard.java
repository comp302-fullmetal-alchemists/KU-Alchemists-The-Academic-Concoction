package system.ui.panels;

import system.domain.Player;
import system.domain.controllers.GameLogController;
import system.domain.IngredientCard;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class PlayerDashboard extends JPanel {
    
    private Player player;
    private Inventory inventory;
    private JLabel playerLabel;

    public PlayerDashboard(Player player){
        super();
        this.player = player;
        this.inventory = new Inventory();
        this.playerLabel = new JLabel(player.getName());
        add(playerLabel);
        add(inventory);
    }

    public void takeIngredients(IngredientCard ingCard) {
        inventory.addItemToInventory(ingCard.getName());
        inventory.update();
    } 
    
    private void click(GameLogController gameLog) {
        //OR CLICK GAMELOG PANEL
	}
    
    private void get(GameLogController gameLog, Player player) {
    	player = this.player;
    	//return gameLog actions
    }
    
}   
