package system.ui.interfaces;

import system.ui.panels.PlayerDashboard;
import system.domain.IngredientCard;
import system.ui.panels.IngredientStorage;

public class Mediator implements PlayerMediator{

    private PlayerDashboard player;
    private IngredientStorage ingStorage;

    public Mediator() {

    }

    @Override
    public void connectPlayer(PlayerDashboard player) {
        this.player = player;
    }

    @Override
    public void connectActionSpace(IngredientStorage ing) {
        this.ingStorage = ing;
    }

    @Override
    public void sendIngredientsToPlayer(IngredientCard ingredient) {
        player.takeIngredients(ingredient);
    }

    @Override
    public void sendArtifactsToPlayer(String artifacts) {
        
    }

    @Override
    public void sendPotionToPlayer(String potion) {
       
    }

    @Override
    public void sendIngredientsToActionSpace(IngredientCard card) {
        
    }

    @Override
    public void sendPotionToActionSpace(String potion) {
        
    }
    
}
