package system.ui.panels;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import system.domain.controllers.TheoryController;
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
        back.setBounds(58, 643, 174, 41);
        add(back);

        //create alchemy buttons 1 to 8
        alchemy1 = new JButton(new ImageIcon(getClass().getResource("/resources/alchemy1.png")));
		alchemy1.addActionListener(new ActionListener() {
            //set alchemy to alchemy 1 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(1);
			}
		});
		alchemy1.setBounds(83, 25, 75, 75);

		add(alchemy1);

		alchemy2 = new JButton(new ImageIcon(getClass().getResource("/resources/alchemy2.png")));
		alchemy2.addActionListener(new ActionListener() {
            //set alchemy to alchemy 2 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(2);
			}
		});
		alchemy2.setBounds(238, 25, 75, 75);

		add(alchemy2);

		alchemy3 = new JButton(new ImageIcon(getClass().getResource("/resources/alchemy3.png")));
		alchemy3.addActionListener(new ActionListener() {
            //set alchemy to alchemy 3 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(3);
			}
		});
		alchemy3.setBounds(415, 25, 75, 75);

		add(alchemy3);

		alchemy4 = new JButton(new ImageIcon(getClass().getResource("/resources/alchemy4.png")));
		alchemy4.addActionListener(new ActionListener() {
            //set alchemy to alchemy 4 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(4);
			}
		});
		alchemy4.setBounds(587, 25, 75, 75);

		add(alchemy4);

		alchemy5 = new JButton(new ImageIcon(getClass().getResource("/resources/alchemy5.png")));
		alchemy5.addActionListener(new ActionListener() {
            //set alchemy to alchemy 5 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(5);
			}
		});
		alchemy5.setBounds(83, 137, 75, 75);

		add(alchemy5);

		alchemy6 = new JButton(new ImageIcon(getClass().getResource("/resources/alchemy6.png")));
		alchemy6.addActionListener(new ActionListener() {
            //set alchemy to alchemy 6 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(6);
			}
		});
		alchemy6.setBounds(238, 137, 75, 75);

		add(alchemy6);

		alchemy7 = new JButton(new ImageIcon(getClass().getResource("/resources/alchemy7.png")));
		alchemy7.addActionListener(new ActionListener() {
            //set alchemy to alchemy 7 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(7);
			}
		});
		alchemy7.setBounds(415, 137, 75, 75);

		add(alchemy7);

		alchemy8 = new JButton(new ImageIcon(getClass().getResource("/resources/alchemy8.png")));
		alchemy8.addActionListener(new ActionListener() {
            //set alchemy to alchemy 8 after clicking

			public void actionPerformed(ActionEvent e) {
                publicationAreaController.setAlchemy(8);
			}
		});
		alchemy8.setBounds(587, 137, 75, 75);
		add(alchemy8);

        //create theory board inside publication area


        this.theoryBoard = new TheoryBoard();
        theoryBoard.setBounds(58, 249, 629, 382);
        add(theoryBoard);

        //create submit button

        this.submitButton = new JButton("Publish");
        submitButton.setBounds(261, 643, 128, 41);
        addActiontoButton(submitButton);
        add(submitButton);

        this.debunkButton = new JButton("Debunk");
        debunkButton.setBounds(406, 643, 128, 41);
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
        endorseButton.setBounds(559, 643, 128, 41);
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
              //JOptionPane.showMessageDialog(this, "Theory published");
              //create theory book
              String pack = msg.split(":")[1];
              theoryBoard.createTheoryBook(pack.split(",")[0], pack.split(",")[1], pack.split(",")[2]);
        }
        else if (msg.contains("THEORY_DEBUNKED")) {
            //JOptionPane.showMessageDialog(this, "Theory debunked");
            String pack = msg.split(":")[1];
            theoryBoard.createTheoryBook(pack.split(",")[0], pack.split(",")[1], pack.split(",")[2]);
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
            //JOptionPane.showMessageDialog(this, "Theory endorsed");
            // add username of the player who endorsed the theory
            String pack = msg.split(":")[1];
            theoryBoard.addEndorsement(pack.split(",")[0], pack.split(",")[1]);
        }
        else if (msg.contains("CANNOT_PUBLISH_THEORY_IN_ROUND_1")) {
            JOptionPane.showMessageDialog(this, "Cannot publish theory in round 1, wait for round 2");
        }
        else if (msg.contains("CANNOT_ENDORSE_THEORY_UNTIL_FINAL_ROUND")) {
            JOptionPane.showMessageDialog(this, "Cannot endorse theory until final round");
        }
        else if (msg.contains("CANNOT_DEBUNK_THEORY_UNTIL_FINAL_ROUND")) {
            JOptionPane.showMessageDialog(this, "Cannot debunk theory until final round");
        }
    }
}