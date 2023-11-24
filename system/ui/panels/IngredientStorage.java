package system.ui.panels;

import system.ui.frame.GameContentPane;
import system.domain.IngredientCard;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IngredientStorage extends JPanel {
    
    private ArrayList<IngredientCard> ingredientPile;

    private JButton back;
    private JButton ingredientButton;
    
    public IngredientStorage() {
        super();

        ingredientPile = new ArrayList<IngredientCard>();
        this.back = createNavButton("environment", "Back to environment");
        add(back);
        this.ingredientButton = new JButton("Draw an Ingredient Card");
        this.ingredientButton.addActionListener(
            new ActionListener() {
                @Override 
                public void actionPerformed(ActionEvent e) {
                    
                }
            }
        );
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
