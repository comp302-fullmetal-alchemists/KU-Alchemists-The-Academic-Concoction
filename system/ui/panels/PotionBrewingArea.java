package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;


import java.awt.event.ActionListener;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.event.MouseEvent;
import system.ui.frame.GameContentPane;
import system.domain.controllers.PotionBrewingAreaController;
import system.domain.Potion;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.IngredientStorageController;
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
    private JLabel lblIng1;
    private JLabel lblIng2;
    private JLabel lblPotion;
    private String ingDefault = "<html>Give<br>Ingredient</html>";
    private JButton makePotion;
    private JButton sellPotionButton;
    private String[] offerStrings = {"You get 1 gold - Your potion is a gamble of curse, calm or charm.", "You get 2 golds - Your potion contains no malevolence.", "You get 3 golds - Your potion is assured of goodly nature."};
    private JLabel adventurerInfo;

	public PotionBrewingArea() {
        super();
		setBackground(new Color(58, 77, 108));
		this.pbaController = GameBoardController.getInstance().getPotionBrewingAreaController();
        pbaController.setObserver(this);
        
        
		navBtn = new JButton("Back to the village");
		navBtn.setBounds(279, 10, 160, 21);
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
		
		JButton sellPotionBtn = new JButton("Select a Potion");
        sellPotionBtn.setBounds(73, 246, 160, 21);
		sellPotionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                    if (pbaController.isActive()) {
                    pbaController.sellPotion();
                    pbaController.deactivate();
                    sellPotionBtn.setText("Select a Potion");
                }
                else {
                    pbaController.activate();
                    sellPotionBtn.setText("Sell the Potion");
                }
			}
		});
        
		sellPotionBtn.setBounds(443, 334, 134, 32);
		add(sellPotionBtn);
		
		adventurerInfo = new JLabel("<html>Hark, potion-masters! The Adventurer proclaims:<br>" + //
		        "for potions positive, three golds be thine;<br>" + //
		        "for brews of good or neutral kind, two golds;<br>" + //
		        "and for any draught, one gold.<br>" + //
		        "Present thy potions and claim thy reward!</html>");
		adventurerInfo.setBounds(443, 94, 134,200);
        adventurerInfo.setOpaque(true);
        adventurerInfo.setBackground(Color.LIGHT_GRAY);
		add(adventurerInfo);
		
		lblPotion = new JLabel("Select a Potion to Sell");
		lblPotion.setOpaque(true);
		lblPotion.setBackground(Color.LIGHT_GRAY);
		lblPotion.setBounds(443, 312, 134, 32);
		add(lblPotion);
		
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
        else if (msg.contains("NEW_POTION")) {
            lblPotion.setText(msg.split(":")[1]);
        }
        else if (msg.contains("ABSENT_INGREDIENTS")) {
            showMessageDialog("Please fill ingredients");
        }
        else if (msg.contains("ABSENT_POTION")) {
            showMessageDialog("Please select potion to sell");
        }
        else if (msg.contains("BREWED_POTION")) {
            lblIng1.setText(ingDefault);
    		lblIng1.setBackground(Color.LIGHT_GRAY);
            lblIng2.setText(ingDefault);
    		lblIng2.setBackground(Color.LIGHT_GRAY);
            showMessageDialog("You have brewed a " + msg.split(":")[1] + " potion!");
        }
        else if (msg.contains("SOLD_POTION")) {
            lblPotion.setText("Select a Potion");
            showMessageDialog(offerStrings[Integer.parseInt(msg.split(":")[1]) - 1]);
        }

        else if (msg.contains("DISCARD_INGREDIENT1")) {
            lblIng1.setText(ingDefault);
    		lblIng1.setBackground(Color.LIGHT_GRAY);
        }
        else if (msg.contains("DISCARD_INGREDIENT2")) {
            lblIng2.setText(ingDefault);
    		lblIng2.setBackground(Color.LIGHT_GRAY);
        }
        else if (msg.contains("DISCARD_POTION")) {
            lblPotion.setText("Select a Potion");
        }

    }
}
