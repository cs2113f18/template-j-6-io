package voteServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

class VoteServerGUI extends JFrame {
	private JTextArea messageArea;
	private JLabel lastMessage;
	
	private HashMap<String, Integer> votes;
	private int cnt;
	

	public synchronized void refresh() {
		messageArea.setText("");
		for(String k: votes.keySet()) {
			messageArea.append(k + ": " + votes.get(k) + "\n");
		}
	}
	
	// synchronize to prevent setting name for one user and message for a second
	public synchronized void vote(String vote) {
		cnt++;
		lastMessage.setText("Received: " + cnt + " votes.");
		if(votes.get(vote) != null) {
			int val = (int) votes.get(vote);
			val++;
			votes.put(vote, val);
		}
		else {
			votes.put(vote, 1);
		}
		
		System.out.println(votes);
		
		refresh();
		
		
	}
	
	public VoteServerGUI() {
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cPane = this.getContentPane();
		
		votes = new HashMap<String, Integer>();

		messageArea = new JTextArea();
		lastMessage = new JLabel();
		cPane.add(lastMessage, BorderLayout.NORTH);
		cPane.add(messageArea);

		lastMessage.setFont(new Font("SansSerif", Font.BOLD, 55));
		lastMessage.setText("Nobody has connected.");
		lastMessage.setForeground(Color.RED);

		messageArea.append("Waiting for clients...\n");
		messageArea.setWrapStyleWord(true);
		messageArea.setEditable(false);
		messageArea.setLineWrap(true);
		messageArea.setFont(new Font("SansSerif", Font.BOLD, 32));
		
		votes.put("C", 0);
		votes.put("Java", 0);
		votes.put("Python", 0);
		votes.put("PHP", 0);
		votes.put("Assembly", 0);
		refresh();

		this.setVisible(true);
	}
}