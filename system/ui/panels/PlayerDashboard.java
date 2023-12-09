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
        this.setBounds(150, 150, 486, 616);
        this.player = player;
        this.inventory = new Inventory(player.getInventory());
        inventory.setBounds(12, 144, 303, 348);
        inventory.setForeground(Color.WHITE); //
        //setLayout(null);
        this.playerLabel = new JLabel(player.getName());
        player.setPlayerDashboard(this);
        playerLabel.setForeground(Color.WHITE);
        playerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        playerLabel.setBounds(152, 13, 98, 21);
        playerLabel.setBackground(new Color(58, 77, 108));
        setLayout(null);
        JLabel lblNewLabel = new JLabel("Player name:");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(53, 17, 79, 16);
        add(lblNewLabel);

        lblReputation = new JLabel("Reputation: \n" + player.getReputation());
        lblReputation.setForeground(Color.WHITE);
        lblReputation.setBounds(12, 58, 88, 16);
        add(lblReputation);

        lblSickness = new JLabel("Sickness: \n"  + player.getSickness()  );
        lblSickness.setForeground(Color.WHITE);
        lblSickness.setBounds(12, 96, 75, 16);
        add(lblSickness);

//GAME LOG

        add(playerLabel);
        add(inventory);

        gameLogDisplayInit();
        this.gameLogDisplayText = new JTextArea(5,35);
        gameLogDisplayText.setBounds(12, 504, 303, 80);
        add(gameLogDisplayText);
        gameLogDisplayText.setEditable(false);
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
        this.gameLogDisplay = new JScrollPane();
        gameLogDisplay.setBounds(19, 459, 446, 0);
        gameLogDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void update(String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}   
