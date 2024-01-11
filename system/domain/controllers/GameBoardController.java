package system.domain.controllers;

import java.util.Iterator;

import system.domain.ArtifactCard;
import system.domain.GameAction;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Mediator;
import system.domain.util.ConcreteMediator;
import system.network.IClientAdapter;


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
    private IClientAdapter clientAdapter;
    private Player player;

    private GameBoardController() {
        this.mediator = new ConcreteMediator();
        this.gameLog = new GameLogController(); //create the gamelog
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
     * The random assignment of aclhemies to ingredients is needed for some parts of the game, this is to get it.
     * */    
    
    public void startGame() {
        return;
    }

}