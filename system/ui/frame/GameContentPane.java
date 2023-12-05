package system.ui.frame;

import system.ui.panels.*;
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

    public GameContentPane() {
        super();
        this.village = new Village();
        this.ingredientStorage = new IngredientStorage();
        this.potionBrewingArea = new PotionBrewingArea();
        this.deductionBoard = new DeductionBoard();
        this.publicationArea = new PublicationArea();
        this.mainMenu = new MainMenuPanel();
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
    }

    public void changeView(String cardName) {
        cards.show(this, cardName);
        switch (cardName) {
            case "ingredientStorage": ingredientStorage.initialize();
            case "potionBrewingArea": potionBrewingArea.initialize();
        }
    }
}