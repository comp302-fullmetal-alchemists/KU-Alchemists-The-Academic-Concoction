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


public class TheoryBoard extends JPanel {
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
        setLayout(null);
        this.theoryController = new TheoryController();
        /* 
        this.ingredient1 = createIngButton(GameBoardController.getInstance().getIngredients()[0]);
        ingredient1.setBounds(22, 194, 103, 21);
        add(ingredient1);
        this.ingredient2 = createIngButton(GameBoardController.getInstance().getIngredients()[1]);
        ingredient2.setBounds(163, 194, 103, 21);
        add(ingredient2);
        this.ingredient3 = createIngButton(GameBoardController.getInstance().getIngredients()[2]);
        ingredient3.setBounds(316, 194, 103, 21);
        add(ingredient3);
        this.ingredient4 = createIngButton(GameBoardController.getInstance().getIngredients()[3]);
        ingredient4.setBounds(459, 194, 103, 21);
        add(ingredient4);
        this.ingredient5 = createIngButton(GameBoardController.getInstance().getIngredients()[4]);
        ingredient5.setBounds(22, 400, 103, 21);
        add(ingredient5);
        this.ingredient6 = createIngButton(GameBoardController.getInstance().getIngredients()[5]);
        ingredient6.setBounds(163, 400, 103, 21);
        add(ingredient6);
        this.ingredient7 = createIngButton(GameBoardController.getInstance().getIngredients()[6]);
        ingredient7.setBounds(316, 400, 103, 21);
        add(ingredient7);
        this.ingredient8 = createIngButton(GameBoardController.getInstance().getIngredients()[7]);
        ingredient8.setBounds(459, 400, 103, 21);
        add(ingredient8);
        */
                
        /*     
        JLabel theoryBook1 = new JLabel("");
		theoryBook1.setBounds(22, 53, 100, 118);
		add(theoryBook1);

		JLabel theoryBook2 = new JLabel("");
		theoryBook2.setBounds(163, 53, 98, 118);
		add(theoryBook2);

		JLabel theoryBook3 = new JLabel("");
		theoryBook3.setBounds(316, 53, 103, 118);
		add(theoryBook3);

		JLabel theoryBook4 = new JLabel("");
		theoryBook4.setBounds(459, 53, 103, 118);
		add(theoryBook4);

		JLabel theoryBook5 = new JLabel("");
		theoryBook5.setBounds(37, 259, 103, 118);
		add(theoryBook5);

		JLabel theoryBook6 = new JLabel("");
		theoryBook6.setBounds(176, 259, 103, 118);
		add(theoryBook6);

		JLabel theoryBook7 = new JLabel("");
		theoryBook7.setBounds(322, 259, 103, 118);
		add(theoryBook7);

		JLabel theoryBook8 = new JLabel("");
		theoryBook8.setBounds(465, 259, 103, 118);
		add(theoryBook8);
        */ 
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

    public JLabel createTheoryBook(String ingName, String alchemy, String player) {
        JLabel label = new JLabel("");
        label.setText(player + " has theorized " + ingName + " with " + alchemy);
        label.setBounds(22, 53, 100, 118);
        add(label);
        return label;
    }


    public String getIngredient (){
        return ingredient;
    }

    public void setIngredient (String ingredient){
        this.ingredient = ingredient;
    }
}

