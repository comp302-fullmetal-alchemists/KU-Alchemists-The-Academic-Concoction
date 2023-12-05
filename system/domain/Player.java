package system.domain;

import system.domain.controllers.InventoryController;
import system.ui.panels.PlayerDashboard;
import system.domain.controllers.GameBoardController;

public class Player {
    
    private String name;
    private Boolean turn;
    private int turnsLeft;
    private String token;
    private int reputationPoint;
    private int sicknessPoint;
    private InventoryController inventory;
    private PlayerDashboard playerDashboardUI;

    public Player(String name, String token) {
        this.name = name;
        this.turn = false;
        this.token = token;
        this.reputationPoint = 0;
        this.sicknessPoint = 0;
        this.inventory = new InventoryController();
    }

    /**********Getters and Setters******************/
    public void setPlayerDashboard(PlayerDashboard playerDashboard){
        this.playerDashboardUI = playerDashboard;
    }

    public PlayerDashboard getPlayerDashboard(){
        return this.playerDashboardUI;
    }

    public void appendToGameLog(String text) {
        playerDashboardUI.appendToGameLog(text);
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

	public void setToken(String token) {
		this.token = token;
	}
    public String getToken() {
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
