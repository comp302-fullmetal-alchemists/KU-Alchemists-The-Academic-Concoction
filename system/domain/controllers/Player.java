package system.domain.controllers;

import system.domain.interfaces.Mediator;
import system.domain.DeductionGrid;
import system.domain.GameAction;
import system.domain.interfaces.Observer;
import system.domain.ResultsTriangle;
import system.domain.util.IngredientFactory;

import javax.swing.Icon;

public class Player {
    
    private String name;
    private Boolean turn;
    private int turnsLeft;
    private int tokenIndex;
    private int round;
    private int reputationPoint;
    private int sicknessPoint;
    private ResultsTriangle resultsTriangle;
    private DeductionGrid deductionGrid;
    private InventoryController inventory;
    private Observer playerUI;

    public Player(String name, int tokenIndex) {
        this.name = name;
        this.turn = false;
        this.tokenIndex = tokenIndex;
        this.reputationPoint = 0;
        this.sicknessPoint = 0;
        this.round = 0;
        this.inventory = new InventoryController();
        this.resultsTriangle = new ResultsTriangle();
        this.deductionGrid = new DeductionGrid();
        GameBoardController.getInstance().getGameLog().GameLogControllerInitPlayer(this);
        GameBoardController.getInstance().getGameLog().recordLog(this, "KU Alchemist", name, "Game has started!", 0);

    }
     
    public void setPlayerUI(Observer observer) {
    	this.playerUI = observer;
        for(GameAction g: GameBoardController.getInstance().getGameLog().getGameActionsOf(this)) {
            appendToGameLog(g.toString());
        }
    }
    
    /**********Getters and Setters******************/

    public void appendToGameLog(String text) {
        if (playerUI != null) {
            playerUI.update("GAMELOG:"+text);
        }
    }

    public void appendToChatLog(String text) {
        if (playerUI != null) {
            playerUI.update(text);
        }
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

    public int getTurn() {
        return turnsLeft;
    }

    public void changeTurn() {
        if (!turn) {
            turnsLeft = 3;
            round += 1;
        }
        turn = !turn;
    }

    public void playedTurn() {
        turnsLeft -= 1;
        if (turnsLeft == 0) {
            GameBoardController.getInstance().endPlayerTurn();
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

    public int getRound() {
        return round;
    }
    
    public InventoryController getInventory() {
        return inventory;
    }
    
    public ResultsTriangle getResultsTriangle() {
    	return resultsTriangle;
    }

    public DeductionGrid getDeductionGrid() {
    	return deductionGrid;
    }
    
    public void updateReputation(int updateVal) {
        reputationPoint = reputationPoint + updateVal;
        playerUI.update("REPUTATION");
    }
    
    public void performSurgery() {
    	playerUI.update("SURGERY");
    	sicknessPoint = 0;
    	inventory.updateGold(-inventory.getGold());
    	playerUI.update("SICKNESS");
    }

    public void updateSickness(int updateVal) {
    	if (sicknessPoint != 0 || updateVal != -1) {
	        sicknessPoint = sicknessPoint + updateVal;
			playerUI.update("SICKNESS");
    		if (sicknessPoint == 3) {
    			performSurgery();
    		}

    	}
    }

    
  


}
