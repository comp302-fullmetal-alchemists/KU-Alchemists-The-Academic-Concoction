package system.domain.controllers;
import java.util.ArrayList;

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

    

    public String giveOffer(int offer, int potionType) {
        //Add to UI
        String potionTypeStr;
        if (potionType>0){
            potionTypeStr = "positive";
        }

        else {
            potionTypeStr = "negative";
        }
        return String.format("The Adventurer offers %d golds for a %s potion\n", offer, potionTypeStr);
    }

    public void removePotion(Potion potion) {
        return;
    }


}
