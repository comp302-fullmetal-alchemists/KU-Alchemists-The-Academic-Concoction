package system.ui.frame;

import system.ui.panels.*;
import system.ui.interfaces.*;

import javax.swing.JPanel;

import java.awt.CardLayout;

public class GameContentPane extends JPanel {
    
    private Village village;
    private IngredientStorage ingredientStorage;
    private PotionBrewingArea potionBrewingArea;
    private DeductionBoard deductionBoard;
    private PublicationArea publicationArea;
    private MainMenuPanel mainMenu;
    private HelpScreenPanel helpScreen;
    private CardLayout cards;
    private PlayerMediator mediator;

    public GameContentPane(PlayerMediator mediator) {
        super();
        this.mediator = mediator;
        this.village = new Village();
        this.ingredientStorage = new IngredientStorage(mediator);
        this.potionBrewingArea = new PotionBrewingArea(mediator);
        this.deductionBoard = new DeductionBoard(mediator);
        this.publicationArea = new PublicationArea(mediator);
        this.mainMenu = new MainMenuPanel(mediator);
        this.helpScreen = new HelpScreenPanel();
        this.cards = new CardLayout();
        setLayout(cards);
        add(village, "village");
        add(ingredientStorage, "ingredientStorage");
        add(potionBrewingArea, "potionBrewingArea");
        add(deductionBoard, "deductionBoard");
        add(publicationArea, "publicationArea");
        add(mainMenu, "mainMenu");
        add(helpScreen, "helpScreen");
        mediator.connectActionSpace(ingredientStorage);
    }

    public void changeView(String cardName) {
        cards.show(this, cardName);
    }
}
