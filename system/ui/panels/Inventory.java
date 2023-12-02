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

    public void addItemToInventory(String text) {
        JLabel label = new JLabel(text);
        label.addMouseListener(
            new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Inventory.this.invController.sendIngredient(text);
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
        this.refresh();
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
            addItemToInventory(msg.substring(16));
        }
    }

}