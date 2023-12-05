package system.ui.panels;

import system.domain.Player;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.GameLogController;
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
    private GameLogController gameLogController;

    public PlayerDashboard(Player player) {
        super();
        this.player = player;
        this.inventory = new Inventory(player.getInventory());
        this.playerLabel = new JLabel(player.getName());
        this.gameLogController = GameBoardController.getInstance().getGameLog();
        gameLogController.setPlayerDashboard(this);
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

    
    private void click(GameLogController gameLog) {
        //OR CLICK GAMELOG PANEL
	}
    
    private void get(GameLogController gameLog, Player player) {
    	player = this.player;
    	//return gameLog actions
    }

    public void gameLogDisplayInit(){
        gameLogDisplayText = new JTextArea(5,25);
        gameLogDisplayText.setEditable(false);
        gameLogDisplay = new JScrollPane(gameLogDisplayText);
        gameLogDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void appendToGameLog(String text) {
        gameLogDisplayText.append(text + "\n");
    }

    @Override
    public void update(String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}   
