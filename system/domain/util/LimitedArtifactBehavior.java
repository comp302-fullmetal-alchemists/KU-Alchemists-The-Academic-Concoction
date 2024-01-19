package system.domain.util;

import java.util.Random;
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
    public int useArtifact(ArtifactCard ac) {
        
        IngredientStorageController ingredientStorage = GameBoardController.getInstance().getIngredientStorageController();
        InventoryController inventoryController = GameBoardController.getInstance().getPlayer().getInventory();


        if (ac.getCardName().equals("Printing Press")) {

            inventoryController.setPrintingPress(true);
            ingredientStorage.getIngredientStorageUI().update(String.format("PRINTING_PRESS"));
            return 1;
       }
       
        else if (ac.getCardName().equals("Magic Mortar")) {
            IngredientCard lastIngredientCard = inventoryController.getLastIngredientCard();
            if(lastIngredientCard == null){
                ingredientStorage.getIngredientStorageUI().update(String.format("MAGIC_MORTAR_NULL"));
                return -1;
            }
            else{
                inventoryController.addIngredient(lastIngredientCard);
                ingredientStorage.getIngredientStorageUI().update(String.format("MAGIC_MORTAR: %s", lastIngredientCard.getCardName()));
                return 1;
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
        return 1;
    }
    
    
}
