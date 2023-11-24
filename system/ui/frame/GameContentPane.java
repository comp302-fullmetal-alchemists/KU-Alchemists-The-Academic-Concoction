package system.ui.frame;

import system.ui.panels.*;

import javax.swing.JPanel;

import java.awt.CardLayout;

public class GameContentPane extends JPanel {
    
    private GameEnvironment gameEnvironment;
    private IngredientStorage ingredientStorage;
    private CardLayout cards;

    public GameContentPane() {
        super();
        this.gameEnvironment = new GameEnvironment();
        this.ingredientStorage = new IngredientStorage();

        this.cards = new CardLayout();
        setLayout(cards);
        add(gameEnvironment, "environment");
        add(ingredientStorage, "ingredientStorage");
    }

    public void changeView(String cardName) {
        cards.show(this, cardName);
    }
}
