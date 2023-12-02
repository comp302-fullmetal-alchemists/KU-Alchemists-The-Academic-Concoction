package system.domain.controllers;

import system.domain.AlchemyMarker;
import system.domain.IngredientCard;
import system.domain.Theory;
import java.util.List;

public class TheoryController {

    private List<Theory> theories;

    public TheoryController() {
    }

    public void publishTheory(AlchemyMarker alchemy, IngredientCard ingredient) {
        for (Theory i : theories) {
            if (i.getAlchemy() == alchemy && i.getIngredient() == ingredient) {
                System.err.println("Theory has already published");
                return;
            }
        } 
        if (GameBoardController.getInstance().getCurrentPlayer().getInventory().getGold() < 1) {
            System.err.println("Not enough gold");
            return;
        }
        else;
            GameBoardController.getInstance().getCurrentPlayer().getInventory().updateGold(-1);
            Theory theory = new Theory(alchemy, ingredient, GameBoardController.getInstance().getCurrentPlayer());
            theories.add(theory);
        return;
    }

    public void debunkTheory(AlchemyMarker alchemy, Theory theory, int index) {
        if(theory.getAlchemy() == alchemy && theory.getOwner() != GameBoardController.getInstance().getPlayer(index)) {
            System.err.println("Theory debunked");
            GameBoardController.getInstance().getPlayer(index).getInventory().updateGold(2);
            theory.setDebunked(true);
            theory.setOwner(GameBoardController.getInstance().getPlayer(index));
        }
        else {
            System.err.println("Theory not debunked");
        }
        return;
    }
    
}
