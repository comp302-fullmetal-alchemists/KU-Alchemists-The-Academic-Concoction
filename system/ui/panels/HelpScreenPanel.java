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
        String helpContent = "## Game Name Help Center\n\n" +
                "Welcome to Game Name! This is a brief overview of the game's features:\n\n" +
                "### Getting Started\n" +
                "1. **Overview:** Explore an exciting world filled with challenges and adventures.\n" +
                "2. **Controls:** Use WASD to move, left mouse button to attack, and spacebar to jump.\n" +
                "3. **Objectives:** Complete quests, defeat enemies, and level up your character.\n\n" +
                "### Gameplay Mechanics\n" +
                "1. **Movement:** Navigate using WASD or arrow keys.\n" +
                "2. **Combat:** Defeat enemies using your weapons and special abilities.\n" +
                "3. **Interactions:** Talk to NPCs, solve puzzles, and discover hidden items.\n" +
                "4. **Power-ups and Abilities:** Unlock and upgrade special powers to enhance your skills.\n\n" +
                "### Levels and Challenges\n" +
                "1. **Level Structure:** Progress through diverse levels with unique challenges.\n" +
                "2. **Challenges and Rewards:** Complete optional challenges to earn rewards.\n\n" +
                "### Settings and Options\n" +
                "1. **Graphics:** Adjust graphical settings for optimal performance.\n" +
                "2. **Sound:** Customize audio settings, including music and sound effects.\n" +
                "3. **Controls:** Configure control settings to match your preferences.\n" +
                "4. **Accessibility:** Explore features for players with different needs.\n\n" +
                "### Troubleshooting\n" +
                "1. **Common Issues:** Find solutions to common problems.\n" +
                "2. **Technical Support:** Contact our support team for assistance.\n\n" +
                "### Contact Us\n" +
                "1. **Feedback:** Share your feedback and suggestions to help us improve the game.\n" +
                "2. **Support:** For technical assistance and other inquiries, reach out to our support team.";

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
