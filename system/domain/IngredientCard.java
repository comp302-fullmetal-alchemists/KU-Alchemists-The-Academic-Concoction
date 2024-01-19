package system.domain;

public class IngredientCard extends Cards{
    private String name;
    private Alchemy alchemy;
    private static String[] ingredients = {"Solaris Root", "Bat Wing", "Toad Stool", "Owl Feather", "Snake Venom", "Rat Tail", "Spider Web", "Newt Eye"};


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
    
    public static String[] getIngredientNames() {
    	return ingredients;
    }
    
	public int getIngredientOrder() {
		for (int i = 0; i < 8; i++) {
			if (ingredients[i].equals(name)) {
				return i;
			}
		}
		return -1;
	}

}
