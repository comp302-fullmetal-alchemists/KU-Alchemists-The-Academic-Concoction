package system.domain.controllers;

import system.domain.Player;

public class AuthenticationController {
 
    private GameBoardController gameController;

    public AuthenticationController(GameBoardController gameController) {
        this.gameController = gameController;
    }

    public String login(String username1, String token1, String username2, String token2) {
        if (username1.equals(username2)){
            return "Usernames cannot be the same";
        }
        else if (username1.equals("") || token1.equals("") || username2.equals("") || token2.equals("")){
            return "Please fill all the fields";
        }
        else{
            gameController.initializeTheBoard(new Player(username1, token1), new Player(username2, token2));
            return "Welcome to the KuAlchemists";
    }
}

}