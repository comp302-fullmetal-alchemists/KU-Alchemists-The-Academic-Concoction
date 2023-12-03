package system.domain;

import system.domain.controllers.InventoryController;

public class Player {
    
    private String name;
    private Boolean turn;
    private String token;
    private int reputationPoint;
    private int sicknessPoint;
    private InventoryController inventory;

    public Player(String name, String token) {
        this.name = name;
        this.turn = false;
        this.token = token;
        this.inventory = new InventoryController();
      
    }

    public String getName(){
        return name;
    }
    
    public void setName(String name) {
		this.name = name;
	}

    public Boolean getTurn() {
		return turn;
	}

	public void setTurn(Boolean turn) {
		this.turn = turn;
	}

	public void setToken(String token) {
		this.token = token;
	}

    public int getReputation() {
        return reputationPoint;
    }

    public void updateReputation(int updateVal) {
        reputationPoint = reputationPoint + updateVal;
    }

    public void updateSickness(int updateVal) {
        sicknessPoint = sicknessPoint + updateVal;
    }

	public Boolean isInTurn() {
        return turn;
    }

    public void changeTurn() {
        turn = !turn;
    }


    public String getToken() {
        return token;
    }
   
   public InventoryController getInventory() {
        return inventory;
   }
   
}
