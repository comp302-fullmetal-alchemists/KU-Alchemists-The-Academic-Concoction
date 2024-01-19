package system.network;

import system.domain.controllers.GameBoardController;
import system.domain.interfaces.Observer;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class OfflineServer implements IServerAdapter {

    private int rounds = 0;
    private int playerNum = 3;
    private int currentClient = 0;
    private List<OfflineClient> clients;
    private List<Integer> ingredients;
    private Observer observer;

    public OfflineServer() {
        this.clients = new ArrayList<OfflineClient>();
        ingredients = new ArrayList<Integer>();
        for (int i = 0; i < 24; i++) {
            ingredients.add(i % 8);
        }
        Collections.shuffle(ingredients);
    }


    // host decides the number of clients
    @Override
    public void setPlayerNumber(int playerNum) {
        this.playerNum = playerNum;
        acceptClients();
    }

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
        List<Integer> alchemyIndex = assignRandomAlchemy();
        Collections.shuffle(clients);
        for (OfflineClient client: clients) {
            client.setAlchemyMap(alchemyIndex);
            client.initialize();
        }
        authorizeClient();
    }

    public List<Integer> assignRandomAlchemy() {
        ArrayList<Integer> alchemyIndex = new ArrayList<Integer>();
        for (int i = 0; i < 8; i++) {
            alchemyIndex.add(i);
        }
        Collections.shuffle(alchemyIndex);
        return alchemyIndex;
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
        if (rounds < 3) clients.get(currentClient).authorize();
    }

    @Override
    public void newRound() {
        currentClient = 0;
        rounds += 1;
        
        if (rounds == 3) {
            clients.get(0).endGame();

        }
    }

    @Override
    public void setNextClient() {
        clients.get(currentClient).changePlayer();
    }

    @Override 
    public void requestIngredient() {
        if (ingredients.isEmpty()) clients.get(currentClient).emptyPile();
        else clients.get(currentClient).takeIngredientIndex(ingredients.remove(0));
    }


    @Override
    public void stopServer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopServer'");
    }

    @Override
    public Integer getClientSize() {
        return clients.size();
    }



}
