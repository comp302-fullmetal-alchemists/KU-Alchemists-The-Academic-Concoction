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
    private JButton debunkButton;

    public PublicationArea() {
        super();
        setBackground(new Color(58, 77, 108));
        setLayout(null);

        this.back = createNavButton("village", "Back to the village");
        back.setBounds(31, 25, 99, 101);
        add(back);

        alchemy1 = new JButton("Alchemy 1");
		alchemy1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 1";
			}
		});
		alchemy1.setBounds(193, 25, 85, 39);
		add(alchemy1);

		alchemy2 = new JButton("Alchemy 2");
		alchemy2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 2";
			}
		});
		alchemy2.setBounds(340, 25, 85, 39);
		add(alchemy2);

		alchemy3 = new JButton("Alchemy 3");
		alchemy3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 3";
			}
		});
		alchemy3.setBounds(488, 25, 85, 39);
		add(alchemy3);

		alchemy4 = new JButton("Alchemy 4");
		alchemy4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 4";
			}
		});
		alchemy4.setBounds(634, 25, 85, 39);
		add(alchemy4);

		alchemy5 = new JButton("Alchemy 5");
		alchemy5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 5";
			}
		});
		alchemy5.setBounds(193, 87, 85, 39);
		add(alchemy5);

		alchemy6 = new JButton("Alchemy 6");
		alchemy6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 6";
			}
		});
		alchemy6.setBounds(340, 87, 85, 39);
		add(alchemy6);

		alchemy7 = new JButton("Alchemy 7");
		alchemy7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 7";
			}
		});
		alchemy7.setBounds(488, 87, 85, 39);
		add(alchemy7);

		alchemy8 = new JButton("Alchemy 8");
		alchemy8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                alchemy = "Alchemy 8";
			}
		});
		alchemy8.setBounds(634, 87, 85, 39);
		add(alchemy8);

        this.theoryBoard = new TheoryBoard();
        theoryBoard.setBounds(31, 136, 688, 487);
        add(theoryBoard);

        this.submitButton = new JButton("Submit");
        submitButton.setBounds(297, 643, 128, 31);
        addActiontoButton(submitButton);
        add(submitButton);

        this.debunkButton = new JButton("Debunk");
        debunkButton.setBounds(297, 685, 128, 31);
        debunkButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //select an ingredient that has a theory and an alchemy
                    //if no ingredient is selected, show error message
                    if (theoryBoard.getIngredient() == null)
                    {
                        System.err.println("Please select an ingredient");
                        
                    }
                    else if (alchemy.isEmpty()){
                        System.err.println("Please select an alchemy");
                    }
                    else {
                        for (Theory i : theoryController.getTheories()) {
                            if (i.getIngredient().equals(theoryBoard.getIngredient())) {
                                int index = Integer.parseInt(alchemy.substring(alchemy.length() - 1));
                                theoryController.debunkTheory(GameBoardController.getInstance().getAlchemies()[index-1], i, GameBoardController.getInstance().getCurrentPlayer());

                                return;
                            }
                        }
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
                        theoryBoard.createTheoryBook(alchemy, GameBoardController.getInstance().getCurrentPlayer().getName());
                        System.out.println("Theory book created by " + GameBoardController.getInstance().getCurrentPlayer().getName() + " with " + theoryBoard.getIngredient() + " and " + alchemy);
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