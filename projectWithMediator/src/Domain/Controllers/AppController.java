package Domain.Controllers;

import Domain.Player;

public class AppController {
    private Player p1;
    private Player p2;


    public AppController() {
    
    }

    public AppController(Player p1, Player p2){
        this();
        addPlayers(p1, p2);
    }

    public void addPlayers(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        p1.changeTurn();
    }

    public void changeTurns() {
        p1.changeTurn();
        p2.changeTurn();
    }

    public Player requestPlayerInTurn() {
        if (p1.isInTurn()) return p1;
        else return p2;
    }


}
