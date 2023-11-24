package system.ui.panels;

import system.domain.Player;
import system.domain.IngredientCard;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class PlayerDashboard extends JPanel {
    
    private Player player;
    private inventoryPanel inventory;

    public PlayerDashboard(Player player){
        super();
        this.player = player;
        this.inventory = new inventoryPanel();
        add(inventory);
    }

    public void takeIngredients(IngredientCard ingCard) {
        inventory.addItemToInventory(ingCard.getName());
        inventory.update();
    } 
    
    private class inventoryPanel extends JPanel {
        private ArrayList<JLabel> items = new ArrayList<JLabel>();

        private inventoryPanel() {
            super();
        }

        private void addItemToInventory(String text) {
            items.add(new JLabel(text));
        }

        private void update() {
            removeAll();
            for (JLabel j: items) {
                add(j);
            }
            revalidate();
        }
    }
}   
