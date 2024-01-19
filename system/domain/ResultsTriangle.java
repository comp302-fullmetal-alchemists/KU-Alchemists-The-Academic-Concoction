package system.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import system.domain.util.IngredientFactory;

public class ResultsTriangle {
	
	private HashMap<String[], Potion> results;
	
	public ResultsTriangle() {
		this.results = new HashMap<String[], Potion>();
	}
	
	public String[] sort(IngredientCard ing1, IngredientCard ing2) {
		// The map IngredientxIngredient -> Potion is commutative, order should not matter 
		// so either I will keep track of the potion in ing1, ing2 and ing2, ing1 orders,
		// or I fix an internal implementation order and do everything with it. 
		// former is more open to bugs & errors, this function is for the internal order of the latter.
		String ingFirst, ingSecond;
		if (ing1.getIngredientOrder() < ing2.getIngredientOrder()) {
			ingFirst = ing1.getName();
			ingSecond = ing2.getName();
		}
		else {
			ingFirst = ing2.getName();
			ingSecond = ing1.getName();
		}
		String[] sorted = {ingFirst, ingSecond};
		return sorted;
	}
	
	public void newResult(IngredientCard ing1, IngredientCard ing2, Potion p) {
		String[] sorted = sort(ing1, ing2);
		results.put(sorted, p);
	}
	
	public Potion get(IngredientCard ing1, IngredientCard ing2) {
		String[] sorted = sort(ing1, ing2);
		return results.get(sorted); //this may still be null, maybe I can check these conditions and throw an exception idk.
	}
	
	public List<String> getResultList() {
		List<String> resultsList = new ArrayList<String>();
		for (String[] ings: results.keySet()) {
			resultsList.add(String.format("%s    +    %s    =    %s\n", ings[0], ings[1], results.get(ings).getStatus()));
		}
		return resultsList;
	}
	
	
}
