package system.network;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;

//this will be an interface for the server adapter
public interface IServerAdapter {
    
    // host sets the number of players that will join the game
    void setPlayerNumber(int playerNum);

    // after number of players is set, server starts accepting clients (local computers)
    void acceptClients();

    // after clients are accepted, authentication as players must be done
    void startAuthentication();

    // after authentication, each client (local computer) should initialize their gameboards
    void initializeGame();

    // ingredient pile is open to all clients, server holds it
    boolean ingPileIsEmpty();

    IngredientCard drawIngredient();

    // artifact pile is open to all clients, server holds it
    boolean artifactPileIsEmpty();
    
    ArtifactCard drawArtifact();

    // players are changed with the following steps
    void changePlayer();

    // 1) current client is deauthorized
    void deauthorizeClient();

    // 2) next client is figured out
    void setNextClient();

    // 3) the client that is to be the next is given authorization to play
    void authorizeClient();

    // add 1 more to the rounds
    void newRound();

}
