package netsimple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {

  public static void main(String[] args) {

    String host = "127.0.0.1";
    int portnum = 5555;

    Socket s;
    try {
      // create socket, input and output streams
      s = new Socket(host, portnum);
      PrintWriter out = new PrintWriter(s.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

      // send a message
      String name = "Hello server";
      out.println(name);

      // Make sure messages are sent
      out.flush();

      // receive and print confirmation
      System.out.println(in.readLine());

      // clean up
      out.close();
      s.close();

    } catch (UnknownHostException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}