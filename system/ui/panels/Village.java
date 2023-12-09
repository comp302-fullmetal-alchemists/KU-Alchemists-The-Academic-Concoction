package system.ui.panels;

import system.ui.frame.GameContentPane;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class Village extends JPanel {
    

    public Village() {
    	setBackground(new Color(58, 77, 108));
        setLayout(null);
        
        JButton btnIngredientStorage = new JButton("Ingredient Storage");
        btnIngredientStorage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 ((GameContentPane) Village.this.getParent()).changeView("ingredientStorage");
        	}
        });
        btnIngredientStorage.setBounds(111, 49, 159, 29);
        add(btnIngredientStorage);
       
       
        
        JButton btnPotionBrewingArea = new JButton("Potion Brewing Area");
        btnPotionBrewingArea.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
       		 ((GameContentPane) Village.this.getParent()).changeView("potionBrewingArea");

        	}
        });
        btnPotionBrewingArea.setBounds(111, 99, 159, 29);
        add(btnPotionBrewingArea);
        
        
        JButton btnPublicationArea = new JButton("Publication Area\n");
        btnPublicationArea.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
       		 ((GameContentPane) Village.this.getParent()).changeView("publicationArea");

        	}
        });
        btnPublicationArea.setBounds(111, 150, 159, 29);
        add(btnPublicationArea);
        
        JButton btnMainMenu = new JButton("Main Menu\n");
        btnMainMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 ((GameContentPane) Village.this.getParent()).changeView("mainMenu");
        	}
        });

        /* 
        btnMainMenu.setBounds(111, 258, 159, 29);
        add(btnMainMenu);
        */
        
        JButton btnDeductionBoard = new JButton("Deduction Board\n");
        btnDeductionBoard.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                ((GameContentPane) Village.this.getParent()).changeView("deductionBoard");
        	}
        });
        btnDeductionBoard.setBounds(111, 203, 159, 29);
        add(btnDeductionBoard);
    }
    
 
}
