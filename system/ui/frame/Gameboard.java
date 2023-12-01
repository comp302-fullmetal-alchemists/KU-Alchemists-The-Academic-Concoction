package system.ui.frame;

import system.domain.Player;
import system.domain.controllers.GameBoardController;
import system.ui.interfaces.*;
import system.ui.panels.AuthenticationPanel;

import javax.swing.JFrame;

import java.awt.GridLayout;

public class Gameboard extends JFrame{

    AuthenticationPanel authPanel;
    PlayerContentPane playerPane;
    GameContentPane gamePane;
    PlayerMediator mediator;
    GameBoardController gameController;
    
    public Gameboard() {
        super();
        this.gameController = GameBoardController.getInstance();

        this.authPanel = new AuthenticationPanel(this);
        add(authPanel);
        this.mediator = new Mediator();
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }

    public void initializeTheBoard() {
        remove(authPanel);
        setLayout(new GridLayout(1, 2));
        this.playerPane = new PlayerContentPane(gameController.getPlayer(0), gameController.getPlayer(1), mediator);
        this.gamePane = new GameContentPane(mediator);
        add(gamePane);
        add(playerPane);
        revalidate();
    }

    public GameBoardController getController() {
        return gameController;
    }

}