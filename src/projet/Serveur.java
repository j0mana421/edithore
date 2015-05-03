package projet;

import java.io.*;
import java.net.ServerSocket;


public class Serveur {
	public ConnexionSocket cs;
	private int port;
	
	ServerSocket serveur;
	public Serveur(String pseudo){
		try{
			serveur = new ServerSocket(0);
			port = serveur.getLocalPort();
			System.out.println("Création du serveur qui est à l'écoute du port "+port);
			System.out.println("En attente du client...");
			cs = new ConnexionSocket(serveur,pseudo);
			Thread t=new Thread(cs);
			t.start();
		} catch (IOException e) {
			System.err.println("Le port "+port+" est déjà utilisé !");
			e.printStackTrace();
		}
	}
	public int getPort() {
		return port;
	}

}	

