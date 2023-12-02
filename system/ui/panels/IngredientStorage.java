package system.ui.panels;

import system.ui.frame.GameContentPane;
import system.domain.IngredientCard;
import system.domain.controllers.IngredientStorageController;
import system.domain.controllers.GameBoardController;
import system.ui.interfaces.PlayerMediator;
import system.domain.interfaces.Observer;

import java.util.ArrayList;

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
    
    public IngredientStorage(PlayerMediator mediator) {
        super();
        this.mediator = mediator;
        this.ingController = GameBoardController.getInstance().getIngredientStorageController(); 
        ingController.setObserver(this);
        this.back = createNavButton("village", "Back to the village");
        add(back);
        this.ingredientButton = createIngButton("Draw Ingredient");
        add(ingredientButton);
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

    @Override
    public void update(String msg) {
        if (msg.equals("Pile is empty!")) {
            JOptionPane.showMessageDialog(IngredientStorage.this, "Pile is empty!");
        }
        else if (msg.contains("CARDREMOVAL")) {
            JOptionPane.showMessageDialog(IngredientStorage.this, String.format("You have drawn %s!", msg.substring(13)));
        }
    }
}
