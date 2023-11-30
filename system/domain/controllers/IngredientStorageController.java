package system.domain.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import system.domain.ArtifactCard;
import system.domain.IngredientCard;

public class IngredientStorageController {
    // Public Storage for the players
    //IngredientStorage	ingredient pile: List<ingredientCard>
    //artifact pile: List<artifactCard>	transmuteIngredient(ingredientCard)
    //buyArtifact()

    private List<IngredientCard> ingredientPile;
    private List<ArtifactCard> artifactPile;
    Random random = new Random();

    public IngredientStorageController() {
        this.ingredientPile = new ArrayList<IngredientCard>();
        this.artifactPile = new ArrayList<ArtifactCard>();
        ingredientPile.add(new IngredientCard("Ingredient1",  null));
        ingredientPile.add(new IngredientCard("Ingredient2", null));
        ingredientPile.add(new IngredientCard("Ingredient3", null));
    }

    public IngredientCard transmuteIngredient(IngredientCard card) {
        giveIngredient(card);
        updateGold(2);
        
        return card;

    }

    public ArtifactCard buyArtifact() {
        //draw an artifact card object from the pile and add it to the artifact card list of the corresponding players inventory
        ArtifactCard artifact = drawArtifact();
        GameBoardController.getCurrentPlayer().getInventory().getArtifactCards().add(artifact);
        return artifact;
    }

    public IngredientCard drawIngredient() {
        if (ingredientPile.isEmpty()) {
            return null;
        }
        return ingredientPile.remove(0);
    }

    public ArtifactCard drawArtifact() {
        if (artifactPile.isEmpty()) {
            return null;
        }
        ArtifactCard drawed = artifactPile.remove(artifactPile.size() - 1);
        return drawed;
    }

}
