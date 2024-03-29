package system.domain;


public class Theory {
        // for each theory there is an alchemy and an ingredient and also an owner
        private Alchemy alchemy;
        private String ingredient;
        private String owner;
        private boolean debunked;
        private boolean endorsed;
        private String endorser;
        

    public Theory(Alchemy alchemy, String ingredient, String owner) {
        this.alchemy = alchemy;
        this.ingredient = ingredient;
        this.owner = owner;
        this.debunked = false;
        this.endorsed = false;
        this.endorser = null;
        
    }

    public Alchemy getAlchemy() {
        return alchemy;
    }

    public void setAlchemy(Alchemy alchemy) {
        this.alchemy = alchemy;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getOwner() {
        return owner;
    }   

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isDebunked() {
        return debunked;
    }

    public void setDebunked(boolean debunked) {
        this.debunked = debunked;
    }
    
    public boolean isEndorsed() {
        return endorsed;
    }

    public void endorsed(String endorser) {
        this.endorser = endorser;
        this.endorsed = true;
    }

    public String getEndorser() {
        return endorser;
    }

    public String  toString() {
        System.out.println("Alchemy: " + alchemy + " Ingredient: " + ingredient + " Owner: " + owner);
        return endorser;
    }
}
