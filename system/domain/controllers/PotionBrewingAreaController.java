package system.domain.controllers;
import java.util.ArrayList;
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
    private Observer potionBrewingUI;
    private Mediator mediator;

    public PotionBrewingAreaController() {
        this.students = new ArrayList<String>();
        this.adventurers = new ArrayList<String>();
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

    
    
    public boolean summonAdventurerOrNot(){
        return random.nextBoolean();// Generate a random boolean value FOR EVERY ROUND
    }
    

    public String giveOffer() {
        //TOOD Add to UI
        //Aventurer gives 3 gold for positive, 2 gold for positive or neutral, 1 gold for any potion.

        String adventurerInfo = "Hark, potion-masters! The Adventurer proclaims: for potions positive, three golds be thine; for brews of good or neutral kind, two golds; and for any draught, one gold. Present thy potions and claim thy reward!";
        return adventurerInfo;
    }

    public int agreeOffer(int offer, Potion potion){
        if ((offer == 3) && (potion.getStatus() == "positive")){
            return offer;
        }
        else if ((offer == 2) && (!(potion.getStatus() == "negative"))){
            return offer;
        }
        else if(offer == 1){
            return offer;
        }
        else{
            return 0;//ERROR WRONG POTION, AS A PUNISHMENT GET THE POTION
        }
    }

    public int agreeOffer(int offer, Potion potion){
        if ((offer == 3) && (potion.getStatus() == "positive")){
            return offer;
        }
        else if ((offer == 2) && (!(potion.getStatus() == "negative"))){
            return offer;
        }
        else if(offer == 1){
            return offer;
        }
        else{
            return 0;//ERROR WRONG POTION, AS A PUNISHMENT GET THE POTION
        }
    }

}
