package game2019;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception, IOException {
		Socket clientSocket = new Socket("10.24.68.57", 9999);
		Player player = Main.me;
		WriteThread writeThread = new WriteThread(clientSocket, player);
		ReadThread readThread = new ReadThread(clientSocket);
		
		writeThread.start();
		readThread.start();
	}
}
