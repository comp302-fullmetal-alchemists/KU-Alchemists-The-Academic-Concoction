package system.domain.controllers;

import java.util.ArrayList;
import java.util.List;

import system.domain.ArtifactCard;
import system.domain.Cards;
import system.domain.IngredientCard;
import system.domain.Player;
import system.domain.Potion;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Mediator;


public class InventoryController {
    // Inventory	gold : int
    //ingredient cards: List<ingredientCard>
    //artifact cards : List<artifactCard>
    //potions: List<potion>	giveIngredient(ingredientCard)
    //showInventory(player)
    //updateGold(int)
    //updateInventory(card)

    private int gold;
    private List<IngredientCard> ingredientCards;
    private List<ArtifactCard> artifactCards;
    private List<Potion> potions;
    private Observer inventoryUI;
    private Mediator mediator;

    public InventoryController() {
        this.gold = 0;
        this.ingredientCards = new ArrayList<IngredientCard>();
        this.artifactCards = new ArrayList<ArtifactCard>();
        this.potions = new ArrayList<Potion>();
        this.mediator = GameBoardController.getInstance().getMediator();
    }

    public void setObserver(Observer observer) {
        this.inventoryUI = observer;
    }


    public int getGold(){
        return gold;
    }
    
    public void setGold(int gold) {
		this.gold = gold;
	}

    public List<ArtifactCard> getArtifactCards(){
        return artifactCards;
    }
    /* 
    public void giveIngredient(IngredientCard card) {
    	//delete card from ingredient card list of the corresponding players inventory
        for(IngredientCard icard : ingredientCards){
            if(icard == card){
                ingredientCards.remove(card);
                
            }
        }

    }
    */
    public void showInventory(Player player) {
       //observer will use this
        return;
    }

    public int updateGold(int amount) {
    	
    	setGold(gold + amount) ;
        
    	return amount;
    }

    public void updateInventory(Cards card) {
        //sanırım bu da uı için bir method tekrar bakalım.
        if(card instanceof IngredientCard){
            IngredientCard ICard = (IngredientCard) card;
            ingredientCards.add(ICard);
        } 
        else if (card instanceof ArtifactCard){
            ArtifactCard ACard = (ArtifactCard) card;
            artifactCards.add(ACard);
        }
        
    }

    public void updateIngredients(IngredientCard ingredient) {
        ingredientCards.add(ingredient);
        inventoryUI.update(String.format("NEW_INGREDIENT: %s", ingredient.getName()));
    }

    /*********/
    /*These should be the accessers of inventory for the mediator */
    public void addIngredient(IngredientCard ingredient) {
        ingredientCards.add(ingredient);
        inventoryUI.update(String.format("NEW_INGREDIENT: %s", ingredient.getName()));
    }
    
    public void addArtifact(ArtifactCard artifact) {
        artifactCards.add(artifact);
        inventoryUI.update(String.format("NEW_ARTIFACT: %s", artifact.getName()));
    }

    public void addPotion(Potion potion) {
        potions.add(potion);
        inventoryUI.update(String.format("NEW_POTION: %s", potion.getName()));
    }

    public void removeIngredient(IngredientCard ingredient) {
        ingredientCards.remove(ingredient);
        inventoryUI.update(String.format("REMOVED_INGREDIENT: %s", ingredient.getName()));
    }

    public void removePotion(Potion potion) {
    	potions.remove(potion);
        inventoryUI.update(String.format("REMOVED_POTION: %s", potion));
    }

    public void sendIngredient(String ingredientName) {
        /// If player will choose ingredients from its dashboard, it cannot choose an ingredient that doesn't belong to it
        /// therefore it makes sense to change ingredientCards to Map<String, IngredientCard>
        for (IngredientCard ing: ingredientCards){
            if (ing.getName().equals(ingredientName)) {
                if (mediator.sendToCollector(ing)) {
                    removeIngredient(ing);
                }
                break;
            }
        }
    }

    public void sendPotion(String potionName) {
        /// If player will choose potions from its dashboard, it cannot choose a potion that doesn't belong to it
        /// therefore it makes sense to change potions to Map<String, IngredientCard>
        for (Potion pot: potions){
            if (pot.getName().equals(potionName)) {
                if (mediator.sendToCollector(pot)) {
                    removePotion(pot);
                }
                break;
            }
        }
    }
    /**********/

}
