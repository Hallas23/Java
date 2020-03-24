package socketOpgave;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) throws Exception {
		
		ServerSocket welcomSocket = new ServerSocket(6969);
		String clientSentence;
		String sentence;
		String responseSentence;

		while (true) {
			Socket connectionSocket = welcomSocket.accept();
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();
			System.out.println("FROM Client: " + clientSentence);

			BufferedReader inFromUser = new BufferedReader(
					new InputStreamReader(System.in));
			sentence = inFromUser.readLine();
			responseSentence = sentence + '\n';
			outToClient.writeBytes(responseSentence);
		}

	}

}
