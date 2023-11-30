package system.domain.controllers;

import system.domain.AlchemyMarker;
import system.domain.IngredientCard;
import system.domain.Theory;

public class TheoryController {

    public TheoryController() {
    }

    public void publishTheory(AlchemyMarker alchemy, IngredientCard ingredient) {
        return;
    }

    public void debunkTheory(AlchemyMarker alchemy, Theory theory, int index) {
        if(theory.getAlchemy() == alchemy && theory.getOwner() != GameBoardController.getPlayer(index)) {
            System.err.println("Theory debunked");
            GameBoardController.getPlayer(index).getInventory().updateGold(2);
            theory.setDebunked(true);
            theory.setOwner(GameBoardController.getPlayer(index));
        }
        else {
            System.err.println("Theory not debunked");
        }
        return;
    }
    
}
