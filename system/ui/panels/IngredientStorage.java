package system.ui.panels;

import system.ui.frame.GameContentPane;
import system.domain.controllers.IngredientStorageController;
import system.domain.ArtifactCard;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;


import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IngredientStorage extends JPanel implements Observer {
    
    private IngredientStorageController ingController;
    private JButton navBtn;
    private JLabel lblIngPile;
    private JLabel lblArtifactPile;
    private JLabel lblIngToSell;
    private JButton transmuteIngBtn;
    
    
    public IngredientStorage() {
        super();
        setBackground(new Color(58, 77, 108));
        this.ingController = GameBoardController.getInstance().getIngredientStorageController(); 
        ingController.setObserver(this);
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Actions");
        lblNewLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 20));
    	lblNewLabel.setForeground(Color.WHITE);
    	lblNewLabel.setBounds(27, 24, 130, 24);
        add(lblNewLabel);
        
        lblIngPile = new JLabel("Draw Ingredient");
        lblIngPile.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                ingController.drawIngredient();

        	}
        });
        lblIngPile.setBackground(new Color(117, 67, 108));
        lblIngPile.setOpaque(true);
        lblIngPile.setHorizontalAlignment(SwingConstants.CENTER);
        lblIngPile.setBounds(111, 117, 150, 160);
        add(lblIngPile);
        
        lblArtifactPile = new JLabel("Buy Artifact");
        lblArtifactPile.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		ingController.buyArtifact();
        	}
        });
    	lblArtifactPile.setOpaque(true);
    	lblArtifactPile.setHorizontalAlignment(SwingConstants.CENTER);
    	lblArtifactPile.setBackground(Color.LIGHT_GRAY);
    	lblArtifactPile.setBounds(111, 370, 150, 160);
    	add(lblArtifactPile);
    	
    	navBtn = new JButton("Back to the village");
    	navBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			((GameContentPane) IngredientStorage.this.getParent()).changeView("village");
    		}
    	});
    	navBtn.setBounds(288, 26, 159, 29);
    	add(navBtn);
    	
    	lblIngToSell = new JLabel("<html>Select<br>Ingredient</html>");
    	lblIngToSell.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
    	lblIngToSell.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			if (ingController.hasIngToSell()) ingController.discardIngToSell();
    		}
    	});
    	lblIngToSell.setBackground(Color.LIGHT_GRAY);
    	lblIngToSell.setHorizontalAlignment(SwingConstants.CENTER);
    	lblIngToSell.setOpaque(true);
    	lblIngToSell.setBounds(518, 133, 60, 100);
    	add(lblIngToSell);
    	
    	transmuteIngBtn = new JButton("Transmute Ingredient");
    	transmuteIngBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if (ingController.isActive()) ingController.transmuteIngredient();
    		}
    	});
    	transmuteIngBtn.setBounds(473, 256, 159, 21);
    	add(transmuteIngBtn);
        
    }
    

    public void clear() {
    	if (ingController.hasIngToSell()) ingController.discardIngToSell();
        ingController.deactivate();
    }
    
    public void activate() {
    	ingController.activate();
    	JOptionPane.showMessageDialog(this, "You may now choose ingredients from your inventory to sell.");
    }


    public void showMessageDialog(String displayMsg) {
        JOptionPane.showMessageDialog(this, displayMsg);
    }


    @Override
    public void update(String msg) {
        if (msg.equals("EMPTY_PILE")) {
            showMessageDialog("Pile is empty!");
        }
        else if (msg.contains("CARDREMOVAL")) {
            showMessageDialog(String.format("You have drawn %s!", msg.split(":")[1]));
        }
        else if (msg.contains("ELIXIR_OF_INSIGHT")) {
            showMessageDialog(String.format("You have drawn the elixir of insight card! The last 3 cards in the ingredient pile:  %s!", msg.substring(19)));
        }
        else if (msg.contains("NEW_INGREDIENT")) {
            String ingName = msg.split(":")[1];
        	lblIngToSell.setIcon(new ImageIcon(getClass().getResource("/resources/" + ingName.toLowerCase() + ".png")));
        }
        else if (msg.contains("DISCARD_INGREDIENT")) {
            lblIngToSell.setIcon(null);
        	lblIngToSell.setText("<html>Select<br>Ingredient</html>");
    		lblIngToSell.setBackground(Color.LIGHT_GRAY);
        }
        else if (msg.contains("ABSENT_INGREDIENT")) {
        	showMessageDialog("Please select an ingredient to sell");
        }
        else if (msg.contains("CARD_SOLD")) {
            lblIngToSell.setIcon(null);
        	lblIngToSell.setText("<html>Select<br>Ingredient</html>");
    		lblIngToSell.setBackground(Color.LIGHT_GRAY);
            showMessageDialog(String.format("You have sold %s!", msg.split(":")[1]));
        }
        else if (msg.contains("ARTIFACT_BOUGHT")) {
            showMessageDialog(String.format("You have bought %s!", msg.split(":")[1]));
        }
        else if (msg.contains("NOT_ENOUGH_GOLD")) {
            showMessageDialog("You do not have enough gold!");
        }
        else if (msg.contains("UNAUTHORIZED_ACTION")) {
            showMessageDialog("You cannot perform action! Wait for your turn!");
        }
    }
}