package system.ui.panels;

import system.ui.frame.GameContentPane;
import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.controllers.IngredientStorageController;
import system.ui.interfaces.PlayerMediator;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IngredientStorage extends JPanel {
    
    private IngredientStorageController ingController;
    private PlayerMediator mediator;
    private JButton back;
    private JButton ingredientButton;
    private JButton artifactButton;
    
    public IngredientStorage(PlayerMediator mediator) {
        super();
        this.mediator = mediator;
        this.ingController = new IngredientStorageController(); 
        ////there will be problems about who should create controller,
        ////should it be gameboard controller or ingredient storage??
        this.back = createNavButton("environment", "Back to environment");
        add(back);
        this.ingredientButton = createIngButton("Draw Ingredient");
        add(ingredientButton);
        this.artifactButton = createArtifactButton("Draw Artifact Card");
        add(artifactButton);
    }

    public JButton createIngButton(String text) {
        JButton ingButton = new JButton(text);
        ingButton.addActionListener(
            new ActionListener() {
                @Override 
                public void actionPerformed(ActionEvent e) {
                    IngredientCard drawn = ingController.drawIngredient();
                    JOptionPane.showMessageDialog(IngredientStorage.this, String.format("You have drawn %s!",drawn.getName()));
                    mediator.sendIngredientsToPlayer(drawn);
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
                    ArtifactCard drawn = ingController.buyArtifact();
                    JOptionPane.showMessageDialog(IngredientStorage.this, String.format("You have drawn %s!",drawn.getName()));
                    JOptionPane.showMessageDialog(IngredientStorage.this, String.format("The effects of the card:!", ingController.useArtifact(drawn).toString()));

                }
            }
        );
        return artifactButton;
    }
}
