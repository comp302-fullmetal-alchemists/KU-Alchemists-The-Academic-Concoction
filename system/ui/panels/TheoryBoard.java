package system.ui.panels;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import system.domain.Alchemy;
import system.domain.IngredientCard;
import system.domain.Player;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.TheoryController;
import system.ui.frame.GameContentPane;
import system.ui.interfaces.PlayerMediator;


public class TheoryBoard extends JPanel {
    private PlayerMediator mediator;
    private TheoryController theoryController;
    private JButton ingredient1;
    private JButton ingredient2;
    private JButton ingredient3;
    private JButton ingredient4;
    private JButton ingredient5;
    private JButton ingredient6;
    private JButton ingredient7;
    private JButton ingredient8;
    private String ingredient;


    public TheoryBoard(){
        super();
        this.theoryController = new TheoryController();
        this.ingredient1 = createIngButton(GameBoardController.getInstance().getIngredients()[0]);
        add(ingredient1);
        this.ingredient2 = createIngButton(GameBoardController.getInstance().getIngredients()[1]);
        add(ingredient2);
        this.ingredient3 = createIngButton(GameBoardController.getInstance().getIngredients()[2]);
        add(ingredient3);
        this.ingredient4 = createIngButton(GameBoardController.getInstance().getIngredients()[3]);
        add(ingredient4);
        this.ingredient5 = createIngButton(GameBoardController.getInstance().getIngredients()[4]);
        add(ingredient5);
        this.ingredient6 = createIngButton(GameBoardController.getInstance().getIngredients()[5]);
        add(ingredient6);
        this.ingredient7 = createIngButton(GameBoardController.getInstance().getIngredients()[6]);
        add(ingredient7);
        this.ingredient8 = createIngButton(GameBoardController.getInstance().getIngredients()[7]);
        add(ingredient8);

        // Add an ActionListener to the JComboBox to handle item selection

    }

    public JButton createIngButton(String ingName) {
        JButton button = new JButton(ingName);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ingredient = ingName;
                }

            }
        );
        return button;
    }

    public String getIngredient (){
        return ingredient;
    }

    public void setIngredient(String ing){
        this.ingredient = ing;
    }


}
