package system.ui.frame;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;
import system.ui.panels.AuthenticationPanel;


public class Gameboard extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	AuthenticationPanel authPanel;
    PlayerContentPane playerPane;
    GameContentPane gamePane;
    GameBoardController gameController;
  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gameboard frame = new Gameboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Gameboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(70, 50, 650, 400);
		this.gameController = GameBoardController.getInstance();
		gameController.setObserver(this);
		this.authPanel = new AuthenticationPanel() ;
		authPanel.setBounds(600, 153, 0, 0);
		getContentPane().add(authPanel);
		authPanel.setLayout(null);
		setSize(1200, 800);
		this.setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		validate();
		
	}

	@Override
	public void update(String msg) {
		// TODO Auto-generated method stub
		if (msg.equals("INITIALIZE_BOARD")) {
            initializeTheBoard();
        }
        else if (msg.equals("CHANGE_PLAYER")) {
            changePlayer();
        }
	}
	
	public void changePlayer() { 
		JOptionPane.showMessageDialog(this, String.format("It is now %s's turn", gameController.getCurrentPlayer().getName()));
        playerPane.changeView(gameController.getCurrentPlayer().getName());
		gamePane.changeView("village");
    }

    public void initializeTheBoard() { 
        authPanel.setVisible(false);
        getContentPane().remove(authPanel);
        this.playerPane = new PlayerContentPane(gameController.getPlayer(0), gameController.getPlayer(1));
        this.gamePane = new GameContentPane();
        playerPane.setBounds(832,32, 335, 700);
        gamePane.setBounds(37,32,752, 700);
        playerPane.changeView(gameController.getCurrentPlayer().getName());
        getContentPane().setLayout(null);
        getContentPane().add(gamePane);
        getContentPane().add(playerPane);
        revalidate();
    }

}