package system.domain.controllers;

import system.domain.Alchemy;
import system.domain.GameAction;
import system.domain.Theory;
import system.domain.interfaces.Mediator;
import system.domain.interfaces.Observer;
import system.domain.util.IngredientFactory;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class TheoryController {
    private List<Theory> theories;
    private Observer theoryUI;
    private GameLogController gameLog;
    private Map<String, Alchemy> alchemyMap; // map of ingredient and alchemy, used for debunking theory
    private String ingredient;


    public TheoryController() {
        this.theories = new ArrayList<Theory>(); //add theories to the list
        this.gameLog = GameBoardController.getInstance().getGameLog();
        //this.alchemyMap = IngredientFactory.getInstance().getAlchemyMap();

    }

    public void setObserver(Observer observer) {
        //connects the observer to the theory controller
        this.theoryUI = observer;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void publishTheory(Alchemy alchemy) {
        
        if (ingredient == null) {
            theoryUI.update("NO_INGREDIENT_CHOSEN");
            return;
        }
        //check if the theory is already published
        for (Theory i : theories) {
            if (i.getAlchemy().equals(alchemy) || i.getIngredient() == ingredient) {
                theoryUI.update("DUPLICATE_THEORY");
                return;
            }
        }
        //check if the player has enough gold to publish a theory
        if (GameBoardController.getInstance().getPlayer().getInventory().getGold() < 1) {
            theoryUI.update("NOT_ENOUGH_GOLD");
            return;
        }
        //publish the theory
        else{
            GameBoardController.getInstance().getPlayer().getInventory().updateGold(-1);
            Theory theory = new Theory(alchemy, ingredient, GameBoardController.getInstance().getPlayer());
            theories.add(theory);
            theoryUI.update("THEORY_PUBLISHED");
            GameBoardController.getInstance().getPublicationAreaController().setAlchemy(0);
            //GAMELOG RECORDS LOG: When a player publishes a theory
            gameLog.recordLog(GameBoardController.getInstance().getPlayer(), GameBoardController.getInstance().getPlayer().getName(), "Everyone", String.format("Published the Theory with %s and %s!", ingredient, alchemy.toString()), 2);
            //increase the turn count
            GameBoardController.getInstance().getPlayer().playedTurn();
        }
        return;
    }

    public void endorseTheory() {
        //check if the theory is already endorsed
        if (ingredient == null) {
            theoryUI.update("NO_THEORY_CHOSEN");
            return;
        }

        for (Theory theory : theories) {
            if (theory.getIngredient() == ingredient) {
                if (theory.isEndorsed()) {
                    theoryUI.update("THEORY_ALREADY_ENDORSED");
                    return;
                }
                //check if the player has enough gold to endorse a theory
                if (GameBoardController.getInstance().getPlayer().getInventory().getGold() < 2) {
                    theoryUI.update("NOT_ENOUGH_GOLD");
                    return;
                }
                if(theory.getOwner() == GameBoardController.getInstance().getPlayer()) {
                        theoryUI.update("CANNOT_ENDORSE_YOUR_OWN_THEORY");
                        return;
                    }
                //endorse the theory
                else {
                    GameBoardController.getInstance().getPlayer().getInventory().updateGold(-2);
                    theory.getOwner().getInventory().updateGold(1);
                    theory.setEndorsed(true);
                    theoryUI.update("THEORY_ENDORSED");
                    //increase the turn count
                    GameBoardController.getInstance().getPlayer().playedTurn();
                }
            }
        }
    }

    public void debunkTheory(Alchemy alchemy, Theory theory, Player player) {
        //if the the theory which is going to be debunked is not current player's theory and the alchemy is not the same as the theory's alchemy
        if(theory.getAlchemy() != alchemy && theory.getOwner() != GameBoardController.getInstance().getPlayer()) {
            for (Theory i : theories) {
                if (i.getIngredient()== theory.getIngredient()) {
                    for (String key: alchemyMap.keySet()) {
                        if (alchemyMap.get(theory.getIngredient()) == alchemy) {
                            //alchemyMap contains true match of the ingredient and the alchemy, if the alchemy is the same as the theory's alchemy, then the theory is debunked
                            theoryUI.update("THEORY_DEBUNKED");
                            GameBoardController.getInstance().getMediator().getPlayer().playedTurn();

                            //GAMELOG RECORDS LOG FOR DEBUNKER
                            gameLog.recordLog(GameBoardController.getInstance().getPlayer(), GameBoardController.getInstance().getPlayer().getName(), theory.getOwner().getName(), "Debunked the theory!", 0);
                            //GAMELOG RECORDS LOG FOR DEBUNKEE
                            gameLog.recordLog(theory.getOwner(), GameBoardController.getInstance().getPlayer().getName(), theory.getOwner().getName(), "Debunked the theory!", 0);

                            i.setAlchemy(alchemy);
                            i.setOwner(GameBoardController.getInstance().getPlayer());
                            i.setDebunked(true);
                            GameBoardController.getInstance().getPlayer().getInventory().updateGold(2);


                            return;
                        }
                    }
                }
            }

        theoryUI.update("THEORY_NOT_DEBUNKED");
        GameBoardController.getInstance().getMediator().getPlayer().playedTurn();
        }
        else if (theory.getOwner() == GameBoardController.getInstance().getPlayer()){
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