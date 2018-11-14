package netsimple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
  public static void main(String[] args) {

    String host = "127.0.0.1";
    int portnum = 5555;

    ServerSocket server = null;
    try {
      server = new ServerSocket(portnum);
    } catch (IOException e) {
      e.printStackTrace();
    }

    while (true) {
      try {
        Socket sock = server.accept(); // wait for a call
        BufferedReader in = new BufferedReader(new InputStreamReader
            (sock.getInputStream()));
        PrintWriter out = new PrintWriter(sock.getOutputStream(), true);

        String input = in.readLine(); // read a message
        out.println("Hello Client! You sent me: " + input);
        out.close();
        in.close();
        sock.close(); // hang up
        System.out.println("Client sent: " + input);

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
