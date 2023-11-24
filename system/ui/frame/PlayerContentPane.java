package system.ui.frame;

import system.ui.panels.*;

import javax.swing.JPanel;

import java.awt.CardLayout;

public class PlayerContentPane extends JPanel {
    
    private CardLayout cards;
    private PlayerDashboard player1;
    private PlayerDashboard player2;


    // this should take player objects in the initialization and create dashboards here in itself
    public PlayerContentPane() { 
        super();
        this.cards = new CardLayout();
        setLayout(cards);
    }

    public void changeView(String cardName) {
        cards.show(this, cardName);
    }

}
