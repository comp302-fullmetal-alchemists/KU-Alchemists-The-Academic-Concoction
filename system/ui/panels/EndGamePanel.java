package system.ui.panels;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import system.domain.controllers.GameBoardController;
import system.network.OfflineClient;
import system.network.OfflineServer;

public class EndGamePanel extends JPanel{

    OfflineClient offlineClient;
    OfflineServer offlineServer;
    GameBoardController gameBoardController;

    public EndGamePanel(){
    
        setBackground(new Color(58, 77, 108));
        setLayout(null);
		
	    JLabel gameOverLbl = new JLabel("GAME OVER!");
	    gameOverLbl.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 23));
        gameOverLbl.setForeground(Color.WHITE);
	    gameOverLbl.setBounds(133, 6, 242, 38);
        add(gameOverLbl);

		//Server will call to display this at the end, change text fields accordingly and make it prettier
		
	    JLabel playerLbl = new JLabel( "Player1 Score:");
	    playerLbl.setForeground(Color.WHITE);
	    playerLbl.setBounds(18, 98, 96, 16);
	    add(playerLbl);
		
	    JLabel playerLbl3 = new JLabel("Player1 Score:");
	    playerLbl3.setForeground(Color.WHITE);
	    playerLbl3.setBounds(18, 186, 96, 16);
	    add(playerLbl3);
		
	    JLabel playerLbl2 = new JLabel("Player1 Score:");
	    playerLbl2.setForeground(Color.WHITE);
	    playerLbl2.setBounds(255, 98, 96, 16);
	    add(playerLbl2);
		
	    JLabel playerLbl4 = new JLabel("Player1 Score:");
	    playerLbl4.setForeground(Color.WHITE);
	    playerLbl4.setBounds(255, 186, 96, 16);
	    add(playerLbl4);
		
	    JButton exitGameBttn = new JButton("exit game");
	    exitGameBttn.setBounds(312, 253, 117, 29);
	    add(exitGameBttn);
		
	    JLabel winnerLbl = new JLabel("WİNNER!!!!");
	    winnerLbl.setForeground(Color.WHITE);
	    winnerLbl.setBounds(18, 70, 122, 16);
	    add(winnerLbl);
		
	    JLabel announceLbl = new JLabel("Player1 won with score ...!!! ");
	    announceLbl.setForeground(Color.WHITE);
	    announceLbl.setBounds(18, 258, 197, 16);
	    add(announceLbl);

    }

}
