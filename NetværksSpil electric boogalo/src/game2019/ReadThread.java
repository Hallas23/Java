package game2019;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {

	private Socket clientSocket;
	public ReadThread(Socket clientSocket) {
		this.clientSocket = clientSocket;		
	}
	
	public void run(){
		while(true) {
			try {
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String sentence = inFromServer.readLine();
//				System.out.println(sentence);
				String[] playerInfo = sentence.split(" ");
				
				boolean newPlayer = true;
				for (Player p : Main.players) {
					if (p.getName().equals(playerInfo[0])) {
						newPlayer = false;
						p.setXpos(Integer.parseInt(playerInfo[1]));
						p.setYpos(Integer.parseInt(playerInfo[2]));
						p.setDirection(playerInfo[3]);
					}
				}
				if (newPlayer) {
					Main.players.add(new Player(playerInfo[0], Integer.parseInt(playerInfo[1]), Integer.parseInt(playerInfo[2]), playerInfo[3]));
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
