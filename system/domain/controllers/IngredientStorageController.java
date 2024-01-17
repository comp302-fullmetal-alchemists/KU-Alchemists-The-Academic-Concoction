package system.domain.controllers;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Collector;
import system.domain.interfaces.Mediator;
import system.domain.util.ArtifactFactory;
import system.domain.util.IngredientFactory;
import system.domain.Cards;
import system.domain.GameAction;

import java.util.List;

public class IngredientStorageController implements Collector{
    // public storage 
    //IngredientStorage	ingredient pile: List<ingredientCard>
    //artifact pile: List<artifactCard>	transmuteIngredient(ingredientCard)
    //buyArtifact()

    private Observer ingredientStorageUI;
    private Mediator mediator;
    private GameLogController gameLog;
    private Boolean active = false;
    private IngredientCard ingToSell;
    private ArtifactFactory artifactFactory;
    private List<ArtifactCard> artifactPile;

    public IngredientStorageController() {
        this.gameLog = GameBoardController.getInstance().getGameLog();
        this.mediator = GameBoardController.getInstance().getMediator();
        this.artifactFactory = new ArtifactFactory();
        this.artifactPile = artifactFactory.createArtifacts();
        
    }

    public void setObserver(Observer observer) {
        this.ingredientStorageUI = observer;
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
        try {
            if (ingToSell != null) {
                mediator.getPlayer().getInventory().updateGold(2);
                ingredientStorageUI.update(String.format("CARD_SOLD:%s", ingToSell.getName()));
                //GAME LOG RECORDS: When a ingredient card is sold to the bank. (Transmute ingredient)
                gameLog.recordLog(mediator.getPlayer(), mediator.getPlayer().getName(), "Bank",  String.format("Ingredient Sold %s", ingToSell.getName()), 0);
                ingToSell = null;
                mediator.getPlayer().playedTurn();
            }
            else {
                ingredientStorageUI.update("ABSENT_INGREDIENT");
            }
        }
        catch (NullPointerException e) {
            ingredientStorageUI.update("UNAUTHORIZED_ACTION");
        }
    	
    }

    public void buyArtifact() {
        // control if the player has enough gold
        try {
            if (mediator.getPlayer().getInventory().getGold() >= 3) {
                //draw an artifact card object from the pile and add it to the artifact card list of the corresponding players inventory
                ArtifactCard artifact = drawArtifact();       
                if (artifact == null) {
                    ingredientStorageUI.update("EMPTY_PILE");
                }
                else {
                    mediator.getPlayer().getInventory().updateGold(-3);
                    mediator.sendToPlayer(artifact);
                    ingredientStorageUI.update(String.format("ARTIFACT_BOUGHT:%s", artifact.getName()));
                    
                    //GAME LOG RECORDS: When a player buys an artifact card.
                    gameLog.recordLog(mediator.getPlayer(), "Artifact Pile", mediator.getPlayer().getName(), String.format("Bought %s", artifact.getName()), 0);
                    
                
                    mediator.getPlayer().playedTurn();
                    
                }
            }
            else {
                ingredientStorageUI.update("NOT_ENOUGH_GOLD");
            }
        }
        catch (NullPointerException e) {
            ingredientStorageUI.update("UNAUTHORIZED_ACTION");
        }

    }

    public void buyArtifact2(String cardName) {

        if (mediator.getPlayer().getInventory().getGold() >= 3 && !artifactPile.isEmpty()) {
            //draw an artifact card object from the pile and add it to the artifact card list of the corresponding players inventory
            if (cardName == null && ingredientStorageUI != null) {
                ingredientStorageUI.update("EMPTY_PILE");





            }
            else {
                ArtifactCard card = null;
                for (ArtifactCard ac: artifactPile) {
                    if (cardName.equals(ac.getName())) {
                         card = ac;
                    }
                }
                mediator.getPlayer().getInventory().updateGold(-3);
                mediator.sendToPlayer(card);
                if (ingredientStorageUI != null) {
                    ingredientStorageUI.update(String.format("ARTIFACT_BOUGHT:%s", card));
                }


                mediator.getPlayer().playedTurn();

            }
        }
        else {
            if (ingredientStorageUI != null) {
                ingredientStorageUI.update("NOT_ENOUGH_GOLD");
            }
        }

    }

    public void drawIngredient() {
        if (mediator.getPlayer() != null) {
            GameBoardController.getInstance().getClientAdapter().requestIngredient();
        }
        else {
            ingredientStorageUI.update("UNAUTHORIZED_ACTION");
        }
    }

    public void emptyPileError() {
        ingredientStorageUI.update("EMPTY_PILE");
    }

    public void takeIngredient(IngredientCard drawn) {
        mediator.sendToPlayer(drawn);
        ingredientStorageUI.update(String.format("CARDREMOVAL: %s", drawn.getName()));

        gameLog.recordLog(mediator.getPlayer(), "Ingredient Pile", mediator.getPlayer().getName(), String.format("Drawn %s", drawn.getName()), 0);
            
        mediator.getPlayer().playedTurn();
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
    /*public void useArtifact(ArtifactCard card) {  
        /
        if (card.getName().equals("Elixir of Insight")) {
            String cardNames = "";
            for (int i = 0; i < (ingredientPile.size() < 3? ingredientPile.size(): 3); i++) {
                cardNames += ingredientPile.get(i).getName() + ", ";

            }
            ingredientStorageUI.update(String.format("ELIXIR_OF_INSIGHT: %s", cardNames));
            
        }

        //GAME LOG RECORDS: When a player uses their artifact card
        gameLog.recordLog(GameBoardController.getInstance().getCurrentPlayer(), GameBoardController.getInstance().getCurrentPlayer().getName(), "themselves",  String.format("%s is Used", card.getName()), 0);
        
    }*/

    public Observer getIngredientStorageUI() {
        return this.ingredientStorageUI;
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