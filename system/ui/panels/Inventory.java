package system.ui.panels;


import system.domain.controllers.InventoryController;
import system.domain.interfaces.Observer;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;


public class Inventory extends JPanel implements Observer {


    private Map<String, Integer> ingredients;
    private ArrayList<String> artifacts;
    private ArrayList<JLabel> potions;
    private ArrayList<String> potions2;
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
    private HashMap<String, JLabel> ingMap = new HashMap<String, JLabel>();
    private JLabel ingcountLbl1;
    private JLabel ingcountLbl2;
    private JLabel ingcountLbl3;
    private JLabel ingcountLbl4;
    private JLabel ingcountLbl5;
    private JLabel ingcountLbl6;
    private JLabel ingcountLbl7;
    private JLabel ingcountLbl8;

    public Inventory(InventoryController invController) {
        super();
        setBackground(new Color(58, 77, 108));
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
        this.artifacts = new ArrayList<String>();
        this.potions = new ArrayList<JLabel>();
        this.potions2 = new ArrayList<String>();
        setLayout(null);
        
        lblGold = new JLabel("Gold:");
        lblGold.setForeground(Color.WHITE);
        lblGold.setBounds(300, 10, 45, 13);
        add(lblGold);
        
        lblPlayerGold = new JLabel(String.format("%d", invController.getGold()));
        lblPlayerGold.setForeground(Color.WHITE);
        lblPlayerGold.setBounds(357, 10, 45, 13);
        add(lblPlayerGold);
        
        
        lblIngredient = new JLabel("Ingredients:");
        lblIngredient.setForeground(Color.WHITE);
        lblIngredient.setBounds(10, 8, 98, 13);
        add(lblIngredient);
        
        ingPanel = new JPanel();
        ingPanel.setBounds(5, 24, 400, 224);
        add(ingPanel);
        ingPanel.setLayout(null);


        ingcountLbl1 = new JLabel("0");
        ingcountLbl1.setBounds(76, 6, 30, 30);
        ingPanel.add(ingcountLbl1);
        
        ingcountLbl2 = new JLabel("0");
        ingcountLbl2.setBounds(174, 6, 30, 30);
        ingPanel.add(ingcountLbl2);
        
        ingcountLbl3 = new JLabel("0");
        ingcountLbl3.setBounds(272, 6, 30, 30);
        ingPanel.add(ingcountLbl3);
        
        ingcountLbl4 = new JLabel("0");
        ingcountLbl4.setBounds(370, 6, 30, 30);
        ingPanel.add(ingcountLbl4);
        
        ingcountLbl5 = new JLabel("0");
        ingcountLbl5.setBounds(76, 116, 30, 30);
        ingPanel.add(ingcountLbl5);
        
        ingcountLbl6 = new JLabel("0");
        ingcountLbl6.setBounds(174, 116, 30, 30);
        ingPanel.add(ingcountLbl6);
        
        ingcountLbl7 = new JLabel("0");
        ingcountLbl7.setBounds(272, 116, 30, 30);
        ingPanel.add(ingcountLbl7);
        
        ingcountLbl8 = new JLabel("0");
        ingcountLbl8.setBounds(370, 116, 30, 30);
        ingPanel.add(ingcountLbl8);
        
        lblIng1 = new JLabel(new ImageIcon(getClass().getResource("/resources/solaris root.png")));
        lblIng1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Solaris Root") > 0) Inventory.this.invController.sendIngredient("Solaris Root");
        	}
        });
        lblIng1.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng1.setOpaque(true);
        lblIng1.setBackground(new Color(117, 67, 108));
        lblIng1.setBounds(4, 6, 60, 100);
        lblIng1.setToolTipText("Solaris Root");
        ingPanel.add(lblIng1);
        ingMap.put("Solaris Root", ingcountLbl1);
        
        lblIng2 = new JLabel(new ImageIcon(getClass().getResource("/resources/bat wing.png")));
        lblIng2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Bat Wing") > 0) Inventory.this.invController.sendIngredient("Bat Wing");
        	}
        });
        lblIng2.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng2.setOpaque(true);
        lblIng2.setBackground(new Color(117, 67, 108));
        lblIng2.setBounds(102, 6, 60, 100);
        lblIng2.setToolTipText("Bat Wing");
        ingPanel.add(lblIng2);
        ingMap.put("Bat Wing", ingcountLbl2);
        
        lblIng3 = new JLabel(new ImageIcon(getClass().getResource("/resources/toad stool.png")));
        lblIng3.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Toad Stool") > 0) Inventory.this.invController.sendIngredient("Toad Stool");
        	}
        });
        lblIng3.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng3.setOpaque(true);
        lblIng3.setBackground(new Color(117, 67, 108));
        lblIng3.setBounds(200, 6, 60, 100);
        lblIng3.setToolTipText("Toad Stool");
        ingPanel.add(lblIng3);
        ingMap.put("Toad Stool", ingcountLbl3);
        
        lblIng4 = new JLabel(new ImageIcon(getClass().getResource("/resources/owl feather.png")));
        lblIng4.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Owl Feather") > 0) Inventory.this.invController.sendIngredient("Owl Feather");
        	}
        });
        lblIng4.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng4.setOpaque(true);
        lblIng4.setBackground(new Color(117, 67, 108));
        lblIng4.setBounds(298, 6, 60, 100);
        lblIng4.setToolTipText("Owl Feather");
        ingPanel.add(lblIng4);
        ingMap.put("Owl Feather", ingcountLbl4);
        
        lblIng5 = new JLabel(new ImageIcon(getClass().getResource("/resources/snake venom.png")));
        lblIng5.setBounds(4, 116, 60, 100);
        ingPanel.add(lblIng5);
        lblIng5.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Snake Venom") > 0) Inventory.this.invController.sendIngredient("Snake Venom");
        	}
        });
        lblIng5.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng5.setOpaque(true);
        lblIng5.setBackground(new Color(117, 67, 108));
        lblIng5.setToolTipText("Snake Venom");
        ingMap.put("Snake Venom", ingcountLbl5);
        
        lblIng6 = new JLabel(new ImageIcon(getClass().getResource("/resources/rat tail.png")));
        lblIng6.setBounds(102, 116, 60, 100);
        ingPanel.add(lblIng6);
        lblIng6.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Rat Tail") > 0) Inventory.this.invController.sendIngredient("Rat Tail");
        	}
        });
        lblIng6.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng6.setOpaque(true);
        lblIng6.setBackground(new Color(117, 67, 108));
        lblIng6.setToolTipText("Rat Tail");
        ingMap.put("Rat Tail", ingcountLbl6);  
        
        lblIng7 = new JLabel(new ImageIcon(getClass().getResource("/resources/spider web.png")));
        lblIng7.setBounds(200, 116, 60, 100);
        ingPanel.add(lblIng7);
        lblIng7.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Spider Web") > 0) Inventory.this.invController.sendIngredient("Spider Web");
        	}
        });
        lblIng7.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng7.setOpaque(true);
        lblIng7.setBackground(new Color(117, 67, 108));
        lblIng1.setToolTipText("Spider Web");
        ingMap.put("Spider Web", ingcountLbl7);

        lblIng8 = new JLabel(new ImageIcon(getClass().getResource("/resources/newt eye.png")));
        lblIng8.setBounds(298, 116, 60, 100);
        ingPanel.add(lblIng8);
        lblIng8.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (ingredients.get("Newt Eye") > 0) Inventory.this.invController.sendIngredient("Newt Eye");
        	}
        });
        lblIng8.setHorizontalAlignment(SwingConstants.CENTER);
        lblIng8.setOpaque(true);
        lblIng8.setBackground(new Color(117, 67, 108));
        lblIng8.setToolTipText("Newt Eye");
        ingMap.put("Newt Eye", ingcountLbl8);
        
        lblArtifact = new JLabel("Artifacts:");
        lblArtifact.setForeground(Color.WHITE);
        lblArtifact.setBounds(10, 255, 70, 13);
        add(lblArtifact);
        
        artifactPanel = new JPanel();
        artifactPanel.setBounds(5, 274, 397, 110);
        artifactPanel.setLayout(null);
        add(artifactPanel);
        
        lblPotion = new JLabel("Potions:");
        lblPotion.setForeground(Color.WHITE);
        lblPotion.setBounds(10, 387, 70, 13);
        add(lblPotion);
        
        potionPanel = new JPanel();
        potionPanel.setBounds(5, 406, 397, 62);
        potionPanel.setLayout(null);
        add(potionPanel);
        

        initializeIng();
    }

    public void initializeIng() {
        addIngredientToInventory(invController.getIngredientCards().get(0).getName());
        addIngredientToInventory(invController.getIngredientCards().get(1).getName());
    }
    
    public void updateGold() {
    	lblPlayerGold.setText(String.format("%d",invController.getGold()));
    }
    
    public void updateIng(String ingName) {
        
        System.out.println(ingMap.get(ingName));
        
    	ingMap.get(ingName).setText(ingredients.get(ingName).toString());
    }
    

    public void addIngredientToInventory(String text) {
    	ingredients.put(text, ingredients.get(text) + 1);
    	updateIng(text);
    }
    
    public void removeIngredientFromInventory(String text) {
    	ingredients.put(text, ingredients.get(text) - 1);
    	updateIng(text);
    }
    
    
    public String artifactNameToLabelText(String name) {
    	String[] parts = name.split(" ");
    	String ret = "<html>";
    	for (int i = 0; i < parts.length; i++) {
    		ret += parts[i];
    		ret += "<br>";
    	}
    	ret += "</html>";
    	return ret;
    }
    
    public void addArtifactToInventory(String text) {
    	artifacts.add(text);
    	updateArtifacts();
    }

    public void removeArtifactFromInventory(String text) {
        artifacts.remove(text);
        updateArtifacts();
    }
 
    public void updateArtifacts() {
    	artifactPanel.removeAll();
    	artifactPanel.revalidate();
    	int x0 = 4;
    	for (int i = 0; i < artifacts.size(); i++) {
            String artifactName = artifacts.get(i);
    		JLabel artifactLabel = new JLabel(new ImageIcon(getClass().getResource("/resources/" + artifacts.get(i).toLowerCase() + ".png")));
    		artifactLabel.setHorizontalAlignment(SwingConstants.CENTER);
    		artifactLabel.setOpaque(true);
    		artifactLabel.setBackground(new Color(49, 81, 50));
    		artifactLabel.setForeground(Color.LIGHT_GRAY);
    		artifactLabel.setBounds(x0 + 83*i, 3, 60, 100);
            artifactLabel.setToolTipText(artifactName);
 
            artifactLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Inventory.this.invController.sendArtifactCard(artifactName);;
                    System.out.printf("Using Artifact:%s\n", artifactName); //Testing RM LATER
                }
                }); 

    		artifactPanel.add(artifactLabel);
    	}
    	artifactPanel.revalidate();
    	artifactPanel.repaint();
    }
    
    public void addPotionToInventory(String text) {
    	potions2.add(text);
    	updatePotions();
    }
    
    public void removePotionFromInventory(String text) {
    	potions2.remove(text);
    	updatePotions();
    }
    
    public void updatePotions() {
    	potionPanel.removeAll();
    	potionPanel.revalidate();
    	int x0 = 4;
    	for (int i = 0; i < potions2.size(); i++) {
    		String status = potions2.get(i);
            System.out.println("/resources/" + status.toLowerCase() + ".png");
    		JLabel potionLabel = new JLabel();
    		potionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    		potionLabel.setOpaque(true);

            potionLabel.setIcon(new ImageIcon(getClass().getResource("/resources/" + status.toLowerCase() + ".png")));

    		potionLabel.setBounds(x0 + 66*i, 3, 54, 54);
            potionLabel.setToolTipText(status);
    		potionLabel.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseClicked(MouseEvent e) {
            		Inventory.this.invController.sendPotion(status);
            	}
            });    
    		potionPanel.add(potionLabel);
    	}
    	potionPanel.revalidate();
    	potionPanel.repaint();
    }
    
    


    @Override
    public void update(String msg) {
        if (msg.contains("NEW_INGREDIENT")) {
            addIngredientToInventory(msg.split(":")[1]);
        }
        else if (msg.contains("REMOVED_INGREDIENT")) {
            removeIngredientFromInventory(msg.split(":")[1]);
        }
        else if (msg.contains("REMOVED_POTION")) {
            removePotionFromInventory(msg.split(":")[1]);
        }
        else if (msg.contains("NEW_POTION")) {
            addPotionToInventory(msg.split(":")[1]);
        }
        else if (msg.contains("NEW_ARTIFACT")){
            addArtifactToInventory(msg.split(":")[1]);
        }
        else if (msg.contains("GOLD_UPDATE")) {
        	updateGold();
        }
        else if (msg.contains("REMOVED_ARTIFACT_CARD")) {
            removeArtifactFromInventory(msg.split(":")[1]);
        }
        
    }
}