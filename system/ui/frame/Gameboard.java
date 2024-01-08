package system.ui.frame;

import java.awt.Dialog;
import java.awt.EventQueue;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;
import system.ui.panels.AuthenticationPanel;
import system.ui.panels.HelpScreenPanel;
import system.ui.panels.OnlineGamePanel;
import system.ui.panels.WaitingScreen;
import system.ui.panels.WelcomePagePanel;

import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Gameboard extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	AuthenticationPanel authPanel;
    PlayerContentPane playerPane;
    GameContentPane gamePane;
    GameBoardController gameController;
	HelpScreenPanel helpScreen;
	WelcomePagePanel welcomePage;
	OnlineGamePanel onlinePanel;
	WaitingScreen waitingScreen;

	public Gameboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(70, 50, 650, 400);
		this.gameController = GameBoardController.getInstance();
		gameController.setObserver(this);
		this.authPanel = new AuthenticationPanel() ;
		welcomePage = new WelcomePagePanel(this);
		welcomePage.setBounds(0, 0, 1200, 800);
		this.waitingScreen = new WaitingScreen(this);
		waitingScreen.setBounds(0, 0, 1200, 800);
		setVisible(true);
		setResizable(false);
		welcomePage.setBounds(0, 0, 1200, 800);
		setVisible(true);
		setResizable(false);
		getContentPane().add(welcomePage);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.helpScreen = new HelpScreenPanel(); // Help screen panel object

	

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Help button on the menu bar
		JButton helpButton = new JButton("Help Screen");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Help screen panel object
				JOptionPane.showConfirmDialog(null, helpScreen, "Help Screen", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

			}
		});
		menuBar.add(helpButton);
	
		JButton pauseButton = new JButton("Pause/Resume");
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String options[] = {"Resume"};
                JOptionPane pane = new JOptionPane("Game Paused", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options , null);
                JDialog dialog = pane.createDialog(null, "Game Paused");
                dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialog.setVisible(true);
			}
		});
		menuBar.add(pauseButton);
		validate();
		repaint();
	}

	@Override
	public void update(String msg) {
		// TODO Auto-generated method stub
		if (msg.equals("INITIALIZE_BOARD")) {
            initializeTheBoard();
        }
		else if (msg.equals("CHANGING_PLAYER")) {
			clear();
		}
        else if (msg.equals("CHANGED_PLAYER")) {
            changePlayer();
        }
	}
	
	public void clear() {
		gamePane.changeView("village");
	}
	
	public void changePlayer() { 
		JOptionPane.showMessageDialog(this, String.format("It is now %s's turn", gameController.getCurrentPlayer().getName()));
        playerPane.changeView(gameController.getCurrentPlayer().getName());
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


	public void showAuthenticationPanel() {
		getContentPane().remove(waitingScreen);
		getContentPane().add(authPanel);
		authPanel.setBounds(600, 153, 0, 0);
		authPanel.setLayout(null);
		setSize(1200, 800);
		this.setResizable(false);
		setVisible(true);
		revalidate();
		repaint();
	}



    public void showWelcomePagePanel() {
		getContentPane().add(welcomePage);
		setVisible(true);
		revalidate();
		repaint();
    }

	public void showWaitingScreen() {
		getContentPane().remove(welcomePage);
		getContentPane().add(waitingScreen);
		setVisible(true);
		revalidate();
		repaint();
	}

}