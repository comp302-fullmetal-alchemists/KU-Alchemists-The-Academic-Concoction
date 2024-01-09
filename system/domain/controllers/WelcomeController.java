package system.domain.controllers;

import system.network.IServerAdapter;
import system.network.OfflineClient;
import system.network.OfflineServer;

public class WelcomeController {
    

    public WelcomeController() {
    }

    public void offlineHostingMode(int numberOfPlayers) {
        IServerAdapter server = new OfflineServer();
        server.setPlayerNumber(numberOfPlayers);
        server.acceptClients();
    }


}
