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
        GameBoardController.getInstance().getCurrentPlayer().getInventory().giveIngredient(card);
        GameBoardController.getInstance().getCurrentPlayer().getInventory().updateGold(2);
        return card;

    }

    public ArtifactCard buyArtifact() {
        // control if the player has enough gold
        if (GameBoardController.getInstance().getCurrentPlayer().getInventory().getGold() < 3) {
            return null;
        }
        //draw an artifact card object from the pile and add it to the artifact card list of the corresponding players inventory
        ArtifactCard artifact = drawArtifact();
        if (artifact == null) {
            return null;
        }
        GameBoardController.getInstance().getCurrentPlayer().getInventory().getArtifactCards().add(artifact);
        useArtifact(artifact);
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

    public ArrayList<IngredientCard> useArtifact(ArtifactCard card) {
        if (card.getEffect().equals("Elixir of Insight")) {
            // player can see 3 ingredient cards from the pile
            ArrayList<IngredientCard> cards = new ArrayList<IngredientCard>();
            if (ingredientPile.size() < 3) {
                return null;
            }
            for (int i = 1; i <= 3; i++) {
                cards.add(ingredientPile.get(ingredientPile.size() - i));
            }
            
            return cards;
        }
        return null;
    }

}
