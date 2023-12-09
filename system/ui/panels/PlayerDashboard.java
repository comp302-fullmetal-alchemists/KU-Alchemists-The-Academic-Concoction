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

        this.setBounds(150, 150, 330, 690);
        this.player = player;
        this.playerLabel = new JLabel(player.getName());
        player.setPlayerDashboard(this);
        playerLabel.setForeground(Color.LIGHT_GRAY);
        playerLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        playerLabel.setBackground(new Color(58, 77, 108));
        playerLabel.setBounds(181, 5, 73, 16);
        add(playerLabel);

        this.inventory = new Inventory(player.getInventory());
        inventory.setBounds(20, 41, 290, 390);
        add(inventory);
        
        JLabel lblNewLabel = new JLabel("Player name:");
        lblNewLabel.setForeground(Color.LIGHT_GRAY);
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

//GAME LOG

        add(playerLabel);
        add(inventory);

        gameLogDisplayInit();
        add(gameLogDisplay);
    }

    public Player getPlayer(){
        return this.player;
    }

    public void takeIngredients(IngredientCard ingCard) {
        inventory.addIngredientToInventory(ingCard.getName());
    } 

    public void appendToGameLog(String text) {
        gameLogDisplayText.append(text);
    }

    public void gameLogDisplayInit(){
        this.gameLogDisplayText = new JTextArea(5,35);
        gameLogDisplayText.setEditable(false);
        this.gameLogDisplay = new JScrollPane(gameLogDisplayText);
        gameLogDisplay.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    }

    @Override
    public void update(String msg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}   
