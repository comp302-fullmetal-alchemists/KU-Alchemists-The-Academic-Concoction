package system.domain.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import system.domain.Alchemy;
import system.domain.IngredientCard;

public class IngredientFactory{
	
	private String[] ingredients = {"Solaris Root", "Bat Wing", "Toad Stool", "Owl Feather", "Snake Venom", "Rat Tail", "Spider Web", "Newt Eye"};
	private Alchemy[] alchemies;
	private Map<String, Alchemy> alchemyMap;
	
	public IngredientFactory() {
		this.alchemies = new Alchemy[8];
		this.alchemyMap = new HashMap<String, Alchemy>();
		setAlchemy();
		assignRandomAlchemy();
	}


	// alchemies are created from the Alchemy class according to the actual game in here 
	public void setAlchemy() {
        alchemies[0] = new Alchemy(-Alchemy.AlchemicalConstants.SMALL, Alchemy.AlchemicalConstants.SMALL, -Alchemy.AlchemicalConstants.LARGE); 
        alchemies[1] = new Alchemy(Alchemy.AlchemicalConstants.LARGE, Alchemy.AlchemicalConstants.LARGE, Alchemy.AlchemicalConstants.LARGE);
        alchemies[2] = new Alchemy(-Alchemy.AlchemicalConstants.LARGE, -Alchemy.AlchemicalConstants.LARGE, -Alchemy.AlchemicalConstants.LARGE);
        alchemies[3] = new Alchemy(-Alchemy.AlchemicalConstants.SMALL, Alchemy.AlchemicalConstants.LARGE, Alchemy.AlchemicalConstants.SMALL);
        alchemies[4] = new Alchemy(Alchemy.AlchemicalConstants.LARGE, Alchemy.AlchemicalConstants.SMALL, -Alchemy.AlchemicalConstants.SMALL);
        alchemies[5] = new Alchemy(Alchemy.AlchemicalConstants.SMALL,- Alchemy.AlchemicalConstants.LARGE, -Alchemy.AlchemicalConstants.SMALL);
        alchemies[6] = new Alchemy(Alchemy.AlchemicalConstants.SMALL, -Alchemy.AlchemicalConstants.SMALL, Alchemy.AlchemicalConstants.LARGE);
        alchemies[7] = new Alchemy(-Alchemy.AlchemicalConstants.LARGE, -Alchemy.AlchemicalConstants.SMALL, Alchemy.AlchemicalConstants.SMALL);
   }
	
	//when game starts, alchemies are randomly assigned to ingredients
	public void assignRandomAlchemy() {
        ArrayList<Integer> alchemyIndex = new ArrayList<Integer>();
        for (int i = 0; i < 8; i++) {
            alchemyIndex.add(i);
        }
        Collections.shuffle(alchemyIndex);
        for (int i = 0; i < 8; i++) {
            alchemyMap.put(ingredients[i], alchemies[alchemyIndex.get(i)]);
        }
    }
	
	public String[] getIngredients() {
		return ingredients;
	}
	
	public Alchemy[] getAlchemies() {
		return alchemies;
	}
	
	public Map<String, Alchemy> getAlchemyMap() {
		return alchemyMap;
	}
	
	public List<IngredientCard> createIngredients(int num) {
		List<IngredientCard> ingredientList = new ArrayList<IngredientCard>();
		for (int i = 0; i < num; i++) {
			ingredientList.add(createIngredient(ingredients[i%8]));
	    }
		Collections.shuffle(ingredientList);
		return ingredientList;
	}
	
	public IngredientCard createIngredient(String name) {
		try {
			return new IngredientCard(name, alchemyMap.get(name));
		}
		catch (Exception e) {
			System.err.println(String.format("Error in creating IngredientCard object with name %s", name));
			return null;
		}
	}

}
