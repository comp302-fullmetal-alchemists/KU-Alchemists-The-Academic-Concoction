package system.ui.panels;

import system.ui.frame.GameContentPane;
import system.domain.IngredientCard;
import system.ui.interfaces.PlayerMediator;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IngredientStorage extends JPanel {
    
    private ArrayList<IngredientCard> ingredientPile;
    private PlayerMediator mediator;
    private JButton back;
    private JButton ingredientButton;
    
    public IngredientStorage(PlayerMediator mediator) {
        super();
        this.mediator = mediator;
        ingredientPile = new ArrayList<IngredientCard>();
        ingredientPile.add(new IngredientCard("Ingredient1",  null));
        ingredientPile.add(new IngredientCard("Ingredient2", null));
        ingredientPile.add(new IngredientCard("Ingredient3", null));
        this.back = createNavButton("environment", "Back to environment");
        add(back);
        this.ingredientButton = new JButton("Draw an Ingredient Card");
        this.ingredientButton.addActionListener(
            new ActionListener() {
                @Override 
                public void actionPerformed(ActionEvent e) {
                    IngredientCard drawn = ingredientPile.remove(0);
                    JOptionPane.showMessageDialog(IngredientStorage.this, String.format("You have drawn %s!",drawn.getName()));
                    mediator.sendIngredientsToPlayer(drawn);
                }
            }
        );
        add(ingredientButton);
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
}
