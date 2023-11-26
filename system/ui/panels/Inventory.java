package system.ui.panels;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class Inventory extends JPanel {


    private ArrayList<JLabel> items = new ArrayList<JLabel>();

    public Inventory() {
        super();
    }

    public void addItemToInventory(String text) {
        items.add(new JLabel(text));
    }

    public void update() {
        removeAll();
        for (JLabel j: items) {
            add(j);
        }
        revalidate();
    }

}