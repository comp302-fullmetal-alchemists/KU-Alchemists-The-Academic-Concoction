package system.ui.panels;

import system.ui.frame.GameContentPane;
import system.domain.controllers.IngredientStorageController;
import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;

import javax.swing.*;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.ArrayList;



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
    private List<String> elixirIngredients = new ArrayList<>();;

    
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
        lblArtifact.setBounds(140, 500, 550, 90);
        add(lblArtifact);
        artifactPanel = new JPanel();
        artifactPanel.setBounds(150, 550, 550, 90);
        artifactPanel.setLayout(null);
        add(artifactPanel);


       artifactPanel.removeAll();
       artifactPanel.revalidate();

       int x = 6; // Initial X position
       int y = 4; // Y position 
       int labelWidth = 100; // Width of the label
       int labelHeight = 60; // Height of the label
       int buttonWidth = 100; // Width of the button
       int buttonHeight = 20; // Height of the button
       int spacingX = 110; // Horizontal spacing between items

       for (int i = 0; i < artifacts.length; i++) {
           String artifactName = artifacts[i];

           // Calculating the X position for this iteration
           int currentX = x + spacingX * i;


           JLabel artifactLabel = new JLabel("<html>" + artifactName.replaceAll(" ", "<br>") + "</html>");
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

        
        
        lblArtifactPile = new JLabel("Buy Artifact");
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
    	
    	navBtn = new JButton("Back to the village");
    	navBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			((GameContentPane) IngredientStorage.this.getParent()).changeView("village");
    		}
    	});
    	navBtn.setBounds(288, 26, 159, 29);
    	add(navBtn);
    	
    	lblIngToSell = new JLabel("<html>Select<br>Ingredient</html>");
    	lblIngToSell.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent e) {
    			if (ingController.hasIngToSell()) ingController.discardIngToSell();
    		}
    	});
    	lblIngToSell.setBackground(Color.LIGHT_GRAY);
    	lblIngToSell.setHorizontalAlignment(SwingConstants.CENTER);
    	lblIngToSell.setOpaque(true);
    	lblIngToSell.setBounds(506, 117, 90, 96);
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

    // Helper method to update elixirIngredients based on the model
    private void updateElixirIngredients(DefaultListModel<String> model) {
        elixirIngredients.clear();
        for (int i = 0; i < model.getSize(); i++) {
            // Assuming elixirIngredients should store the indices of ingredients
            // You can convert the ingredient name to an index if needed
            String ingredientName = model.getElementAt(i);
            elixirIngredients.add(ingredientName);
        }
    }

    public void showPopupAndWait(String msg) {
        // Create a modal dialog
        JDialog dialog = new JDialog();
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setTitle("Elixir of Insight");

        // Instruction label
        JLabel instructions = new JLabel("<html><center>Select an ingredient and use the buttons to change its order in the pile.<br>Items on top of the pile can be rearranged using these buttons.</center></html>");
        instructions.setHorizontalAlignment(JLabel.CENTER);
        instructions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Extract items from the msg string
        String[] items = msg.substring(msg.indexOf(":") + 1).trim().split(",\\s*");

        // Create a DefaultListModel and add items
        DefaultListModel<String> model = new DefaultListModel<>();
        Arrays.stream(items).forEach(model::addElement);

        // Create a JList and set its model
        JList<String> list = new JList<>(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create buttons
        JButton btnMoveUp = new JButton("Move Up");
        JButton btnMoveDown = new JButton("Move Down");
        JButton btnDone = new JButton("Done");

    // Add action listener for Move Up button
    btnMoveUp.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = list.getSelectedIndex();
            if (selectedIndex > 0) {
                String item = model.remove(selectedIndex);
                model.add(selectedIndex - 1, item);
                list.setSelectedIndex(selectedIndex - 1);
                updateElixirIngredients(model); // Update elixirIngredients
            }
        }
    });

    // Add action listener for Move Down button
    btnMoveDown.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = list.getSelectedIndex();
            if (selectedIndex < model.getSize() - 1) {
                String item = model.remove(selectedIndex);
                model.add(selectedIndex + 1, item);
                list.setSelectedIndex(selectedIndex + 1);
                updateElixirIngredients(model); // Update elixirIngredients
            }
        }
    });

    // Add action listener for the Done button
    btnDone.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            updateElixirIngredients(model); // Update elixirIngredients
            System.out.println(list); // Print the list
            dialog.dispose(); // Close the dialog
        }
    });
        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnMoveUp);
        buttonPanel.add(btnMoveDown);
        buttonPanel.add(btnDone);

        // Create and set up the content pane
        dialog.getContentPane().setLayout(new BorderLayout());
        dialog.getContentPane().add(instructions, BorderLayout.NORTH);
        dialog.getContentPane().add(new JScrollPane(list), BorderLayout.CENTER);
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        dialog.setSize(300, 300);

        // Position the dialog in the center of the screen
        dialog.setLocationRelativeTo(null);

        // Add a window listener to print the list when the dialog is closed
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                updateElixirIngredients(model); // Update elixirIngredients
                System.out.println(list); // Print the list
                dialog.dispose(); // Close the dialog
            }
        });

        // Make the dialog visible
        dialog.setVisible(true);
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
            System.out.printf(msg);
            showPopupAndWait(msg);
            //CONVERT INGREDIENT TO INTEGER, PUT TO SERVER.elixirIngredients
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
            showMessageDialog(String.format("You have used the Wisdom Idol card! Using this artifact, you do not not lose any reputation points even if your theory has been proven to be wrong. If you choose to keep this artifact until the end of the game, you gain an additional 1 reputation point."));

        }

        else if (msg.contains("DISCOUNT_CARD")) {
            showMessageDialog(String.format("You have used the Discount C card!"));

        }

        else if (msg.contains("NEW_INGREDIENT")) {
        	String[] ingName = msg.split(":")[1].split(" ");
        	lblIngToSell.setText("<html>" + ingName[0] + "<br>" + ingName[1] + "</html>");
        	lblIngToSell.setBackground(new Color(117, 67, 108));
        }
        else if (msg.contains("DISCARD_INGREDIENT")) {
        	lblIngToSell.setText("<html>Select<br>Ingredient</html>");
    		lblIngToSell.setBackground(Color.LIGHT_GRAY);
        }
        else if (msg.contains("ABSENT_INGREDIENT")) {
        	showMessageDialog("Please select an ingredient to sell");
        }
        else if (msg.contains("CARD_SOLD")) {
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