package system.domain;

import java.util.ArrayList;
public class Potion {
    //potion	status: String (int?)
    //formula: ArrayList<IngredientCard>	+ operation1(params):returnType
    //- operation2(params)
    //- operation3()

    private String status;
    private ArrayList<IngredientCard> formula;

    public Potion(String status, ArrayList<IngredientCard> formula) {
        this.status = status;
        this.formula = formula;
    }

    public String getStatus() {
        return this.status;
    }


}
