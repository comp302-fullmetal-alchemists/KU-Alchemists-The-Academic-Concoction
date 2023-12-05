package system.ui.panels;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;


import system.domain.Alchemy;
import system.domain.controllers.TheoryController;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;
import system.ui.frame.GameContentPane;

public class PublicationArea extends JPanel implements Observer{

    private JButton back;
    private TheoryBoard theoryBoard;
    private JButton Alchemy1;
    private JButton Alchemy2;
    private JButton Alchemy3;
    private JButton Alchemy4;
    private JButton Alchemy5;
    private JButton Alchemy6;
    private JButton Alchemy7;
    private JButton Alchemy8;
    private String alchemy = "";
    private TheoryController theoryController;
    private JButton submitButton;

    public PublicationArea() {
        super();
        this.back = createNavButton("village", "Back to the village");
        add(back);
        this.Alchemy1= createAlchemyButton("Alchemy 1");
        add(Alchemy1);
        this.Alchemy2= createAlchemyButton("Alchemy 2");
        add(Alchemy2);
        this.Alchemy3= createAlchemyButton("Alchemy 3");
        add(Alchemy3);
        this.Alchemy4= createAlchemyButton("Alchemy 4");
        add(Alchemy4);
        this.Alchemy5= createAlchemyButton("Alchemy 5");
        add(Alchemy5);
        this.Alchemy6= createAlchemyButton("Alchemy 6");
        add(Alchemy6);
        this.Alchemy7= createAlchemyButton("Alchemy 7");
        add(Alchemy7);
        this.Alchemy8= createAlchemyButton("Alchemy 8");
        add(Alchemy8);
        this.theoryBoard = new TheoryBoard();
        add(theoryBoard);
        this.submitButton = new JButton("Submit");
        addActiontoButton(submitButton);
        add(submitButton);
        this.theoryController = GameBoardController.getInstance().getTheoryController();
        theoryController.setObserver(this);
    }

    public JButton createNavButton(String nav, String text) {
        JButton button = new JButton(text);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameContentPane) PublicationArea.this.getParent()).changeView(nav);
                }

            }
        );
        return button;
    }

    public JButton createAlchemyButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    alchemy = text;
                }

            }
        );
        return button;
    }

    public void addActiontoButton(JButton button){
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (alchemy.isEmpty()) {
                        System.err.println("Please select an alchemy");
                        theoryBoard.setIngredient(null);
                    }
                    else if (theoryBoard.getIngredient() == null)
                    {
                        System.err.println("Please select an ingredient");
                        alchemy = "";
                    }
                    else {
                        System.out.println(String.format("Alchemy = %s, Ingredient = %s", alchemy, theoryBoard.getIngredient()));
                        //convert last character of alchemy to int
                        int index = Integer.parseInt(alchemy.substring(alchemy.length() - 1));
                        theoryController.publishTheory(GameBoardController.getInstance().getAlchemies()[index-1], theoryBoard.getIngredient());
                        System.out.println("Theory published");
                        alchemy = "";
                        theoryBoard.setIngredient(null);
                    }
                }

            }
        );
    }

    @Override
    public void update(String msg) {
       if (msg.contains("DUPLICATE_THEORY")) {
              JOptionPane.showMessageDialog(this, "Duplicate theory");
         }
         else if (msg.contains("NOT_ENOUGH_GOLD")) {
              JOptionPane.showMessageDialog(this, "Not enough gold");
         }
         else if (msg.contains("THEORY_PUBLISHED")) {
              JOptionPane.showMessageDialog(this, "Theory published");
       }
    }


    
}
