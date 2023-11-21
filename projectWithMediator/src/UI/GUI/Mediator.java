package UI.GUI;

import Domain.Player;

public class Mediator {
    
    private PlayerDashboard playerDB;
    private GameArea gameArea;


    public Mediator() {
    }


    public void startSessionWith(PlayerDashboard playerDB){
        this.playerDB = playerDB;
    }

    public void startSessionWith(GameArea gameArea) {
        this.gameArea = gameArea;
        playerDB.readyArea(gameArea.getName());
    }

    public void sendChoice(String choice) {
        gameArea.takePlayerChoice(choice);
    }

    public void sendIntChoice(int choice) {
        gameArea.takePlayerIntChoice(choice);
    }


}
