package system.network;

import system.domain.ArtifactCard;
import system.domain.IngredientCard;
import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;
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
    private Observer observer;

    public OfflineServer() {
        this.clients = new ArrayList<OfflineClient>();
        this.ingFactory = new IngredientFactory();
        this.artifactFactory = new ArtifactFactory();
        ingPile = ingFactory.createIngredients(24);
        artifactPile = artifactFactory.createArtifacts();
    }


    // host decides the number of clients
    @Override
    public void setPlayerNumber(int playerNum) {
        this.playerNum = playerNum;
    }

    @Override
    public void acceptClients() {
        /// in offline mode there is only one client (local computer)
        clients.add(new OfflineClient(this));
        GameBoardController.getInstance().setClientAdapter(clients.get(0));
        clients.get(0).setPlayerNum(playerNum);
        startAuthentication();
    }

    @Override
    public void startAuthentication() {
        clients.get(0).startAuthentication();
    }

    @Override
    public void initializeGame() {
        Collections.shuffle(clients);
        for (OfflineClient client: clients) {
            client.initialize();
        }
        authorizeClient();
    }

    @Override
    public boolean ingPileIsEmpty() {
        return ingPile.isEmpty();
    }

    @Override
    public IngredientCard drawIngredient() {
        return ingPile.remove(0);
    }

    @Override
    public boolean artifactPileIsEmpty() {
        return artifactPile.isEmpty();
    }

    @Override
    public ArtifactCard drawArtifact() {
        return artifactPile.remove(0);
    }

    @Override
    public void changePlayer() {
        //first remove the old player
        deauthorizeClient();
        //then find new player, change round number if needed
        setNextClient();
        //finally, give permission to new player
        authorizeClient();
    }

    @Override
    public void deauthorizeClient() {
        //remove players connection to mediator.
        clients.get(currentClient).deauthorize();    
    }

    
    @Override
    public void authorizeClient() {
        clients.get(currentClient).authorize();
    }

    @Override
    public void newRound() {
        if(rounds <= 3){
            currentClient = 0;
            rounds += 1;
        }
        else{
            //offline clientstan winner() çağır
           for(OfflineClient c: clients){
                c.winner();
                observer.update("END_GAME");
                
           }
            //display Game over screen 
            //announce game over call calculate Final score func.
        }
        
        //if round is 3 call a function to finish game
        
    }

    @Override
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
