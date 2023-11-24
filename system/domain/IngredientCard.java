package system.domain;
import java.util.Map;

public class IngredientCard extends Cards{

    //IngredientCard	card name: String
    //static formula: Map<String, alchemyMarker>

    private String name;
    private AlchemyMarker alchemy;
    private static Map<String, AlchemyMarker> formula;

    public IngredientCard(String name, AlchemyMarker alchemy) {
        super(name);
        this.name = name;
        this.alchemy = alchemy;
    }
    

    public String getName() {
        return name;
    }

}
