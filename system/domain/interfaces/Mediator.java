package system.domain.interfaces;

import system.domain.Player;

public interface Mediator {
    
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
