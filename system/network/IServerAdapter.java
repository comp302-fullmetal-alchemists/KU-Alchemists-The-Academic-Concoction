package system.network;

//this will be an interface for the server adapter
public interface IServerAdapter {
    
    // host sets the number of players that will join the game
    void setPlayerNumber(int playerNum);

    // after clients are accepted, authentication as players must be done
    void startAuthentication();

    // after authentication, each client (local computer) should initialize their gameboards
    void initializeGame();

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

    void requestIngredient();

    void stopServer();
}
