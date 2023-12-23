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
    private List<Player> players;
    private IngredientStorageController ingredientStorage;
    private PotionBrewingAreaController potionBrewingArea;
    private DeductionBoardController deductionBoard;
    private PublicationAreaController publicationArea;
    private GameLogController gameLog; 
    private GameAction gameAction; //to be used for start game gameAction
    private TheoryController theory;
    private Mediator mediator;
    private Observer gameboardUI;
    private String[] artifacts = {"Philosopher's Compass", "Elixir of Insight", "Discount Card", "Amulet of Rhetoric"};
    private String[] effects = {"Once per round, the player can swap the position of two alchemy markers on the Deduction Board.","Allows a player to view the top three cards of the ingredient deck and rearrange them in any order.", "Your next artifact costs 2 gold less. After that, artifacts cost you 1 gold less.", "Gain 5 points of reputation." };
    private String[] usages = {null,null,"Immediate effect." };

    private GameBoardController() {
        this.players = new ArrayList<Player>();
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
    public void initializeTheBoard(List<Player> players) {
        this.players = players;
        gameLog.GameLogControllerInit(players.get(0), players.get(1)); //initalize the gamelog with the players

        Random random = new Random();
        int firstPlayer = random.nextInt(this.players.size());
        players.get(firstPlayer).changeTurn();
        mediator.connectPlayer(players.get(firstPlayer));
        this.ingredientStorage = new IngredientStorageController();
        this.publicationArea = new PublicationAreaController();
        this.deductionBoard = new DeductionBoardController();
        this.potionBrewingArea = new PotionBrewingAreaController();
        this.theory = new TheoryController();
        gameboardUI.update("INITIALIZE_BOARD");
        this.ingredientStorage.initializePiles();
        for (Player p: players) {
        	p.getInventory().addIngredient(ingredientStorage.pullIngredientCard());
        	p.getInventory().addIngredient(ingredientStorage.pullIngredientCard());
        }
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }
        
    public String[] getArtifactStrings() {
        return artifacts;
    }

    public String[] getArtifactEffect() {
        return effects;
    }

    public String[] getArtifactUsage() {
        return usages;
    }

    /*	when a player is going to be changed, first the game areas are cleared and items remaining in them
    	are sent back to the previous player because the previous player may have objects left in those areas, 
    	like an ingredient that is going to be sold.This was the "CHANGING_PLAYER" update.
    	 Then it changes the view to the next player, this is the "CHANGED_PLAYER" update.
     
     */
    public void changePlayer() {
        //GAMELOG RECORDS LOG: When the round is over for a player
        gameAction = new GameAction("KU Alchemist", getCurrentPlayer().getName(),  String.format("Round over!"), 0);
        gameLog.recordLog(getCurrentPlayer(), gameAction);

        gameboardUI.update("CHANGING_PLAYER");
        
        players.get(0).changeTurn();
        players.get(1).changeTurn();
        mediator.connectPlayer(getCurrentPlayer());
        gameboardUI.update("CHANGED_PLAYER");

        //GAMELOG RECORDS LOG: When its a different players turn to play
        gameAction = new GameAction("KU Alchemist", getCurrentPlayer().getName(),  String.format("Its Your Turn!"), 0);
        gameLog.recordLog(getCurrentPlayer(), gameAction);

    }
    
    public Player getCurrentPlayer() {
        if (players.get(0).isInTurn()) {
            return players.get(0);
        } else {
            return players.get(1);
        }
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