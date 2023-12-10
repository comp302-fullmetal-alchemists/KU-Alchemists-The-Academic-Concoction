package system.domain;

public class GameAction {

    private String from;
    private String to;
    private String action;
    private int score;

    //has the variables for: Where the action is coming from, who is affected/notified by it, what is the action and its score value for final score calculation.
    public GameAction (String from, String to, String action, int score) {
        this.from = from;
        this.to = to;
        this.action = action;
        this.score = score;
    }
    
    public String toString() { //Example "From player1 to player2, 3 gold given." or "From Player1 to Adventurer, negative potion given."
        return String.format("From %s to %s, %s\n", from, to, action);
    }

    public int getScore(){
        return score;
    }
}
