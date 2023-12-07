package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import system.ui.frame.GameContentPane;
import system.domain.controllers.PotionBrewingAreaController;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PotionBrewingArea extends JPanel implements Observer {
	private PotionBrewingAreaController pbaController;
    private JButton navBtn;
    private JButton activationBtn;
    private JButton makePotionBtn;
    String deactiveText = "Choose Ingredients";
    String activeText = "Make Potion";
    private JTextField ing1;
    private String ing1Default = "Give Ingredient1";
    private String ing2Default = "Give Ingredient2";
    private JTextField ing2;
    private JTextField potion;
	
	public PotionBrewingArea() {
		setBackground(new Color(58, 77, 108));
		this.pbaController = GameBoardController.getInstance().getPotionBrewingAreaController();
        pbaController.setObserver(this);
        
        
		navBtn = new JButton("Back to the village");
		navBtn.setBounds(28, 10, 160, 21);
		navBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameContentPane) PotionBrewingArea.this.getParent()).changeView("village");
                initialize();
			}
		});
		setLayout(null);
		add(navBtn);
		

		ing1 = new JTextField();
		ing1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pbaController.hasIng1()) pbaController.discardIngredient(1);
				ing1.setText(ing1Default);
			}
		});
		ing1.setEditable(false);
		ing1.setText(ing1Default);
		ing1.setBounds(28, 95, 160, 19);
		add(ing1);
		ing1.setColumns(10);
		
		ing2 = new JTextField();
		ing2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pbaController.hasIng2()) pbaController.discardIngredient(2);
				ing2.setText(ing2Default);
			}
		});
		ing2.setText(ing2Default);
		ing2.setEditable(false);
		ing2.setBounds(28, 124, 160, 19);
		add(ing2);
		ing2.setColumns(10);
		
		makePotionBtn = new JButton("Choose Ingredients");
		makePotionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pbaController.isActive()) {
                    pbaController.makePotion();
                    pbaController.deactivate();
                    makePotionBtn.setText(deactiveText);
                }
                else {
                    pbaController.activate();
                    makePotionBtn.setText(activeText);
                }
			}
		});
		makePotionBtn.setBounds(28, 153, 160, 21);
		add(makePotionBtn);
		
		JLabel makePotionLabel = new JLabel("Brew Potions");
		makePotionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		makePotionLabel.setForeground(Color.LIGHT_GRAY);
		makePotionLabel.setBounds(28, 61, 160, 13);
		add(makePotionLabel);
		
		JLabel sellPotionLabel = new JLabel("Sell a Potion");
		sellPotionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sellPotionLabel.setForeground(Color.LIGHT_GRAY);
		sellPotionLabel.setBounds(256, 61, 160, 13);
		add(sellPotionLabel);
		
		potion = new JTextField();
		potion.setText("Give Potion");
		potion.setEditable(false);
		potion.setBounds(256, 124, 160, 19);
		add(potion);
		potion.setColumns(10);
		
		JButton sellPotionBtn = new JButton("Sell Potion");
		sellPotionBtn.setBounds(256, 153, 160, 21);
		add(sellPotionBtn);
		
	}
	
    public void initialize() {
    	if (pbaController.hasIng1()) pbaController.discardIngredient(1);
        if (pbaController.hasIng2()) pbaController.discardIngredient(2);
        pbaController.deactivate();
        makePotionBtn.setText(deactiveText);
    }
   
    public void showMessageDialog(String displayMsg) {
        JOptionPane.showMessageDialog(this, displayMsg);
    }
    
    @Override
    public void update(String msg) {
        if (msg.contains("NEW_INGREDIENT1")) {
            ing1.setText(msg.split(":")[1]);
        }
        else if (msg.contains("NEW_INGREDIENT2")) {
            ing2.setText(msg.split(":")[1]);
        }
        else if (msg.contains("ABSENT_INGREDIENTS")) {
            showMessageDialog("Please fill ingredients");
        }
        else if (msg.contains("BREWED_POTION")) {
            ing1.setText(ing1Default);
            ing2.setText(ing2Default);
            showMessageDialog("You have brewed a " + msg.split(":")[1] + " potion!");
        }
        else if (msg.contains("DISCARD_INGREDIENT1")) {
        	System.out.println("z");
            ing1.setText(ing1Default);
        }
        else if (msg.contains("DISCARD_INGREDIENT2")) {
            ing2.setText(ing2Default);
        }
    }
}
