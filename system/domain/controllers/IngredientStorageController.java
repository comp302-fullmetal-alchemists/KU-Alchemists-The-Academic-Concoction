package system.domain.controllers;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Collector;
import system.domain.interfaces.Mediator;
import system.domain.util.ArtifactFactory;


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
                mediator.getPlayer().getInventory().setMagicMortar(true);
                mediator.getPlayer().getInventory().updateGold(1);
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
 

    public void buyArtifact(String cardName) {

        if (mediator.getPlayer().getInventory().getGold() >= 1 && !artifactPile.isEmpty()) {
            if (cardName == null && ingredientStorageUI != null) {
                ingredientStorageUI.update("EMPTY_PILE");

            }
            else if ( (mediator.getPlayer().getInventory().getGold() < 3 && mediator.getPlayer().getInventory().getDiscountCard() == -1) || 
            (mediator.getPlayer().getInventory().getGold() == 1 && mediator.getPlayer().getInventory().getDiscountCard() != 0)) {
                ingredientStorageUI.update("NOT_ENOUGH_GOLD");
            }
            
           // if gold = 1 discount card 0 must buy  
            // if gold = 2 discount card > 1 must buy  
             
            else {
                ArtifactCard card = null;
                for (ArtifactCard ac: artifactPile) {
                    if (cardName.equals(ac.getName())) {
                         card = ac;
                    }
                }
               
                decreaseGold();
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


    public void decreaseGold() {
        if (mediator.getPlayer().getInventory().getDiscountCard() == 0) {
            mediator.getPlayer().getInventory().updateGold(-1);
            mediator.getPlayer().getInventory().discountCardButton();

        }


        else if (mediator.getPlayer().getInventory().getDiscountCard() > 0) {
            mediator.getPlayer().getInventory().updateGold(-2);
            mediator.getPlayer().getInventory().discountCardButton();
        }

        else {
            mediator.getPlayer().getInventory().updateGold(-3);
        }
    
    }
 

    public void drawIngredient() {
        try {
            if (mediator.getPlayer() != null) {
                GameBoardController.getInstance().getClientAdapter().requestIngredient();
            }
        }
        catch (NullPointerException e) {
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

    

    public void elixirOfInsight(List<Integer> topIngredients) {
        String msg = "ELIXIR_OF_INSIGHT:";
        for(int ingInt: topIngredients){
            msg += IngredientCard.getIngredientName(ingInt);
            msg += ", ";
        }
        ingredientStorageUI.update(msg);
    }

    public void rewriteIng(List<String> elixirIngredients) {
        String serverMsg = "";
        for(String e: elixirIngredients){
            serverMsg += IngredientCard.getIngredientIndex(e);
            }
        GameBoardController.getInstance().getClientAdapter().rewriteIng(serverMsg);
    }

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