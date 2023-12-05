package system.ui.panels;

import system.ui.frame.GameContentPane;
import system.domain.controllers.IngredientStorageController;
import system.domain.ArtifactCard;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
        this.ingController = GameBoardController.getInstance().getIngredientStorageController(); 
        ingController.setObserver(this);
        this.back = createNavButton("village", "Back to the village");
        add(back);
        this.ingredientButton = createIngButton("Draw Ingredient");
        add(ingredientButton);
        this.artifactButton = createArtifactButton("Draw Artifact Card");
        add(artifactButton);
        this.transmuteIngButton = createTransmuteIngButton();
        add(transmuteIngButton);
    }

    public void initialize() {
        ingController.deactivate();
        transmuteIngButton.setText(deactiveText);
    }

    public JButton createIngButton(String text) {
        JButton ingButton = new JButton(text);
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
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameContentPane) IngredientStorage.this.getParent()).changeView(nav);
                    initialize();
                }
            }
        );
        return button;
    }

    public JButton createArtifactButton(String text) {
        JButton artifactButton = new JButton(text);
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
            initialize();
        }
        else if (msg.contains("ARTIFACT_BOUGHT")) {
            showMessageDialog(String.format("You have bought %s!", msg.split(":")[1]));
        }
        else if (msg.contains("NOT_ENOUGH_GOLD")) {
            showMessageDialog("You do not have enough gold!");
        }
    }
}