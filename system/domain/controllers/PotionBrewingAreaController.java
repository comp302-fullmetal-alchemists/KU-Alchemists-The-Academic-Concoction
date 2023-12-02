package system.domain.controllers;
import java.util.ArrayList;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.Potion;
import system.domain.interfaces.Collector;
import system.domain.interfaces.Observer;

public class PotionBrewingAreaController implements Collector{

    //students : ArrayList<String>
    //PotionBrewingArea	students: ArrayList<String>
    //	makePotion(ingreidentCard, ingredientCard)
    //chooseExperiment()
    //makeExperiment()
    //giveOffer(offer)
    //removePotion(potion)

    private ArrayList<String> students;
    private ArrayList<String> adventurers;
    private IngredientCard ing1;
    private IngredientCard ing2;
    private Observer potionBrewingUI;

    public PotionBrewingAreaController() {
        this.students = new ArrayList<String>();
        this.adventurers = new ArrayList<String>();
    }

    public void setObserver(Observer observer){
        this.potionBrewingUI = observer;
    }

    public void activate() {
        GameBoardController.getInstance().getMediator().connectCollector(this);
    }
    
    public void deactivate() {
        GameBoardController.getInstance().getMediator().disconnectCollector();
    }

    public void makePotion(IngredientCard card1, IngredientCard card2) {
        return;
    }

    public void chooseExperiment() {
        return;
    }

    public void makeExperiment() {
        return;
    }

    public void giveOffer(Integer offer) {
        return;
    }

    public void removePotion(Potion potion) {
        return;
    }


    @Override
    public void collectIngredient(IngredientCard ing) {
        if (ing1==null) {
            ing1 = ing;
            potionBrewingUI.update(String.format("NEW_INGREDIENT1: %s", ing.getName()));

        }
        else if (ing2 == null) {
            ing2 = ing;
            potionBrewingUI.update(String.format("NEW_INGREDIENT2: %s", ing.getName()));

        }
    }


    @Override
    public void collectPotion(Potion potion) {}


    @Override
    public void collectArtifact(ArtifactCard artifact) {}


}
