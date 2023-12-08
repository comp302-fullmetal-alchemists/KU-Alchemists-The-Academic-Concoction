package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.Color;
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
    private JButton makePotionBtn;
    String deactiveText = "Choose Ingredients";
    String activeText = "Make Potion";
    private JTextField potion;
    private JLabel lblIng1;
    private JLabel lblIng2;
    private String ingDefault = "<html>Give<br>Ingredient</html>";
	
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
		
		lblIng1 = new JLabel(ingDefault);
		lblIng1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pbaController.hasIng1()) pbaController.discardIngredient(1);
			}
		});
		lblIng1.setHorizontalAlignment(SwingConstants.CENTER);
		lblIng1.setOpaque(true);
		lblIng1.setBackground(Color.LIGHT_GRAY);
		lblIng1.setBounds(28, 98, 90, 96);
		add(lblIng1);
		
		lblIng2 = new JLabel(ingDefault);
		lblIng2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (pbaController.hasIng2()) pbaController.discardIngredient(2);
			}
		});
		lblIng2.setHorizontalAlignment(SwingConstants.CENTER);
		lblIng2.setOpaque(true);
		lblIng2.setBackground(Color.LIGHT_GRAY);
		lblIng2.setBounds(188, 98, 90, 96);
		add(lblIng2);
		
		
		
		
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
		makePotionBtn.setBounds(73, 246, 160, 21);
		add(makePotionBtn);
		
		JLabel makePotionLabel = new JLabel("Brew Potions");
		makePotionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		makePotionLabel.setForeground(Color.LIGHT_GRAY);
		makePotionLabel.setBounds(73, 61, 160, 13);
		add(makePotionLabel);
		
		JLabel sellPotionLabel = new JLabel("Sell a Potion");
		sellPotionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sellPotionLabel.setForeground(Color.LIGHT_GRAY);
		sellPotionLabel.setBounds(433, 61, 160, 13);
		add(sellPotionLabel);
		
		potion = new JTextField();
		potion.setText("Give Potion");
		potion.setEditable(false);
		potion.setBounds(433, 95, 160, 19);
		add(potion);
		potion.setColumns(10);
		
		JButton sellPotionBtn = new JButton("Sell Potion");
		sellPotionBtn.setBounds(433, 123, 160, 21);
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
        	String[] ingName = msg.split(":")[1].split(" ");
        	lblIng1.setText("<html>" + ingName[0] + "<br>" + ingName[1] + "</html>");
        	lblIng1.setBackground(new Color(117, 67, 108));
        }
        else if (msg.contains("NEW_INGREDIENT2")) {
        	String[] ingName = msg.split(":")[1].split(" ");
        	lblIng2.setText("<html>" + ingName[0] + "<br>" + ingName[1] + "</html>");
        	lblIng2.setBackground(new Color(117, 67, 108));
        }
        else if (msg.contains("ABSENT_INGREDIENTS")) {
            showMessageDialog("Please fill ingredients");
        }
        else if (msg.contains("BREWED_POTION")) {
            lblIng1.setText(ingDefault);
    		lblIng1.setBackground(Color.LIGHT_GRAY);
            lblIng2.setText(ingDefault);
    		lblIng2.setBackground(Color.LIGHT_GRAY);
            showMessageDialog("You have brewed a " + msg.split(":")[1] + " potion!");
        }
        else if (msg.contains("DISCARD_INGREDIENT1")) {
            lblIng1.setText(ingDefault);
    		lblIng1.setBackground(Color.LIGHT_GRAY);
        }
        else if (msg.contains("DISCARD_INGREDIENT2")) {
            lblIng2.setText(ingDefault);
    		lblIng2.setBackground(Color.LIGHT_GRAY);

        }
    }
}
