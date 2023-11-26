package system.domain;

import java.util.ArrayList;
import java.util.List;

public class IngredientStorage {
    //IngredientStorage	ingredient pile: List<ingredientCard>
    //artifact pile: List<artifactCard>	transmuteIngredient(ingredientCard)
    //buyArtifact()

    private List<IngredientCard> ingredientPile;
    private List<ArtifactCard> artifactPile;

    public IngredientStorage() {
        this.ingredientPile = new ArrayList<IngredientCard>();
        this.artifactPile = new ArrayList<ArtifactCard>();
    }

    public void transmuteIngredient(IngredientCard card) {
        return;
    }

    public void buyArtifact() {
        return;
    }

    public void drawIngredient() {
        return;
    }

}
