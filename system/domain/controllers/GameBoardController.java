package system.domain.controllers;

import system.domain.interfaces.Observer;
import java.util.HashMap;
import java.util.Iterator;

import system.domain.ArtifactCard;
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
    private HashMap<String, Integer> winnerList;

    private GameBoardController() {
        this.mediator = new ConcreteMediator();
        this.gameLog = new GameLogController(); //create the gamelog
        this.welcomeController = new WelcomeController();
        this.winnerList = new HashMap<>();
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
     * The random assignment of aclhemies to ingredients is needed for some parts of the game, this is to get it.
     * */    
    
    public void startGame() {
        return;
    }

    public void startNewGame() {
        gameboardUI.update("NEW_GAME");
    }

    public int calculateFinalScore(Player player) {
    
        //to do: get rep, gold, artifact from player's inventory
        int finalScore = 0;
        for (ArtifactCard ac: player.getInventory().getArtifactCards()) {
            if (ac.getName().equals("Wisdom Idol")) {
                player.updateReputation(1);
            }
        }
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

    public void showEndgameScreen(String message) {
        //splitle ve bilgiyi al
        // bunu local bir fielda ata
        this.winnerList = new HashMap<>();
        String[] info = message.split(":");
        for(int i = 1; i < info.length; i+=3){
            winnerList.put(info[i+1], Integer.parseInt(info[i+2]));
        }

        gameboardUI.update("END_GAME");
    }

    public HashMap<String, Integer> getWinnerList() {
        return winnerList;
    }

    public void showError(String string) {
        
        gameboardUI.update(string);
    }

}