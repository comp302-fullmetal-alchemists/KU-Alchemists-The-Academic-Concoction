package system.domain.controllers;

import system.domain.interfaces.Mediator;
import system.domain.GameAction;
import system.domain.interfaces.Observer;
import system.domain.ResultsTriangle;
import system.domain.util.IngredientFactory;

import javax.swing.Icon;

public class Player {
    
    private String name;
    private Boolean turn;
    private int turnsLeft;
    private Icon token;
    private int reputationPoint;
    private int sicknessPoint;
    private ResultsTriangle resultsTriangle;
    private InventoryController inventory;
    private Observer playerUI;
    private Mediator mediator;

    public Player(String name, Icon token) {
        this.name = name;
        this.turn = false;
        this.token = token;
        this.reputationPoint = 0;
        this.sicknessPoint = 2;
        this.inventory = new InventoryController();
        this.resultsTriangle = new ResultsTriangle();
        this.mediator = GameBoardController.getInstance().getMediator();
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
        }
        turn = !turn;
    }

    public void playedTurn() {
        turnsLeft -= 1;
        if (turnsLeft == 0) {
            GameBoardController.getInstance().endPlayerTurn();
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

    public int getSickness(){
        return sicknessPoint;
    }
    
    public InventoryController getInventory() {
        return inventory;
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
    
    public ResultsTriangle getResultsTriangle() {
    	return resultsTriangle;
    }
    
    public void sendResults() {
    	mediator.sendToCollector(resultsTriangle);
    }


}
