package system.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.GameLogController;
import system.domain.controllers.Player;

public class OfflineClient implements IClientAdapter {

    private OfflineServer server;
    private List<Player> players;
    private int playerNum;
    private int currentPlayer;
    
    public OfflineClient(OfflineServer server) {
        this.server = server;
        this.players = new ArrayList<Player>();
    }

    @Override 
    public void connectToServer() {
        // in offline there is no need for this method
    }
    
    @Override
    public void startAuthentication() {
        GameBoardController.getInstance().startAuthentication();
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    // for checking in authentication
    @Override
    public boolean validateUserChoices(String username) {
        for (Player p: players) {
            if (p.getName().equals(username)) return false;
        }
        return true;
    }

    // after check, register the players
    @Override
    public void registerPlayer(Player p) {
        // get ingredients from server and add them to the player here
        p.getInventory().initializeIngredients(server.drawIngredient(), server.drawIngredient());
        players.add(p);
        if (players.size() == playerNum) {
            server.initializeGame();            
        }
        else {
            GameBoardController.getInstance().startAuthentication();
        }
    }

    @Override
    public void initialize() {
        for (Player p: players) {
            GameBoardController.getInstance().getGameLog().GameLogControllerInitPlayer(p);
        }
        GameBoardController.getInstance().initializeTheBoard();
        Collections.shuffle(players);
    }

    @Override
    public void authorize() {
        GameBoardController.getInstance().setPlayer(players.get(currentPlayer));
        players.get(currentPlayer).changeTurn();
        GameBoardController.getInstance().authorizePlayer();
    }

    @Override
    public void deauthorize() {
        players.get(currentPlayer).changeTurn();
        GameBoardController.getInstance().deauthorizePlayer();    
    }


    public void endPlayerTurn() {
        server.changePlayer();
    }
    
    public void changePlayer() {
        currentPlayer += 1;
        if (currentPlayer == playerNum) {
            currentPlayer = 0;
            server.newRound();
        }
    }
    
    public boolean ingPileIsEmpty() {
        return server.ingPileIsEmpty();
    }

    public IngredientCard drawIngredient() {
        return server.drawIngredient();
    }

    public boolean artifactPileIsEmpty() {
        return server.artifactPileIsEmpty();
    }

    public ArtifactCard drawArtifact() {
        return server.drawArtifact();
    }

    public int calculateFinalScore(Player player) {
    
        //to do: get rep, gold, artifact from player's inventory
            int finalScore = 0;
            //10 points for each rep points
            if(player.getReputation() >= 0){
                finalScore += (player.getReputation() * 10) ;
            }
            
           //trade each artifact with 2 golds
           Iterator<ArtifactCard> iterator = player.getInventory().getArtifactCards().iterator();
           while (iterator.hasNext()) {
               ArtifactCard a = iterator.next();
               player.getInventory().updateGold(2);
               iterator.remove(); // Remove the artifact after trading for gold
           }
            
            //1 point for 3 gold  
            if(player.getInventory().getGold() > 0){
                int amount = (player.getInventory().getGold() / 3) ;
                finalScore += amount ;
                player.getInventory().updateGold(- (amount * 3)) ;
                
            }
            return finalScore;
        }



       
        public HashMap<Player, Integer> winner(){
            //hashmap player ve score
            HashMap<Player, Integer> scores = new HashMap<>();
            for(Player p : players ){
                int score = calculateFinalScore(p);
                scores.put(p, score);
                //onlineda goldu da çağır!

            }

            //score map oluştur sonra onu sortla (compare to kullan collections sort kullan)
            List<Map.Entry<Player, Integer>> scores_values = new ArrayList<>(scores.entrySet());
            

            Collections.sort(scores_values, new Comparator<Map.Entry<Player, Integer>>() {
                @Override
                public int compare(Map.Entry<Player, Integer> player1, Map.Entry<Player, Integer> player2) {
                    int scoreCompare = player2.getValue().compareTo(player1.getValue()); 
    
                    // compare gold if final scores are equal
                    if (scoreCompare == 0) {
                        int goldComparison = player1.getKey().getInventory().getGold() - player2.getKey().getInventory().getGold();
                        return goldComparison;
                    }
    
                    return scoreCompare;
                }
            });

            
            HashMap<Player, Integer> scores_sorted = new HashMap<>();
             for(Entry<Player, Integer> p : scores_values){
                scores_sorted.put(p.getKey(), p.getValue());
             }
            
            return scores_sorted;

            }
            

//cfs da winner dw buraya al serverda çağır

}
