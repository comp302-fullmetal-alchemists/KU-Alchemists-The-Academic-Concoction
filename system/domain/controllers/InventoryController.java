package system.domain.controllers;

import java.util.ArrayList;
import java.util.List;
import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.Potion;
import system.domain.interfaces.Observer;
import system.domain.interfaces.Mediator;


public class InventoryController {
    // Player's inventory
    // Inventory	gold : int
    //ingredient cards: List<ingredientCard>
    //artifact cards : List<artifactCard>
    //potions: List<potion>	giveIngredient(ingredientCard)
    //showInventory(player)
    //updateGold(int)
    //updateInventory(card)
    // initialize an artifact card object and add it to the artifact card list of the corresponding players inventory
    // initialize an ingredient card object and add it to the ingredient card list of the corresponding players inventory
    // initialize a potion object and add it to the potion list of the corresponding players inventory
    // delete card from ingredient card list of the corresponding players inventory
    // delete card from artifact card list of the corresponding players inventory   
    // delete card from potion list of the corresponding players inventory
    

    private int gold;
    private List<IngredientCard> ingredientCards;
    public  List<ArtifactCard> artifactCards;
    private List<Potion> potions;
    private Observer inventoryUI;
    private Mediator mediator;
    private GameLogController gameLog;
    private IngredientCard lastIngredientCard;
    private int discountCard = -1;
    private boolean printingPress = false;
    private boolean wisdomIdol = false;
    private boolean magicMortar = false;


    public InventoryController() {
        this.gold = 10;
        this.ingredientCards = new ArrayList<IngredientCard>();
        this.artifactCards = new ArrayList<ArtifactCard>();
        this.potions = new ArrayList<Potion>();
        this.mediator = GameBoardController.getInstance().getMediator();
        this.gameLog = GameBoardController.getInstance().getGameLog();
        

    }

    public IngredientCard getLastIngredientCard() {
        return lastIngredientCard;
    }

    public void discountCardButton() {
        discountCard++;
    }

    public int getDiscountCard() {
        return discountCard;
    }

    public boolean getPrintingPress() {
        return printingPress;
    }
    

    public void setPrintingPress(boolean state) {
        this.printingPress = state;
    }
    
    public boolean getWisdomIdol() {
        return wisdomIdol;
    }
    
    
    public void setWisdomIdol(boolean state) {
        this.wisdomIdol = state;
    } 

    public void setMagicMortar(boolean state) {
        this.magicMortar = state;
    }

    public boolean getMagicMortar() {
        return magicMortar;
    }

    public void setObserver(Observer observer) {
        this.inventoryUI = observer;
    }

    public List<Potion> getPotions(){
        return potions;
    }
    
    public int getGold(){
        return gold;
    }
    
    public void setGold(int gold) {
		this.gold = gold;
	}

    public List<ArtifactCard> getArtifactCards(){
        return artifactCards;
    }

    public List<IngredientCard> getIngredientCards(){
        return ingredientCards;
    }
 

    public int updateGold(int amount) {
    	setGold(gold + amount) ;
        inventoryUI.update("GOLD_UPDATE");
    	return gold;
    }

    public void initializeIngredients(IngredientCard ingredient1, IngredientCard ingredient2) {
        ingredientCards.add(ingredient1);
        ingredientCards.add(ingredient2);
    }


    /*********/
    /*These should be the accessers of inventory for the mediator */
    public void addIngredient(IngredientCard ingredient) {
        ingredientCards.add(ingredient);
        inventoryUI.update(String.format("NEW_INGREDIENT:%s", ingredient.getName()));

        //GAMELOG LOGS SILENTLY TO SAVE GAME STATUS
        gameLog.recordLogSilent(GameBoardController.getInstance().getPlayer(), "KU Alchemist", GameBoardController.getInstance().getPlayer().getName(),  String.format("Added ingredient %s", ingredient.getName()), 0);
    }
    
    public void addArtifact(ArtifactCard artifact) {
        artifactCards.add(artifact);
        inventoryUI.update(String.format("NEW_ARTIFACT:%s", artifact.getName()));

        //GAMELOG LOGS SILENTLY TO SAVE GAME STATUS
        gameLog.recordLogSilent(GameBoardController.getInstance().getPlayer(), "KU Alchemist", GameBoardController.getInstance().getPlayer().getName(),  String.format("Added artifact card %s", artifact.getName()), 0);
    }

    public void addPotion(Potion potion) {
        potions.add(potion);
        inventoryUI.update(String.format("NEW_POTION:%s", potion.getStatus()));

        //GAMELOG LOGS SILENTLY TO SAVE GAME STATUS
        gameLog.recordLogSilent(GameBoardController.getInstance().getPlayer(), "KU Alchemist", GameBoardController.getInstance().getPlayer().getName(),  String.format("Added potion %s", potion.getStatus()), 0);
    }

    public void removeIngredient(IngredientCard ingredient) {
        lastIngredientCard = ingredient;
        System.out.printf("Last ing set to %s\n", lastIngredientCard.getCardName()); //TESTING DELETE LATER
        ingredientCards.remove(ingredient);
        inventoryUI.update(String.format("REMOVED_INGREDIENT:%s", ingredient.getName()));

        //GAMELOG LOGS SILENTLY TO SAVE GAME STATUS
       // gameLog.recordLogSilent(GameBoardController.getInstance().getCurrentPlayer(), "KU Alchemist", GameBoardController.getInstance().getCurrentPlayer().getName(),  String.format("Removed ingredient %s", ingredient.getName()), 0);
    }

    public void removePotion(Potion potion) {
    	potions.remove(potion);
        inventoryUI.update(String.format("REMOVED_POTION:%s", potion.getStatus()));

        //GAMELOG LOGS SILENTLY TO SAVE GAME STATUS
        //gameLog.recordLogSilent(GameBoardController.getInstance().getCurrentPlayer(), "KU Alchemist", GameBoardController.getInstance().getCurrentPlayer().getName(),  String.format("Removed potion %s", potion.getStatus()), 0);
    }

    public void sendIngredient(String ingredientName) {
        /// If player will choose ingredients from its dashboard, it cannot choose an ingredient that doesn't belong to it
        /// therefore it makes sense to change ingredientCards to Map<String, IngredientCard>
        for (IngredientCard ing: ingredientCards){
            if (ing.getName().equals(ingredientName)) {
                if (mediator.sendToCollector(ing)) {
                    removeIngredient(ing);
                }
                break;
            }
        }
    }

    public void sendPotion(String potionStatus) {
        /// If player will choose potions from its dashboard, it cannot choose a potion that doesn't belong to it
        /// therefore it makes sense to change potions to Map<String, IngredientCard>
        for (Potion pot: potions){
            if (pot.getStatus().equals(potionStatus)) {
                if (mediator.sendToCollector(pot)) {
                    removePotion(pot);
                }
                break;
            }
        }
    }

    public void removeArtifact(ArtifactCard artifactCard) {
        if (artifactCard.getUsage().equals("immediate")) {
            artifactCards.remove(artifactCard);
            inventoryUI.update(String.format("REMOVED_ARTIFACT_CARD:%s", artifactCard.getName()));

        }
        //artifactCards.remove(artifactCard);
       // inventoryUI.update(String.format("REMOVED_ARTIFACT_CARD:%s", artifactCard.getCardName()));

        //GAMELOG LOGS SILENTLY TO SAVE GAME STATUS
        // do we get the current player with getPlayer()
        gameLog.recordLogSilent(GameBoardController.getInstance().getPlayer(), "KU Alchemist", GameBoardController.getInstance().getPlayer().getName(),  String.format("Removed artifact %s", artifactCard.getName()), 0);
    }

    public void sendArtifactCard(String artifactName) {
        if (GameBoardController.getInstance().getPlayer().isInTurn()) {
            for (ArtifactCard artifact: artifactCards){
                if (artifact.getName().equals(artifactName)) {
                    artifact.performUseArtifact();
                    removeArtifact(artifact);
                    break;
                }
            }
        }

        //gameLog.recordLogSilent(GameBoardController.getInstance().getPlayer(), "KU Alchemist", GameBoardController.getInstance().getPlayer().getName(),  String.format("Used artifact %s", artifactName), 0);

    }


    /**********/

}
