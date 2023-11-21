package UI.GUI;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.CardLayout;

public class GamePanel extends JPanel{

    private GameArea g1;
    private GameArea g2;
    private GameEnvironment gameEnvironment;
    private CardLayout cards;
    private Mediator mediator;

    public GamePanel(Mediator mediator){
        super();
        this.g1 = new GameArea(0, mediator);
        this.g2 = new GameArea(1, mediator);
        this.gameEnvironment = new GameEnvironment();
        this.cards = new CardLayout();
        this.mediator = mediator;
        setLayout(cards);
        add(gameEnvironment, "environment");
        add(g1, "GameArea1");
        add(g2, "GameArea2");

    }

    public void changeToCard(String cardName) {
        cards.show(this, cardName);
        if (cardName.equals("GameArea1")) mediator.startSessionWith(g1);
        else mediator.startSessionWith(g2);
    }

}
