package system.domain.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import system.domain.Alchemy;
import system.domain.IngredientCard;

public class IngredientFactory{
	
	private static IngredientFactory instance = null;
	private String[] ingredients = {"Solaris Root", "Bat Wing", "Toad Stool", "Owl Feather", "Snake Venom", "Rat Tail", "Spider Web", "Newt Eye"};
	private Map<String, Alchemy> alchemyMap;
	
	private IngredientFactory() {
		this.alchemyMap = new HashMap<String, Alchemy>();
	}

	public static IngredientFactory getInstance() {
		if (instance == null) {
			instance = new IngredientFactory();
		}
		return instance;
	}

	//when game starts, alchemies are randomly assigned to ingredients
	public void setAlchemyMap(List<Integer> alchemyIndex) {
        for (int i = 0; i < 8; i++) {
            alchemyMap.put(ingredients[i], Alchemy.getAlchemy(alchemyIndex.get(i)));
        }
    }
	
	public String[] getIngredients() {
		return ingredients;
	}
	
	public Map<String, Alchemy> getAlchemyMap() {
		return alchemyMap;
	}
	
	public IngredientCard createIngredient(String name) {
		return new IngredientCard(name, alchemyMap.get(name));
	}

	public IngredientCard createIngredient(int index) {
		return createIngredient(ingredients[index]);
	}

}
