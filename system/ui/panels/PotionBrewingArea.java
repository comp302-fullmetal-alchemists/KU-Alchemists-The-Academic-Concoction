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
    private JButton activationPotion;
    String deactiveText = "Choose Ingredients";
    String activeText = "Make Potion";
    private JTextField potion;
    private JLabel lblIng1;
    private JLabel lblIng2;
    private String ingDefault = "<html>Give<br>Ingredient</html>";
    private JButton makePotion;
    private JTextArea AdventurerInfo;
    private JComboBox<String> offerDropdown;
    private JButton sellPotionButton;
    private String[] offerStrings = {"I demand 1 gold - My potion is a gamble of curse, calm or charm.", "I demand 2 golds - My potion contains no malevolence.", "I demand 3 golds - My potion is assured of goodly nature."};


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

		
		/*
        this.AdventurerInfo = new JTextArea(pbaController.giveOffer(), 5,12);
        Font f = new Font("Serif", Font.ITALIC, 15);
        AdventurerInfo.setFont(f);
        //add(AdventurerInfo);

        this.potionToSell = createPotionField("sell a potion");
        //add(potionToSell);

        this.offerDropdown = optionsDropdown();
        //add(offerDropdown);
        this.sellPotionButton = sellPotionButton();
        //add(sellPotionButton);
		 */
		
		JLabel sellPotionLabel = new JLabel("Sell a Potion");
		sellPotionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sellPotionLabel.setForeground(Color.LIGHT_GRAY);
		sellPotionLabel.setBounds(433, 61, 160, 13);
		add(sellPotionLabel);
		
		JButton sellPotionBtn = new JButton("New button");
		sellPotionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sellPotionBtn.setBounds(443, 334, 134, 32);
		add(sellPotionBtn);
		
		adventurerInfo = new JTextField();
		adventurerInfo.setBounds(443, 94, 134, 173);
		add(adventurerInfo);
		adventurerInfo.setColumns(10);
		
		lblPotion = new JLabel("New label");
		lblPotion.setOpaque(true);
		lblPotion.setBackground(Color.LIGHT_GRAY);
		lblPotion.setBounds(443, 292, 134, 32);
		add(lblPotion);
		
    }
		
	public JComboBox<String> optionsDropdown() {
            String[] quantityOptions = {"One", "Two", "Three"};
            return new JComboBox<String>(quantityOptions);
        }
    
    public JButton sellPotionButton(){
            JButton sellPotionButton = new JButton("Sell the Potion");
            sellPotionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedOffer = offerDropdown.getSelectedIndex();
                    pbaController.agreeOffer(selectedOffer + 1);
                }
            });
            return sellPotionButton;
	}

    public JTextField createPotionField(String name) {
        JTextField potion = new JTextField(20);
        potion.setEnabled(false);
        potion.setText(name);
        potion.addMouseListener(
            new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    potion.setText(name);
                    if (name.equals("Give a Potion")) pbaController.discardPotion();
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}

            }
        );
        return potion;
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
            potion.setText(msg.split(":")[1]);
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
            potion.setText("Select Potion to sell");
            showMessageDialog(offerStrings[Integer.parseInt(msg.split(":")[1])]);
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
            potion.setText("Select Potion to sell");
        }

    }
}
