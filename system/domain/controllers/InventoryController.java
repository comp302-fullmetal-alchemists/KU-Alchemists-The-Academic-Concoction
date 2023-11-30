package system.domain.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import system.domain.ArtifactCard;
import system.domain.Cards;
import system.domain.IngredientCard;
import system.domain.Player;
import system.domain.Potion;


public class InventoryController {
    // Player's inventory
    // Inventory	gold : int
    //ingredient cards: List<ingredientCard>
    //artifact cards : List<artifactCard>
    //potions: List<potion>	giveIngredient(ingredientCard)
    //showInventory(player)
    //updateGold(int)
    //updateInventory(card)
    // initialize an artifact card object and add it to the artifact card list of the corresponding players inventory
    // initialize an ingredient card object and add it to the ingredient card list of the corresponding players inventory
    // initialize a potion object and add it to the potion list of the corresponding players inventory
    // delete card from ingredient card list of the corresponding players inventory
    // delete card from artifact card list of the corresponding players inventory   
    // delete card from potion list of the corresponding players inventory
    

    private int gold;
    private List<IngredientCard> ingredientCards;
    private ArrayList<ArtifactCard> artifactCards;
    private List<Potion> potions;
    public InventoryController() {
        this.gold = 0;
        this.ingredientCards = new ArrayList<IngredientCard>();
        this.artifactCards = new ArrayList<ArtifactCard>();
        this.potions = new ArrayList<Potion>();
    }


    public int getGold(){
        return gold;
    }
    
    public void setGold(int gold) {
		this.gold = gold;
	}

    public ArrayList<ArtifactCard> getArtifactCards(){
        return artifactCards;
    }

    public List<IngredientCard> getIngredientCards(){
        return ingredientCards;
    }

    public List<Potion> getPotions(){
        return potions;
    }
 

    public void giveIngredient(IngredientCard card) {
    	//delete card from ingredient card list of the corresponding players inventory
        for(IngredientCard icard : ingredientCards){
            if(icard == card){
                ingredientCards.remove(card);
                
            }
        }

    }

    public void showInventory(Player player) {
       //observer will use this
        return;
    }

    public int updateGold(int amount) {
    	
    	setGold(player.getGold() + amount) ;
        
    	return amount;
    }

    public void updateInventory(Cards card) {
        //sanırım bu da uı için bir method tekrar bakalım.
        if(card.instanceOf(IngredientCard)){
            ingredientCards.add(card);
        }
        else if (card.instanceOf(ArtifactCard)){
            artifactCards.add(card);
        }
        
    }
    
    public void removePotion(Potion potion) {
    	//potions.remove(potion);
        return;
    }

}
