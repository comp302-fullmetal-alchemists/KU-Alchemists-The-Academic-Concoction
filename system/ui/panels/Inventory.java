package system.ui.panels;

import system.domain.controllers.InventoryController;
import system.domain.interfaces.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.util.ArrayList;


public class Inventory extends JPanel implements Observer {


    private ArrayList<JLabel> items;
    private InventoryController invController;
    private JLabel lblGold;

    public Inventory() {
        super();
        this.items = new ArrayList<JLabel>();

        JLabel lblGold = new JLabel("Gold: \n" + invController.getGold());
        lblGold.setBounds(23, 51, 61, 16);
        lblGold.setForeground(Color.WHITE);
        add(lblGold);
    }

    public Inventory(InventoryController invController) {
        super();
        this.invController = invController;
        invController.setObserver(this);
        this.items = new ArrayList<JLabel>();
      
        JLabel lblGold = new JLabel("Gold: \n" + invController.getGold());
        lblGold.setBounds(6, 6, 61, 16);
        add(lblGold);
        
        JPanel ingredientPanel = new JPanel();
        ingredientPanel.setBounds(17, 43, 75, 100);
        add(ingredientPanel);


    }

          /*
    Icon solarisRoot = new ImageIcon(getClass().getResource( "/solaris root.jpg"));
    Icon owlFeather = new ImageIcon(getClass().getResource("/owl feather.jpg"));
    Icon batWing = new ImageIcon(getClass().getResource("/bat wing.jpg"));
    Icon toadStool = new ImageIcon(getClass().getResource("/toad stool.jpg"));

    */


    public void addItemToInventory(String text, String type) {
        JLabel label = new JLabel(text);
        label.addMouseListener(
            new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (type == "Ingredient") {
                        Inventory.this.invController.sendIngredient(text);
                    }
                    else if (type == "Potion") {
                        //as long as there is no collector of potions is present in
                        //concrete mediator, this method is safe
                        Inventory.this.invController.sendPotion(text);
                    }
                    else if (type == "Artifact") {
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
        items.add(label);
        //  System.out.println(label.getText());
        refresh();
    }

    public void removeItemFromInventory(String item) {
        for (int i = 0; i < items.size(); i++) {
             // if (items.get(i).getIcon().equals(item)) {
            if (items.get(i).getText().equals(item)) {
                items.remove(i);
                break;
            }
        }
        refresh();
    }

    public void refresh() {
        removeAll();
        for (JLabel j: items){
            add(j);
        }
        lblGold = new JLabel("Gold: \n" + invController.getGold());
        add(lblGold); 
        revalidate();
        repaint();
    }


    @Override
    public void update(String msg) {
        if (msg.contains("NEW_INGREDIENT")) {
            addItemToInventory(msg.split(":")[1], "Ingredient");
        }
        else if (msg.contains("REMOVED_INGREDIENT")) {
            removeItemFromInventory(msg.split(":")[1]);
        }
        else if (msg.contains("NEW_POTION")) {
            addItemToInventory(msg.split(":")[1], "Potion");
        }
        else if (msg.contains("NEW_ARTIFACT")){
            addItemToInventory(msg.split(":")[1], "Artifact");
        }
    }

}