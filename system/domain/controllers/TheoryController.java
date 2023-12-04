package system.domain.controllers;

import system.domain.Alchemy;
import system.domain.IngredientCard;
import system.domain.Theory;

public class TheoryController {

    public TheoryController() {
    }

    public void publishTheory(Alchemy alchemy, IngredientCard ingredient) {
        return;
    }

    public void debunkTheory(Alchemy alchemy, Theory theory, int index) {
        if(theory.getAlchemy() == alchemy && theory.getOwner() != GameBoardController.getInstance().getPlayer(index)) {
            //System.err.println("Theory debunked");
            GameBoardController.getInstance().getPlayer(index).getInventory().updateGold(2);
            theory.setDebunked(true);
            theory.setOwner(GameBoardController.getInstance().getPlayer(index));
        }
        else {
            //System.err.println("Theory not debunked");
        }
        return;
    }
    
}
