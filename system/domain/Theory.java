package system.domain;

public class Theory {
        private Alchemy alchemy;
        private IngredientCard ingredient;
        private Player owner;
        private boolean debunked;
        

    public Theory(Alchemy alchemy, IngredientCard ingredient, Player owner) {
        this.alchemy = alchemy;
        this.ingredient = ingredient;
        this.owner = owner;
        this.debunked = false;
        
    }

    public Alchemy getAlchemy() {
        return alchemy;
    }

    public void setAlchemy(Alchemy alchemy) {
        this.alchemy = alchemy;
    }

    public IngredientCard getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientCard ingredient) {
        this.ingredient = ingredient;
    }

    public Player getOwner() {
        return owner;
    }   

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isDebunked() {
        return debunked;
    }

    public void setDebunked(boolean debunked) {
        this.debunked = debunked;
    }
    
}
