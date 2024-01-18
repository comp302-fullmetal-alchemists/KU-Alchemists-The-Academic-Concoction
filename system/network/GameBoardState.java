package system.network;

import system.domain.util.ArtifactFactory;
import system.domain.util.IngredientFactory;
import system.domain.IngredientCard;
import system.domain.ArtifactCard;

import java.util.List;

public class GameBoardState {

    private IngredientFactory ingFactory;
    private ArtifactFactory artifactFactory;
    private List<IngredientCard> ingPile;
    private List<ArtifactCard> artifactPile;

    public GameBoardState() {
        this.ingFactory = new IngredientFactory();
        this.artifactFactory = new ArtifactFactory();
        ingPile = ingFactory.createIngredients(24);
        artifactPile = artifactFactory.createArtifacts();
    }

    public List<IngredientCard> getIngredientPile() {
        return ingPile;
    }

    public IngredientFactory getIngredientFactory() {
        return ingFactory;
    }




}
