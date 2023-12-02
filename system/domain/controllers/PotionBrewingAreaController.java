package system.domain.controllers;
import java.util.ArrayList;
import java.util.Random;

import system.domain.IngredientCard;
import system.domain.Potion;

public class PotionBrewingAreaController {

    //students : ArrayList<String>
    //PotionBrewingArea	students: ArrayList<String>
    //	makePotion(ingreidentCard, ingredientCard)
    //chooseExperiment()
    //makeExperiment()
    //giveOffer(offer)
    //removePotion(potion)

    private ArrayList<String> students;
    Random random = new Random();

    //private ArrayList<String> adventurers; //Removed the list of adventurers

    public void makePotion(IngredientCard card1, IngredientCard card2) {
        return;
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
