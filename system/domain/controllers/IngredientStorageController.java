package system.domain.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Collector;
import system.domain.interfaces.Mediator;
import system.domain.Cards;
import system.domain.GameAction;

public class IngredientStorageController implements Collector{
    // public storage 
    //IngredientStorage	ingredient pile: List<ingredientCard>
    //artifact pile: List<artifactCard>	transmuteIngredient(ingredientCard)
    //buyArtifact()

    private List<IngredientCard> ingredientPile;
    private List<ArtifactCard> artifactPile;
    private Observer ingredientStorageUI;
    private Mediator mediator;
    private GameLogController gameLog;
    private GameAction gameAction;
    private Boolean active = false;
    private IngredientCard ingToSell;

    public IngredientStorageController() {
        this.gameLog = GameBoardController.getInstance().getGameLog();
        this.ingredientPile = new ArrayList<IngredientCard>();
        this.artifactPile = new ArrayList<ArtifactCard>();
        this.mediator = GameBoardController.getInstance().getMediator();
        
    }

    public void setObserver(Observer observer) {
        this.ingredientStorageUI = observer;
    }

    public void initializePiles() {
        for (int i = 0; i < 24; i++) {
            String ingredientName = GameBoardController.getInstance().getIngredients()[i%8];
            ingredientPile.add(new IngredientCard(ingredientName, GameBoardController.getInstance().getAlchemyMap().get(ingredientName)));
        }
        Collections.shuffle(ingredientPile);

        for (int i = 0; i < GameBoardController.getInstance().getArtifactStrings().length - 1; i++) {
            artifactPile.add(new ArtifactCard(GameBoardController.getInstance().getArtifactStrings()[i], GameBoardController.getInstance().getArtifactEffect()[i], GameBoardController.getInstance().getArtifactUsage()[i]));
        }

        //gameStart is logged in for both players.
        gameAction = new GameAction("KU Alchemist", GameBoardController.getInstance().getPlayer(0).getName(), "Game has started!", 0);
        gameLog.recordLog(GameBoardController.getInstance().getPlayer(0), gameAction);

        gameAction = new GameAction("KU Alchemist", GameBoardController.getInstance().getPlayer(1).getName(), "Game has started!", 0);
        gameLog.recordLog(GameBoardController.getInstance().getPlayer(1), gameAction);
    }
    
    //This function is for gameboard to use when sending initial cards to players
    public IngredientCard pullIngredientCard() {
    	return ingredientPile.remove(0);
    }
    
    public boolean hasIngToSell() {
    	return ingToSell != null;
    }
    
    public void discardIngToSell() {
    	mediator.sendToPlayer(ingToSell);
    	ingToSell = null;
    	ingredientStorageUI.update("DISCARD_INGREDIENT");
    }

    public void transmuteIngredient() {
    	if (ingToSell != null) {
    		mediator.updatePlayerGold(2);
            ingredientStorageUI.update(String.format("CARD_SOLD:%s", ingToSell.getName()));
            //GAME LOG RECORDS: When a ingredient card is sold to the bank. (Transmute ingredient)
            gameAction = new GameAction(GameBoardController.getInstance().getCurrentPlayer().getName(), "Bank",  String.format("Ingredient Sold %s", ingToSell.getName()), 0);
            gameLog.recordLog(GameBoardController.getInstance().getCurrentPlayer(), gameAction);
            ingToSell = null;
            mediator.playerPlayedTurn();
    	}
    	else {
    		ingredientStorageUI.update("ABSENT_INGREDIENT");
    	}
    	
    }

    public void buyArtifact() {
        // control if the player has enough gold
        if (mediator.playerGoldAtLeast(3)) {
            //draw an artifact card object from the pile and add it to the artifact card list of the corresponding players inventory
            ArtifactCard artifact = drawArtifact();       
            if (artifact == null) {
                ingredientStorageUI.update("EMPTY_PILE");
            }
            else {
                mediator.updatePlayerGold(-3);
                mediator.sendToPlayer(artifact);
                ingredientStorageUI.update(String.format("ARTIFACT_BOUGHT:%s", artifact.getName()));
                
                //GAME LOG RECORDS: When a player buys an artifact card.
                gameAction = new GameAction("Artifact Pile", GameBoardController.getInstance().getCurrentPlayer().getName(), String.format("Bought %s", artifact.getName()), 0);
                gameLog.recordLog(GameBoardController.getInstance().getCurrentPlayer(), gameAction);
                
                useArtifact(artifact);
                mediator.playerPlayedTurn();
                
            }
        }
        else {
            ingredientStorageUI.update("NOT_ENOUGH_GOLD");
        }

    }

    public void drawIngredient() {
        if (ingredientPile.isEmpty()) {
            ingredientStorageUI.update("EMPTY_PILE");
        }
        else {
            IngredientCard drawn = ingredientPile.remove(0);

            mediator.sendToPlayer(drawn);
            ingredientStorageUI.update(String.format("CARDREMOVAL: %s", drawn.getName()));

            //GAME LOG RECORDS: When a player draws a card.
            gameAction = new GameAction("Ingredient Pile", GameBoardController.getInstance().getCurrentPlayer().getName(), String.format("Drawn %s", drawn.getName()), 0);
            gameLog.recordLog(GameBoardController.getInstance().getCurrentPlayer(), gameAction);
            
            mediator.playerPlayedTurn();
        }
    }
    // draw an artifact card from the pile according to the rule of taking the last card from the pile
    public ArtifactCard drawArtifact() {
        if (artifactPile.isEmpty()) {
            return null;
        }
        ArtifactCard drawed = artifactPile.remove(artifactPile.size() - 1);
        return drawed;
    }
    // since the phase I. stated that the Elixir of Insight card must be implemented, this method is only
    // being used to show the top 3 cards of the ingredient pile, later on other artifact cards will be implemented 
    public void useArtifact(ArtifactCard card) {  
        if (card.getName().equals("Elixir of Insight")) {
            String cardNames = "";
            for (int i = 0; i < (ingredientPile.size() < 3? ingredientPile.size(): 3); i++) {
                cardNames += ingredientPile.get(i).getName() + ", ";

            }
            ingredientStorageUI.update(String.format("ELIXIR_OF_INSIGHT: %s", cardNames));
        }

        //GAME LOG RECORDS: When a player uses their artifact card
        gameAction = new GameAction(GameBoardController.getInstance().getCurrentPlayer().getName(), "themselves",  String.format("%s is Used", card.getName()), 0);
        gameLog.recordLog(GameBoardController.getInstance().getCurrentPlayer(), gameAction);
    }

    @Override
    public <T> boolean collectItem(T item) {
        if (item instanceof IngredientCard) {
            IngredientCard ing = (IngredientCard) item;
            if (ingToSell != null) {
            	discardIngToSell();
            }
            ingToSell = ing;
            ingredientStorageUI.update("NEW_INGREDIENT:" + ing.getName());
            return true;
        }
        return false;
    }

    @Override
    public void activate() {
        mediator.connectCollector(this);
        active = true;
    }

    @Override
    public void deactivate() {
        mediator.disconnectCollector();
        active = false;
    }

    @Override 
    public boolean isActive() {
        return active;
    }



    

}