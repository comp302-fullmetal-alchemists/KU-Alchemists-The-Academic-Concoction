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


public class Inventory extends JPanel implements Observer {


    private ArrayList<JLabel> items;
    private InventoryController invController;

    public Inventory() {
        super();
        this.items = new ArrayList<JLabel>();
    }

    public Inventory(InventoryController invController) {
        super();
        this.invController = invController;
        invController.setObserver(this);
        this.items = new ArrayList<JLabel>();
    }

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
        refresh();
    }

    public void removeItemFromInventory(String item) {
        for (int i = 0; i < items.size(); i++) {
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
        revalidate();
        repaint();
    }


    @Override
    public void update(String msg) {
        if (msg.contains("NEW_INGREDIENT")) {
            addItemToInventory(msg.substring(16), "Ingredient");
        }
        else if (msg.contains("REMOVED_INGREDIENT")) {
            removeItemFromInventory(msg.substring(20));
        }
        else if (msg.contains("NEW_POTION")) {
            addItemToInventory(msg.substring(12), "Potion");
        }
    }

}