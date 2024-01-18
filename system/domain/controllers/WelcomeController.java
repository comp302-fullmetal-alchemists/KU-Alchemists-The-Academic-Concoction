package system.domain.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import system.domain.interfaces.Observer;
import system.network.IClientAdapter;
import system.network.IServerAdapter;
import system.network.OfflineClient;
import system.network.OfflineServer;
import system.network.OnlineClient;
import system.network.OnlineServer;

public class WelcomeController {

    private IServerAdapter server;

    public WelcomeController() {
        System.out.println(System.getProperty("os.name"));

    }

    public void offlineHostingMode(int numberOfPlayers) {
        this.server = new OfflineServer();
        server.setPlayerNumber(numberOfPlayers);
    }

    public void onlineHostingMode(int Port) {
        try {
            this.server = new OnlineServer(Port);
            Thread serverThread = new Thread((Runnable) server);
            serverThread.start();
            IClientAdapter client = new OnlineClient(learnIP(),Port);
            GameBoardController.getInstance().setClientAdapter(client);
            Thread clientThread = new Thread((Runnable) client);
            clientThread.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void onlineJoiningMode(String ip, int Port) {
        try {
            IClientAdapter client = new OnlineClient(ip,Port);
            GameBoardController.getInstance().setClientAdapter(client);
            Thread clientThread = new Thread((Runnable) client);
            clientThread.start();
        }
        catch (Exception e){
            e.printStackTrace();
            throw e;
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

    public String learnIP() {
        if (System.getProperty("os.name").contains("Mac")) {
            try {
                Process process = Runtime.getRuntime().exec("ipconfig getifaddr en0");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                StringBuilder ipAddress = new StringBuilder();
                
                while ((line = reader.readLine()) != null) {
                    ipAddress.append(line);
                }
                
                // Close the reader
                reader.close();
                
                // Print the IP address
                return ipAddress.toString();
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
        else if (System.getProperty("os.name").contains("Windows")) {
            try {
                Process process = Runtime.getRuntime().exec("ipconfig");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                
                String line;
                StringBuilder ipAddress = new StringBuilder();
                
                while ((line = reader.readLine()) != null) {
                    if (line.contains("IPv4 Address")) {
                        ipAddress.append(line.substring(line.indexOf(":") + 2));
                    }
                }
                
                // Close the reader
                reader.close();
                
                // Print the IP address
                return ipAddress.toString();
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
        else if (System.getProperty("os.name").contains("Linux")) {
            try {
                Process process = Runtime.getRuntime().exec("hostname -I");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                
                String line;
                StringBuilder ipAddress = new StringBuilder();
                
                while ((line = reader.readLine()) != null) {
                    ipAddress.append(line);
                }
                
                // Close the reader
                reader.close();
                
                // Print the IP address
                return ipAddress.toString();
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
        return null;
    }

    public IServerAdapter getServer() {
        return server;
    }

    

}
