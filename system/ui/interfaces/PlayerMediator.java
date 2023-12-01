package system.ui.interfaces;

import system.domain.IngredientCard;
import system.ui.panels.PlayerDashboard;
import system.ui.panels.IngredientStorage;

public interface PlayerMediator {
    
    void connectPlayer(PlayerDashboard player);

    void connectActionSpace(IngredientStorage ing);

    void sendIngredientsToPlayer(IngredientCard ingredients);

    void sendArtifactsToPlayer(String artifacts);
    
    void sendPotionToPlayer(String potion);

    void sendIngredientsToActionSpace(IngredientCard card);

    void sendPotionToActionSpace(String potion);
}
