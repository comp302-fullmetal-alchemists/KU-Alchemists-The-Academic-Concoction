package system.domain;

import system.domain.controllers.Player;

public class Theory {
        // for each theory there is an alchemy and an ingredient and also an owner
        private Alchemy alchemy;
        private String ingredient;
        private Player owner;
        private boolean debunked;
        private boolean endorsed;
        private Player endorser;
        

    public Theory(Alchemy alchemy, String ingredient, Player owner) {
        this.alchemy = alchemy;
        this.ingredient = ingredient;
        this.owner = owner;
        this.debunked = false;
        this.endorsed = false;
        
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

    public Player getOwner() {
        return owner;
    }   

    public void setOwner(Player owner) {
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

    public void endorsed(Player endorser) {
        this.endorser = endorser;
        this.endorsed = true;
    }
}
