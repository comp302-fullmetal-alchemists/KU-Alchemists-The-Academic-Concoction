package system.domain.controllers;
import system.domain.Alchemy;
import system.domain.Theory;
import system.domain.interfaces.Observer;
import system.domain.util.IngredientFactory;
import system.network.IClientAdapter;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class TheoryController {
    private List<Theory> theories;
    private Observer theoryUI;
    private GameLogController gameLog;
    private Map<String, Alchemy> alchemyMap; // map of ingredient and alchemy, used for debunking theory
    private String ingredient;
    private IClientAdapter clientAdapter;


    public TheoryController() {
        this.theories = new ArrayList<Theory>(); //add theories to the list
        this.gameLog = GameBoardController.getInstance().getGameLog();
        this.alchemyMap = IngredientFactory.getInstance().getAlchemyMap();
        this.clientAdapter = GameBoardController.getInstance().getClientAdapter();

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
            Theory theory = new Theory(alchemy, ingredient, GameBoardController.getInstance().getPlayer().getName());
            theories.add(theory);
            theoryUI.update("THEORY_PUBLISHED");
            clientAdapter.reportPuplishTheroryToServer(theory);
            GameBoardController.getInstance().getPublicationAreaController().setAlchemy(0);
            //GAMELOG RECORDS LOG: When a player publishes a theory
            gameLog.recordLog(GameBoardController.getInstance().getPlayer(), GameBoardController.getInstance().getPlayer().getName(), "Everyone", String.format("Published the Theory with %s and %s!", ingredient, alchemy.toString()), 2);
            //increase the turn count
            GameBoardController.getInstance().getPlayer().playedTurn();
            System.out.println("Theory published by :" + GameBoardController.getInstance().getPlayer().getName() + " " + ingredient + " " + alchemy.toString());

        }
        return;
    }

    public void endorseTheory() {
        //check if the theory is already endorsed
        if (ingredient == null) {
            theoryUI.update("NO_THEORY_CHOSEN");
            System.out.println("ingredient is null");
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
                if(theory.getOwner() == GameBoardController.getInstance().getPlayer().getName()) {
                        theoryUI.update("CANNOT_ENDORSE_YOUR_OWN_THEORY");
                        return;
                    }
                //endorse the theory
                else {
                    GameBoardController.getInstance().getPlayer().getInventory().updateGold(-2);
                    theory.endorsed(GameBoardController.getInstance().getPlayer().getName());
                    theoryUI.update("THEORY_ENDORSED");
                    clientAdapter.reportEndorseTheoryToServer(theory);
                    //increase the turn count
                    GameBoardController.getInstance().getPlayer().playedTurn();
                    return;
                }
            }
        }
        theoryUI.update("NO_THEORY_CHOSEN");
    }

    public Theory getChosenTheory() {
        //get the chosen theory
        for (Theory theory : theories) {
            if (theory.getIngredient() == ingredient) {
                return theory;
            }
        }
        return null;
    }

    public void debunkTheory(Alchemy alchemy) {
        //if the the theory which is going to be debunked is not current player's theory and the alchemy is not the same as the theory's alchemy
        Theory theory = getChosenTheory();

        if (theory == null) {
            theoryUI.update("NO_THEORY_CHOSEN");
            System.out.println("theory is null");
            return;
        }

        if(!theory.getAlchemy().equals(alchemy) && theory.getOwner() != GameBoardController.getInstance().getPlayer().getName()) {
            for (String key: alchemyMap.keySet()) {
                System.out.println(alchemyMap.get(key));
                System.out.println(alchemyMap.get(theory.getIngredient()));
                if (alchemyMap.get(theory.getIngredient()).equals(alchemy)) {
                    //alchemyMap contains true match of the ingredient and the alchemy, if the alchemy is the same as the theory's alchemy, then the theory is debunked
                    theoryUI.update("THEORY_DEBUNKED");
                    //reputation düşürme theory nin eski sahibi için

                    //GAMELOG RECORDS LOG FOR DEBUNKER
                    gameLog.recordLog(GameBoardController.getInstance().getPlayer(), GameBoardController.getInstance().getPlayer().getName(), theory.getOwner(), "Debunked the theory!", 0);

                    theory.setAlchemy(alchemy);
                    theory.setOwner(GameBoardController.getInstance().getPlayer().getName());
                    theory.setDebunked(true);
                    GameBoardController.getInstance().getPlayer().getInventory().updateGold(2);
                    clientAdapter.reportDebunkTheoryToServer(theory);
                    GameBoardController.getInstance().getMediator().getPlayer().playedTurn();


                    return;
                }
            }
            
        theoryUI.update("THEORY_NOT_DEBUNKED");
        GameBoardController.getInstance().getMediator().getPlayer().playedTurn();
        }
        else if (theory.getOwner() == GameBoardController.getInstance().getPlayer().getName()){
            theoryUI.update("CANNOT_DEBUNK_YOUR_OWN_THEORY");
        }
        else if (theory.getAlchemy().equals(alchemy)) {
            theoryUI.update("CANNOT_DEBUNK_SAME_ALCHEMY");
        }
        return;
    }


    public List<Theory> getTheories() {
        return theories;
    }

    
}