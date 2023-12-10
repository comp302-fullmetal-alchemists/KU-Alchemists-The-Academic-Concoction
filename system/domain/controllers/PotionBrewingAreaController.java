package system.domain.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import system.domain.ArtifactCard;
import system.domain.GameAction;
import system.domain.IngredientCard;
import system.domain.Potion;
import system.domain.interfaces.Collector;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Mediator;

public class PotionBrewingAreaController implements Collector{

    //students : ArrayList<String>
    //PotionBrewingArea	students: ArrayList<String>
    //	makePotion(ingreidentCard, ingredientCard)
    //chooseExperiment()
    //makeExperiment()
    //giveOffer(offer)
    //removePotion(potion)

    private ArrayList<String> students;
    private IngredientCard ing1;
    private IngredientCard ing2;
    private Potion potionToSell;
    private Observer potionBrewingUI;
    private Mediator mediator;
    private GameLogController gameLog;
    private GameAction gameAction;
    private Boolean active = false;
    private int offer;

    public PotionBrewingAreaController() {
        this.gameLog = GameBoardController.getInstance().getGameLog();
        this.students = new ArrayList<String>();
        this.mediator = GameBoardController.getInstance().getMediator();
    }

    public void setObserver(Observer observer){
        this.potionBrewingUI = observer;
    }

    
    /*
     * Here we can brew a potion of two ingredients, based on their alchemies. 
     * The testing and results of potion are not implemented yet.
     * */
    public void makePotion() {
        if (ing1 != null && ing2 != null) {
            Potion brewed = new Potion(ing1, ing2);
            mediator.sendToPlayer(brewed);
            potionBrewingUI.update(String.format("BREWED_POTION:%s", brewed.getStatus()));

            //GAMELOG RECORDS LOG: When a player brews a potion
            gameAction = new GameAction(GameBoardController.getInstance().getCurrentPlayer().getName(), "Everyone", String.format("Brewed potion %s", brewed.getStatus()), 0);
            gameLog.recordLog(GameBoardController.getInstance().getCurrentPlayer(), gameAction);
        
            ing1 = null;
            ing2 = null;
            mediator.playerPlayedTurn();
        }
        else {
            potionBrewingUI.update("ABSENT_INGREDIENTS");
        }
    }

    public void chooseExperiment() {
        return;
    }

    public void makeExperiment() {
        return;
    }

    public boolean hasIng1() {
    	return !(ing1 == null);
    }
    
    public boolean hasIng2() {
    	return !(ing2 == null);
    }
    
    public boolean hasPotionToSell() {
    	return !(potionToSell == null);
    }


    public void discardIngredient(int num) {
        if (num == 1) {
            mediator.sendToPlayer(ing1);
            ing1 = null;
            potionBrewingUI.update("DISCARD_INGREDIENT1");
        }
        else if (num == 2) {
            mediator.sendToPlayer(ing2);
            ing2 = null;
            potionBrewingUI.update("DISCARD_INGREDIENT2");
        } 
        
    }

    public void discardPotion(){
            mediator.sendToPlayer(potionToSell);
            potionToSell = null;
            potionBrewingUI.update("DISCARD_POTION");
    }
    

    public void sellPotion(){
        if(potionToSell != null){//If there is a potion to sell
            if (potionToSell.getStatus().substring(potionToSell.getStatus().length() - 1).equals("+")){ //if its positive give 3 golds
                offer = 3;
            }
            else if (!(potionToSell.getStatus().substring(potionToSell.getStatus().length() - 1).equals("-"))){ //if its not negative, meaning positive or neutral give 2 golds
                offer = 2;
            }
            else{ //if it has unknown qualities, give 1 gold.
                offer = 1;
            }

			//GAMELOG RECORDS LOG: When a potion is sold
            gameAction = new GameAction(mediator.getPlayerName(), "Adventurer", String.format("Sold potion %s", potionToSell.getStatus()), 0);
            gameLog.recordLog(GameBoardController.getInstance().getCurrentPlayer(), gameAction);
        
            mediator.updatePlayerGold(offer);
            potionToSell = null;//this makes sure the potion is removed from inventory
            potionBrewingUI.update(String.format("SOLD_POTION:%d", offer));//send a message to the UI so its updated



            mediator.playerPlayedTurn();
        }
        else{ //If there is no potions to sell
            potionBrewingUI.update("ABSENT_POTION");//tell the UI so it gives the appropriate message
        }
    }

    
    /* 
     * Collector interface works with mediator, they make taking objects from players easier while maintaining
     * low coupling. When a collector like potionBrewingArea is opened, player can send ingredients and potions to
     * it by clicking those objects from its inventory.
     * PotionBrewing area handles the objects it receives accordingly.
     * */
    @Override
    public <T> boolean collectItem(T item) {
        if (item instanceof IngredientCard) {
            IngredientCard ing = (IngredientCard) item;
            if (ing1==null) {
	            ing1 = ing;
	
	            potionBrewingUI.update(String.format("NEW_INGREDIENT1:%s", ing.getName()));
	            return true;
            }
            else if (ing2 == null) {
	            ing2 = ing;
	
	            potionBrewingUI.update(String.format("NEW_INGREDIENT2:%s", ing.getName()));
	            return true;
            }
            return false;
        }

        if (item instanceof Potion) { //if the clicked item is a potion
            Potion potion = (Potion) item; //cast the item into potion
            if (potionToSell!=null) { //if we are not already holding a potion
                discardPotion();
            }
            this.potionToSell = potion; //make the holding potion the clicked potion
            potionBrewingUI.update(String.format("NEW_POTION:%s", potion.getStatus())); //send a message to UI to update
            return true;
        }
        return false;
    }

    @Override
    public void activate() {
        mediator.connectCollector(this);
        active = true;
    }

    @Override
    public void deactivate() {
        mediator.disconnectCollector();
        active = false;
    }

    @Override
    public boolean isActive() {
        return active;
    }
}