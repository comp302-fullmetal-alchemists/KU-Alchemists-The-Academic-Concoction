package system.domain.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import system.domain.ArtifactCard;
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

    public PotionBrewingAreaController() {
        this.students = new ArrayList<String>();
        //this.adventurers = new ArrayList<String>();
        this.mediator = GameBoardController.getInstance().getMediator();
    }

    public void setObserver(Observer observer){
        this.potionBrewingUI = observer;
    }

    public void makePotion() {
        if (ing1 != null && ing2 != null) {
            Potion brewed = new Potion("Potion");
            mediator.sendToPlayer(brewed);
            potionBrewingUI.update("DISCARD_INGREDIENTS");
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

    public void discardPotion() {
            mediator.sendToPlayer(potionToSell);
            potionToSell = null;
            potionBrewingUI.update("DISCARD_POTION");
    }
    
    public List<Potion> getPotions(){
        return GameBoardController.getInstance().getCurrentPlayer().getInventory().gePotions();
    }

    public String giveOffer() {
        //TOOD Add to UI
        //Aventurer gives 3 gold for positive, 2 gold for positive or neutral, 1 gold for any potion.

        String adventurerInfo = "Hark, potion-masters! The Adventurer proclaims:\nfor potions positive, three golds be thine;\nfor brews of good or neutral kind, two golds;\nand for any draught, one gold.\nPresent thy potions and claim thy reward!";
        return adventurerInfo;
    }

    public int agreeOffer(int offer, Potion potion){
        if ((offer == 3) && (potion.getStatus() == "positive")){
            GameBoardController.getInstance().getCurrentPlayer().getInventory().updateGold(offer);
            GameBoardController.getInstance().getCurrentPlayer().getInventory().removePotion(potion);
            return offer;
        }
        else if ((offer == 2) && (!(potion.getStatus() == "negative"))){
            GameBoardController.getInstance().getCurrentPlayer().getInventory().updateGold(offer);
            GameBoardController.getInstance().getCurrentPlayer().getInventory().removePotion(potion);
            return offer;
        }
        else if(offer == 1){
            GameBoardController.getInstance().getCurrentPlayer().getInventory().updateGold(offer);
            GameBoardController.getInstance().getCurrentPlayer().getInventory().removePotion(potion);
            return offer;
        }
        else{
            GameBoardController.getInstance().getCurrentPlayer().getInventory().removePotion(potion);
            return 0;//ERROR WRONG POTION, AS A PUNISHMENT GET THE POTION
        }
    }

    @Override
    public <T> void collectItem(T item) {
        if (item instanceof IngredientCard) {
            IngredientCard ing = (IngredientCard) item;
            if (ing1==null) {
            ing1 = ing;
            potionBrewingUI.update(String.format("NEW_INGREDIENT1: %s", ing.getName()));
            }
            else if (ing2 == null) {
            ing2 = ing;
            potionBrewingUI.update(String.format("NEW_INGREDIENT2: %s", ing.getName()));
            }
        }

        if (item instanceof Potion) {
            Potion potion = (Potion) item;
            if (potionToSell==null) {
                this.potionToSell = potion; 
                potionBrewingUI.update(String.format("POTION_TO_SELL: %s", potion.getName()));
            }
        }
    }

    @Override
    public void activate() {
        mediator.connectCollector(this);
    }

    @Override
    public void deactivate() {
        mediator.disconnectCollector();
    }
}