package system.ui.frame;

import system.domain.Player;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;
import system.ui.interfaces.*;
import system.ui.panels.AuthenticationPanel;

import javax.swing.JFrame;

import java.awt.GridLayout;

public class Gameboard extends JFrame implements Observer{

    AuthenticationPanel authPanel;
    PlayerContentPane playerPane;
    GameContentPane gamePane;
    PlayerMediator mediator;
    GameBoardController gameController;
    
    public Gameboard() {
        super();
        this.gameController = GameBoardController.getInstance();
        gameController.setObserver(this);
        this.authPanel = new AuthenticationPanel();
        add(authPanel);
        this.mediator = new Mediator();
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }
    
    @Override
    public void update(String msg) {
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
        setLayout(new GridLayout(1, 2));
        this.playerPane = new PlayerContentPane(gameController.getPlayer(0), gameController.getPlayer(1), mediator);
        this.gamePane = new GameContentPane(mediator);
        playerPane.changeView(gameController.getCurrentPlayer().getName());
        add(gamePane);
        add(playerPane);
        revalidate();
    }


}