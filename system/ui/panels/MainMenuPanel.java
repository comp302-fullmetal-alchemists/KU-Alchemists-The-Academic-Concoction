package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import system.ui.frame.GameContentPane;
import system.ui.interfaces.PlayerMediator;
import system.domain.MainMenu;


public class MainMenuPanel extends JPanel{

    private PlayerMediator mediator;
    private JButton back;
    private JButton helpButton;

    public MainMenuPanel(PlayerMediator mediator) {
        super();
        this.back = createNavButton("environment", "Back to environment");
        this.mediator = mediator;
        add(back);
        this.helpButton = createHelpButton("Help");
        add(helpButton);
    }

    public JButton createNavButton(String nav, String text) {
        JButton button = new JButton(text);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameContentPane) MainMenuPanel.this.getParent()).changeView(nav);
                }

            }
        );
        return button;
    }

    public JButton createHelpButton(String text) {
        JButton helpButton = new JButton(text);
        helpButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String helpString = MainMenu.helpScreen();
                    JOptionPane.showMessageDialog(MainMenuPanel.this, helpString);
                }
            }
        );
        return helpButton;
    }


}
