package knockknock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by timwood@seas.gwu.edu on 11/16/16.
 */
public class KnockServer {

    public KnockServer() {
        ServerSocket serverSocket = null;
        boolean listening = true;

        System.out.println("Waiting for connections...");

        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 6666.");
            System.exit(-1);
        }

        while (listening) {
            try {
                // wait for a connection
                Socket sock = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                System.out.println("Got connection from " + sock.getInetAddress());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new KnockServer();
    }
}
