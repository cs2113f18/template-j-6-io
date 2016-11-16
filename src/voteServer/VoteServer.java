package voteServer;

// CS2113
// T. Wood

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



class VoteListener implements Runnable {
	private Socket sock;
	private VoteServerGUI gui;

	public VoteListener(Socket s, VoteServerGUI g) {
		this.sock = s;
		this.gui = g;
	}

	public void run() {
		try {
			PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			System.out.println("Got connection from " + sock.getInetAddress());
 
			// read in the  name and message
			String v = in.readLine();
			System.out.println("read vote:" + v);
			gui.vote(v);
			
			if(v.equalsIgnoreCase("Dr. T")) {
				out.println("You cast your ballot for Dr. T... do you think you'll get bonus points or something?");
			}
			out.println("You cast your ballot for " + v);

			// clean things up
			out.close();
			in.close();
			sock.close();
			System.out.println("Thread done.");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class VoteServer {

	public VoteServer() {
		VoteServerGUI gui = new VoteServerGUI();
		
		ServerSocket serverSocket = null;
		boolean listening = true;

		System.out.println("Waiting for connections...");

		try {
			serverSocket = new ServerSocket(5555);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 5555.");
			System.exit(-1);
		}

		while (listening) {
			try {
				// wait for a connection
				VoteListener job = new VoteListener(serverSocket.accept(), gui);
				// start a new thread to handle the connection
				Thread t = new Thread(job);
				t.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new VoteServer();

	}

}
