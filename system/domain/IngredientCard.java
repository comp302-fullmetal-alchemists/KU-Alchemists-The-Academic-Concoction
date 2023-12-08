package system.domain;

public class IngredientCard extends Cards{
    private String name;
    private Alchemy alchemy;


    public IngredientCard(String name, Alchemy alchemy) {
        super(name);
        this.name = name;
        this.alchemy = alchemy;
    }

    public String getName() {
        return name;
    }

    public Alchemy getAlchemy() {
        return alchemy;
    }

}
