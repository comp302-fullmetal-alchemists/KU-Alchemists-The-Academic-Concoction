/*package system.ui.frame;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;
import system.ui.panels.AuthenticationPanel;
import system.ui.panels.HelpScreenPanel;*/
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

import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Gameboard extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	AuthenticationPanel authPanel;
    PlayerContentPane playerPane;
    GameContentPane gamePane;
    GameBoardController gameController;

	private JButton helpButton;
	private JButton pauseButton;
	private HelpScreenPanel helpScreen;

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
		this.helpScreen = new HelpScreenPanel();
	

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		JButton helpButton = new JButton("Help Screen");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane pane = new JOptionPane (helpContent, JOptionPane.INFORMATION_MESSAGE);
				JDialog dialog = pane.createDialog(null, "Help Screen");
				dialog.setVisible(true);	
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