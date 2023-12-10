package system.domain.interfaces;

import system.domain.controllers.Player;

public interface Mediator {
	
	/* 
	 * Mediator interface works with collector interface to maintain low coupling between game areas and current player.
	 * Mediators should hold one player instance that is updated whenever a player is changed, and one collector instance that
	 * awaits items from the player to handle them. When a collector like ingredient storage is opened in UI, it is connected to the
	 * mediator and mediator creates a communication between that and the current player.
	 * Through mediator, an area can send items to player, like the potions that are brewed and player can send
	 * items to collector to let it handle them. 
	 * Since mediator holds only the current player instance, game areas can behave as if there is only one player in the game
	 * and do not care about how many players in the game etc. 
	 */
    
    void connectPlayer(Player player);

    void connectCollector(Collector collector);

    void disconnectCollector();

    <T> void sendToPlayer(T item);

    <T> boolean sendToCollector(T item);

    void updatePlayerGold(int updateAmount);

    boolean playerGoldAtLeast(int threshold);

    void playerPlayedTurn();
    
    String getPlayerName();
}
