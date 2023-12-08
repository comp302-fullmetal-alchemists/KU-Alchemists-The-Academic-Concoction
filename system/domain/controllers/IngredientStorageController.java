package system.domain.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Collector;
import system.domain.interfaces.Mediator;

public class IngredientStorageController implements Collector{
    // public storage 
    //IngredientStorage	ingredient pile: List<ingredientCard>
    //artifact pile: List<artifactCard>	transmuteIngredient(ingredientCard)
    //buyArtifact()

    private List<IngredientCard> ingredientPile;
    private List<ArtifactCard> artifactPile;
    private Observer ingredientStorageUI;
    private Mediator mediator;
    private Boolean active = false;

    public IngredientStorageController() {
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
            ingredientPile.add(new IngredientCard(ingredientName,GameBoardController.getInstance().getAlchemyMap().get(ingredientName)));
        }
        Collections.shuffle(ingredientPile);

        for (int i = 0; i < GameBoardController.getInstance().getArtifactStrings().length - 1; i++) {
            artifactPile.add(new ArtifactCard(GameBoardController.getInstance().getArtifactStrings()[i], GameBoardController.getInstance().getArtifactEffect()[i], GameBoardController.getInstance().getArtifactUsage()[i]));
        }
        
        for (int i = 0; i < 2; i++) {
            GameBoardController.getInstance().getPlayer(0).getInventory().addIngredient(ingredientPile.remove(0));
        }
        for (int i = 0; i < 2; i++) {
            GameBoardController.getInstance().getPlayer(1).getInventory().addIngredient(ingredientPile.remove(0));
        }
    }


    public void buyArtifact() {
        // control if the player has enough gold
        /*if (GameBoardController.getInstance().getCurrentPlayer().getInventory().getGold() < 3) {
            return null;
           
        }*/
        //draw an artifact card object from the pile and add it to the artifact card list of the corresponding players inventory
        if (mediator.playerGoldAtLeast(3)) {
            ArtifactCard artifact = drawArtifact();
            if (artifact == null) {
                ingredientStorageUI.update("EMPTY_PILE");
            }
            else {
                mediator.sendToPlayer(artifact);
                mediator.updatePlayerGold(-3);
                ingredientStorageUI.update(String.format("ARTIFACT_BOUGHT:%s", artifact.getName()));
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
            ingredientStorageUI.update(String.format("CARDREMOVAL:%s", drawn.getName()));
            mediator.playerPlayedTurn();
        }
    }

    public ArtifactCard drawArtifact() {
        if (artifactPile.isEmpty()) {
            return null;
        }
        ArtifactCard drawed = artifactPile.remove(artifactPile.size() - 1);
        return drawed;
    }

    public void useArtifact(ArtifactCard card) {  
        if (card.getName().equals("Elixir of Insight")) {
            String cardNames = "";
            for (int i = 0; i < (ingredientPile.size() < 3? ingredientPile.size(): 3); i++) {
                cardNames += ingredientPile.get(i).getName() + ", ";

            }
            ingredientStorageUI.update(String.format("ELIXIR_OF_INSIGHT: %s", cardNames));
        }
    }

    @Override
    public <T> boolean collectItem(T item) {
        if (item instanceof IngredientCard) {
            IngredientCard ing = (IngredientCard) item;
            GameBoardController.getInstance().getCurrentPlayer().getInventory().updateGold(2);
            ingredientStorageUI.update(String.format("CARD_SOLD:%s", ing.getName()));
            mediator.playerPlayedTurn();
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