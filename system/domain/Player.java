package system.domain;

import system.domain.controllers.InventoryController;

import javax.swing.Icon;

import system.domain.controllers.GameBoardController;

public class Player {
    
    private String name;
    private Boolean turn;
    private int turnsLeft;
    private Icon token;
    private int reputationPoint;
    private int sicknessPoint;
    private InventoryController inventory;

    public Player(String name, Icon token) {
        this.name = name;
        this.turn = false;
        this.token = token;
        this.reputationPoint = 0;
        this.sicknessPoint = 0;
        this.inventory = new InventoryController();
      
    }

    /**********Getters and Setters******************/
    public void setName(String name) {
		this.name = name;
	}

    public String getName(){
        return name;
    }

    public Boolean isInTurn() {
        return turn;
    }

    public void changeTurn() {
        if (!turn) {
            turnsLeft = 3;
        }
        turn = !turn;
    }

    public void playedTurn() {
        turnsLeft -= 1;
        if (turnsLeft == 0) {
            GameBoardController.getInstance().changePlayer();
        }
    }

	public void setToken(Icon token) {
		this.token = token;
	}
    
    public Icon getToken() {
        return token;
    }

    public int getReputation() {
        return reputationPoint;
    }

    public InventoryController getInventory() {
        return inventory;
    }


    public void updateReputation(int updateVal) {
        reputationPoint = reputationPoint + updateVal;
    }

    public void updateSickness(int updateVal) {
        sicknessPoint = sicknessPoint + updateVal;
    }


}
