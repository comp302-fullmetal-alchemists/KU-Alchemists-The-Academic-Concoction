package system.domain.controllers;

import system.domain.Player;
import system.domain.interfaces.Observer;

public class AuthenticationController {
 
    private Observer authenticationUI;

    public AuthenticationController() {
    }

    public void setObserver(Observer observer) {
        this.authenticationUI = observer;
    }

    public void login(String username1, String token1, String username2, String token2) {
        if (username1.equals(username2)){
            authenticationUI.update("Usernames cannot be the same");
        }
        else if (username1.equals("") || token1.equals("") || username2.equals("") || token2.equals("")){
            authenticationUI.update("Please fill all the fields");
        }
        else{
            GameBoardController.getInstance().initializeTheBoard(new Player(username1, token1), new Player(username2, token2));
            authenticationUI.update("VALID");
    }
}

}