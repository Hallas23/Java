package socketOpgave4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) throws Exception {
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		String sentence = inFromUser.readLine();
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("10.24.20.135");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 28008);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);

		if (sentence.equals("Liste")) {
			System.out.println(receiveData);
		} else {
			String IP = new String(receivePacket.getData()).trim();
			System.out.println("Client " + IP);
			LæseThread readThread;
			SkriveThread writeThread;

			Socket socket2 = new Socket(IP, 42069);
			readThread = new LæseThread(socket2);
			writeThread = new SkriveThread(socket2);
			readThread.start();
			writeThread.start();
		}
	}
}
