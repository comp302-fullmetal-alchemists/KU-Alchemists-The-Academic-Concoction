package system.ui.panels;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;

public class HelpScreenPanel extends JPanel {

    public HelpScreenPanel() {
        
        // Help content string, for the outline I took it from the chatGBT
        String helpContent =
        "KuAlchemists Help Center\n\n" +
        "Welcome to KuAlchemists! This is a brief overview of the game's features:\n\n" +

        "There are 2 modes of the game: Offline and Online.\n" +
        "For offline mode please choose your player count and start to have fun!\n" +
        "For online mode, please enter your host's port and IP to join a game.\n" + 
        "To host a game, enter a port number and click the host game button.\n\n" +

        "Getting Started\n" +
        "Explore an exciting world filled with magic and adventures!\n" +
        "2. Controls:Use the mouse or touchpad to click on buttons.\n" +
        "3. Objectives:Make experiments, publish theories, earn reputation points to win the game.\n\n" +

        "Game Board\n" +
        "1. Village: Visit the village to access different areas.\n" +
        "2. Potion Brewing Area: Make experiments to discover new potions and sell them to earn gold.\n" +
        "3. Publication Area: Publish theories to earn reputation points and debunk theories.\n" +
        "4. Deduction Board: Use deduction to form theories.\n" +
        "5. Ingredient Storage: Draw ingredients and artifact cards, you can also transmute ingredients.\n\n" +

        "Player Actions\n" +
        "1. Draw Ingredient: Draw an ingredient card from the ingredient storage.\n" +
        "2. Draw Artifact Card: Draw an artifact card from the ingredient storage, it will cost 3 golds.\n" +
        "3. Make Experiment: Make an experiment to discover a new potion, you can have negative, positive or neutral results.\n" +
        "4. Publish Theory:Publish a theory to earn reputation points, do not forget you need golds.\n" +
        "5. Debunk Theory: Debunk a published theory to earn reputation points but do not forget if you are wrong you will lose gold.\n" +
        "6. Sell Potion: Sell a potion to earn gold.\n" +
        "7. Transmute Ingredient: Transmute an ingredient to earn gold.\n\n" +

        "Rounds and allowed actions\n" +
        "Round 1: You are only allowed to forage ingredients, transmute ingredients,make experiment, and buy artifact cards.\n" +
        "Round 2: You are additionally allowed to sell a potion and publish theories.\n" +
        "Round 3: You are additionally allowed to  debunk theories.\n\n" ;

        // Set layout and add components
        setLayout(new BorderLayout());
        // Create a text area
        JTextArea helpTextArea = new JTextArea();
        helpTextArea.append(helpContent);
        helpTextArea.setEditable(false);
        // Add text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(helpTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Set text area properties
        helpTextArea.setFont(new Font("Arial", Font.PLAIN, 20));
        helpTextArea.setForeground(Color.WHITE);
        helpTextArea.setBackground(new Color(58, 77, 108)); // semi-transparent color

        // Set scroll pane properties
       helpTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 73, 94), 3),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

    }


}


