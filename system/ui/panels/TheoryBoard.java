package system.ui.panels;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import system.domain.controllers.GameBoardController;
import system.domain.controllers.TheoryController;

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
	private HashMap<String, JLabel> bookMap = new HashMap<String, JLabel>();

    public TheoryBoard(){
        super();
        setBackground(new Color(58, 77, 108));
        setLayout(null);
        this.theoryController = GameBoardController.getInstance().getTheoryController();
        //create theory books
        theoryBook1 = new JLabel("<html><br>Solaris Root</html>"); //ingredient name of the theory

		theoryBook1.setVerticalAlignment(SwingConstants.TOP);
		theoryBook1.setBackground(new Color(204, 204, 255));
		theoryBook1.setOpaque(true);
		theoryBook1.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook1.setBounds(62, 20, 90, 150);
		// set a photo for the theory book
		theoryBook1.setIcon(new ImageIcon(getClass().getResource("/resources/theory.png")));
		//theoryBook1.setBounds(32, 53, 150, 140);
        theoryBook1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                theoryController.setIngredient("Solaris Root"); //set the ingredient to the ingredient name of the theory
			}
		});
		bookMap.put("Solaris Root", theoryBook1);
		add(theoryBook1);

		theoryBook2 = new JLabel("<html><br>Bat Wing</html>"); //ingredient name of the theory

		theoryBook2.setVerticalAlignment(SwingConstants.TOP);
		theoryBook2.setOpaque(true);
		theoryBook2.setBackground(new Color(204, 204, 255));
		theoryBook2.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook2.setBounds(194, 20, 90, 150);
        theoryBook2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                theoryController.setIngredient("Bat Wing"); //set the ingredient to the ingredient name of the theory
			}
		});
		bookMap.put("Bat Wing", theoryBook2);
		add(theoryBook2);

		theoryBook3 = new JLabel("<html><br>Toad Stool</html>"); //ingredient name of the theory

		theoryBook3.setVerticalAlignment(SwingConstants.TOP);
		theoryBook3.setOpaque(true);
		theoryBook3.setBackground(new Color(204, 204, 255));
		theoryBook3.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook3.setBounds(326, 20, 90, 150);
        theoryBook3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Toad Stool"); //set the ingredient to the ingredient name of the theory
			}
		});
		bookMap.put("Toad Stool", theoryBook3);
		add(theoryBook3);

		theoryBook4 = new JLabel("<html><br>Owl Feather</html>"); //ingredient name of the theory

		theoryBook4.setVerticalAlignment(SwingConstants.TOP);
		theoryBook4.setBackground(new Color(204, 204, 255));
		theoryBook4.setOpaque(true);
		theoryBook4.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook4.setBounds(458, 20, 90, 150);
        theoryBook4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Owl Feather"); //set the ingredient to the ingredient name of the theory
			}
		});
		bookMap.put("Owl Feather", theoryBook4);
		add(theoryBook4);

		theoryBook5 = new JLabel("<html><br>Snake Venom</html>"); //ingredient name of the theory

		theoryBook5.setVerticalAlignment(SwingConstants.TOP);
		theoryBook5.setBackground(new Color(204, 204, 255));
		theoryBook5.setOpaque(true);
		theoryBook5.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook5.setBounds(62, 207, 90, 150);
        theoryBook5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Snake Venom"); //set the ingredient to the ingredient name of the theory
			}
		});
		bookMap.put("Snake Venom", theoryBook5);
		add(theoryBook5);

		theoryBook6 = new JLabel("<html><br>Rat Tail</html>"); //ingredient name of the theory
		theoryBook6.setVerticalAlignment(SwingConstants.TOP);
		theoryBook6.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook6.setBackground(new Color(204, 204, 255));
		theoryBook6.setOpaque(true);
		theoryBook6.setBounds(194, 207, 90, 150);
        theoryBook6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Rat Tail"); //set the ingredient to the ingredient name of the theory
			}
		});
		bookMap.put("Rat Tail", theoryBook6);
		add(theoryBook6);

		theoryBook7 = new JLabel("<html><br>Spider Web</html>"); //ingredient name of the theory

		theoryBook7.setVerticalAlignment(SwingConstants.TOP);
		theoryBook7.setBackground(new Color(204, 204, 255));
		theoryBook7.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook7.setOpaque(true);
		theoryBook7.setBounds(326, 207, 90, 150);
        theoryBook7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Spider Web"); //set the ingredient to the ingredient name of the theory

			}
		});
		bookMap.put("Spider Web", theoryBook7);
		add(theoryBook7);

		theoryBook8 = new JLabel("<html><br>Newt Eye</html>"); //ingredient name of the theory

		theoryBook8.setVerticalAlignment(SwingConstants.TOP);
		theoryBook8.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook8.setBackground(new Color(204, 204, 255));
		theoryBook8.setOpaque(true);
		theoryBook8.setBounds(458, 207, 90, 150);
        theoryBook8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Newt Eye"); //set the ingredient to the ingredient name of the theory

			}
		});
		bookMap.put("Newt Eye", theoryBook8);
		add(theoryBook8);
        // Add an ActionListener to the JComboBox to handle item selection

    }

    //create theory book prints the theory to the theory book of the ingredient
    public void createTheoryBook(String alchemy, String ingredient, String player) {
		theoryBook = bookMap.get(ingredient);
        theoryBook.setText("<html><br>"+ ingredient + "<br><br>" + alchemy + "<br><br>" + player + "</html>");
        
    }

	// add username of the player who endorsed the theory
	public void addEndorsement(String ingredient, String player) {
		theoryBook = bookMap.get(ingredient);
		theoryBook.setText(theoryBook.getText().replace("</html>", "<br>" + player + "</html>"));
	}

	public void clear() {
		theoryController.setIngredient(null);
		theoryBook = null;
	}
	
}

