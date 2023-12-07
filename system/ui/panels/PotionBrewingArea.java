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

public class PotionBrewingArea extends JPanel implements Observer {
	private PotionBrewingAreaController pbaController;
    private JButton navBtn;
    private JButton activationBtn;
    private JButton makePotionBtn;
    String deactiveText = "Activate choice";
    String activeText = "Deactivate choice";
    private JTextField ing1;
    private JTextField ing2;
	
	public PotionBrewingArea() {
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
		
		activationBtn = new JButton("Activate Choice");
		activationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pbaController.isActive()) {
                    pbaController.deactivate();
                    activationBtn.setText(deactiveText);
                }
                else {
                    pbaController.activate();
                    activationBtn.setText(activeText);
                }
			}
		});
		activationBtn.setBounds(28, 41, 160, 21);
		add(activationBtn);
		
		ing1 = new JTextField();
		ing1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!ing1.getText().equals("Give Ingredient1")) pbaController.discardIngredient(1);
				ing1.setText("Give Ingredient1");
			}
		});
		ing1.setEditable(false);
		ing1.setText("Give Ingredient1");
		ing1.setBounds(28, 71, 160, 19);
		add(ing1);
		ing1.setColumns(10);
		
		ing2 = new JTextField();
		ing2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!ing2.getText().equals("Give Ingredient1")) pbaController.discardIngredient(2);
				ing2.setText("Give Ingredient2");
			}
		});
		ing2.setText("Give Ingredient2");
		ing2.setEditable(false);
		ing2.setBounds(28, 93, 160, 19);
		add(ing2);
		ing2.setColumns(10);
		
		makePotionBtn = new JButton("Make Potion");
		makePotionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pbaController.makePotion();
			}
		});
		makePotionBtn.setBounds(28, 122, 160, 21);
		add(makePotionBtn);
		
	}
	
    public void initialize() {
    	if (!ing1.getText().equals("Give Ingredient1")) pbaController.discardIngredient(1);
        if (!ing2.getText().equals("Give Ingredient2")) pbaController.discardIngredient(2);
        pbaController.deactivate();
        activationBtn.setText(deactiveText);
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
            ing1.setText("Give Ingredient1");
            ing2.setText("Give Ingredient2");
            showMessageDialog("You have brewed a " + msg.split(":")[1] + " potion!");

        }
        else if (msg.contains("DISCARD_INGREDIENT1")) {
            ing1.setText("Give Ingredient1");
        }
        else if (msg.contains("DISCARD_INGREDIENT2")) {
            ing2.setText("Give Ingredient2");
        }
    }

}
