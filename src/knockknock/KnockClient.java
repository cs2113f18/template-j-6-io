package knockknock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by timwood@seas.gwu.edu on 11/16/16.
 */
public class KnockClient {

    public KnockClient() {
        // FILL THIS IN FROM OUR NEIGHBOR -- run FindMyIP.java to find out
        String host = "127.0.0.1";

        int portnum = 6666;

        String msg = "This is the client's message.";
        Socket s;
        try {

            // create socket and output stream
            s = new Socket(host, portnum);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    s.getInputStream()));


            // clean up
            out.close();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new KnockClient();
    }

}
