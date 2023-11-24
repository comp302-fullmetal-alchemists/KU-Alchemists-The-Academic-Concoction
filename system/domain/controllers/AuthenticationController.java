package system.domain.controllers;

import system.domain.Player;

public class AuthenticationController {
 
    public AuthenticationController() {
    
    }

    public String login(String username1, String token1, String username2, String token2) {
        if (username1.equals(username2)){
            return "Usernames cannot be the same";
        }
        else if (username1.equals("") || token1.equals("") || username2.equals("") || token2.equals("")){
            return "Please fill all the fields";
        }
        else{
            Player p1 = new Player(username1, token1);
            Player p2 = new Player(username2, token2);
            return "Welcome to the KuAlchemists";
    }
}

}