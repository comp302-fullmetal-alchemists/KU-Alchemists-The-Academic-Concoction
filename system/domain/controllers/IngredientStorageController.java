package system.domain.controllers;

import java.util.ArrayList;
import java.util.List;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.interfaces.Observer;
import system.domain.Cards;

public class IngredientStorageController {
    //IngredientStorage	ingredient pile: List<ingredientCard>
    //artifact pile: List<artifactCard>	transmuteIngredient(ingredientCard)
    //buyArtifact()

    private List<IngredientCard> ingredientPile;
    private List<ArtifactCard> artifactPile;
    private Observer ingredientStorageUI;

    public IngredientStorageController() {
        this.ingredientPile = new ArrayList<IngredientCard>();
        this.artifactPile = new ArrayList<ArtifactCard>();
    }

    public void setObserver(Observer observer) {
        this.ingredientStorageUI = observer;
    }

    public void initializePiles() {
        ingredientPile.add(new IngredientCard("Ingredient1",  null));
        ingredientPile.add(new IngredientCard("Ingredient2", null));
        ingredientPile.add(new IngredientCard("Ingredient3", null));
        // pick cards from the pile and send them to each player
    }

    public IngredientCard transmuteIngredient(int index, IngredientCard card) {
        GameBoardController.getInstance().getPlayer(index).getInventory().giveIngredient(card);
        GameBoardController.getInstance().getPlayer(index).getInventory().updateGold(2);
        
        return card;

    }

    public void buyArtifact() {
        return;
    }

    public void drawIngredient() {
        if (ingredientPile.isEmpty()) {
            ingredientStorageUI.update("Pile is empty!");
        }
        else {
            IngredientCard drawn = ingredientPile.remove(0);
            GameBoardController.getInstance().getCurrentPlayer().getInventory().updateIngredients(drawn);
            ingredientStorageUI.update(String.format("CARDREMOVAL: %s", drawn.getName()));
        }
    }

}
