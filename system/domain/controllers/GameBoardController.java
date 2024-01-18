package system.domain.controllers;

import system.domain.interfaces.Observer;
import system.domain.Theory;
import system.domain.interfaces.Mediator;
import system.domain.util.ConcreteMediator;
import system.network.IClientAdapter;

public class GameBoardController {

    private static GameBoardController instance;
    private WelcomeController welcomeController;
    private IngredientStorageController ingredientStorage;
    private PotionBrewingAreaController potionBrewingArea;
    private DeductionBoardController deductionBoard;
    private PublicationAreaController publicationArea;
    private GameLogController gameLog; 
    private TheoryController theory;
    private Mediator mediator;
    private Observer gameboardUI;
    private IClientAdapter clientAdapter;
    private Player player;

    private GameBoardController() {
        this.mediator = new ConcreteMediator();
        this.gameLog = new GameLogController(); //create the gamelog
        this.welcomeController = new WelcomeController();
    }

    public void setObserver(Observer observer) {
        this.gameboardUI = observer;
    }

    public void setClientAdapter(IClientAdapter clientAdapter) {
        this.clientAdapter = clientAdapter;
    }

    public void setPlayer(Player p) {
        this.player = p;
    }
    
    //GameboardController is a singleton class, this is the lazy initialization method
    public static GameBoardController getInstance() {
        if (instance == null) {
            instance = new GameBoardController();  
        }
        return instance;
    }

    public void startAuthentication() {
        gameboardUI.update("AUTHENTICATION");
    }   

    //authentication sends players to gameboard and gameboard readies the game areas
    public void initializeTheBoard() { 
        this.ingredientStorage = new IngredientStorageController();
        this.publicationArea = new PublicationAreaController();
        this.deductionBoard = new DeductionBoardController();
        this.potionBrewingArea = new PotionBrewingAreaController();
        this.theory = new TheoryController();
        gameboardUI.update("INITIALIZE_BOARD");
    }

    public void initializePlayer() {
        gameboardUI.update("INITIALIZE_PLAYER");
    }

    public void deauthorizePlayer() {
        gameLog.recordLog(player, "KU Alchemist", player.getName(),  String.format("Round over!"), 0);
        gameboardUI.update("DEAUTHORIZATION");
        mediator.disconnectPlayer();
    }

    public void authorizePlayer() {
        mediator.connectPlayer(player);
        gameboardUI.update("AUTHORIZATION"); 
        gameLog.recordLog(player, "KU Alchemist", player.getName(),  String.format("Its Your Turn!"), 0);
  
    }

    public void endPlayerTurn() {
        clientAdapter.endPlayerTurn();
    }


    public Player getPlayer() {
        return player;
    }
    
    /*
     UI objects get their controllers from GameBoardController.
     */

    public WelcomeController getWelcomeController() {
        return welcomeController;   
    }
    
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

    public IClientAdapter getClientAdapter() {
        return clientAdapter;
    }



    /////// This is to be changed accordingly.
/* 
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
*/
        
    /* 
     * The random assignment of aclhemies to ingredients is needed for some parts of the game, this is to get it.
     * */    
    
    public void startGame() {
        return;
    }

}