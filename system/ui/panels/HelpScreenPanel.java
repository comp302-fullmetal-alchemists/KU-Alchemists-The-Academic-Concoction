package system.ui.panels;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import system.ui.frame.GameContentPane;
import system.ui.interfaces.PlayerMediator;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpScreenPanel extends JPanel {

    public HelpScreenPanel() {
        // Initialize and add components for the help screen
        //chatgbt is used for striing helpcontent
        // Sample help content
        String helpContent = "## KuAlchemists Help Center\n\n" +
                "Welcome to KuAlchemists! This is a brief overview of the game's features:\n\n" +
                "### Getting Started\n" +
                "1. Explore an exciting world filled with magic and adventures.\n" +
                "2. **Controls:** Use mouse or touchpad to click on buttons.\n" +
                "3. **Objectives:** Make experiments, publish theories, earn reputation point to win the game.\n\n" +
                "### Game Board\n" +
                "1. **Village:** Visit the village to access different areas.\n" +
                "2. **Potion Brewing Area:** Make experiments to discover new potions.\n" +
                "3. **Publication Area:** Publish theories to earn reputation points.\n" +
                "4. **Deduction Board:** Use deduction to form theories.\n" +
                "5. **Ingredient Storage:** Draw ingredients and artifact cards.\n\n" +
                "### Player Actions\n" +
                "1. **Draw Ingredient:** Draw an ingredient card from the ingredient storage.\n" +
                "2. **Draw Artifact Card:** Draw an artifact card from the ingredient storage.\n" +
                "3. **Make Experiment:** Make an experiment to discover a new potion.\n" +
                "4. **Publish Theory:** Publish a theory to earn reputation points.\n" +
                "5. **Debunk Theory:** Debunk a theory to earn reputation points.\n\n" +
                "6. **Sell Potion:** Sell a potion to earn gold.\n" +
                "7. **Transmute Ingredient:** Transmute an ingredient to earn gold.\n" +

                "### Levels and Challenges\n" +
                "1. **Level 1:** You are only allowed to forage ingredients, transmute ingredients, and sell potions.\n" +
                "2. **Level 2:** You are only allowed to make experiments and publish theories.\n" +
                "3. **Level 3:** You are allowed to make experiments, publish theories, and debunk theories.\n\n" +
                "### Contact Us\n" +
                "If you have any questions or concerns, please contact us at";

        JTextArea helpTextArea = new JTextArea(helpContent);
        helpTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(helpTextArea);
        add(scrollPane);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the help screen dialog
                //JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(HelpScreenPanel.this);
                //dialog.dispose();
                ((GameContentPane) HelpScreenPanel.this.getParent()).changeView("mainMenu");
            }
        });
        add(closeButton);

        // Add other components as needed
    }


}
