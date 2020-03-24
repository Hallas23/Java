package socketOpgaveZia3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SkriveThread extends Thread {

	String sentence;
	Socket clientSocket;

	public SkriveThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void run() {
		while (true) {
			try {
				BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				sentence = inFromUser.readLine();
				outToServer.writeBytes(sentence + '\n');
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
