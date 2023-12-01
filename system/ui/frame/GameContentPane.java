package system.ui.frame;

import system.ui.panels.*;
import system.ui.interfaces.*;

import javax.swing.JPanel;

import java.awt.CardLayout;

public class GameContentPane extends JPanel {
    
    private Village gameEnvironment;
    private IngredientStorage ingredientStorage;
    private PotionBrewingArea potionBrewingArea;
    private DeductionBoard deductionBoard;
    private PublicationArea publicationArea;
    private MainMenu mainMenu;
    private CardLayout cards;
    private PlayerMediator mediator;

    public GameContentPane(PlayerMediator mediator) {
        super();
        this.mediator = mediator;
        this.gameEnvironment = new Village();
        this.ingredientStorage = new IngredientStorage(mediator);
        this.potionBrewingArea = new PotionBrewingArea(mediator);
        this.deductionBoard = new DeductionBoard(mediator);
        this.publicationArea = new PublicationArea(mediator);
        this.mainMenu = new MainMenu(mediator);
        this.cards = new CardLayout();
        setLayout(cards);
        add(gameEnvironment, "environment");
        add(ingredientStorage, "ingredientStorage");
        add(potionBrewingArea, "potionBrewingArea");
        add(deductionBoard, "deductionBoard");
        add(publicationArea, "publicationArea");
        add(mainMenu, "mainMenu");
        mediator.connectActionSpace(ingredientStorage);
    }

    public void changeView(String cardName) {
        cards.show(this, cardName);
    }
}
