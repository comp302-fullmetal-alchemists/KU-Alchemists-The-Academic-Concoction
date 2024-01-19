package system.domain.util;

import system.domain.interfaces.IUsingBehavior;
import system.domain.ArtifactCard;
import system.domain.controllers.*;

public class UnlimitedArtifactBehavior implements IUsingBehavior{
    @Override
    public int useArtifact(ArtifactCard ac) {
        if (ac.getCardName().equals("Discount Card")) {

        IngredientStorageController ingController = GameBoardController.getInstance().getIngredientStorageController();
        InventoryController invController = GameBoardController.getInstance().getPlayer().getInventory();
        if (invController.getDiscountCard() ==  -1) {
            invController.discountCardButton();
        }
         ingController.getIngredientStorageUI().update(String.format("DISCOUNT_CARD"));
       }
       return 1;
    }
}
