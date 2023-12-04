package system.ui.panels;

import system.ui.frame.GameContentPane;
import system.domain.controllers.IngredientStorageController;
import system.domain.ArtifactCard;
import system.domain.controllers.GameBoardController;
import system.ui.interfaces.PlayerMediator;
import system.domain.interfaces.Observer;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IngredientStorage extends JPanel implements Observer {
    
    private IngredientStorageController ingController;
    private PlayerMediator mediator;
    private JButton back;
    private JButton ingredientButton;
    private JButton artifactButton;
    
    public IngredientStorage(PlayerMediator mediator) {
        super();
        this.mediator = mediator;
        this.ingController = GameBoardController.getInstance().getIngredientStorageController(); 
        ingController.setObserver(this);
        this.back = createNavButton("village", "Back to the village");
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
                    ingController.useArtifact(drawn);
                    
                    
                }
            }
        );
        return artifactButton;}
    @Override
    public void update(String msg) {
        if (msg.equals("Pile is empty!")) {
            JOptionPane.showMessageDialog(IngredientStorage.this, "Pile is empty!");
        }
        else if (msg.contains("CARDREMOVAL")) {
            JOptionPane.showMessageDialog(IngredientStorage.this, String.format("You have drawn %s!", msg.substring(13)));
        }
        else if (msg.contains("ELIXIR_OF_INSIGHT")) {
            JOptionPane.showMessageDialog(IngredientStorage.this, String.format("You have drawn the elixir of insight card! The last 3 cards in the ingredient pile:  %s!", msg.substring(19)));
        }
    }
}
