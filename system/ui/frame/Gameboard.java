package system.ui.frame;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;
import system.ui.interfaces.Mediator;
import system.ui.interfaces.PlayerMediator;
import system.ui.panels.AuthenticationPanel;


public class Gameboard extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	AuthenticationPanel authPanel;
    PlayerContentPane playerPane;
    GameContentPane gamePane;
    PlayerMediator mediator;
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
		this.mediator = new Mediator();
		setSize(859, 607);
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
        playerPane.changeView(gameController.getCurrentPlayer().getName());
    }

    public void initializeTheBoard() { 
        remove(authPanel);
        getContentPane().setLayout(new GridLayout(1, 2));
        this.playerPane = new PlayerContentPane(gameController.getPlayer(0), gameController.getPlayer(1), mediator);
        this.gamePane = new GameContentPane(mediator);
        playerPane.changeView(gameController.getCurrentPlayer().getName());
        getContentPane().add(gamePane);
        getContentPane().add(playerPane);
        revalidate();
    }

}