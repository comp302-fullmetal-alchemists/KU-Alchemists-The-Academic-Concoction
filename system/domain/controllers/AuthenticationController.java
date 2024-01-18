package system.domain.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import system.domain.interfaces.Observer;

public class AuthenticationController {
 
    private Observer authenticationUI;
    private List<Player> players;

    public AuthenticationController() {
        this.players = new ArrayList<Player>();
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
            GameBoardController.getInstance().initializeTheBoard(new Player(username1, token1), new Player(username2, token2));
            authenticationUI.update("VALID");
    }
    */

    // This method is called by the UI when the user clicks the login button
    public void login(String username, int tokenIndex){
        //checks whether username is empty
        if (username.equals("")){
            authenticationUI.update("Please fill all the fields");
        }

        else if (checkUsername(username)){
            Player player = new Player(username, tokenIndex);
            players.add(player);
            GameBoardController.getInstance().registerPlayer(player);
            authenticationUI.update("VALID");
            if (players.size() == GameBoardController.getInstance().getNoPlayers()){
                GameBoardController.getInstance().initializeTheBoard();
            }
        }
        //creates a player and calls the initializeTheBoard method
        else{
            authenticationUI.update("Username already exists");
        }
    }

    public boolean checkUsername(String username){
        for (Player i : players){
            if (i.getName().equals(username)){
                return false;
            }
        }
        return true;
    }
}

