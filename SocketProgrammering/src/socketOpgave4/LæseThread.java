package socketOpgave4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class LæseThread extends Thread {

	String sentence;
	Socket clientSocket;
	
	public LæseThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		while (true) {
			try {
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				sentence = inFromServer.readLine();
				System.out.println("From modtager: " + sentence);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
