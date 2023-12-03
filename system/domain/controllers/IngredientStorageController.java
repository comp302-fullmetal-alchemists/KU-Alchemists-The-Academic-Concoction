package system.domain.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Mediator;
import system.domain.Cards;

public class IngredientStorageController {
    //IngredientStorage	ingredient pile: List<ingredientCard>
    //artifact pile: List<artifactCard>	transmuteIngredient(ingredientCard)
    //buyArtifact()

    private List<IngredientCard> ingredientPile;
    private List<ArtifactCard> artifactPile;
    private Observer ingredientStorageUI;
    private Mediator mediator;

    public IngredientStorageController() {
        this.ingredientPile = new ArrayList<IngredientCard>();
        this.artifactPile = new ArrayList<ArtifactCard>();
        this.mediator = GameBoardController.getInstance().getMediator();
    }

    public void setObserver(Observer observer) {
        this.ingredientStorageUI = observer;
    }

    public void initializePiles() {
        for (Integer i = 0; i < 10; i++) {
            ingredientPile.add(new IngredientCard("Ingredient" + i.toString(), null));
        }
        for (Integer i = 0; i < 10; i++) {
            artifactPile.add(new ArtifactCard("Artifact" + i.toString(), null));
        }
        Random random = new Random();   
        for (int i = 0; i < 2; i++) {
            GameBoardController.getInstance().getPlayer(0).getInventory().addIngredient(ingredientPile.remove(random.nextInt(ingredientPile.size())));
        }
        for (int i = 0; i < 2; i++) {
            GameBoardController.getInstance().getPlayer(1).getInventory().addIngredient(ingredientPile.remove(random.nextInt(ingredientPile.size())));
        }
    }

    

    public IngredientCard transmuteIngredient(int index, IngredientCard card) {
        //this just yielded an error but it is not my use case so I just commented it out
        //GameBoardController.getInstance().getPlayer(index).getInventory().giveIngredient(card);
        GameBoardController.getInstance().getPlayer(index).getInventory().updateGold(2);
        
        return card;

    }

    public void buyArtifact() {
        return;
    }

    public void drawIngredient() {
        if (ingredientPile.isEmpty()) {
            ingredientStorageUI.update("Pile is empty!");
        }
        else {
            IngredientCard drawn = ingredientPile.remove(0);
            GameBoardController.getInstance().getCurrentPlayer().getInventory().addIngredient(drawn);
            ingredientStorageUI.update(String.format("CARDREMOVAL: %s", drawn.getName()));
        }
    }

}
