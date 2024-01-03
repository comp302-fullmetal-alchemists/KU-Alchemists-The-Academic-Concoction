package system.domain.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import system.domain.ArtifactCard;
import system.domain.GameAction;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Mediator;
import system.domain.util.ConcreteMediator;


public class GameBoardController {

    private static GameBoardController instance;
    private IngredientStorageController ingredientStorage;
    private PotionBrewingAreaController potionBrewingArea;
    private DeductionBoardController deductionBoard;
    private PublicationAreaController publicationArea;
    private GameLogController gameLog; 
    private TheoryController theory;
    private Mediator mediator;
    private Observer gameboardUI;

    private GameBoardController() {
        this.mediator = new ConcreteMediator();
        this.gameLog = new GameLogController(); //create the gamelog
    }

    public void setObserver(Observer observer) {
        this.gameboardUI = observer;
    }
    
    //GameboardController is a singleton class, this is the lazy initialization method
    public static GameBoardController getInstance() {
        if (instance == null) {
            instance = new GameBoardController();  
        }
        return instance;
    }

    //authentication sends players to gameboard and gameboard readies the game areas
    public void initializeTheBoard() {
        //gameLog.GameLogControllerInitPlayer(player1); //initalize the gamelog with the players
        //gameLog.GameLogControllerInitPlayer(player2); 
        this.ingredientStorage = new IngredientStorageController();
        this.publicationArea = new PublicationAreaController();
        this.deductionBoard = new DeductionBoardController();
        this.potionBrewingArea = new PotionBrewingAreaController();
        this.theory = new TheoryController();
        gameboardUI.update("INITIALIZE_BOARD");
    }


    /*	when a player is going to be changed, first the game areas are cleared and items remaining in them
    	are sent back to the previous player because the previous player may have objects left in those areas, 
    	like an ingredient that is going to be sold.This was the "CHANGING_PLAYER" update.
    	 Then it changes the view to the next player, this is the "CHANGED_PLAYER" update.
     
     */
    public void changePlayer() {
        //GAMELOG RECORDS LOG: When the round is over for a player
        //gameLog.recordLog(getCurrentPlayer(), "KU Alchemist", getCurrentPlayer().getName(),  String.format("Round over!"), 0);

        gameboardUI.update("CHANGING_PLAYER");
        
        players.get(0).changeTurn();
        players.get(1).changeTurn();
        mediator.connectPlayer(getCurrentPlayer());
        gameboardUI.update("CHANGED_PLAYER");

        //GAMELOG RECORDS LOG: When its a different players turn to play
        gameLog.recordLog(getCurrentPlayer(), "KU Alchemist", getCurrentPlayer().getName(),  String.format("Its Your Turn!"), 0);

    }

    
    /*
     UI objects get their controllers from GameBoardController.
     */
    public IngredientStorageController getIngredientStorageController(){
        return ingredientStorage;
    }
    
    public GameLogController getGameLog(){
        return gameLog;
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

    public TheoryController getTheoryController() {
        return theory;
    }

    public Mediator getMediator() {
        return mediator;
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
            player.getInventory().updateGold(- (player.getInventory().getGold() / 3)) ;
    
    
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
                
                if(player1.getInventory().getGold() > player2.getInventory().getGold()) {
                    return player1;
                }
                else if (player1.getInventory().getGold() == player2.getInventory().getGold()) {
                    return null ; //check it !!
                }
                else{
                 return player2;
               }
            
            }
            else{
                return player2; 
            }
            }

        
    /* 
     * The random assignment of aclhemies to ingredients is needed for some parts of the game, this is to get it.
     * */    
    
    public void startGame() {
        return;
    }

}