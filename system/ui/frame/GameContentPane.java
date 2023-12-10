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
    private CardLayout cards;
    

    public GameContentPane() {
        super();
        this.village = new Village();
        this.ingredientStorage = new IngredientStorage();
        this.potionBrewingArea = new PotionBrewingArea();
        this.deductionBoard = new DeductionBoard();
        this.publicationArea = new PublicationArea();
        this.cards = new CardLayout();
        setLayout(cards);
        add(village, "village");
        add(ingredientStorage, "ingredientStorage");
        add(potionBrewingArea, "potionBrewingArea");
        add(deductionBoard, "deductionBoard");
        add(publicationArea, "publicationArea");
    }

    public void changeView(String cardName) {
    	if (ingredientStorage.isVisible()) ingredientStorage.clear();
    	else if (potionBrewingArea.isVisible()) potionBrewingArea.clear();
        cards.show(this, cardName);
        if (cardName.equals("ingredientStorage")) ingredientStorage.activate();
        else if (cardName.equals("potionBrewingArea")) potionBrewingArea.activate();
    }
}