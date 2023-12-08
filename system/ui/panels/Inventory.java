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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class Inventory extends JPanel implements Observer {


    private ArrayList<JLabel> ingredients;
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

    public Inventory(InventoryController invController) {
        super();
        this.invController = invController;
        invController.setObserver(this);
        this.ingredients = new ArrayList<JLabel>();
        this.artifacts = new ArrayList<JLabel>();
        this.potions = new ArrayList<JLabel>();
        setLayout(null);
        
        lblGold = new JLabel("Gold:");
        lblGold.setBounds(180, 10, 45, 13);
        add(lblGold);
        
        lblPlayerGold = new JLabel("New label");
        lblPlayerGold.setBounds(235, 10, 45, 13);
        add(lblPlayerGold);
        
        
        lblIngredient = new JLabel("Ingredients:");
        lblIngredient.setBounds(10, 25, 70, 13);
        add(lblIngredient);
        
        ingPanel = new JPanel();
        ingPanel.setBounds(10, 40, 270, 140);
        add(ingPanel);
        
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


    public void addItemToInventory(String text, String type) {
        JLabel label = new JLabel(text);
        label.addMouseListener(
            new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (type.equals("Ingredient")) {
                        Inventory.this.invController.sendIngredient(text);
                    }
                    else if (type.equals("Potion")) {
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
        if (type.equals("Ingredient")) {
        	ingredients.add(label);
        	refresh("Ingredient");
        }
        else if (type.equals("Potion")) {
        	potions.add(label);
        	refresh("Potion");
        }
        else if (type.equals("Artifact")) {
        	artifacts.add(label);
        	refresh("Artifact");
        }
    }

    public void removeItemFromInventory(String item, String type) {
    	if (type.equals("Ingredient")) {
    		for (int i = 0; i < ingredients.size(); i++) {
                if (ingredients.get(i).getText().equals(item)) {
                    ingredients.remove(i);
                    break;
                }
            }
    		refresh("Ingredient");

    	}
    	else if (type.equals("Artifact")) {
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
    	if (type.equals("Ingredient")) {
    		ingPanel.removeAll();
            ingPanel.revalidate();
            for (JLabel j: ingredients){
                ingPanel.add(j);
            }
            ingPanel.revalidate();
            ingPanel.repaint();
    	}
    	else if (type.equals("Artifact")) {
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
            addItemToInventory(msg.split(":")[1], "Ingredient");
        }
        else if (msg.contains("REMOVED_INGREDIENT")) {
            removeItemFromInventory(msg.split(":")[1], "Ingredient");
        }
        else if (msg.contains("NEW_POTION")) {
            addItemToInventory(msg.split(":")[1], "Potion");
        }
        else if (msg.contains("NEW_ARTIFACT")){
            addItemToInventory(msg.split(":")[1], "Artifact");
        }
    }
}