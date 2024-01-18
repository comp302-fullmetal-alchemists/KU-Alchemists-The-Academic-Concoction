package system.domain.controllers;

import system.domain.interfaces.Observer;

import javax.swing.Icon;

import system.ui.panels.PlayerDashboard;

public class Player {
    
    private String name;
    private Boolean turn;
    private int turnsLeft;
    private int tokenIndex;
    private int reputationPoint;
    private int sicknessPoint;
    private InventoryController inventory;
    private Observer playerUI;

    public Player(String name, int tokenIndex) {
        this.name = name;
        this.turn = false;
        this.tokenIndex = tokenIndex;
        this.reputationPoint = 0;
        this.sicknessPoint = 0;
        this.inventory = new InventoryController();
      
    }

    public void setPlayerUI(Observer observer) {
    	this.playerUI = observer;
    }
    
    /**********Getters and Setters******************/

    public void appendToGameLog(String text) {
        playerUI.update("GAMELOG:"+text);
    }

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

	public void setToken(int tokenIndex) {
		this.tokenIndex = tokenIndex;
	}
    
    public int getTokenIndex() {
        return tokenIndex;
    }

    public int getReputation() {
        return reputationPoint;
    }

    public int getSickness(){
        return sicknessPoint;
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
