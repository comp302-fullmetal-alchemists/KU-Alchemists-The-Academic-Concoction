package system.domain;

import java.util.ArrayList;
import java.util.List;

import Domain.Player;


public class Inventory {
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

    public Inventory() {
        this.gold = 0;
        this.ingredientCards = new ArrayList<IngredientCard>();
        this.artifactCards = new ArrayList<ArtifactCard>();
        this.potions = new ArrayList<Potion>();
    }

    public void giveIngredient(IngredientCard card) {
    	//delete card from ingredient card list of the corresponding players inventory
        
    	return;
    }

    public void showInventory(Player player) {
        return;
    }

    public int updateGold(int amount) {
    	
    	//player.setGold(player.getGold() + amount) ;
       
    	return amount;
    }

    public void updateInventory(Cards card) {
        return;
    }

}
