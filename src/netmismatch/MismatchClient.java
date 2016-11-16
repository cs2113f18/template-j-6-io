package netmismatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MismatchClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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

			// read message from server:
			System.out.println("Trying to receive message from server");
			msg = in.readLine();
			System.out.println("Recvd:" + msg);

			// send my message
			System.out.println("Sending message to server");
			out.println(msg);
			System.out.println("Sent: " + msg);
			System.out.println("Message sent!");

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
