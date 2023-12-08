package system.domain;


public class PublicationCard extends Cards {
//publicationCard	card name: String
//ingredient: ingredientCard
//alchemyMarker: alchemy

    private String name;
    private IngredientCard ingredient;
    private Alchemy alchemy;
    
    public PublicationCard(String name, IngredientCard ingredient, Alchemy alchemy) {
        super(name);
        this.name = name;
        this.ingredient = ingredient;
        this.alchemy = alchemy;
    }

    public String getName() {
        return this.name;
    }

    public IngredientCard getIngredient() {
        return this.ingredient;
    }

    public Alchemy getAlchemy() {
        return this.alchemy;
    }


}
