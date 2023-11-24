package system.domain;

public class PublicationCard extends Cards {
//publicationCard	card name: String
//ingredient: ingredientCard
//alchemyMarker: alchemy

    private String name;
    private IngredientCard ingredient;
    private AlchemyMarker alchemy;

    public PublicationCard(String name, IngredientCard ingredient, AlchemyMarker alchemy) {
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

    public AlchemyMarker getAlchemy() {
        return this.alchemy;
    }


}
