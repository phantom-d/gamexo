package gamexo.network;

import java.io.*;
import java.net.*;

public class Client {

	public void init() throws UnknownHostException, IOException {
		String sentence;
		String modifiedSentence;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		try (Socket clientSocket = new Socket("localhost", 100100)) {
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			sentence = inFromUser.readLine();
			outToServer.writeBytes(sentence + '\n');
			modifiedSentence = inFromServer.readLine();
			System.out.println("FROM SERVER: " + modifiedSentence);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
