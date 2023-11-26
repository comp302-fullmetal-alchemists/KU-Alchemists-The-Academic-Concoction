package system.ui.frame;

import system.ui.panels.*;
import system.ui.interfaces.*;

import javax.swing.JPanel;

import java.awt.CardLayout;

public class GameContentPane extends JPanel {
    
    private Village gameEnvironment;
    private IngredientStorage ingredientStorage;
    private CardLayout cards;
    private PlayerMediator mediator;

    public GameContentPane(PlayerMediator mediator) {
        super();
        this.mediator = mediator;
        this.gameEnvironment = new Village();
        this.ingredientStorage = new IngredientStorage(mediator);
        this.cards = new CardLayout();
        setLayout(cards);
        add(gameEnvironment, "environment");
        add(ingredientStorage, "ingredientStorage");
        mediator.connectActionSpace(ingredientStorage);
    }

    public void changeView(String cardName) {
        cards.show(this, cardName);
    }
}
