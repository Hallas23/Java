package socketOpgave4;


import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) throws Exception {
		
		ServerSocket welcomSocket = new ServerSocket(6969);
	

			Socket connectionSocket = welcomSocket.accept();
			Thread skriveThread = new LæseThread(connectionSocket);
			Thread læseThread = new SkriveThread(connectionSocket);
			skriveThread.start();
			læseThread.start();

		

	}

}
