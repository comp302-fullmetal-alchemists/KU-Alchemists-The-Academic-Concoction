package system.ui.panels;

import system.domain.Player;
import system.domain.interfaces.Observer;
import system.domain.IngredientCard;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

public class PlayerDashboard extends JPanel implements Observer {
    
    private Player player;
    private Inventory inventory;
    private JLabel playerLabel;
    private JTextArea gameLogDisplayText;
    private JScrollPane gameLogDisplay;

    public PlayerDashboard(Player player) {
        super();
        this.player = player;
        this.inventory = new Inventory(player.getInventory());
        this.playerLabel = new JLabel(player.getName());
        player.setPlayerDashboard(this);
        add(playerLabel);
        add(inventory);
        gameLogDisplayInit();
        add(gameLogDisplay);
    }

    public Player getPlayer(){
        return this.player;
    }
    public void takeIngredients(IngredientCard ingCard) {
        inventory.addItemToInventory(ingCard.getName(), "Ingredient");
    } 

    public void appendToGameLog(String text) {
        gameLogDisplayText.append(text);
    }

    public void gameLogDisplayInit(){
        gameLogDisplayText = new JTextArea(5,35);
        gameLogDisplayText.setEditable(false);
        gameLogDisplay = new JScrollPane(gameLogDisplayText);
        gameLogDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void update(String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}   
