package system.ui.panels;

import system.domain.controllers.InventoryController;
import system.domain.interfaces.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;


public class Inventory extends JPanel implements Observer {


    private Map<String, Integer> ingredients;
    private ArrayList<JLabel> artifacts;
    private ArrayList<JLabel> potions;
    private InventoryController invController;
    private JLabel lblGold;
    private JLabel lblPlayerGold;
    private JLabel lblIngredient;
    private JPanel ingPanel;
    private JLabel lblArtifact;
    private JPanel artifactPanel;
    private JLabel lblPotion;
    private JPanel potionPanel;
    private JLabel lblIng1;
    private JLabel lblIng2;
    private JLabel lblIng3;
    private JLabel lblIng4;
    private JLabel lblIng5;
    private JLabel lblIng6;
    private JLabel lblIng7;
    private JLabel lblIng8;

    public Inventory(InventoryController invController) {
        super();
        this.invController = invController;
        invController.setObserver(this);
        this.ingredients = new HashMap<String, Integer>();
        ingredients.put("Solaris Root", 0);
        ingredients.put("Bat Wing", 0);
        ingredients.put("Toad Stool", 0);
        ingredients.put("Owl Feather", 0);
        ingredients.put("Snake Venom", 0);
        ingredients.put("Rat Tail", 0);
        ingredients.put("Spider Web", 0);
        ingredients.put("Newt Eye", 0);
        this.artifacts = new ArrayList<JLabel>();
        this.potions = new ArrayList<JLabel>();
        setLayout(null);
        
        lblGold = new JLabel("Gold:");
        lblGold.setBounds(180, 10, 45, 13);
        add(lblGold);
        
        lblPlayerGold = new JLabel(String.format("%d", invController.getGold()));
        lblPlayerGold.setBounds(235, 10, 45, 13);
        add(lblPlayerGold);
        
        
        lblIngredient = new JLabel("Ingredients:");
        lblIngredient.setBounds(10, 25, 70, 13);
        add(lblIngredient);
        
        ingPanel = new JPanel();
        ingPanel.setBounds(10, 40, 270, 140);
        add(ingPanel);
        ingPanel.setLayout(null);
        
        lblIng1 = new JLabel("<html>Solaris<br>Root<br>0</html>");
        lblIng1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Solaris Root") > 0) Inventory.this.invController.sendIngredient("Solaris Root");
        	}
        });
        lblIng1.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng1.setOpaque(true);
        lblIng1.setBackground(new Color(139, 69, 19));
        lblIng1.setBounds(6, 4, 60, 64);
        ingPanel.add(lblIng1);
        
        lblIng2 = new JLabel("<html>Bat<br>Wing<br>0</html>");
        lblIng2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Bat Wing") > 0) Inventory.this.invController.sendIngredient("Bat Wing");
        	}
        });
        lblIng2.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng2.setOpaque(true);
        lblIng2.setBackground(new Color(139, 69, 19));
        lblIng2.setBounds(72, 4, 60, 64);
        ingPanel.add(lblIng2);
        
        lblIng3 = new JLabel("<html>Toad<br>Stool<br>0</html>");
        lblIng3.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Toad Stool") > 0) Inventory.this.invController.sendIngredient("Toad Stool");
        	}
        });
        lblIng3.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng3.setOpaque(true);
        lblIng3.setBackground(new Color(139, 69, 19));
        lblIng3.setBounds(138, 4, 60, 64);
        ingPanel.add(lblIng3);
        
        lblIng4 = new JLabel("<html>Owl<br>Feather<br>0</html>");
        lblIng4.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Owl Feather") > 0) Inventory.this.invController.sendIngredient("Owl Feather");
        	}
        });
        lblIng4.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng4.setOpaque(true);
        lblIng4.setBackground(new Color(139, 69, 19));
        lblIng4.setBounds(204, 4, 60, 64);
        ingPanel.add(lblIng4);
        
        lblIng5 = new JLabel("<html>Snake<br>Venom<br>0</html>");
        lblIng5.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Snake Venom") > 0) Inventory.this.invController.sendIngredient("Snake Venom");
        	}
        });
        lblIng5.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng5.setOpaque(true);
        lblIng5.setBackground(new Color(139, 69, 19));
        lblIng5.setBounds(6, 72, 60, 64);
        ingPanel.add(lblIng5);
        
        lblIng6 = new JLabel("<html>Rat<br>Tail<br>0</html>");
        lblIng6.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Rat Tail") > 0) Inventory.this.invController.sendIngredient("Rat Tail");
        	}
        });
        lblIng6.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng6.setOpaque(true);
        lblIng6.setBackground(new Color(139, 69, 19));
        lblIng6.setBounds(72, 72, 60, 64);
        ingPanel.add(lblIng6);
        
        lblIng7 = new JLabel("<html>Spider<br>Web<br>0</html>");
        lblIng7.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Spider Web") > 0) Inventory.this.invController.sendIngredient("Spider Web");
        	}
        });
        lblIng7.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng7.setOpaque(true);
        lblIng7.setBackground(new Color(139, 69, 19));
        lblIng7.setBounds(138, 72, 60, 64);
        ingPanel.add(lblIng7);
        
        lblIng8 = new JLabel("<html>Newt<br>Eye<br>0</html>");
        lblIng8.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Newt Eye") > 0) Inventory.this.invController.sendIngredient("Newt Eye");
        	}
        });
        lblIng8.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng8.setOpaque(true);
        lblIng8.setBackground(new Color(139, 69, 19));
        lblIng8.setBounds(204, 72, 60, 64);
        ingPanel.add(lblIng8);
        
        lblArtifact = new JLabel("Artifacts:");
        lblArtifact.setBounds(10, 195, 70, 13);
        add(lblArtifact);
        
        artifactPanel = new JPanel();
        artifactPanel.setBounds(10, 210, 270, 70);
        add(artifactPanel);
        
        lblPotion = new JLabel("Potions:");
        lblPotion.setBounds(10, 295, 70, 13);
        add(lblPotion);
        
        potionPanel = new JPanel();
        potionPanel.setBounds(10, 310, 270, 70);
        add(potionPanel);
        
    }
    
    public void updateIng(String ingName) {
    	JLabel ingLbl = null;
    	if (ingName.equals("Solaris Root")) {
    		ingLbl = lblIng1;
    	}
    	else if (ingName.equals("Bat Wing")) {
    		ingLbl = lblIng2;
    	}
    	else if (ingName.equals("Toad Stool")) {
    		ingLbl = lblIng3;
    	}
    	else if (ingName.equals("Owl Feather")) {
    		ingLbl = lblIng4;
    	}
    	else if (ingName.equals("Snake Venom")) {
    		ingLbl = lblIng5;
    	}
    	else if (ingName.equals("Rat Tail")) {
    		ingLbl = lblIng6;
    	}
    	else if (ingName.equals("Spider Web")) {
    		ingLbl = lblIng7;
    	}
    	else if (ingName.equals("Newt Eye")) {
    		ingLbl = lblIng8;
    	}
    	String[] splitted = ingName.split(" ");
    	ingLbl.setText("<html>" + splitted[0] + "<br>" + splitted[1] + "<br>" + ingredients.get(ingName) + "</html>");
    }
    

    public void addIngredientToInventory(String text) {
    	ingredients.put(text, ingredients.get(text) + 1);
    	updateIng(text);
    }
    
    public void removeIngredientFromInventory(String text) {
    	ingredients.put(text, ingredients.get(text) - 1);
    	updateIng(text);
    	lblPlayerGold.setText(String.format("%d",invController.getGold()));
    }
    
    
    public void addItemToInventory(String text, String type) {
        JLabel label = new JLabel(text);
        label.addMouseListener(
            new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (type.equals("Potion")) {
                        //as long as there is no collector of potions is present in
                        //concrete mediator, this method is safe
                        Inventory.this.invController.sendPotion(text);
                    }
                    else if (type.equals("Artifact")) {
                        /// maybe use the artifact or something
                    }
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
        
        if (type.equals("Potion")) {
        	potions.add(label);
        	refresh("Potion");
        }
        else if (type.equals("Artifact")) {
        	artifacts.add(label);
        	refresh("Artifact");
        }
    }

    public void removeItemFromInventory(String item, String type) {
    	if (type.equals("Artifact")) {
    		for (int i = 0; i < artifacts.size(); i++) {
                if (artifacts.get(i).getText().equals(item)) {
                    artifacts.remove(i);
                    break;
                }
            }
    		refresh("Artifact");
    	}
    	else if (type.equals("Potion")) {
    		for (int i = 0; i < potions.size(); i++) {
                if (potions.get(i).getText().equals(item)) {
                    potions.remove(i);
                    break;
                }
            }
    		refresh("Potion");
    	}
        
    }

    public void refresh(String type) {
    	
    	if (type.equals("Artifact")) {
    		artifactPanel.removeAll();
            artifactPanel.revalidate();
            for (JLabel j: artifacts){
                artifactPanel.add(j);
            }
            artifactPanel.revalidate();
            artifactPanel.repaint();
    	}
    	else if (type.equals("Potion")) {
    		potionPanel.removeAll();
            potionPanel.revalidate();
            for (JLabel j: potions){
                potionPanel.add(j);
            }
            potionPanel.revalidate();
            potionPanel.repaint();
    	}
    	
        lblPlayerGold.setText(String.format("%d",invController.getGold()));
    }


    @Override
    public void update(String msg) {
        if (msg.contains("NEW_INGREDIENT")) {
            addIngredientToInventory(msg.split(":")[1]);
        }
        else if (msg.contains("REMOVED_INGREDIENT")) {
            removeIngredientFromInventory(msg.split(":")[1]);
        }
        else if (msg.contains("NEW_POTION")) {
            addItemToInventory(msg.split(":")[1], "Potion");
        }
        else if (msg.contains("NEW_ARTIFACT")){
            addItemToInventory(msg.split(":")[1], "Artifact");
        }
    }
}