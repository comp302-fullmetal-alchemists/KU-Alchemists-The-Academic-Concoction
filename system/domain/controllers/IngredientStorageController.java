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

    public IngredientCard drawIngredient(int index) {
        if (ingredientPile.isEmpty()) {
            return null;
        }
        //this function actually in the inventory controller I want to use it here but I don't know how to call it
        GameBoardController.getInstance().getPlayer(index).getInventory().updateInventory(ingredientPile.get(0));
        return ingredientPile.remove(0);
    }

}
