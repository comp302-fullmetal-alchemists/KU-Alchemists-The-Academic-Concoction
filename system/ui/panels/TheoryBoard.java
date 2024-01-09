package system.ui.panels;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import system.domain.Alchemy;
import system.domain.IngredientCard;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.Player;
import system.domain.controllers.TheoryController;
import system.ui.frame.GameContentPane;


public class TheoryBoard extends JPanel{
    private TheoryController theoryController;
    private JLabel theoryBook1;
    private JLabel theoryBook2;
    private JLabel theoryBook3;
    private JLabel theoryBook4;
    private JLabel theoryBook5;
    private JLabel theoryBook6;
    private JLabel theoryBook7;
    private JLabel theoryBook8;
    private JLabel theoryBook;


    public TheoryBoard(){
        super();
        setLayout(null);
        this.theoryController = GameBoardController.getInstance().getTheoryController();
        //create theory books
        theoryBook1 = new JLabel("<html><br>Solaris Root</html>"); //ingredient name of the theory

		theoryBook1.setVerticalAlignment(SwingConstants.TOP);
		theoryBook1.setBackground(new Color(204, 204, 255));
		theoryBook1.setOpaque(true);
		theoryBook1.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook1.setBounds(32, 53, 100, 118);
        theoryBook1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                theoryController.setIngredient("Solaris Root"); //set the ingredient to the ingredient name of the theory
                theoryBook = theoryBook1; //set the theory book to the theory book of the ingredient

			}
		});
		add(theoryBook1);

		theoryBook2 = new JLabel("<html><br>Bat Wing</html>"); //ingredient name of the theory

		theoryBook2.setVerticalAlignment(SwingConstants.TOP);
		theoryBook2.setOpaque(true);
		theoryBook2.setBackground(new Color(204, 204, 255));
		theoryBook2.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook2.setBounds(200, 53, 100, 118);
        theoryBook2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                theoryController.setIngredient("Bat Wing"); //set the ingredient to the ingredient name of the theory
                theoryBook = theoryBook2; //set the theory book to the theory book of the ingredient

			}
		});
		add(theoryBook2);

		theoryBook3 = new JLabel("<html><br>Toad Stool</html>"); //ingredient name of the theory

		theoryBook3.setVerticalAlignment(SwingConstants.TOP);
		theoryBook3.setOpaque(true);
		theoryBook3.setBackground(new Color(204, 204, 255));
		theoryBook3.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook3.setBounds(361, 53, 100, 118);
        theoryBook3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Toad Stool"); //set the ingredient to the ingredient name of the theory
                theoryBook = theoryBook3; //set the theory book to the theory book of the ingredient

			}
		});
		add(theoryBook3);

		theoryBook4 = new JLabel("<html><br>Owl Feather</html>"); //ingredient name of the theory

		theoryBook4.setVerticalAlignment(SwingConstants.TOP);
		theoryBook4.setBackground(new Color(204, 204, 255));
		theoryBook4.setOpaque(true);
		theoryBook4.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook4.setBounds(538, 53, 100, 118);
        theoryBook4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Owl Feather"); //set the ingredient to the ingredient name of the theory
                theoryBook = theoryBook4;   //set the theory book to the theory book of the ingredient

			}
		});
		add(theoryBook4);

		theoryBook5 = new JLabel("<html><br>Snake Venom</html>"); //ingredient name of the theory

		theoryBook5.setVerticalAlignment(SwingConstants.TOP);
		theoryBook5.setBackground(new Color(204, 204, 255));
		theoryBook5.setOpaque(true);
		theoryBook5.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook5.setBounds(32, 259, 100, 118);
        theoryBook5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Snake Venom"); //set the ingredient to the ingredient name of the theory
                theoryBook = theoryBook5; //set the theory book to the theory book of the ingredient
			}
		});
		add(theoryBook5);

		theoryBook6 = new JLabel("<html><br>Rat Tail</html>"); //ingredient name of the theory
		theoryBook6.setVerticalAlignment(SwingConstants.TOP);
		theoryBook6.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook6.setBackground(new Color(204, 204, 255));
		theoryBook6.setOpaque(true);
		theoryBook6.setBounds(200, 259, 100, 118);
        theoryBook6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Rat Tail"); //set the ingredient to the ingredient name of the theory
                theoryBook = theoryBook6; //set the theory book to the theory book of the ingredient
			}
		});
		add(theoryBook6);

		theoryBook7 = new JLabel("<html><br>Spider Web</html>"); //ingredient name of the theory

		theoryBook7.setVerticalAlignment(SwingConstants.TOP);
		theoryBook7.setBackground(new Color(204, 204, 255));
		theoryBook7.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook7.setOpaque(true);
		theoryBook7.setBounds(361, 259, 100, 118);
        theoryBook7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Spider Web"); //set the ingredient to the ingredient name of the theory
                theoryBook = theoryBook7; //set the theory book to the theory book of the ingredient

			}
		});
		add(theoryBook7);

		theoryBook8 = new JLabel("<html><br>Newt Eye</html>"); //ingredient name of the theory

		theoryBook8.setVerticalAlignment(SwingConstants.TOP);
		theoryBook8.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook8.setBackground(new Color(204, 204, 255));
		theoryBook8.setOpaque(true);
		theoryBook8.setBounds(535, 259, 100, 118);
        theoryBook8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Newt Eye"); //set the ingredient to the ingredient name of the theory
                theoryBook = theoryBook8; //set the theory book to the theory book of the ingredient

			}
		});
		add(theoryBook8);
        // Add an ActionListener to the JComboBox to handle item selection

    }

    //create theory book prints the theory to the theory book of the ingredient
    public void createTheoryBook(String alchemy, String player) {
        theoryBook.setText("<html><br>"+ theoryController.getIngredient() + "<br><br>" + alchemy + "<br><br>" + player + "</html>");
        
    }

	public void clear() {
		theoryController.setIngredient(null);
		theoryBook = null;
	}
	
}

