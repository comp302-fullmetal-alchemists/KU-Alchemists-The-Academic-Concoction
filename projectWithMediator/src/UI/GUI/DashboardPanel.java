package UI.GUI;

import Domain.Player;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.CardLayout;

public class DashboardPanel extends JPanel {
    
    private PlayerDashboard playerD1;
    private PlayerDashboard playerD2;
    private CardLayout cards;
    private Mediator mediator;

    public DashboardPanel(Player player1, Player player2, Mediator mediator) {
        super();
        this.playerD1 = new PlayerDashboard(player1, mediator);
        this.playerD2 = new PlayerDashboard(player2, mediator);
        this.cards = new CardLayout();
        this.mediator = mediator;
        setLayout(cards);
        add(playerD1, player1.getName());
        add(playerD2, player2.getName());
    }

    public void changeToCard(String cardName) {
        cards.show(this, cardName);
        notifyMediator();
    }

    public void changeTurns() {
        cards.next(this);
        notifyMediator();
    }

    public void notifyMediator() {
        if (playerD1.isVisible()) mediator.startSessionWith(playerD1);
        else mediator.startSessionWith(playerD2);
    }

}
