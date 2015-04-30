package projet;

import java.io.*;
import java.net.*;

public class Client {

	public static Socket socket = null;
	public static Thread t1;
	
	public Client(String pseudoD,String msgP){
	try {
		
		System.out.println("Demande de connexion");
		String ip = Tracker.chercheIP(pseudoD);
		socket = new Socket(ip, Tracker.cherchePort(pseudoD));
		System.out.println("Connexion établie avec le serveur.");
		OutputStream out = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(out);
		
		writer.print(msgP);
		writer.flush();
		socket.close();
		

	} catch (UnknownHostException e) {
	  System.err.println("Impossible de se connecter à l'adresse "+socket.getLocalAddress());
	} catch (IOException e) {
	  System.err.println("Aucun serveur à l'écoute du port "+socket.getLocalPort());
	}
	
	}

}
