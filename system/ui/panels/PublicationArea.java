package system.ui.panels;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import system.domain.controllers.TheoryController;
import system.domain.Theory;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.PublicationAreaController;
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
    private PublicationAreaController publicationAreaController;
    private TheoryController theoryController;
    private JButton submitButton;
    private JButton debunkButton; //debunk theory use case button

    public PublicationArea() {
        super();
        this.publicationAreaController = GameBoardController.getInstance().getPublicationAreaController();
        publicationAreaController.setObserver(this);
        GameBoardController.getInstance().getTheoryController().setObserver(this);
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
                publicationAreaController.setAlchemy(1);
			}
		});
		alchemy1.setBounds(183, 25, 95, 39);

		add(alchemy1);

		alchemy2 = new JButton("Alchemy 2");
		alchemy2.addActionListener(new ActionListener() {
            //set alchemy to alchemy 2 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(2);
			}
		});
		alchemy2.setBounds(330, 25, 95, 39);

		add(alchemy2);

		alchemy3 = new JButton("Alchemy 3");
		alchemy3.addActionListener(new ActionListener() {
            //set alchemy to alchemy 3 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(3);
			}
		});
		alchemy3.setBounds(478, 25, 95, 39);

		add(alchemy3);

		alchemy4 = new JButton("Alchemy 4");
		alchemy4.addActionListener(new ActionListener() {
            //set alchemy to alchemy 4 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(4);
			}
		});
		alchemy4.setBounds(624, 25, 95, 39);

		add(alchemy4);

		alchemy5 = new JButton("Alchemy 5");
		alchemy5.addActionListener(new ActionListener() {
            //set alchemy to alchemy 5 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(5);
			}
		});
		alchemy5.setBounds(183, 87, 95, 39);

		add(alchemy5);

		alchemy6 = new JButton("Alchemy 6");
		alchemy6.addActionListener(new ActionListener() {
            //set alchemy to alchemy 6 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(6);
			}
		});
		alchemy6.setBounds(330, 87, 95, 39);

		add(alchemy6);

		alchemy7 = new JButton("Alchemy 7");
		alchemy7.addActionListener(new ActionListener() {
            //set alchemy to alchemy 7 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(7);
			}
		});
		alchemy7.setBounds(478, 87, 95, 39);

		add(alchemy7);

		alchemy8 = new JButton("Alchemy 8");
		alchemy8.addActionListener(new ActionListener() {
            //set alchemy to alchemy 8 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(8);
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
                    publicationAreaController.debunkTheory();
                }
            }
        );  
        add(debunkButton);

        // create endorse button
        JButton endorseButton = new JButton("Endorse");
        endorseButton.setBounds(525, 643, 128, 31);
        endorseButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    theoryController.endorseTheory();
                }
            }
        );
        add(endorseButton);


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
                    publicationAreaController.publishTheory();
                    clear();
                }
            }
        );
    }

    public void clear() {
        publicationAreaController.setAlchemy(0);
        theoryBoard.clear();
    }
    //update method for observer

    @Override
    public void update(String msg) {
       if (msg.contains("DUPLICATE_THEORY")) {
              JOptionPane.showMessageDialog(this, "Duplicate theory");
              
        }
        else if (msg.contains("NO_ALCHEMY_CHOSEN")) {
                JOptionPane.showMessageDialog(this, "No alchemy chosen!");
        }
        else if (msg.contains("NO_INGREDIENT_CHOSEN")) {
                JOptionPane.showMessageDialog(this, "No ingredient chosen!");
        }
         else if (msg.contains("NOT_ENOUGH_GOLD")) {
              JOptionPane.showMessageDialog(this, "Not enough gold");
        }
         else if (msg.contains("THEORY_PUBLISHED")) {
            JOptionPane.showMessageDialog(this, "Theory published");
            //create theory book
            String alchemyName = String.format("Alchemy %d", publicationAreaController.getAlchemyIndex());
            theoryBoard.createTheoryBook(alchemyName, GameBoardController.getInstance().getPlayer().getName());
        }
        else if (msg.contains("THEORY_DEBUNKED")) {
            JOptionPane.showMessageDialog(this, "Theory debunked");
            String alchemyName = String.format("Alchemy %d", publicationAreaController.getAlchemyIndex());
            theoryBoard.createTheoryBook(alchemyName, GameBoardController.getInstance().getPlayer().getName());
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

        else if (msg.contains("NO_THEORY_CHOSEN")){
            JOptionPane.showMessageDialog(this, "No theory chosen");
        }
        else if(msg.contains("THEORY_ALREADY_ENDORSED")){
            JOptionPane.showMessageDialog(this, "Theory already endorsed");
        }
        else if (msg.contains("CANNOT_ENDORSE_YOUR_OWN_THEORY")){
            JOptionPane.showMessageDialog(this, "Cannot endorse your own theory");
        }
        else if (msg.contains("THEORY_ENDORSED")){
            JOptionPane.showMessageDialog(this, "Theory endorsed");
            // add username of the player who endorsed the theory
            theoryBoard.addEndorsement(GameBoardController.getInstance().getPlayer().getName());
        }
        else if (msg.contains("Server updated:publish")) {
            theoryBoard.serverCreateTheoryBook( msg.split(",")[1],msg.split(",")[2], msg.split(",")[3]);
        }
        else if (msg.contains("Server updated: debunk")) {
            theoryBoard.serverCreateTheoryBook( msg.split(",")[1],msg.split(",")[2], msg.split(",")[3]);
        }
        else if (msg.contains("Server updated: endorse")) {
            theoryBoard.setTheoryBook(msg.split(",")[2]);
            theoryBoard.addEndorsement(msg.split(",")[3]);
        }
    }

}