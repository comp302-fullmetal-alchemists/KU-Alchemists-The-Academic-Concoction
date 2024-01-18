package system.domain;

import java.util.ArrayList;
import system.domain.controllers.GameBoardController;

public class Potion {

    private String status;
    private ArrayList<IngredientCard> formula;


    public Potion(IngredientCard ing1, IngredientCard ing2) {
        Alchemy alch1 = ing1.getAlchemy();
        Alchemy alch2 = ing2.getAlchemy();
        this.status = Alchemy.combine(alch1, alch2);
    }

    public Potion(String status, ArrayList<IngredientCard> formula) {
        this.status = status;
        this.formula = formula;
    }

    public String getStatus() {
        return this.status;
    }

    public ArrayList<IngredientCard> getFormula() {
        return this.formula;
    }


}
