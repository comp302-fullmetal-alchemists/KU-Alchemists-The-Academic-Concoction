package system.ui.panels;

import system.domain.controllers.InventoryController;
import system.domain.interfaces.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
        items.add(new JLabel(text));
        this.refresh();
    }

    public void refresh() {
        removeAll();
        for (JLabel j: items){
            add(j);
        }
        revalidate();
    }

    @Override
    public void update(String msg) {
        if (msg.contains("NEW_INGREDIENT")) {
            addItemToInventory(msg.substring(16));
        }
    }

}