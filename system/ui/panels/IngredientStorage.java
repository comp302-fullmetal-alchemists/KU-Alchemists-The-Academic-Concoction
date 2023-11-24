package system.ui.panels;

import system.ui.frame.GameContentPane;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IngredientStorage extends JPanel {
    
    private JButton back;
    
    public IngredientStorage() {
        super();
        this.back = createNavButton("environment", "Back to environment");
        add(back);
    }

    public JButton createNavButton(String nav, String text) {
        JButton button = new JButton(text);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameContentPane) IngredientStorage.this.getParent()).changeView(nav);
                }

            }
        );
        return button;
    }

}
