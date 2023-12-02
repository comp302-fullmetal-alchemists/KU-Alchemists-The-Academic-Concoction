package system.domain.interfaces;

import system.domain.IngredientCard;
import system.domain.Potion;
import system.domain.ArtifactCard;

public interface Collector {
    
    void collectIngredient(IngredientCard ing);

    void collectPotion(Potion potion);

    //actually this would be unnecessary as far as my understanding of the game, neither of IngStorage,PotionArea,PubliationArea,DeductionBoard
    //collects artifacts from players. But still it can wait.
    void collectArtifact(ArtifactCard artifact);

}
