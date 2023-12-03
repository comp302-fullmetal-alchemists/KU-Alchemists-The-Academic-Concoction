package system.ui.panels;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import system.domain.Alchemy;
import system.domain.Player;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.TheoryController;
import system.ui.frame.GameContentPane;
import system.ui.interfaces.PlayerMediator;


public class TheoryBoard extends JPanel {
    private PlayerMediator mediator;
    private TheoryController theoryController;
    private JLabel ingredients1;
    private JComboBox<Alchemy> alchemy1;
    private JLabel ingredients2;
    private JComboBox<Alchemy> alchemy2;
    private JLabel ingredients3;
    private JComboBox<Alchemy> alchemy3;
    private JLabel ingredients4;
    private JComboBox<Alchemy> alchemy4;
    private JLabel ingredients5;
    private JComboBox<Alchemy> alchemy5;
    private JLabel ingredients6;
    private JComboBox<Alchemy> alchemy6;
    private JLabel ingredients7;
    private JComboBox<Alchemy> alchemy7;
    private JLabel ingredients8;
    private JComboBox<Alchemy> alchemy8;

    public JButton createNavButton(String nav, String text) {
        JButton button = new JButton(text);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameContentPane) TheoryBoard.this.getParent()).changeView(nav);
                }

            }
        );
        return button;
    }


    public TheoryBoard(){
        super();
        this.theoryController = new TheoryController();
        this.ingredients1 = new JLabel("Ingredient 1");
        this.alchemy1= new JComboBox<Alchemy>();
        this.ingredients2 = new JLabel("Ingredient 2");
        this.alchemy2= new JComboBox<Alchemy>();
        this.ingredients3 = new JLabel("Ingredient 3");
        this.alchemy3= new JComboBox<Alchemy>();
        this.ingredients4 = new JLabel("Ingredient 4");
        this.alchemy4= new JComboBox<Alchemy>();
        this.ingredients5 = new JLabel("Ingredient 5");
        this.alchemy5= new JComboBox<Alchemy>();
        this.ingredients6 = new JLabel("Ingredient 6");
        this.alchemy6= new JComboBox<Alchemy>();
        this.ingredients7 = new JLabel("Ingredient 7");
        this.alchemy7= new JComboBox<Alchemy>();
        this.ingredients8 = new JLabel("Ingredient 8");
        this.alchemy8= new JComboBox<Alchemy>();
        add(ingredients1);
        add(alchemy1);
        add(ingredients2);
        add(alchemy2);
        add(ingredients3);
        add(alchemy3);
        add(ingredients4);
        add(alchemy4);
        add(ingredients5);
        add(alchemy5);
        add(ingredients6);
        add(alchemy6);
        add(ingredients7);
        add(alchemy7);
        add(ingredients8);
        add(alchemy8);

        JButton submit = createNavButton("environment", "Submit");
        add(submit);

        // Add an ActionListener to the JComboBox to handle item selection

    }

}
