package system.domain.interfaces;

import system.domain.IngredientCard;
import system.domain.Potion;
import system.domain.ArtifactCard;

public interface Collector {

    <T> boolean collectItem(T item);

    void activate();
    
    void deactivate();

    boolean isActive();
}
