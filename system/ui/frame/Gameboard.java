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
import system.ui.panels.HostingScreen;
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
	HostingScreen hostingScreen;

	public Gameboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(70, 50, 650, 400);
		this.gameController = GameBoardController.getInstance();
		gameController.setObserver(this);
		this.authPanel = new AuthenticationPanel();
		setSize(1200, 800);
		this.setResizable(false);
		welcomePage = new WelcomePagePanel(this);
		welcomePage.setBounds(0, 0, 1200, 800);
		this.waitingScreen = new WaitingScreen(this);
		waitingScreen.setBounds(0, 0, 1200, 800);
		this.hostingScreen = new HostingScreen(this);
		hostingScreen.setBounds(0, 0, 1200, 800);
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

		JButton exitButton = new JButton("Exit Game");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String options[] = {"Yes", "No"};
				JOptionPane pane = new JOptionPane("Are you sure you want to exit?", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options , null);
				JDialog dialog = pane.createDialog(null, "Exit Game");
				dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
				dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				dialog.setVisible(true);
				if (pane.getValue() == "Yes") {
					if (GameBoardController.getInstance().getClientAdapter() != null) {
						GameBoardController.getInstance().getClientAdapter().reportExitGameToServer();
					}
					else {
						System.exit(0);
					}
				}
			}
		});
		menuBar.add(exitButton);

		validate();
		repaint();
	}

	@Override
	public void update(String msg) {
		// TODO Auto-generated method stub
		if (msg.equals("INITIALIZE_BOARD")) {
            initializeTheBoard();
        }
		else if (msg.equals("INITIALIZE_PLAYER")) {
			initializePlayer();
		}
		else if (msg.equals("DEAUTHORIZATION")) {
			clear();
		}
        else if (msg.equals("AUTHORIZATION")) {
            changePlayer();
        }
		else if (msg.equals("AUTHENTICATION")) {
			showAuthenticationPanel();
		}
		else if (msg.equals("NEW_GAME")) {
			showWelcomePagePanel();
		}
	}
	
	public void clear() {
		gamePane.changeView("village");
	}

	public void initializePlayer() {
		playerPane.addPlayerDashboard(gameController.getPlayer());
		playerPane.changeView(gameController.getPlayer().getName());
	}

	public void changePlayer() {
		playerPane.addPlayerDashboard(gameController.getPlayer());
		JOptionPane.showMessageDialog(this, String.format("It is now %s's turn", gameController.getPlayer().getName()));
        playerPane.changeView(gameController.getPlayer().getName());
    }

    public void initializeTheBoard() { 
        authPanel.setVisible(false);
        getContentPane().remove(authPanel);
        this.gamePane = new GameContentPane();
		this.playerPane = new PlayerContentPane();
        playerPane.setBounds(832,32, 335, 700);
        gamePane.setBounds(37,32,752, 700); 
        getContentPane().setLayout(null);
        getContentPane().add(gamePane);
        getContentPane().add(playerPane);
        revalidate();
    }


	public void showAuthenticationPanel() {
		getContentPane().removeAll();
		//getContentPane().remove(waitingScreen);
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
		getContentPane().removeAll();
		getContentPane().add(welcomePage);
		setVisible(true);
		revalidate();
		repaint();
    }

	public void showWaitingScreen() {
		getContentPane().remove(welcomePage);
		getContentPane().add(waitingScreen);
		waitingScreen.setVisible(true);
		revalidate();
		repaint();
	}

	public void showHostingScreen() {
		getContentPane().remove(welcomePage);
		getContentPane().add(hostingScreen);
		waitingScreen.setVisible(true);
		revalidate();
		repaint();
	}

}