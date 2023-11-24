package system.ui.interfaces;

public interface PlayerMediator {
    
    void connectPlayer();

    void connectActionSpace();

    void sendIngredientsToPlayer(String ingredients);

    void sendArtifactsToPlayer(String artifacts);
    
    void sendPotionToPlayer(String potion);

    void sendIngredientsToActionSpace(String ingredients);

    void sendPotionToActionSpace(String potion);
}
