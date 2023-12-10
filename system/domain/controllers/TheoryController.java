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
    private List<Theory> theories;
    private Observer theoryUI;
    private GameLogController gameLog;
    private GameAction gameAction;
    private Map<String, Alchemy> alchemyMap; // map of ingredient and alchemy, used for debunking theory

    public TheoryController() {
        this.theories = new ArrayList<Theory>(); //add theories to the list
        this.gameLog = GameBoardController.getInstance().getGameLog();
        this.alchemyMap = GameBoardController.getInstance().getAlchemyMap();

    }

    public void setObserver(Observer observer) {
        //connects the observer to the theory controller
        this.theoryUI = observer;
    }

    public void publishTheory(Alchemy alchemy, String ingredient) {
        //check if the theory is already published
        for (Theory i : theories) {
            if (i.getAlchemy() == alchemy || i.getIngredient() == ingredient) {
                theoryUI.update("DUPLICATE_THEORY");
                return;
            }
        } 
        //check if the player has enough gold to publish a theory
        if (GameBoardController.getInstance().getCurrentPlayer().getInventory().getGold() < 1) {
            theoryUI.update("NOT_ENOUGH_GOLD");
            return;
        }
        //publish the theory
        else{
            GameBoardController.getInstance().getCurrentPlayer().getInventory().updateGold(-1);
            Theory theory = new Theory(alchemy, ingredient, GameBoardController.getInstance().getCurrentPlayer());
            theories.add(theory);
            theoryUI.update("THEORY_PUBLISHED");

            //GAMELOG RECORDS LOG: When a player publishes a theory
            gameAction = new GameAction(GameBoardController.getInstance().getCurrentPlayer().getName(), "Everyone", String.format("Published the Theory with %s and %s!", ingredient, alchemy.toString()), 2);
            gameLog.recordLog(GameBoardController.getInstance().getCurrentPlayer(), gameAction);
            //increase the turn count
            GameBoardController.getInstance().getMediator().playerPlayedTurn();
        }
        return;
    }

    public void debunkTheory(Alchemy alchemy, Theory theory, Player player) {
        //if the the theory which is going to be debunked is not current player's theory and the alchemy is not the same as the theory's alchemy
        if(theory.getAlchemy() != alchemy && theory.getOwner() != GameBoardController.getInstance().getCurrentPlayer()) {
            for (Theory i : theories) {
                if (i.getIngredient()== theory.getIngredient()) {
                    for (String key: alchemyMap.keySet()) {
                        if (alchemyMap.get(theory.getIngredient()) == alchemy) {
                            //alchemyMap contains true match of the ingredient and the alchemy, if the alchemy is the same as the theory's alchemy, then the theory is debunked
                            theoryUI.update("THEORY_DEBUNKED");
                            GameBoardController.getInstance().getMediator().playerPlayedTurn();

                            //GAMELOG RECORDS LOG FOR DEBUNKER
                            gameAction = new GameAction(GameBoardController.getInstance().getCurrentPlayer().getName(), theory.getOwner().getName(), "Debunked the theory!", 0);
                            gameLog.recordLog(GameBoardController.getInstance().getCurrentPlayer(), gameAction);
                            //GAMELOG RECORDS LOG FOR DEBUNKEE
                            gameLog.recordLog(theory.getOwner(), gameAction);

                            i.setAlchemy(alchemy);
                            i.setOwner(GameBoardController.getInstance().getCurrentPlayer());
                            i.setDebunked(true);
                            GameBoardController.getInstance().getCurrentPlayer().getInventory().updateGold(2);


                            return;
                        }
                    }
                }
            }

        theoryUI.update("THEORY_NOT_DEBUNKED");
        GameBoardController.getInstance().getMediator().playerPlayedTurn();
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
