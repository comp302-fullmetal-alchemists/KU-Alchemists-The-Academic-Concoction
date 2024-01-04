package system.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
    private static final int PORT = 8080;
    private static final String HOST = "127.0.0.1";
    Socket socket;

    public Client() throws UnknownHostException, IOException {
        Socket socket = new Socket(HOST, PORT);

        System.out.println("Connected to server on port " + PORT);
        
    }

    public void run (Socket socket) throws IOException {
        socket = new Socket(HOST, PORT);
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String input = fromUser.readLine();
            toServer.println(input);
            String response = fromServer.readLine();
            System.out.println(response);
        }
    }

    public Socket getSocket() {
        return socket;
    }
    
}
