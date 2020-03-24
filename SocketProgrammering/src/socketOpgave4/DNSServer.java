package socketOpgave4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DNSServer {

	public static void main(String args[]) throws Exception {
		HashMap navneListe = new HashMap<String, String>();
		navneListe.put("Simon", "10.24.12.206");
		navneListe.put("Oliver", "10.24.68.176");
		navneListe.put("Zia", "10.24.84.136");
		navneListe.put("Jeppe", "10.24.20.135");
		StringBuilder sb = new StringBuilder();

		DatagramSocket serverSocket = new DatagramSocket(8787);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		String IP = "1";

		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData()).trim();
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();

			if (navneListe.containsKey(sentence)) {
				for (int i = 0; i < navneListe.size(); i++) {
					if (sentence == "Liste") {
						sendData = navneListe.values().toString().getBytes();
					} else {
						IP = (String) navneListe.get(sentence);
						sendData = IP.getBytes();
					}
				}
			}
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}
}
