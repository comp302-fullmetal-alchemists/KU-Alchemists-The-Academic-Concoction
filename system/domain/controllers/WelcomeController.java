package system.domain.controllers;

import system.network.IServerAdapter;
import system.network.OfflineClient;
import system.network.OfflineServer;

public class WelcomeController {
    

    public WelcomeController() {
    }

    public void offlineHostingMode() {
        IServerAdapter server = new OfflineServer();
        server.acceptClients();
    }


}
