package system.network;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.controllers.GameBoardController;
import system.domain.controllers.Player;
import system.domain.util.ArtifactFactory;
import system.domain.util.IngredientFactory;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class OfflineServer implements IServerAdapter {

    private int rounds = 0;
    private int playerNum = 3;
    private int currentClient = 0;
    private List<OfflineClient> clients;
    private IngredientFactory ingFactory;
    private ArtifactFactory artifactFactory;
    private List<IngredientCard> ingPile;
    private List<ArtifactCard> artifactPile;

    public OfflineServer() {
        this.clients = new ArrayList<OfflineClient>();
        this.ingFactory = new IngredientFactory();
        this.artifactFactory = new ArtifactFactory();
        ingPile = ingFactory.createIngredients(24);
        artifactPile = artifactFactory.createArtifacts();
    }

    // host decides the number of clients
    public void setPlayerNumber(int playerNum) {
        this.playerNum = playerNum;
    }

    public void acceptClients() {
        /// in offline mode there is only one client (local computer)
        clients.add(new OfflineClient(this));
        GameBoardController.getInstance().setClientAdapter(clients.get(0));
        clients.get(0).setPlayerNum(playerNum);
        startAuthentication();
    }

    public void startAuthentication() {
        clients.get(0).startAuthentication();
    }

    public void initializeGame() {
        Collections.shuffle(clients);
        for (OfflineClient client: clients) {
            client.initialize();
        }
        authorizeClient();
    }

    public boolean ingPileIsEmpty() {
        return ingPile.isEmpty();
    }

    public IngredientCard drawIngredient() {
        return ingPile.remove(0);
    }

    public boolean artifactPileIsEmpty() {
        return artifactPile.isEmpty();
    }

    public ArtifactCard drawArtifact() {
        return artifactPile.remove(0);
    }


    public void changePlayer() {
        //first remove the old player
        deauthorizeClient();
        //then find new player, change round number if needed
        setNextClient();
        //finally, give permission to new player
        authorizeClient();
    }


    public void deauthorizeClient() {
        //remove players connection to mediator.
        clients.get(currentClient).deauthorize();    
    }

    public void authorizeClient() {
        clients.get(currentClient).authorize();
    }

    public void newRound() {
        currentClient = 0;
        rounds += 1;
        System.out.println(rounds);
        //if round is 3 call a function to finish game
    }
    public void setNextClient() {
        clients.get(currentClient).changePlayer();
        /* 
        *** this would work for online server's method
        currentClient += 1;
        if (currentClient == playerNum) {
            newRound();
        }
        */ 
    }

}
