package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpScreenPanel extends JPanel {

    public HelpScreenPanel() {
        // Initialize and add components for the help screen
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the help screen dialog
                SwingUtilities.getWindowAncestor(HelpScreenPanel.this).dispose();
                
            }
        });
        add(closeButton);

        // Add other components as needed
    }
}
