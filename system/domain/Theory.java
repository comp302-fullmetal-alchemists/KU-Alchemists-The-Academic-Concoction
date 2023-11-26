package system.domain;

public class Theory {
        private AlchemyMarker alchemy;
        private IngredientCard ingredient;
        private Player owner;

    public Theory(AlchemyMarker alchemy, IngredientCard ingredient, Player owner) {
        this.alchemy = alchemy;
        this.ingredient = ingredient;
        this.owner = owner;
        
    }

    public AlchemyMarker getAlchemy() {
        return alchemy;
    }

    public void setAlchemy(AlchemyMarker alchemy) {
        this.alchemy = alchemy;
    }

    public IngredientCard getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientCard ingredient) {
        this.ingredient = ingredient;
    }
    
}
