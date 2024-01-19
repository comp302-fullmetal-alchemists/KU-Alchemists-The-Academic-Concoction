package system.ui.panels;

import system.ui.frame.GameContentPane;
import system.domain.controllers.IngredientStorageController;
import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;


import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.ArrayList;

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
    private JLabel lblArtifact;
    private JPanel artifactPanel;
    private String[] artifacts = {"Magic Mortar", "Elixir of Insight", "Discount Card", "Printing Press", "Wisdom Idol"};
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;

    
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

        lblArtifact = new JLabel("Artifacts:");
        lblArtifact.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        lblArtifact.setForeground(Color.WHITE);
        lblArtifact.setBounds(111, 336, 550, 90);
        add(lblArtifact);
        artifactPanel = new JPanel();
        artifactPanel.setBackground(new Color(58, 77, 108));
        artifactPanel.setBounds(121, 426, 550, 149);
        artifactPanel.setLayout(null);
        add(artifactPanel);


       artifactPanel.removeAll();
       artifactPanel.revalidate();

       int x = 6; // Initial X position
       int y = 4; // Y position 
       int labelWidth = 60; // Width of the label
       int labelHeight = 100; // Height of the label
       int buttonWidth = 60; // Width of the button
       int buttonHeight = 20; // Height of the button
       int spacingX = 110; // Horizontal spacing between items

       for (int i = 0; i < artifacts.length; i++) {
           String artifactName = artifacts[i];

           // Calculating the X position for this iteration
           int currentX = x + spacingX * i;

           JLabel artifactLabel = new JLabel(new ImageIcon(getClass().getResource("/resources/" + artifacts[i].toLowerCase() + ".png")));
           artifactLabel.setHorizontalAlignment(SwingConstants.CENTER);
           artifactLabel.setOpaque(true);
           artifactLabel.setBackground(new Color(117, 67, 108));
           artifactLabel.setForeground(Color.WHITE);
           artifactLabel.setBounds(currentX, y, labelWidth, labelHeight);
           artifactPanel.add(artifactLabel);


           JButton buyButton = new JButton("Buy");
           buyButton.setBounds(currentX, y + labelHeight, buttonWidth, buttonHeight);
           buyButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   System.out.println("Buying artifact: " + artifactName);
                   ingController.buyArtifact2(artifactName);
               }
           });
           artifactPanel.add(buyButton);
       }

       artifactPanel.revalidate();
       artifactPanel.repaint();

        
        
      /*   lblArtifactPile = new JLabel("Buy Artifact");
        lblArtifactPile.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//ingController.buyArtifact();
        	}
        });
    	lblArtifactPile.setOpaque(true);
    	lblArtifactPile.setHorizontalAlignment(SwingConstants.CENTER);
    	lblArtifactPile.setBackground(Color.LIGHT_GRAY);
    	lblArtifactPile.setBounds(111, 370, 150, 160);
    	add(lblArtifactPile);
    	*/
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
    	
    	lblNewLabel_1 = new JLabel("Magic Mortar");
    	lblNewLabel_1.setForeground(Color.WHITE);
    	lblNewLabel_1.setBounds(121, 410, 92, 16);
    	add(lblNewLabel_1);
    	
    	lblNewLabel_2 = new JLabel("Elixir of Insight");
    	lblNewLabel_2.setForeground(Color.WHITE);
    	lblNewLabel_2.setBounds(214, 410, 106, 16);
    	add(lblNewLabel_2);
    	
    	lblNewLabel_3 = new JLabel("Discount Card");
    	lblNewLabel_3.setForeground(Color.WHITE);
    	lblNewLabel_3.setBounds(328, 410, 92, 16);
    	add(lblNewLabel_3);
    	
    	lblNewLabel_4 = new JLabel("Printing Press");
    	lblNewLabel_4.setForeground(Color.WHITE);
    	lblNewLabel_4.setBounds(446, 410, 92, 16);
    	add(lblNewLabel_4);
    	
    	lblNewLabel_5 = new JLabel("Wisdom Idol");
    	lblNewLabel_5.setForeground(Color.WHITE);
    	lblNewLabel_5.setBounds(557, 410, 92, 16);
    	add(lblNewLabel_5);
        
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
            showMessageDialog(String.format("You have used the Elixir of Insight card! The last 3 cards in the ingredient pile:  %s!", msg.substring(19)));
        }

        else if (msg.contains("MAGIC_MORTAR")) {
            showMessageDialog(String.format("You have used the Magic Mortar card! You got the ingredient %s!", msg.split(":")[1]));

        }

        else if (msg.contains("DISCOUNT_CARD")) {
            showMessageDialog(String.format("You have used the Discount card! Your next artifact card will cost 2 gold less from the original price. After that every artifact card will cost you 1 gold less!"));

        }
        else if (msg.contains("PRINTING_PRESS")) {
            showMessageDialog(String.format("You have used the Printing Press card! Your next theory will be published free of charge."));

        }
    

        else if (msg.contains("WISDOM_IDOL")) {
            showMessageDialog(String.format("You have used the Wisdom Idol card! This card allows you to not lose any reputation points even if your theory is debunked. You gain 1 reputation point if you keep it in your inventory until the end."
            ));

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
        /*else if (msg.contains("ARTIFACT_BOUGHT")) {
            System.out.println(msg);
            showMessageDialog(String.format("You have bought %s!", msg.split(":")[1]));
        }*/
        else if (msg.contains("NOT_ENOUGH_GOLD")) {
            showMessageDialog("You do not have enough gold!");
        }
        else if (msg.contains("UNAUTHORIZED_ACTION")) {
            showMessageDialog("You cannot perform action! Wait for your turn!");
        }
    }
}