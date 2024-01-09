package system.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.sound.sampled.Port;


public class Client {
    private static final String HOST = "127.0.0.1";
    Socket socket;

    public Client(Integer Port) throws UnknownHostException, IOException {
        this.socket = new Socket(HOST, Port);
        System.out.println("Connected to server on port " + Port);
    }

    public void run () throws IOException {
        try {
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);

            String userInput;
            while ((userInput = fromUser.readLine()) != null) {
                toServer.println(userInput);
                System.out.println("Server says: " + fromServer.readLine());
            }
        } catch (IOException e) {
            System.out.println("Error in client communication");
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing the client socket");
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        try {
            Client client = new Client(8080);
            client.run();
        } catch (IOException e) {
            System.out.println("Cannot start client");
            e.printStackTrace();
        }
    }

    
}
