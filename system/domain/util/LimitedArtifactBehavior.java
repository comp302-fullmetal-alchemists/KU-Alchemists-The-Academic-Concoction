package system.domain.util;

import system.domain.interfaces.IUsingBehavior;

import javax.management.openmbean.InvalidOpenTypeException;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.IngredientStorageController;
import system.domain.controllers.TheoryController;

import system.domain.controllers.InventoryController;


public class LimitedArtifactBehavior implements IUsingBehavior{

    

    @Override
    public void useArtifact(ArtifactCard ac) {
        
        IngredientStorageController ingredientStorage = GameBoardController.getInstance().getIngredientStorageController();
        InventoryController inventoryController = GameBoardController.getInstance().getPlayer().getInventory();


        if (ac.getCardName().equals("Printing Press")) {

            inventoryController.setPrintingPress(true);
            ingredientStorage.getIngredientStorageUI().update(String.format("PRINTING_PRESS"));

       }
       
        else if (ac.getCardName().equals("Magic Mortar")) {
            IngredientCard lastIngredientCard = inventoryController.getLastIngredientCard();
            inventoryController.addIngredient(lastIngredientCard);

            ingredientStorage.getIngredientStorageUI().update(String.format("MAGIC_MORTAR: %s", lastIngredientCard.getCardName()));

       }
        else if (ac.getCardName().equals("Elixir of Insight")) {
            String cardNames = "";
            for (int i = 0; i < (IngredientFactory.getInstance().getIngredients().length < 3? IngredientFactory.getInstance().getIngredients().length: 3); i++) {
                cardNames += IngredientFactory.getInstance().getIngredients()[i] + ", ";
            }
            
            ingredientStorage.getIngredientStorageUI().update(String.format("ELIXIR_OF_INSIGHT: %s", cardNames));
       }

        else if (ac.getCardName().equals("Wisdom Idol")) {
            inventoryController.setWisdomIdol(true);
            ingredientStorage.getIngredientStorageUI().update(String.format("WISDOM_IDOL"));

        
        }


        
    }

    
    
    
}
