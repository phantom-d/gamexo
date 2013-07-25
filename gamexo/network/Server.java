package gamexo.network;

import java.io.*;
import java.net.*;

public class Server {

	public void init() throws IOException {
		String clientSentence;
		String capitalizedSentence;
		ServerSocket welcomeSocket = new ServerSocket(100100);

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			clientSentence = inFromClient.readLine();
			System.out.println("Received: " + clientSentence);
			capitalizedSentence = clientSentence.toUpperCase() + '\n';
			outToClient.writeBytes(capitalizedSentence);
		}
	}
}
