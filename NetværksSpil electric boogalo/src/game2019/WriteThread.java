package game2019;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class WriteThread extends Thread {

	private Socket clientSocket;
	private Player player;
	
	public WriteThread(Socket clientSocket, Player player) {
		this.clientSocket = clientSocket;		
		this.player = player;
	}
	
	public void run(){
		while(true) {
			try {
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//				BufferedReader inFromUser = new BufferedReader(
//						new InputStreamReader(System.in));
//				String sentence = inFromUser.readLine();
				String sentence = player.getName() + " " + player.getXpos() + " " + player.getYpos() + " " + player.getDirection();
				outToServer.writeBytes(sentence + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
