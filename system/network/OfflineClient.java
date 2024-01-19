package system.network;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import system.domain.Alchemy;
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


    public void peek3Ingredients(){
        List<Integer> topIngredients = server.peek3Ingredients();
        GameBoardController.getInstance().getIngredientStorageController().elixirOfInsight(topIngredients);

    }

    public void rewriteIng(String serverMsg){
        server.rewriteIng(serverMsg);
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
                    p.updateReputation(-1);
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


    @Override
    public void send(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'send'");
    }

    public void endGame() {
        
        List<String> scoreList = new ArrayList<String>();
        for (Player p: players) {
            String score = String.format("my_score:%s,%d:%d", p.getName(), p.getTokenIndex(), GameBoardController.getInstance().calculateFinalScore(p));
            scoreList.add(score);
        }
        Collections.sort(scoreList, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] score1 = s1.split(":");
                String[] score2 = s2.split(":");

                // Convert score[2] to integer for comparison
                int score1Int = Integer.parseInt(score1[2]);
                int score2Int = Integer.parseInt(score2[2]);

                // For descending order
                return Integer.compare(score2Int, score1Int);
            }
        });
        String message = "show_endgame_screen";
        for (String score: scoreList) {
            message += ":" + score ;
            System.out.println(score);
        }
        GameBoardController.getInstance().showEndgameScreen(message);
    }


    @Override
    public String getMode() {
        return "Offline";
    }
    
}
