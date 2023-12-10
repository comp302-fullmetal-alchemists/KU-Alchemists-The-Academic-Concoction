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
import system.domain.Player;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.TheoryController;
import system.ui.frame.GameContentPane;


public class TheoryBoard extends JPanel {
    private TheoryController theoryController;
    private JLabel theoryBook1;
    private JLabel theoryBook2;
    private JLabel theoryBook3;
    private JLabel theoryBook4;
    private JLabel theoryBook5;
    private JLabel theoryBook6;
    private JLabel theoryBook7;
    private JLabel theoryBook8;
    private String ingredient;
    private JLabel theoryBook;


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
                
        theoryBook1 = new JLabel("<html><br>Solaris Root</html>");
		theoryBook1.setVerticalAlignment(SwingConstants.TOP);
		theoryBook1.setBackground(new Color(204, 204, 255));
		theoryBook1.setOpaque(true);
		theoryBook1.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook1.setBounds(32, 53, 100, 118);
        theoryBook1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                ingredient = "Solaris Root";
                theoryBook = theoryBook1;
			}
		});
		add(theoryBook1);

		theoryBook2 = new JLabel("<html><br>Bat Wing</html>");
		theoryBook2.setVerticalAlignment(SwingConstants.TOP);
		theoryBook2.setOpaque(true);
		theoryBook2.setBackground(new Color(204, 204, 255));
		theoryBook2.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook2.setBounds(200, 53, 100, 118);
        theoryBook2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                ingredient = "Bat Wing";
                theoryBook = theoryBook2;
			}
		});
		add(theoryBook2);

		theoryBook3 = new JLabel("<html><br>Toad Stool</html>");
		theoryBook3.setVerticalAlignment(SwingConstants.TOP);
		theoryBook3.setOpaque(true);
		theoryBook3.setBackground(new Color(204, 204, 255));
		theoryBook3.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook3.setBounds(361, 53, 100, 118);
        theoryBook3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                ingredient = "Toad Stool";
                theoryBook = theoryBook3;
			}
		});
		add(theoryBook3);

		theoryBook4 = new JLabel("<html><br>Owl Feather</html>");
		theoryBook4.setVerticalAlignment(SwingConstants.TOP);
		theoryBook4.setBackground(new Color(204, 204, 255));
		theoryBook4.setOpaque(true);
		theoryBook4.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook4.setBounds(538, 53, 100, 118);
        theoryBook4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                ingredient = "Owl Feather";
                theoryBook = theoryBook4;   
			}
		});
		add(theoryBook4);

		theoryBook5 = new JLabel("<html><br>Snake Venom</html>");
		theoryBook5.setVerticalAlignment(SwingConstants.TOP);
		theoryBook5.setBackground(new Color(204, 204, 255));
		theoryBook5.setOpaque(true);
		theoryBook5.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook5.setBounds(32, 259, 100, 118);
        theoryBook5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                ingredient = "Snake Venom";
                theoryBook = theoryBook5;
			}
		});
		add(theoryBook5);

		theoryBook6 = new JLabel("<html><br>Rat Tail</html>");
		theoryBook6.setVerticalAlignment(SwingConstants.TOP);
		theoryBook6.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook6.setBackground(new Color(204, 204, 255));
		theoryBook6.setOpaque(true);
		theoryBook6.setBounds(200, 259, 100, 118);
        theoryBook6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                ingredient = "Rat Tail";
                theoryBook = theoryBook6;
			}
		});
		add(theoryBook6);

		theoryBook7 = new JLabel("<html><br>Spider Web</html>");
		theoryBook7.setVerticalAlignment(SwingConstants.TOP);
		theoryBook7.setBackground(new Color(204, 204, 255));
		theoryBook7.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook7.setOpaque(true);
		theoryBook7.setBounds(361, 259, 100, 118);
        theoryBook7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                ingredient = "Spider Web";
                theoryBook = theoryBook7;
			}
		});
		add(theoryBook7);

		theoryBook8 = new JLabel("<html><br>Newt Eye</html>");
		theoryBook8.setVerticalAlignment(SwingConstants.TOP);
		theoryBook8.setHorizontalAlignment(SwingConstants.CENTER);
		theoryBook8.setBackground(new Color(204, 204, 255));
		theoryBook8.setOpaque(true);
		theoryBook8.setBounds(535, 259, 100, 118);
        theoryBook8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                ingredient = "Newt Eye";
                theoryBook = theoryBook8;
			}
		});
		add(theoryBook8);
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

    public void createTheoryBook(String alchemy, String player) {
        theoryBook.setText("<html><br>"+ ingredient + "<br><br>" + alchemy + "<br><br>" + player + "</html>");
        
    }


    public String getIngredient (){
        return ingredient;
    }

    public void setIngredient (String ingredient){
        this.ingredient = ingredient;
    }
}

