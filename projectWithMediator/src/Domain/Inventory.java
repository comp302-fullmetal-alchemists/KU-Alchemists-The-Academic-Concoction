package Domain;
import java.util.ArrayList;
import java.util.List;


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
        return;
    }

    public void showInventory(Player player) {
        return;
    }

    public void updateGold(int amount) {
        return;
    }

    public void updateInventory(Cards card) {
        return;
    }

}
ö