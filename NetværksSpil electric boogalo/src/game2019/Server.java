package game2019;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {

	public static List<Socket> clients = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		
		ServerSocket welcomeSocket = new ServerSocket(9999);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept(); // TCP Socket
			clients.add(connectionSocket);
			(new ServerThread(connectionSocket)).start();
		}
//		WriteThread writeThread = new WriteThread(connectionSocket, player);
//		ReadThread readThread = new ReadThread(connectionSocket);
//		
//		writeThread.start();
//		readThread.start();
	}

}
