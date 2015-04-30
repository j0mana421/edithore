package projet;

import java.io.*;
import java.net.ServerSocket;


public class Serveur {
	public ConnexionSocket cs;
	
	ServerSocket serveur;
	public Serveur(int PORT , String pseudo){
		try{
			serveur = new ServerSocket(PORT, 1);
			System.out.println("Création du serveur qui est à l'écoute du port "+serveur.getLocalPort());
			System.out.println("En attente du client...");
			cs = new ConnexionSocket(serveur,pseudo);
			Thread t=new Thread(cs);
			t.start();
		} catch (IOException e) {
			System.err.println("Le port "+PORT+" est déjà utilisé !");
			e.printStackTrace();
		}
	}

}	

