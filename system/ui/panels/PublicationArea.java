package system.ui.panels;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;


import system.ui.frame.GameContentPane;
import system.ui.interfaces.PlayerMediator;

public class PublicationArea extends JPanel{

    private PlayerMediator mediator;
    private JButton back;
    private TheoryBoard theoryBoard;

    public PublicationArea(PlayerMediator mediator) {
        super();
        this.mediator = mediator;
        this.back = createNavButton("village", "Back to the village");
        add(back);
        this.theoryBoard = new TheoryBoard();
        add(theoryBoard);
    }

    public JButton createNavButton(String nav, String text) {
        JButton button = new JButton(text);
        button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((GameContentPane) PublicationArea.this.getParent()).changeView(nav);
                }

            }
        );
        return button;
    }
    
}
