package system.ui.panels;

import system.ui.frame.GameContentPane;
import system.domain.controllers.IngredientStorageController;
import system.domain.ArtifactCard;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Font;

public class IngredientStorage extends JPanel implements Observer {
    
    private IngredientStorageController ingController;
    private JButton back;
    private JButton ingredientButton;
    private JButton artifactButton;
    private JButton transmuteIngButton;
    String deactiveText = "Transmute Ingredient";
    String activeText = "Finish action";
    
    
    public IngredientStorage() {
        super();
        setBackground(new Color(58, 77, 108));
        this.ingController = GameBoardController.getInstance().getIngredientStorageController(); 
        ingController.setObserver(this);
        setLayout(null);
        this.back = createNavButton("village", "Back to the village");
        add(back);
        this.ingredientButton = createIngButton("Draw Ingredient");
        add(ingredientButton);
        this.artifactButton = createArtifactButton("Draw Artifact Card");
        add(artifactButton);
        this.transmuteIngButton = createTransmuteIngButton();
        add(transmuteIngButton);
        
        JLabel lblNewLabel = new JLabel("Actions");
        lblNewLabel.setFont(new Font("Apple Chancery", Font.PLAIN, 20));
    	lblNewLabel.setForeground(Color.WHITE);
    	lblNewLabel.setBounds(27, 24, 130, 24);
        add(lblNewLabel);
        
    }
    

    public void clear() {
        ingController.deactivate();
        transmuteIngButton.setText(deactiveText);
    }

    public JButton createIngButton(String text) {
        JButton ingButton = new JButton(text);
        ingButton.setBounds(27, 60, 179, 29);
        ingButton.addActionListener(
            new ActionListener() {
                @Override 
                public void actionPerformed(ActionEvent e) {
                    ingController.drawIngredient();
                }
            }
        );
        return ingButton;
    }

    public JButton createNavButton(String nav, String text) {
        JButton button = new JButton(text);
        button.setBounds(271, 19, 159, 29);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameContentPane) IngredientStorage.this.getParent()).changeView(nav);
                }
            }
        );
        return button;
    }

    public JButton createArtifactButton(String text) {
        JButton artifactButton = new JButton(text);
        artifactButton.setBounds(27, 101, 179, 29);
        artifactButton.addActionListener(
            new ActionListener() {
                @Override 
                public void actionPerformed(ActionEvent e) {
                    ingController.buyArtifact();
                }
            }
        );
        return artifactButton;}
    
        public JButton createTransmuteIngButton() {
            JButton button = new JButton(deactiveText);
            button.setBounds(27, 142, 179, 29);
            button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (ingController.isActive()){
                            ingController.deactivate();
                            button.setText(deactiveText);
                        }
                        else {
                            ingController.activate();
                            button.setText(activeText);
                        }
                    }
    
                }
            );
            
            return button;
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
        else if (msg.contains("CARD_SOLD")) {
            showMessageDialog(String.format("You have sold %s!", msg.split(":")[1]));
            clear();
        }
        else if (msg.contains("ARTIFACT_BOUGHT")) {
            showMessageDialog(String.format("You have bought %s!", msg.split(":")[1]));
        }
        else if (msg.contains("NOT_ENOUGH_GOLD")) {
            showMessageDialog("You do not have enough gold!");
        }
    }
}