package system.domain.controllers;

import system.domain.Alchemy;
import system.domain.GameAction;
import system.domain.Theory;
import system.domain.interfaces.Mediator;
import system.domain.interfaces.Observer;
import system.domain.Player;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class TheoryController {
    //This can be created as map
    private List<Theory> theories;
    private Observer theoryUI;
    private GameLogController gameLog;
    private GameAction gameAction;
    private Map<String, Alchemy> alchemyMap;

    public TheoryController() {
        this.theories = new ArrayList<Theory>();
        this.gameLog = GameBoardController.getInstance().getGameLog();
        this.alchemyMap = GameBoardController.getInstance().getAlchemyMap();

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

            //GAMELOG RECORDS LOG: When a player publishes a theory
            gameAction = new GameAction(GameBoardController.getInstance().getCurrentPlayer().getName(), "Everyone", String.format("Published the Theory: %s!", theory.getIngredient()), 0);
            gameLog.recordLog(GameBoardController.getInstance().getCurrentPlayer(), gameAction);
        }
        return;
    }

    public void debunkTheory(Alchemy alchemy, Theory theory, Player player) {
        if(theory.getAlchemy() != alchemy && theory.getOwner() != GameBoardController.getInstance().getCurrentPlayer()) {
            for (Theory i : theories) {
                if (i.getIngredient()== theory.getIngredient()) {
                    for (String key: alchemyMap.keySet()) {
                        if (alchemyMap.get(theory.getIngredient()) == alchemy) {
                            theoryUI.update("THEORY_DEBUNKED");
                            i.setAlchemy(alchemy);
                            i.setOwner(GameBoardController.getInstance().getCurrentPlayer());
                            i.setDebunked(true);
                            GameBoardController.getInstance().getCurrentPlayer().getInventory().updateGold(2);

                            //GAMELOG RECORDS LOG FOR DEBUNKER
                            gameAction = new GameAction(GameBoardController.getInstance().getCurrentPlayer().getName(), theory.getOwner().getName(), String.format("Debunked the Theory: %s!", theory.getIngredient()), 0);
                            gameLog.recordLog(GameBoardController.getInstance().getCurrentPlayer(), gameAction);
                            //GAMELOG RECORDS LOG FOR DEBUNKEE
                            gameLog.recordLog(theory.getOwner(), gameAction);
                            return;
                        }
                    }
                }
            }

        theoryUI.update("THEORY_NOT_DEBUNKED");
            
        }
        else if (theory.getOwner() == GameBoardController.getInstance().getCurrentPlayer()){
            theoryUI.update("CANNOT_DEBUNK_YOUR_OWN_THEORY");
        }
        else if (theory.getAlchemy() == alchemy) {
            theoryUI.update("CANNOT_DEBUNK_SAME_ALCHEMY");
        }
        return;
    }

    public List<Theory> getTheories() {
        return theories;
    }

    public void getTrueAlchemy() {
        
    }
    
}
