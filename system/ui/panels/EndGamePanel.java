package system.ui.panels;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map.Entry;
import system.domain.controllers.GameBoardController;

public class EndGamePanel extends JPanel {

    private HashMap<String, Integer> winnerList;

    public EndGamePanel() {
        winnerList = new HashMap<>(); // Initialize an empty HashMap
        setBackground(new Color(58, 77, 108));
        setLayout(null);
        initializeComponents(); // Initialize UI components
    }

    // Method to initialize components
    private void initializeComponents() {
        JLabel gameOverLbl = new JLabel("GAME OVER!");
        gameOverLbl.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 23));
        gameOverLbl.setForeground(Color.WHITE);
        gameOverLbl.setBounds(133, 6, 242, 38);
        add(gameOverLbl);

        JLabel winnerLbl = new JLabel("WINNER!!!!");
        winnerLbl.setForeground(Color.WHITE);
        winnerLbl.setBounds(18, 70, 122, 16);
        add(winnerLbl);

        updateScoreLabels();
    }

    // Method to update the score labels
    private void updateScoreLabels() {
        int yPos = 98; // Starting Y position for the first score label
        int yIncrement = 30; 

        if (!winnerList.isEmpty()) {
            for (Entry<String, Integer> entry : winnerList.entrySet()) {
                JLabel playerScoreLbl = new JLabel(entry.getKey() + " Score: " + entry.getValue());
                playerScoreLbl.setForeground(Color.WHITE);
                playerScoreLbl.setBounds(18, yPos, 200, 16);
                add(playerScoreLbl);

                yPos += yIncrement; 
            }
        }
    }

    // Method to update winnerList and refresh the panel
    public void updateWinnerList(HashMap<String, Integer> newWinnerList) {
        this.winnerList = newWinnerList;
        removeAll(); // Clear existing components
        initializeComponents(); // Reinitialize components
        revalidate();
        repaint();
    }
}
