package projet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;

/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 * affichages divers de ResultSet SQL
 */
public class VueSQL {
	
	
	/**
	 * @param rs le ResultSet
	 * @return une table
	 */
	public static String listeFichiers(ArrayList<Fichier> fichiers) {
		String s = "";
		for (Fichier fichier : fichiers) {
			s += "<div class='fichier conteneur'>"
					+ "<div class='fichier titre'>"+fichier.nomAffichage+"</div>"
					+ "<div class='fichier boutons'>"
						+"<a class='picto' href=\"EditeurDeTexte.jsp?idFichier="+fichier.idFichier+"\">✍</a>"
						+ "<form class='form_supprimer' action='SupprimeFichier' method='post'>"
							+ "<input type='hidden' name='idFichier' value='"+fichier.idFichier+"'/>"
							+ "<input class='picto' type='submit' value='×'/>"
						+ "</form>"
					+ "</div>"
				+ "<hr>"
					+ "créé par "+fichier.nomCreateur
					+ ", le "+fichier.dateCreation
					+ "<br>"
					+ "modifié par "+fichier.nomModificateur
					+ ", le "+fichier.dateModification
				+ "<hr>"
					+ "<div class='fichier description'>"+fichier.description+"</div>"
			+ "</div>";
		}
		return(s);
	}
	
	public String contenuFichier(String nomStock) {
		String s = null;

		try {
			FTPClient client = new FTPClient();
			client.connect("ftp.byethost15.com");
			client.login("b7_16124912", "moumoutte42rpz");
			InputStream in = client.retrieveFileStream("htdocs/tomcat_files/"+nomStock);
			InputStreamReader is = new InputStreamReader(in);
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(is);
			String read = br.readLine();
			while(read != null) {
			    System.out.println("--"+read);
			    sb.append(read);
			    sb.append('\n');
			    read = br.readLine();
			}
			s = sb.toString();
			client.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return(s);
	}
	
	public static String listeContributeurs(ArrayList<String> liste) {
		String s = "";
		for (String pseudo : liste) {
			s += "<a href='Personne.jsp?pseudo="+pseudo+"'>"+pseudo+"</a> ";
		}
		return(s);
	}
	
	public static String messagesApplication(ArrayList<Message> listeMessages) {
		String s = "";
		for (Message message : listeMessages) {
			s += message.toString()+"<hr>";
		}
		return(s);
	}
}
