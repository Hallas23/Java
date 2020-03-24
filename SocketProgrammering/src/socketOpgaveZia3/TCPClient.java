package socketOpgaveZia3;

import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) throws Exception  {

	
	

			Socket clientSocket = new Socket("10.24.77.11", 6969);
			Thread skriveThread = new LæseThread(clientSocket);
			Thread læseThread = new SkriveThread(clientSocket);
			skriveThread.start();
			læseThread.start();
		
	}

}
