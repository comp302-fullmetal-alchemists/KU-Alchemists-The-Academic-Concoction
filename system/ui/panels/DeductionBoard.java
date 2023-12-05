package system.ui.panels;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import system.ui.frame.GameContentPane;


public class DeductionBoard extends JPanel{

    private JButton back;

    public DeductionBoard() {
        super();
        this.back = createNavButton("village", "Back to the village");
        add(back);
    }

    public JButton createNavButton(String nav, String text) {
        JButton button = new JButton(text);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameContentPane) DeductionBoard.this.getParent()).changeView(nav);
                }

            }
        );
        return button;
    }
    
}
