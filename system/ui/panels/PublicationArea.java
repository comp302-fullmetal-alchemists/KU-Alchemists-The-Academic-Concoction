package system.ui.panels;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;

import system.domain.Alchemy;
import system.domain.Theory;
import system.domain.controllers.TheoryController;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;
import system.ui.frame.GameContentPane;

public class PublicationArea extends JPanel implements Observer{

    private JButton back;
    private TheoryBoard theoryBoard;
    private JButton alchemy1;
    private JButton alchemy2;
    private JButton alchemy3;
    private JButton alchemy4;
    private JButton alchemy5;
    private JButton alchemy6;
    private JButton alchemy7;
    private JButton alchemy8;
    private String alchemy = "";
    private TheoryController theoryController;
    private JButton submitButton;
    private JButton debunkButton; //debunk theory use case button

    public PublicationArea() {
        super();
        setLayout(null);
        setBackground(new Color(58, 77, 108));
        //create back button
        this.back = createNavButton("village", "Back to the village");
        back.setBounds(31, 25, 99, 101);
        add(back);

        //create alchemy buttons 1 to 8
        alchemy1 = new JButton("Alchemy 1");
		alchemy1.addActionListener(new ActionListener() {
            //set alchemy to alchemy 1 after clicking

			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 1";
			}
		});
		alchemy1.setBounds(183, 25, 95, 39);

		add(alchemy1);

		alchemy2 = new JButton("Alchemy 2");
		alchemy2.addActionListener(new ActionListener() {
            //set alchemy to alchemy 2 after clicking

			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 2";
			}
		});
		alchemy2.setBounds(330, 25, 95, 39);

		add(alchemy2);

		alchemy3 = new JButton("Alchemy 3");
		alchemy3.addActionListener(new ActionListener() {
            //set alchemy to alchemy 3 after clicking

			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 3";
			}
		});
		alchemy3.setBounds(478, 25, 95, 39);

		add(alchemy3);

		alchemy4 = new JButton("Alchemy 4");
		alchemy4.addActionListener(new ActionListener() {
            //set alchemy to alchemy 4 after clicking

			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 4";
			}
		});
		alchemy4.setBounds(624, 25, 95, 39);

		add(alchemy4);

		alchemy5 = new JButton("Alchemy 5");
		alchemy5.addActionListener(new ActionListener() {
            //set alchemy to alchemy 5 after clicking

			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 5";
			}
		});
		alchemy5.setBounds(183, 87, 95, 39);

		add(alchemy5);

		alchemy6 = new JButton("Alchemy 6");
		alchemy6.addActionListener(new ActionListener() {
            //set alchemy to alchemy 6 after clicking

			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 6";
			}
		});
		alchemy6.setBounds(330, 87, 95, 39);

		add(alchemy6);

		alchemy7 = new JButton("Alchemy 7");
		alchemy7.addActionListener(new ActionListener() {
            //set alchemy to alchemy 7 after clicking

			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 7";
			}
		});
		alchemy7.setBounds(478, 87, 95, 39);

		add(alchemy7);

		alchemy8 = new JButton("Alchemy 8");
		alchemy8.addActionListener(new ActionListener() {
            //set alchemy to alchemy 8 after clicking

			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 8";
			}
		});
		alchemy8.setBounds(624, 87, 95, 39);
		add(alchemy8);

        //create theory board inside publication area


        this.theoryBoard = new TheoryBoard();
        theoryBoard.setBounds(31, 136, 688, 487);
        add(theoryBoard);

        //create submit button

        this.submitButton = new JButton("Submit");
        submitButton.setBounds(225, 643, 128, 31);
        addActiontoButton(submitButton);
        add(submitButton);

        this.debunkButton = new JButton("Debunk");
        debunkButton.setBounds(375, 643, 128, 31);
        debunkButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //if ingredient and alchemy is empty, do not let debunk
                    if (theoryBoard.getIngredient() == null)
                    {
                        System.err.println("Please select an ingredient"); 
                        
                    }
                    else if (alchemy.isEmpty()){
                        System.err.println("Please select an alchemy");
                    }
                    else {
                        for (Theory i : theoryController.getTheories()) {
                            //after finding the theory, debunk it if a theory is not found then do not debunk
                            if (i.getIngredient().equals(theoryBoard.getIngredient())) {
                                int index = Integer.parseInt(alchemy.substring(alchemy.length() - 1));
                                //debunk theory use case
                                theoryController.debunkTheory(GameBoardController.getInstance().getAlchemies()[index-1], i, GameBoardController.getInstance().getCurrentPlayer());
                                if(i.isDebunked()) {
                                    theoryBoard.createTheoryBook(alchemy, GameBoardController.getInstance().getCurrentPlayer().getName());
                                }
                                return;
                            }
                        }
                        //after looping through all theories and not finding the theory, make null alchemy and ingredient
                        alchemy = "";
                        theoryBoard.setIngredient(null);
                    }

            }
        }
        );  
        add(debunkButton);

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

    public void addActiontoButton(JButton button){
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //check if alchemy and ingredient is selected
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
                        //alchemy and ingredient is selected properly
                        System.out.println(String.format("Alchemy = %s, Ingredient = %s", alchemy, theoryBoard.getIngredient()));
                        //convert last character of alchemy to int to get the index of alchemy
                        int index = Integer.parseInt(alchemy.substring(alchemy.length() - 1));
                        //call publish theory method from theory controller
                        theoryController.publishTheory(GameBoardController.getInstance().getAlchemies()[index-1], theoryBoard.getIngredient());
                        System.out.println("Theory published");
                        //reset alchemy and ingredient

                        alchemy = "";
                        theoryBoard.setIngredient(null);
                    }
                }

            }
        );
    }

    //update method for observer

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
              //create theory book
              theoryBoard.createTheoryBook(alchemy, GameBoardController.getInstance().getCurrentPlayer().getName());
        }
        else if (msg.contains("THEORY_DEBUNKED")) {
            JOptionPane.showMessageDialog(this, "Theory debunked");
        }
        else if (msg.contains("THEORY_NOT_DEBUNKED")) {
            JOptionPane.showMessageDialog(this, "Theory not debunked");
        }
        else if (msg.contains("CANNOT_DEBUNK_YOUR_OWN_THEORY")) {
            JOptionPane.showMessageDialog(this, "Cannot debunk your own theory");
        }
        else if (msg.contains("CANNOT_DEBUNK_SAME_ALCHEMY")) {
            JOptionPane.showMessageDialog(this, "Cannot debunk same alchemy");
        }
    }
}