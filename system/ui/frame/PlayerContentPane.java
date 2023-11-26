package system.ui.frame;

import system.ui.panels.*;
import system.domain.Player;
import system.ui.interfaces.PlayerMediator;

import javax.swing.JPanel;

import java.awt.CardLayout;

public class PlayerContentPane extends JPanel {
    
    private CardLayout cards;
    private PlayerDashboard player1;
    private PlayerDashboard player2;
    private PlayerMediator mediator;

    // this should take player objects in the initialization and create dashboards here in itself
    public PlayerContentPane(Player p1, Player p2, PlayerMediator mediator) { 
        super();
        this.cards = new CardLayout();
        setLayout(cards);
        player1 = new PlayerDashboard(p1);
        player2 = new PlayerDashboard(p2);
        add(player1, "player1");
        add(player2, "player2");
        this.mediator = mediator;
        mediator.connectPlayer(player1);
    }

    public void changeView(String cardName) {
        cards.show(this, cardName);
    }

}
