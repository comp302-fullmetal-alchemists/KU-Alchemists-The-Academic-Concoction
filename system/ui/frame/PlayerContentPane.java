package system.ui.frame;

import system.domain.controllers.Player;
import system.ui.panels.*;

import javax.swing.JPanel;

import java.awt.CardLayout;
import java.util.List;
import java.util.ArrayList;

public class PlayerContentPane extends JPanel {
    
    private CardLayout cards;
    private List<String> playerNames;
    
    // this should take player objects in the initialization and create dashboards here in itself
    public PlayerContentPane() { 
        super();
        this.cards = new CardLayout();
        setLayout(cards);
        playerNames = new ArrayList<String>();
    }

    public void addPlayerDashboard(Player player) {
        if (!playerNames.contains(player.getName())) {
            PlayerDashboard playerDB = new PlayerDashboard(player);
            playerNames.add(player.getName());

            add(playerDB, player.getName());
        }
    }

    public void changeView(String playerName) {
        cards.show(this, playerName);
    }

}
