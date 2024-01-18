package system.ui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import system.domain.controllers.GameBoardController;
import system.domain.controllers.WelcomeController;
import system.ui.frame.Gameboard;

public class HostingScreen extends JPanel {
    private JButton startGameButton;
    private JButton backButton;
    private JLabel playerCountLabel;
    private JLabel ipAddressLabel;
    private WelcomeController controller;
    private Timer updateTimer;

    public HostingScreen(Gameboard gameBoard) {
        setBackground(new Color(58, 77, 108));
        controller = GameBoardController.getInstance().getWelcomeController();
        setLayout(null);
        setupUpdateTimer();

        startGameButton = new JButton("Start Game");
        startGameButton.setBounds(466, 562, 215, 49);
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int playerCount = controller.getServer().getClientSize();
                if (playerCount <= 1) {
                    JOptionPane.showMessageDialog(null, "You cannot start the game with only one player!");
                } else {
                    controller.authentication();
                }
            }
        });

        backButton = new JButton("Back");
        backButton.setBackground(new Color(204, 51, 204));
        backButton.setBounds(494, 31, 132, 59);
        backButton.addActionListener(e -> {
            gameBoard.showWelcomePagePanel();
            controller.getServer().stopServer();
            stopUpdateTimer();
        });

        playerCountLabel = new JLabel("Current number of players: 1");
        playerCountLabel.setForeground(Color.WHITE);
        playerCountLabel.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 19));
        playerCountLabel.setBounds(444, 470, 370, 38);

        ipAddressLabel = new JLabel("Your IP address is: " + getSafeIpAddress());
        ipAddressLabel.setForeground(Color.WHITE);
        ipAddressLabel.setFont(new Font("Telugu MN", Font.BOLD | Font.ITALIC, 19));
        ipAddressLabel.setBounds(438, 198, 319, 59);

        add(startGameButton);
        add(backButton);
        add(playerCountLabel);
        add(ipAddressLabel);
    }

    private String getSafeIpAddress() {
        try {
            return controller.learnIP();
        } catch (Exception e) {
            return "Unavailable";
        }
    }

    private void setupUpdateTimer() {
        updateTimer = new Timer();
        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> updatePlayerCountLabel());
            }
        }, 0, 1000); // Update every second
    }

    private void stopUpdateTimer() {
        if (updateTimer != null) {
            updateTimer.cancel();
        }
    }

    private void updatePlayerCountLabel() {
        if (controller.getServer() == null) {
            return;
        }
        int playerCount = controller.getServer().getClientSize();
        playerCountLabel.setText("Current number of players: " + playerCount);
    }
}
