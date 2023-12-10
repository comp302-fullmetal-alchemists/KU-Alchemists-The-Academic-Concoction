package system.domain.util;

import system.domain.interfaces.Collector;
import system.domain.interfaces.Mediator;
import system.domain.IngredientCard;
import system.domain.ArtifactCard;
import system.domain.Potion;
import system.domain.controllers.Player;

public class ConcreteMediator implements Mediator {
	
	/* 
	 * This is the concrete version of mediator, that holds one player that is always the current player and always connected,
	 * and one collector that is connected to the other side of communication when opened.
	 */

    private Player player;
    private Collector collector;


    @Override
    public void connectPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void connectCollector(Collector collector) {
        this.collector = collector;
    }

    @Override
    public void disconnectCollector() {
        this.collector = null;
    }

    // Mediator instead of player decides actions to be done because, if it were the player
    // then player's method needed to have these dispatches as well because it is for sure that
    // player holds artifacts, ingredients and potions
    @Override
    public <T> void sendToPlayer(T item) {
        if (item instanceof IngredientCard) {
            player.getInventory().addIngredient((IngredientCard) item);
        }
        else if (item instanceof ArtifactCard) {
            player.getInventory().addArtifact((ArtifactCard) item);
        }
        else if (item instanceof Potion) {
            player.getInventory().addPotion((Potion) item);
        }
    }
    // Collector will decide its action based on item type, since they may not need all types of items
    // or they dont hold some types of items at all
    @Override 
    public <T> boolean sendToCollector(T item) {
        if (collector != null) {
            return collector.collectItem(item);
        }
        return false;
    }

    @Override
    public void updatePlayerGold(int updateAmount) {
        player.getInventory().updateGold(updateAmount);
    }

    @Override
    public boolean playerGoldAtLeast(int threshold) {
        return player.getInventory().getGold() >= threshold;
    }

    
    @Override
    public void playerPlayedTurn() {
        player.playedTurn();
    }
    
    @Override
    public String getPlayerName() {
    	return player.getName();
    }
}
