package system.domain.util;

import java.util.Random;
import system.domain.interfaces.IUsingBehavior;
import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.IngredientStorageController;
import system.domain.controllers.InventoryController;


public class LimitedArtifactBehavior implements IUsingBehavior{

    

    @Override
    public int useArtifact(ArtifactCard ac) {
        
        IngredientStorageController ingredientStorage = GameBoardController.getInstance().getIngredientStorageController();
        InventoryController inventoryController = GameBoardController.getInstance().getPlayer().getInventory();


        if (ac.getCardName().equals("Printing Press")) {

            inventoryController.setPrintingPress(true);
            ingredientStorage.getIngredientStorageUI().update(String.format("PRINTING_PRESS"));
            return 1;
       }
       
        else if (ac.getCardName().equals("Magic Mortar")) {
            if (!(inventoryController.getLastIngredientCard() == null)) {
                IngredientCard lastIngredientCard = inventoryController.getLastIngredientCard();
            inventoryController.addIngredient(lastIngredientCard);
            ingredientStorage.getIngredientStorageUI().update(String.format("MAGIC_MORTAR: %s", lastIngredientCard.getCardName()));
            }
            else {
                ingredientStorage.getIngredientStorageUI().update(String.format("MAGIC_MORTAR_UNAVAILABLE"));    
                GameBoardController.getInstance().getPlayer().getInventory().addArtifact(ac);

            }

       }
  

        else if (ac.getCardName().equals("Elixir of Insight")) {
            GameBoardController.getInstance().getClientAdapter().peek3Ingredients();
       }

        else if (ac.getCardName().equals("Wisdom Idol")) {
            inventoryController.setWisdomIdol(true);
            ingredientStorage.getIngredientStorageUI().update(String.format("WISDOM_IDOL"));
            return 1;
        }


        
    }

    
    
    
}
