package system.domain.controllers;
import java.util.ArrayList;

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
    private ArrayList<String> adventurers;
    private IngredientCard ing1;
    private IngredientCard ing2;
    private Observer potionBrewingUI;
    private Mediator mediator;
    private Boolean active = false;

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
            Potion brewed = new Potion(ing1, ing2);
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

    public void giveOffer(Integer offer) {
        return;
    }

    public void removePotion(Potion potion) {
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
