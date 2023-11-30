package system.domain.controllers;

import java.util.ArrayList;
import java.util.List;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;

public class IngredientStorageController {
    //IngredientStorage	ingredient pile: List<ingredientCard>
    //artifact pile: List<artifactCard>	transmuteIngredient(ingredientCard)
    //buyArtifact()

    private List<IngredientCard> ingredientPile;
    private List<ArtifactCard> artifactPile;

    public IngredientStorageController() {
        this.ingredientPile = new ArrayList<IngredientCard>();
        this.artifactPile = new ArrayList<ArtifactCard>();
        ingredientPile.add(new IngredientCard("Ingredient1",  null));
        ingredientPile.add(new IngredientCard("Ingredient2", null));
        ingredientPile.add(new IngredientCard("Ingredient3", null));
    }

    public IngredientCard transmuteIngredient(IngredientCard card) {
        GameBoardController.getCurrPlayer().getInventory().giveIngredient(card);
        GameBoardController.getCurrPlayer().getInventory().updateGold(2);
        
        return card;

    }

    public void buyArtifact() {
        return;
    }

    public IngredientCard drawIngredient() {
        if (ingredientPile.isEmpty()) {
            return null;
        }
        GameBoardController.getCurrPlayer().getInventory().updateInventory(ingredientPile.get(0));
        return ingredientPile.remove(0);
    }

}