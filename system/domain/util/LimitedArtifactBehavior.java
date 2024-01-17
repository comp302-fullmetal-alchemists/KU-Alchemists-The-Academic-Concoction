package system.domain.util;

import system.domain.interfaces.IUsingBehavior;
import system.domain.ArtifactCard;

import system.domain.controllers.GameBoardController;
import system.domain.controllers.IngredientStorageController;


public class LimitedArtifactBehavior implements IUsingBehavior{
    @Override
    public void useArtifact(ArtifactCard ac) {
       if (ac.getCardName().equals("Printing Press")) {

       }
       else if (ac.getCardName().equals("Elixir of Insight")) {
        String cardNames = "";
        IngredientStorageController ingredientStorage = GameBoardController.getInstance().getIngredientStorageController();
        for (int i = 0; i < (IngredientFactory.getInstance().getIngredients().length < 3? IngredientFactory.getInstance().getIngredients().length: 3); i++) {
            cardNames += IngredientFactory.getInstance().getIngredients()[i] + ", ";
        }
        
        ingredientStorage.getIngredientStorageUI().update(String.format("ELIXIR_OF_INSIGHT: %s", cardNames));
       }
        
    }
    
    
}
