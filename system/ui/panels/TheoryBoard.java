package system.ui.panels;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import system.domain.Alchemy;
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
	private JLabel theoryBookText;
	private HashMap<String, JLabel> bookMap = new HashMap<String, JLabel>();
	private HashMap<String, JLabel> bookTextMap = new HashMap<String, JLabel>();

    public TheoryBoard(){
        super();
        setBackground(new Color(58, 77, 108));
        setLayout(null);
        this.theoryController = GameBoardController.getInstance().getTheoryController();
        //create theory books
        theoryBook1 = new JLabel(); //ingredient name of the theory
		theoryBook1.setVerticalAlignment(SwingConstants.TOP);
		//theoryBook1.setBackground(new Color(204, 204, 255));
		theoryBook1.setBackground(new Color(58, 77, 108));
		theoryBook1.setOpaque(true);
		theoryBook1.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook1.setBounds(10, 20, 150, 150);
		// set a photo for the theory book
		theoryBook1.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/theorybook.png")).getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
		((ImageIcon) theoryBook1.getIcon()).getAccessibleContext().setAccessibleDescription("Solaris Root");
        theoryBook1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                theoryController.setIngredient("Solaris Root"); //set the ingredient to the ingredient name of the theory
			}
		});
		
		JLabel theoryBook1Text = new JLabel("<html><br>Solaris <br> Root</html>");
		theoryBook1Text.setVerticalAlignment(SwingConstants.TOP);
		theoryBook1Text.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook1Text.setBounds(75, 0, 75, 150);
		theoryBook1.add(theoryBook1Text);
		bookTextMap.put("Solaris Root", theoryBook1Text);
		
		bookMap.put("Solaris Root", theoryBook1);
		add(theoryBook1);

		//theoryBook2 = new JLabel("<html><br>Bat Wing</html>"); //ingredient name of the theory
		theoryBook2 = new JLabel(); //ingredient name of the theory		
		theoryBook2.setVerticalAlignment(SwingConstants.TOP);
		theoryBook2.setOpaque(true);
		theoryBook2.setBackground(new Color(58, 77, 108));
		theoryBook2.setHorizontalAlignment(SwingConstants.CENTER);
		//theoryBook2.setBounds(194, 20, 90, 150);
		theoryBook2.setBounds(170, 20, 150, 150);
		theoryBook2.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/theorybook.png")).getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
        theoryBook2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                theoryController.setIngredient("Bat Wing"); //set the ingredient to the ingredient name of the theory
			}
		});

		JLabel theoryBook2Text = new JLabel("<html><br>Bat<br>Wing</html>");
		theoryBook2Text.setBounds(75, 0, 75, 150);
		theoryBook2Text.setVerticalAlignment(SwingConstants.TOP);
		theoryBook2Text.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook2.add(theoryBook2Text);
		bookTextMap.put("Bat Wing", theoryBook2Text);
		bookMap.put("Bat Wing", theoryBook2);
		add(theoryBook2);

		theoryBook3 = new JLabel(); //ingredient name of the theory

		theoryBook3.setVerticalAlignment(SwingConstants.TOP);
		theoryBook3.setOpaque(true);
		theoryBook3.setBackground(new Color(58, 77, 108));
		theoryBook3.setHorizontalAlignment(SwingConstants.CENTER);
		//theoryBook3.setBounds(326, 20, 90, 150);
		theoryBook3.setBounds(330, 20, 150, 150);

		theoryBook3.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/theorybook.png")).getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));

        theoryBook3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Toad Stool"); //set the ingredient to the ingredient name of the theory
			}
		});
		JLabel theoryBook3Text = new JLabel("<html><br>Toad<br>Stool</html>");
		theoryBook3Text.setBounds(75, 0, 75, 150);
		theoryBook3Text.setVerticalAlignment(SwingConstants.TOP);
		theoryBook3Text.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook3.add(theoryBook3Text);
		bookTextMap.put("Toad Stool", theoryBook3Text);
		bookMap.put("Toad Stool", theoryBook3);
		add(theoryBook3);

		theoryBook4 = new JLabel(); //ingredient name of the theory

		theoryBook4.setVerticalAlignment(SwingConstants.TOP);
		theoryBook4.setBackground(new Color(58, 77, 108));
		theoryBook4.setOpaque(true);
		theoryBook4.setHorizontalAlignment(SwingConstants.CENTER);
		//theoryBook4.setBounds(458, 20, 90, 150);
		theoryBook4.setBounds(490, 20, 150, 150);

		theoryBook4.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/theorybook.png")).getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));

        theoryBook4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Owl Feather"); //set the ingredient to the ingredient name of the theory
			}
		});
		JLabel theoryBook4Text = new JLabel("<html><br>Owl<br>Feather</html>");
		theoryBook4Text.setBounds(75, 0, 75, 150);
		theoryBook4Text.setVerticalAlignment(SwingConstants.TOP);
		theoryBook4Text.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook4.add(theoryBook4Text);
		bookTextMap.put("Owl Feather", theoryBook4Text);
		bookMap.put("Owl Feather", theoryBook4);
		add(theoryBook4);

		//theory book 5 is created
		theoryBook5 = new JLabel(); //ingredient name of the theory

		theoryBook5.setVerticalAlignment(SwingConstants.TOP);
		theoryBook5.setBackground(new Color(58, 77, 108));
		theoryBook5.setOpaque(true);
		theoryBook5.setHorizontalAlignment(SwingConstants.CENTER);
		//theoryBook5.setBounds(62, 207, 90, 150);
		theoryBook5.setBounds(10, 207, 150, 150);
		theoryBook5.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/theorybook.png")).getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
        theoryBook5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Snake Venom"); //set the ingredient to the ingredient name of the theory
			}
		});
		JLabel theoryBook5Text = new JLabel("<html><br>Snake<br>Venom</html>");
		theoryBook5Text.setBounds(75, 0, 75, 150);
		theoryBook5Text.setVerticalAlignment(SwingConstants.TOP);
		theoryBook5Text.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook5.add(theoryBook5Text);
		bookTextMap.put("Snake Venom", theoryBook5Text);
		bookMap.put("Snake Venom", theoryBook5);
		add(theoryBook5);

		//theory book 6 is created
		theoryBook6 = new JLabel(); //ingredient name of the theory
		theoryBook6.setVerticalAlignment(SwingConstants.TOP);
		theoryBook6.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook6.setBackground(new Color(58, 77, 108));
		theoryBook6.setOpaque(true);
		//theoryBook6.setBounds(194, 207, 90, 150);
		theoryBook6.setBounds(170, 207, 150, 150);
		theoryBook6.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/theorybook.png")).getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
        theoryBook6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Rat Tail"); //set the ingredient to the ingredient name of the theory
			}
		});
		JLabel theoryBook6Text = new JLabel("<html><br>Rat<br>Tail</html>");
		theoryBook6Text.setBounds(75, 0, 75, 150);
		theoryBook6Text.setVerticalAlignment(SwingConstants.TOP);
		theoryBook6Text.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook6.add(theoryBook6Text);
		bookTextMap.put("Rat Tail", theoryBook6Text);
		bookMap.put("Rat Tail", theoryBook6);
		add(theoryBook6);

		//theory book 7 is created
		theoryBook7 = new JLabel(); //ingredient name of the theory

		theoryBook7.setVerticalAlignment(SwingConstants.TOP);
		theoryBook7.setBackground(new Color(58, 77, 108));
		theoryBook7.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook7.setOpaque(true);
		//theoryBook7.setBounds(326, 207, 90, 150);
		theoryBook7.setBounds(330, 207, 150, 150);
		theoryBook7.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/theorybook.png")).getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
        theoryBook7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Spider Web"); //set the ingredient to the ingredient name of the theory

			}
		});
		JLabel theoryBook7Text = new JLabel("<html><br>Spider<br>Web</html>");
		theoryBook7Text.setBounds(75, 0, 75, 150);
		theoryBook7Text.setVerticalAlignment(SwingConstants.TOP);
		theoryBook7Text.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook7.add(theoryBook7Text);
		bookTextMap.put("Spider Web", theoryBook7Text);
		bookMap.put("Spider Web", theoryBook7);
		add(theoryBook7);

		//theory book 8 is created
		theoryBook8 = new JLabel(); //ingredient name of the theory
		theoryBook8.setVerticalAlignment(SwingConstants.TOP);
		theoryBook8.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook8.setBackground(new Color(58, 77, 108));
		theoryBook8.setOpaque(true);
		//theoryBook8.setBounds(458, 207, 90, 150);
		theoryBook8.setBounds(490, 207, 150, 150);
		theoryBook8.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/theorybook.png")).getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));
        theoryBook8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theoryController.setIngredient("Newt Eye"); //set the ingredient to the ingredient name of the theory

			}
		});
		JLabel theoryBook8Text = new JLabel("<html><br>Newt<br>Eye</html>");
		theoryBook8Text.setBounds(75, 0, 75, 150);
		theoryBook8Text.setVerticalAlignment(SwingConstants.TOP);
		theoryBook8Text.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook8.add(theoryBook8Text);
		bookTextMap.put("Newt Eye", theoryBook8Text);
		bookMap.put("Newt Eye", theoryBook8);
		add(theoryBook8);
        // Add an ActionListener to the JComboBox to handle item selection

    }

    //create theory book prints the theory to the theory book of the ingredient
    public void createTheoryBook(String alchemy, String ingredient, String player) {
		theoryBookText = bookTextMap.get(ingredient);
		// add alchemy image to the theory book
		ImageIcon alchemyIcon = new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/alchemy" + Alchemy.findAlchemyIndex(alchemy) + ".png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
		JLabel alchemyPic = new JLabel(alchemyIcon);
		alchemyPic.setBounds(90, 55, 40, 40);
		alchemyPic.setVerticalAlignment(SwingConstants.CENTER);
		alchemyPic.setHorizontalAlignment(SwingConstants.CENTER);
		bookMap.get(ingredient).add(alchemyPic);
		theoryBookText.setText(theoryBookText.getText().replace("</html>", "<br><br><br><br>" + player + "</html>"));
        
    }

	// add username of the player who endorsed the theory
	public void addEndorsement(String ingredient, String player) {
		theoryBookText = bookTextMap.get(ingredient);
		theoryBookText.setText(theoryBookText.getText().replace("</html>", "<br>" + player + "</html>"));
	}

	public void clear() {
		theoryController.setIngredient(null);
		theoryBookText = null;
	}



	
}

