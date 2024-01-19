package system.network;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.Map.Entry;

import system.domain.Alchemy;
import system.domain.ArtifactCard;
import system.domain.controllers.AuthenticationController;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.GameLogController;
import system.domain.controllers.Player;
import system.domain.util.IngredientFactory;

public class OfflineClient implements IClientAdapter {

    private OfflineServer server;
    private List<Player> players;
    private int playerNum;
    private int currentPlayer;
    private GameLogController gamelog;
    
    public OfflineClient(OfflineServer server) {
        this.server = server;
        this.players = new ArrayList<Player>();
        this.gamelog = GameBoardController.getInstance().getGameLog();
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
    public void validateUserChoices(String username) {
        for (Player p: players) {
            if (p.getName().equals(username)) {
                AuthenticationController.getInstance().invalidUsername();
                return;
            }
            
        }
        AuthenticationController.getInstance().validUsername();
    }

    // after check, register the players
    @Override
    public void registerPlayer(Player p) {
        players.add(p);
        if (players.size() == playerNum) {
            server.initializeGame();            
        }
        else {
            GameBoardController.getInstance().startAuthentication();
        }
    }

    @Override
    public void setAlchemyMap(List<Integer> alchemyIndex) {
        IngredientFactory.getInstance().setAlchemyMap(alchemyIndex);
    }

    @Override
    public void initialize() {
        Random random = new Random();
        for (Player p: players) {
            p.getInventory().initializeIngredients(IngredientFactory.getInstance().createIngredient(random.nextInt(8)), 
                                                   IngredientFactory.getInstance().createIngredient(random.nextInt(8)));
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

    @Override
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


    @Override
    public void requestIngredient() {
        server.requestIngredient();
    }

    @Override
    public void emptyPile() {
        GameBoardController.getInstance().getIngredientStorageController().emptyPileError();
    }

    @Override
    public void takeIngredientIndex(int index) {
        GameBoardController.getInstance().getIngredientStorageController().takeIngredient(IngredientFactory.getInstance().createIngredient(index));
    }


    @Override
    public void reportPublishTheoryToServer(Alchemy alchemy, String ingredient, String playerName) {
        for (Player p: players) {
            if (!p.getName().equals(playerName)) {
                gamelog.recordLog(p, "Academy", p.getName(), String.format("%s published the Theory with %s and %s!", playerName, ingredient, alchemy.toString()), 2);
            }
        }
    }

    @Override
    public void reportEndorseTheoryToServer(String ingredient, String playerName, String ownerName) {
        for (Player p: players) {
            if (!p.getName().equals(playerName)) {
                if (p.getName().equals(ownerName)) {
                    gamelog.recordLog(p, "Academy", p.getName(), String.format("%s paid 1 gold to endorse your theory!", playerName), 2);
                    p.getInventory().updateGold(1);
                }
                else {
                    gamelog.recordLog(p, "Academy", p.getName(), String.format("%s endorsed the Theory of %s about %s!", playerName, ownerName, ingredient), 2);
                }
            }
        }
    }


    @Override
    public void reportDebunkTheoryToServer(Alchemy alchemy, String ingredient, String playerName, String ownerName) {
        for (Player p: players) {
            if (!p.getName().equals(playerName)) {
                if (p.getName().equals(ownerName)) {
                    if (p.getInventory().getWisdomIdol()) {
                        p.getInventory().setWisdomIdol(false);
                    }
                    else {
                        p.updateReputation(-1);
                    }
                    gamelog.recordLog(p, "Academy", p.getName(), String.format("%s debunked your Theory about %s!", playerName, ingredient), 0);
                }
                else {
                    gamelog.recordLog(p, "Academy", p.getName(), String.format("%s debunked the Theory of %s about %s!", playerName, ownerName, ingredient), 0);

                }
            }
        }
    }



    public void closeResources() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'closeResources'");
    }


    @Override
    public void reportExitGameToServer() {
        System.exit(0);
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
            


            
}
