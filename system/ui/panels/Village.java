package system.ui.panels;

import system.ui.frame.GameContentPane;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Village extends JPanel {
    
    private JButton ingButton;
    private JButton potionButton;
    private JButton publicationButton;
    private JButton deductionButton;


    public Village() {
        super();
        this.ingButton = createNavButton("ingredientStorage", "Ingredient Storage");
        this.potionButton = new JButton("Potion Brewing Area");
        this.publicationButton = new JButton("Publication Area");
        this.deductionButton = new JButton("Deduction Board");
        add(ingButton);
        add(potionButton);
        add(publicationButton);
        add(deductionButton);
    }

    public JButton createNavButton(String nav, String text) {
        JButton button = new JButton(text);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameContentPane) Village.this.getParent()).changeView(nav);
                }

            }
        );
        return button;
    }
}
