package game2019;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket clientSocket;
	public ServerThread(Socket clientSocket) {
		this.clientSocket = clientSocket;		
	}
	
	public void run(){
		while(true) {
			try {
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String sentence = inFromClient.readLine();
				
				for (Socket s : Server.clients) {
					DataOutputStream outToClient = new DataOutputStream(s.getOutputStream());
					outToClient.writeBytes(sentence + "\n");
					System.out.println(sentence);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
