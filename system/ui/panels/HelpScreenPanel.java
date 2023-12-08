package system.ui.panels;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import system.ui.frame.GameContentPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.BorderLayout;

public class HelpScreenPanel extends JPanel {

    public HelpScreenPanel() {
        // Initialize and add components for the help screen
        //chatgbt is used for striing helpcontent
        // Sample help content
        String helpContent =
            "# KuAlchemists Help Center\n\n" +
            "Welcome to KuAlchemists! This is a brief overview of the game's features:\n\n" +

            "## Getting Started\n" +
            "1. Explore an exciting world filled with magic and adventures.\n" +
            "2. **Controls:** Use the mouse or touchpad to click on buttons.\n" +
            "3. **Objectives:** Make experiments, publish theories, earn reputation points\n"+ "to win the game.\n\n" +

            "## Game Board\n" +
            "1. **Village:** Visit the village to access different areas.\n" +
            "2. **Potion Brewing Area:** Make experiments to discover new potions.\n" +
            "3. **Publication Area:** Publish theories to earn reputation points.\n" +
            "4. **Deduction Board:** Use deduction to form theories.\n" +
            "5. **Ingredient Storage:** Draw ingredients and artifact cards.\n\n" +

            "## Player Actions\n" +
            "1. **Draw Ingredient:** Draw an ingredient card from the ingredient storage.\n" +
            "2. **Draw Artifact Card:** Draw an artifact card from the ingredient storage.\n" +
            "3. **Make Experiment:** Make an experiment to discover a new potion, you can have negative, positive\n" + " or neutral results.\n" +
            "4. **Publish Theory:** Publish a theory to earn reputation points, do not forget you need golds.\n" +
            "5. **Debunk Theory:** Debunk a published theory to earn reputation points but do not forget \n" + "if you are wrong you will lose gold.\n\n" +
            "6. **Sell Potion:** Sell a potion to earn gold.\n" +
            "7. **Transmute Ingredient:** Transmute an ingredient to earn gold.\n\n" +

            "## Levels and Challenges\n" +
            "1. **Level 1:** You are only allowed to forage ingredients, transmute ingredients,\n"+" and sell potions.\n" +
            "2. **Level 2:** You are only allowed to make experiments and publish theories.\n" +
            "3. **Level 3:** You are allowed to make experiments, publish theories,\n" + "and debunk theories.\n\n" ;

        setLayout(new BorderLayout());
        JTextArea helpTextArea = new JTextArea(25,40);
        helpTextArea.append(helpContent);
        helpTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(helpTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        helpTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
        helpTextArea.setForeground(Color.WHITE);
        helpTextArea.setBackground(new Color(58, 77, 108)); // semi-transparent color
        helpTextArea.setEditable(false);

        // Apply 3D text effect
       helpTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 73, 94), 3),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JButton artifactCards = new JButton("Artifact Cards");

        artifactCards.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HelpScreenPanel.this, String.format("You see artifact cards %s!",artifactCards.getName()));
            }
        });

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((MainMenuPanel) HelpScreenPanel.this.getParent()).initiliaze();
            }
        });
        add(closeButton, BorderLayout.NORTH);

        // Add other components as needed
        
    }


}


