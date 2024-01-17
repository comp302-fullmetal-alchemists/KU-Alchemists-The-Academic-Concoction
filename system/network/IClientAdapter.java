package system.network;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.Theory;
import system.domain.controllers.Player;

import java.util.List;

public interface IClientAdapter {

    // Authentication on a local computer
    void startAuthentication();

    // clientAdapter talks to server to validate choices
    void validateUserChoices(String username);

    // clientAdapter sends authenticated player to server
    void registerPlayer(Player p);

    // sets the alchemyMap for this game, it will be obtained from server
    void setAlchemyMap(List<Integer> alchemyIndex);

    // local computer is initialized for playing
    void initialize();

    // local computer is given the authority to play
    void authorize();
    
    // local computer is taken the authority to play
    void deauthorize();

    // clientAdapter informs the server that player turn has ended
    void endPlayerTurn();

    // clientAdapter talks to server to get the relevant information on gameobjects
    void requestIngredient();

    void emptyPile();

    void takeIngredientIndex(int index);

    void reportPuplishTheroryToServer(Theory a);

    void reportDebunkTheoryToServer(Theory a);

    void reportEndorseTheoryToServer(Theory a);

}
