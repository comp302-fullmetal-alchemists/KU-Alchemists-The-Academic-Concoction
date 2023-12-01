package system.domain.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import system.domain.Player;
import system.domain.ArtifactCard;
import system.domain.interfaces.Observer;


public class GameBoardController {

    private static GameBoardController instance;
    private List<Player> players;
    private IngredientStorageController ingredientStorage;
    private PotionBrewingAreaController potionBrewingArea;
    private DeductionBoardController deductionBoard;
    private PublicationAreaController publicationArea;
    private Observer gameboardUI;

    //GameLogController gameLog = new GameLogController(players.get(0), players.get(1)); //get the players and initalize the gamelog
    GameLogController gameLog;

    private GameBoardController() {
        this.players = new ArrayList<Player>();
    }

    public static GameBoardController getInstance() {
        if (instance == null) {
            instance = new GameBoardController();
        }
        return instance;
    }

    public void setObserver(Observer observer) {
        this.gameboardUI = observer;
    }


    public void initializeTheBoard(Player player1, Player player2) {
        players.add(player1);
        players.add(player2);
        Random random = new Random();
        int firstPlayer = random.nextInt(2);
        players.get(firstPlayer).changeTurn();
        this.ingredientStorage = new IngredientStorageController();
        this.publicationArea = new PublicationAreaController();
        this.deductionBoard = new DeductionBoardController();
        this.potionBrewingArea = new PotionBrewingAreaController();
        this.ingredientStorage.initializePiles();
        gameboardUI.update("INITIALIZE_BOARD");
    }


    public GameLogController getGameLog(){
        return gameLog;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public void changePlayer() {
        players.get(0).changeTurn();
        players.get(1).changeTurn();
        gameboardUI.update("CHANGE_PLAYER");
    }
    
    public Player getCurrentPlayer() {
        if (players.get(0).getTurn()) {
            return players.get(0);
        } else {
            return players.get(1);
        }
    }

    public IngredientStorageController getIngredientStorageController(){
        return ingredientStorage;
    }

    public PotionBrewingAreaController getPotionBrewingAreaController() {
        return potionBrewingArea;
    }

    public PublicationAreaController getPublicationAreaController() {
        return publicationArea;
    }

    public DeductionBoardController getDeductionBoardController() {
        return deductionBoard;
    }


    
    public int calculateFinalScore(Player player) {
    //to do: get rep, gold, artifact from player's inventory
    	int finalScore = 0;
        
        //10 points for each rep points
        finalScore += (player.getReputation() * 10) ;
       //trade each artifact with 2 golds
        for(ArtifactCard a : player.getInventory().getArtifactCards()) {
                player.getInventory().updateGold(2);
        }
        //1 point for 3 gold  
        finalScore += (player.getInventory().getGold() / 3) ;

        return finalScore;
    }


    public Player winner(){
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        int score1 = calculateFinalScore(player1); 
        int score2 = calculateFinalScore(player2);

        if(score1 > score2){
            return player1;
        }
        else if(score1 == score2){
            
            for(Player p: players){
                p.getInventory().updateGold(- (p.getInventory().getGold() / 3)) ;
            }

            if(player1.getInventory().getGold() > player2.getInventory().getGold()) {
                return player1;
            }
           // else if(player1.getInventory().getGold() == player2.getInventory().getGold()) {
                //display they are both tie
           // }
           else{
             return player2;
           }
        
        }
        else{
            return player2; 
        }
        }


    public void startGame() {

        return;
    }
}
