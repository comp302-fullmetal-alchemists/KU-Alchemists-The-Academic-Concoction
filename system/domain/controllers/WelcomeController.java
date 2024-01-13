package system.domain.controllers;

import java.io.IOException;

import system.network.IClientAdapter;
import system.network.IServerAdapter;
import system.network.OfflineClient;
import system.network.OfflineServer;
import system.network.OnlineClient;
import system.network.OnlineServer;

public class WelcomeController {

    private IServerAdapter server;

    public WelcomeController() {

    }

    public void offlineHostingMode(int numberOfPlayers) {
        this.server = new OfflineServer();
        server.setPlayerNumber(numberOfPlayers);
        server.acceptClients();
    }

    public void onlineHostingMode(int Port) {
        try {
            this.server = new OnlineServer(Port);
            Thread serverThread = new Thread((Runnable) server);
            serverThread.start();
            IClientAdapter client = new OnlineClient(Port);
            GameBoardController.getInstance().setClientAdapter(client);
            Thread clientThread = new Thread((Runnable) client);
            clientThread.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void onlineJoiningMode(int Port) {
        try {
            IClientAdapter client = new OnlineClient(Port);
            GameBoardController.getInstance().setClientAdapter(client);
            Thread clientThread = new Thread((Runnable) client);
            clientThread.start();
        } catch (IOException e) { 
            e.printStackTrace();
        }
    }

    public void authentication() {
        try {
            server.setPlayerNumber(0);
            server.startAuthentication();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
