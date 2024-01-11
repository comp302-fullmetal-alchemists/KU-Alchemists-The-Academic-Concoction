package system.domain.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import java.util.ArrayList;

import system.domain.interfaces.Observer;

public class AuthenticationController {
 
    private Observer authenticationUI;

    public AuthenticationController() {

    }

    // This method is called by the UI when the user clicks the login button
    public void setObserver(Observer observer) {
        this.authenticationUI = observer;
    }
    
    /* 
    // This method is called by the UI when the user clicks the login button
    public void login(String username1, Icon token1, String username2, Icon token2) {
        //chechks if the usernames are the same
        if (username1.equals(username2)){
            authenticationUI.update("Usernames cannot be the same");
        }
        //checks whether usernames are empty
        else if (username1.equals("") || username2.equals("")){
            authenticationUI.update("Please fill all the fields");
        }
        //creates 2 players and calls the initializeTheBoard method
        else{
        	ArrayList<Player> players = new ArrayList<Player>();
        	players.add(new Player(username1, token1));
        	players.add(new Player(username2, token2));
            GameBoardController.getInstance().initializeTheBoard(players.get(0), players.get(1));
            authenticationUI.update("VALID");
    }
    */

    // This method is called by the UI when the user clicks the login button
    public void login(String username, Icon token){
        //checks whether username is empty
        if (username.equals("")){
            authenticationUI.update("Please fill all the fields");
        }

        else if (GameBoardController.getInstance().getClientAdapter().validateUserChoices(username)){
            Player player = new Player(username, token);
            GameBoardController.getInstance().getClientAdapter().registerPlayer(player);
            authenticationUI.update("VALID");
        }
        
        else{
            authenticationUI.update("Username already exists");
        }
    }
}

