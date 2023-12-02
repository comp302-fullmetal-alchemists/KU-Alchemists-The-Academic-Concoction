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
    private JButton mainMenuButton;


    public Village() {
        super();
        this.ingButton = createNavButton("ingredientStorage", "Ingredient Storage");
        this.potionButton = createNavButton("potionBrewingArea", "Potion Brewing Area");
        this.publicationButton = createNavButton("publicationArea", "Publication Area");
        this.deductionButton = createNavButton("deductionBoard", "Deduction Board");
        this.mainMenuButton = createNavButton("mainMenu", "Main Menu");

        add(ingButton);
        add(potionButton);
        add(publicationButton);
        add(deductionButton);
        add(mainMenuButton);
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
