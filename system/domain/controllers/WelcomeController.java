package system.domain.controllers;

import system.network.OfflineServer;

public class WelcomeController {
    

    public WelcomeController() {
    }

    public void offlineHostingMode() {
        OfflineServer server = new OfflineServer();
        server.acceptClients();
    }


}
