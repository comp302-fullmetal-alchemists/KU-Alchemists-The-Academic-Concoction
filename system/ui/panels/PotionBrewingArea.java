package system.ui.panels;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.SwingConstants;

import system.ui.frame.GameContentPane;
import system.domain.controllers.PotionBrewingAreaController;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;
import java.awt.Font;


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
    private JButton sellPotionBtn;
    private String[] offerStrings = {"You get 1 gold - Your potion is a gamble of curse, calm or charm.", "You get 2 golds - Your potion contains no malevolence.", "You get 3 golds - Your potion is assured of goodly nature."};
    private JLabel adventurerInfo;

	public PotionBrewingArea() {
        super();
		setBackground(new Color(58, 77, 108));
		this.pbaController = GameBoardController.getInstance().getPotionBrewingAreaController();
        pbaController.setObserver(this);
        
        
		navBtn = new JButton("Back to the village");
		navBtn.setBounds(255, 10, 184, 32);
		navBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((GameContentPane) PotionBrewingArea.this.getParent()).changeView("village");
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
		lblIng1.setBounds(73, 100, 57, 96);
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
		lblIng2.setBounds(173, 98, 60, 100);
		add(lblIng2);
		
		
		
		
		makePotionBtn = new JButton("Make Potion");
		makePotionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pbaController.isActive()) {
                    pbaController.makePotion();
                }
			}
		});
		
		makePotionBtn.setBounds(73, 246, 160, 21);
		add(makePotionBtn);
		
		JLabel makePotionLabel = new JLabel("Brew Potions");
		makePotionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		makePotionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		makePotionLabel.setForeground(Color.WHITE);
		makePotionLabel.setBounds(73, 61, 160, 13);
		add(makePotionLabel);
		
		JLabel sellPotionLabel = new JLabel("Sell a Potion");
		sellPotionLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		sellPotionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sellPotionLabel.setForeground(Color.WHITE);
		sellPotionLabel.setBounds(433, 61, 160, 13);
		add(sellPotionLabel);
		
		sellPotionBtn = new JButton("Sell a Potion");
		sellPotionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                    if (pbaController.isActive()) {
                    pbaController.sellPotion();
                }
			}
		});
		sellPotionBtn.setBounds(459, 427, 134, 32);
		add(sellPotionBtn);
		
		adventurerInfo = new JLabel("<html>Hark, potion-masters! The Adventurer proclaims:<br>" + //
		        "for potions positive, three golds be thine;<br>" + //
		        "for brews of good or neutral kind, two golds;<br>" + //
		        "and for any draught, one gold.<br>" + //
		        "Present thy potions and claim thy reward!</html>");
		adventurerInfo.setBounds(410, 98, 225,181);
        adventurerInfo.setOpaque(true);
        adventurerInfo.setBackground(Color.WHITE);
		add(adventurerInfo);
		
		lblPotion = new JLabel("Select a Potion");
		lblPotion.setHorizontalAlignment(SwingConstants.CENTER);
		lblPotion.addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (pbaController.hasPotionToSell()) pbaController.discardPotion();
					}
				});
		lblPotion.setOpaque(true);
		lblPotion.setBackground(Color.LIGHT_GRAY);
		lblPotion.setBounds(476, 315, 100, 100);
		add(lblPotion);
		
    }
	
    public void clear() {
    	if (pbaController.hasIng1()) pbaController.discardIngredient(1);
        if (pbaController.hasIng2()) pbaController.discardIngredient(2);
        if (pbaController.hasPotionToSell()) pbaController.discardPotion();
        pbaController.deactivate();
    }
    
    public void activate() {
    	pbaController.activate();
    	JOptionPane.showMessageDialog(this, "You may now choose ingredients and potions from your inventory.");
    }
   
    public void showMessageDialog(String displayMsg) {
        JOptionPane.showMessageDialog(this, displayMsg);
    }
    
    @Override
    public void update(String msg) {//messager for observer
        if (msg.contains("NEW_INGREDIENT1")) {
        	//String[] ingName = msg.split(":")[1].split(" ");
        	//lblIng1.setText("<html>" + ingName[0] + "<br>" + ingName[1] + "</html>");
        	//lblIng1.setBackground(new Color(117, 67, 108));
			String ingName = msg.split(":")[1];
        	lblIng1.setIcon(new ImageIcon(getClass().getResource("/resources/" + ingName.toLowerCase() + ".png")));
        }
        else if (msg.contains("NEW_INGREDIENT2")) {
        	//String[] ingName = msg.split(":")[1].split(" ");
        	//lblIng2.setText("<html>" + ingName[0] + "<br>" + ingName[1] + "</html>");
        	//lblIng2.setBackground(new Color(117, 67, 108));
			String ingName = msg.split(":")[1];
        	lblIng2.setIcon(new ImageIcon(getClass().getResource("/resources/" + ingName.toLowerCase() + ".png")));
        }
        else if (msg.contains("NEW_POTION")) {
        	String status = msg.split(":")[1];
			lblPotion.setIcon(new ImageIcon(getClass().getResource("/resources/" + status.toLowerCase() + ".png")));
			
        }
        else if (msg.contains("ABSENT_INGREDIENTS")) {
            showMessageDialog("Please fill ingredients");
        }
        else if (msg.contains("ABSENT_POTION")) {
            showMessageDialog("Please select potion to sell");
        }
        else if (msg.contains("BREWED_POTION")) {
			lblIng1.setIcon(null);
			lblIng2.setIcon(null);
            lblIng1.setText(ingDefault);
    		lblIng1.setBackground(Color.LIGHT_GRAY);
            lblIng2.setText(ingDefault);
    		lblIng2.setBackground(Color.LIGHT_GRAY);
            showMessageDialog("You have brewed a " + msg.split(":")[1] + " potion!");
        }
        else if (msg.contains("SOLD_POTION")) {
            lblPotion.setText("Select a Potion");
            lblPotion.setBackground(Color.LIGHT_GRAY);
            showMessageDialog(offerStrings[Integer.parseInt(msg.split(":")[1]) - 1]);//gets the offer and shows the message dialog according to that offer value.
        }

        else if (msg.contains("DISCARD_INGREDIENT1")) {
			lblIng1.setIcon(null);
			lblIng1.setText(ingDefault);
    		lblIng1.setBackground(Color.LIGHT_GRAY);
        }
        else if (msg.contains("DISCARD_INGREDIENT2")) {
			lblIng2.setIcon(null);
            lblIng2.setText(ingDefault);
    		lblIng2.setBackground(Color.LIGHT_GRAY);
        }
        else if (msg.contains("DISCARD_POTION")) {
            lblPotion.setText("Select a Potion");
            lblPotion.setBackground(Color.LIGHT_GRAY);
        }
		else if (msg.contains("NO_SELL_FIRST_ROUND")) {
			showMessageDialog("You cannot sell potions in the first round, wait for second round.");
		}

    }
}
