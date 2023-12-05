package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;


import system.ui.frame.GameContentPane;
import system.domain.MainMenu;


public class MainMenuPanel extends JPanel{

    private JButton back;
    private JButton helpButton;
    JButton pauseButton;

    public MainMenuPanel() {
        super();
        this.back = createNavButton("village", "Back to village");
        add(back);
        this.helpButton = createHelpButton("Help");
        add(helpButton);
        this.pauseButton = createPauseButton("Pause/Resume");
        add(pauseButton);
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

    public JButton createPauseButton(String text) {
        JButton pauseButton = new JButton(text);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the pop-up dialog
                String options[] = {"Resume"};
                JOptionPane pane = new JOptionPane("Game Paused", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options , null);
                JDialog dialog = pane.createDialog(null, "Game Paused");
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
                


                }
        });
        return pauseButton;
    }


    


}
