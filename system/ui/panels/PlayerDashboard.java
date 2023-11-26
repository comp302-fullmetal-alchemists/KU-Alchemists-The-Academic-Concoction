package system.ui.panels;

import system.domain.Player;
import system.domain.IngredientCard;

import javax.swing.JPanel;

import system.domain.GameLog;

public class PlayerDashboard extends JPanel {
    
    private Player player;
    private Inventory inventory;

    public PlayerDashboard(Player player){
        super();
        this.player = player;
        this.inventory = new Inventory();
        add(inventory);
    }

    public void takeIngredients(IngredientCard ingCard) {
        inventory.addItemToInventory(ingCard.getName());
        inventory.update();
    } 
    
    private void click(GameLog gameLog) {
        //OR CLICK GAMELOG PANEL
	}
    
    private void get(GameLog gameLog, Player player) {
    	player = this.player;
    	//return gameLog actions
    }
    
}   
