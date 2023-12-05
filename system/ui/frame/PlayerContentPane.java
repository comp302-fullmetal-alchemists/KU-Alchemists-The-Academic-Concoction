package system.ui.frame;

import system.ui.panels.*;
import system.domain.Player;

import javax.swing.JPanel;

import java.awt.CardLayout;

public class PlayerContentPane extends JPanel {
    
    private CardLayout cards;
    private PlayerDashboard player1;
    private PlayerDashboard player2;

    // this should take player objects in the initialization and create dashboards here in itself
    public PlayerContentPane(Player p1, Player p2) { 
        super();
        this.cards = new CardLayout();
        setLayout(cards);
        player1 = new PlayerDashboard(p1);
        player2 = new PlayerDashboard(p2);
        add(player1, p1.getName());
        add(player2, p2.getName());
    }

    public void changeView(String cardName) {
        cards.show(this, cardName);
    }

}
