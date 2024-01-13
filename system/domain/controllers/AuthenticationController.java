package system.domain.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import java.util.ArrayList;

import system.domain.interfaces.Observer;

public class AuthenticationController {
    
    private static AuthenticationController instance;
    private Observer authenticationUI;
    private String tempUsername;
    private Icon tempToken;

    public static AuthenticationController getInstance(){
        if (instance == null) {
            instance = new AuthenticationController();
        }
        return instance;
    }

    // This method is called by the UI when the user clicks the login button
    public void setObserver(Observer observer) {
        this.authenticationUI = observer;
    }
    

    // This method is called by the UI when the user clicks the login button
    public void login(String username, Icon token){
        //checks whether username is empty
        if (username.equals("")){
            authenticationUI.update("Please fill all the fields");
        }
        else {
            this.tempUsername = username;
            this.tempToken = token;
            GameBoardController.getInstance().getClientAdapter().validateUserChoices(username);
        }
    }

    public void invalidUsername() {
        authenticationUI.update("Username already exists");
    }

    public void validUsername() {
        Player player = new Player(tempUsername, tempToken);
        GameBoardController.getInstance().getClientAdapter().registerPlayer(player);
        authenticationUI.update("VALID");
    }
    
}

