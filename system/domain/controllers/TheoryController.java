package system.domain.controllers;
import system.domain.Alchemy;
import system.domain.Theory;
import system.domain.interfaces.Mediator;
import system.domain.interfaces.Observer;
import system.domain.util.IngredientFactory;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class TheoryController {
    private List<Theory> theories;
    private Observer theoryUI;
    private GameLogController gameLog;
    private Map<String, Alchemy> alchemyMap; // map of ingredient and alchemy, used for debunking theory
    private String ingredient;
    private Mediator mediator;


    public TheoryController() {
        this.theories = new ArrayList<Theory>(); //add theories to the list
        this.gameLog = GameBoardController.getInstance().getGameLog();
        this.alchemyMap = IngredientFactory.getInstance().getAlchemyMap();
        this.mediator = GameBoardController.getInstance().getMediator();
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

    public void publishTheory(Alchemy alchemy, String ingredient, String playerName) {
        Theory theory = new Theory(alchemy, ingredient, mediator.getPlayer());
        theories.add(theory);
        theoryUI.update(String.format("THEORY_PUBLISHED:%s,%s,%s", alchemy.toString(), ingredient, playerName));
        GameBoardController.getInstance().getPublicationAreaController().setAlchemy(0);
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
        try {
            if (mediator.getPlayer().getInventory().getGold() < 1) {
                theoryUI.update("NOT_ENOUGH_GOLD");
            }
            //publish the theory
            else{
                mediator.getPlayer().getInventory().updateGold(-1);
                publishTheory(alchemy, ingredient, mediator.getPlayer().getName());
                gameLog.recordLog(mediator.getPlayer(), mediator.getPlayer().getName(), "Academy", String.format("Published the Theory with %s and %s!", ingredient, alchemy.toString()), 2);
                GameBoardController.getInstance().getClientAdapter().reportPublishTheoryToServer(alchemy, ingredient, mediator.getPlayer().getName());
                mediator.getPlayer().playedTurn();

            }
        }
        catch (NullPointerException e) {
            theoryUI.update("UNAUTHORIZED_ACTION");
        }
    }

    public void endorseTheory(String ingredient, String playerName) {
        Theory theory = getTheoryOfIngredient(ingredient);
        theory.endorsed(mediator.getPlayer());
        theoryUI.update(String.format("THEORY_ENDORSED:%s,%s", ingredient, playerName));
    }

    public void endorseTheory() {
        //check if the theory is already endorsed
        if (ingredient == null) {
            theoryUI.update("NO_THEORY_CHOSEN");
            return;
        }
        try {
            for (Theory theory : theories) {
                if (theory.getIngredient() == ingredient) {
                    if (theory.isEndorsed()) {
                        theoryUI.update("THEORY_ALREADY_ENDORSED");
                        return;
                    }
                    //check if the player has enough gold to endorse a theory
                    else if (mediator.getPlayer().getInventory().getGold() < 2) {
                        theoryUI.update("NOT_ENOUGH_GOLD");
                        return;
                    }
                    else if(theory.getOwner() == mediator.getPlayer()) {
                        theoryUI.update("CANNOT_ENDORSE_YOUR_OWN_THEORY");
                        return;
                    }
                    //endorse the theory
                    else {
                        mediator.getPlayer().getInventory().updateGold(-2);
                        endorseTheory(ingredient, mediator.getPlayer().getName());
                        gameLog.recordLog(mediator.getPlayer(), mediator.getPlayer().getName(), "Academy", String.format("Endorsed the Theory of %s about %s!", theory.getOwner().getName(), ingredient), 2);
                        GameBoardController.getInstance().getClientAdapter().reportEndorseTheoryToServer(ingredient, mediator.getPlayer().getName(), theory.getOwner().getName());
                        mediator.getPlayer().playedTurn();
                        return;
                    }
                }
            }
        }
        catch(NullPointerException e) {
            theoryUI.update("UNAUTHORIZED_ACTION");
        }
        
    }

    public Theory getTheoryOfIngredient(String ingredient) {
        //get the chosen theory
        for (Theory theory : theories) {
            if (theory.getIngredient() == ingredient) {
                return theory;
            }
        }
        return null;
    }

    public void debunkTheory(Alchemy alchemy, String ingredient, String playerName) {
        Theory theory = getTheoryOfIngredient(ingredient);
        theory.setAlchemy(alchemy);
        theory.setOwner(mediator.getPlayer());
        theory.setDebunked(true);
        theoryUI.update(String.format("THEORY_DEBUNKED:%s,%s,%s", alchemy.toString(), theory.getIngredient(), playerName));
    }

    public void debunkTheory(Alchemy alchemy) {
        //if the the theory which is going to be debunked is not current player's theory and the alchemy is not the same as the theory's alchemy
        if (ingredient == null) {
            theoryUI.update("NO_THEORY_CHOSEN");
            return;
        }
        try {
            Theory theory = getTheoryOfIngredient(ingredient);

            if (theory == null) {
                theoryUI.update("NO_THEORY_CHOSEN");
                return;
            }

            if(!theory.getAlchemy().equals(alchemy) && theory.getOwner() != mediator.getPlayer()) {
                if (alchemyMap.get(theory.getIngredient()).equals(alchemy)) {
                    mediator.getPlayer().getInventory().updateGold(2);
                    //alchemyMap contains true match of the ingredient and the alchemy, if the alchemy is the same as the theory's alchemy, then the theory is debunked
                    //reputation düşürme theory nin eski sahibi için
                    String owner = theory.getOwner().getName();
                    debunkTheory(alchemy, ingredient, mediator.getPlayer().getName());
                    //GAMELOG RECORDS LOG FOR DEBUNKER
                    gameLog.recordLog(mediator.getPlayer(), mediator.getPlayer().getName(), "Academy", String.format("Debunked the theory of %s!", owner), 0);
                    GameBoardController.getInstance().getClientAdapter().reportDebunkTheoryToServer(alchemy, ingredient, mediator.getPlayer().getName(), owner);

                    mediator.getPlayer().playedTurn();
                    return;
                
                }
                else {
                    theoryUI.update("THEORY_NOT_DEBUNKED");
                    mediator.getPlayer().playedTurn();
                    return;
                }
            }
            else if (theory.getOwner() == mediator.getPlayer()){
                theoryUI.update("CANNOT_DEBUNK_YOUR_OWN_THEORY");
            }
            else if (theory.getAlchemy().equals(alchemy)) {
                theoryUI.update("CANNOT_DEBUNK_SAME_ALCHEMY");
            }
            return;
        }
        catch(NullPointerException e) {
            theoryUI.update("UNAUTHORIZED_ACTION");
        }
        
    }


    public List<Theory> getTheories() {
        return theories;
    }

    public void getTrueAlchemy() {
        
    }
    
}