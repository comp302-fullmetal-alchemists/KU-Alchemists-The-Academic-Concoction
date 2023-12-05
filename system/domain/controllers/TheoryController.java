package system.domain.controllers;

import system.domain.Alchemy;
import system.domain.Theory;
import system.domain.interfaces.Observer;

import java.util.List;
import java.util.ArrayList;

public class TheoryController {
    //This can be created as map
    private List<Theory> theories;
    private Observer theoryUI;

    public TheoryController() {
        this.theories = new ArrayList<Theory>();
    }

    public void setObserver(Observer observer) {
        this.theoryUI = observer;
    }

    public void publishTheory(Alchemy alchemy, String ingredient) {
        for (Theory i : theories) {
            if (i.getAlchemy() == alchemy || i.getIngredient() == ingredient) {
                theoryUI.update("DUPLICATE_THEORY");
                return;
            }
        } 
        if (GameBoardController.getInstance().getCurrentPlayer().getInventory().getGold() < 1) {
            theoryUI.update("NOT_ENOUGH_GOLD");
            return;
        }
        else{
            GameBoardController.getInstance().getCurrentPlayer().getInventory().updateGold(-1);
            Theory theory = new Theory(alchemy, ingredient, GameBoardController.getInstance().getCurrentPlayer());
            theories.add(theory);
            theoryUI.update("THEORY_PUBLISHED");

        }
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
