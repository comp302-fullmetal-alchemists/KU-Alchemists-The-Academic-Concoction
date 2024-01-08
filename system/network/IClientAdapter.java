package system.network;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.controllers.Player;

public interface IClientAdapter {
    
    // client connecting to server, only required for OnlineClient
    void connectToServer();

    // Authentication on a local computer
    void startAuthentication();

    // clientAdapter talks to server to validate choices
    boolean validateUserChoices(String username);

    // clientAdapter sends authenticated player to server
    void registerPlayer(Player p);

    // local computer is initialized for playing
    void initialize();

    // local computer is given the authority to play
    void authorize();
    
    // local computer is taken the authority to play
    void deauthorize();

    // clientAdapter informs the server that player turn has ended
    void endPlayerTurn();

    // clientAdapter talks to server to get the relevant information on gameobjects
    boolean ingPileIsEmpty();

    IngredientCard drawIngredient();

    boolean artifactPileIsEmpty();
    
    ArtifactCard drawArtifact();
}
