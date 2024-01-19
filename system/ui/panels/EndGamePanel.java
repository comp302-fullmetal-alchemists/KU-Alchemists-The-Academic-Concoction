package system.ui.panels;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.ImageIcon;
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
        gameOverLbl.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 80));
        gameOverLbl.setForeground(Color.WHITE);
        gameOverLbl.setBounds(350, 70, 600, 150);
        add(gameOverLbl);

        JLabel winnerLbl = new JLabel("WINNER!!!!");
        winnerLbl.setForeground(Color.WHITE);
        winnerLbl.setBounds(530, 200, 240, 80);
        winnerLbl.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 40));
        add(winnerLbl);

        updateScoreLabels();
    }

    // Method to update the score labels
    private void updateScoreLabels() {
        if (!winnerList.isEmpty()) {
            int lstlength = winnerList.size();
            int xpos = 1300 / (lstlength);
            int count=0;  
            //print the lstlength
            System.out.println(lstlength);
            System.out.println(xpos);

            for (Entry<String, Integer> entry : winnerList.entrySet()){
                String winnerName = entry.getKey().split(",")[0];
                Integer tokenIndex = Integer.parseInt(entry.getKey().split(",")[1]);
                JLabel playerScoreLbl = new JLabel(winnerName + " Score: " + entry.getValue());
                if (count==0){
                    playerScoreLbl.setBounds(600, 280, 200, 50);
                    playerScoreLbl.setForeground(Color.WHITE);
                    add(playerScoreLbl);
                    JLabel lblToken = new JLabel("");
                    lblToken.setBounds(550, 330, 200, 200);
                    lblToken.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/token" + tokenIndex + ".png")).getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
                    add(lblToken);
                }

                else{
                    playerScoreLbl.setBounds(xpos-50, 530, 200, 50);
                    playerScoreLbl.setForeground(Color.WHITE);
                    add(playerScoreLbl);
                    JLabel lblToken = new JLabel("");
                    lblToken.setBounds(xpos-50, 570, 100, 100);
                    lblToken.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/token" + tokenIndex + ".png")).getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
                    add(lblToken);
                    xpos += 1300 / (lstlength + 1);
                }
                count++;

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
