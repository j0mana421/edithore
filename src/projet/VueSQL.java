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
	public static String listeFichiers(ResultSet rs) {
		String s = "<table>"
		  + "<tr><th>nom</th><th>description</th>"
		  + "<th>créateur</th><th>dernierUser</th>"
		  + "<th>type</th><th>date</th><th>actions</th></tr>";
		try {
			while(rs.next()) {
				s += "<tr>";
				s += "<td>"+rs.getString("nomAffichage")+"</td>";
				s += "<td>"+rs.getString("description")+"</td>";
				s += "<td>"+RequetesSQL.getNomUtilisateur(rs.getInt("idCreateur"))+"</td>";
				s += "<td>"+RequetesSQL.getNomUtilisateur(rs.getInt("idDernierUser"))+"</td>";
				s += "<td>"+rs.getString("type")+"</td>";
				s += "<td>"+rs.getString("date")+"</td>";
				s += "<td>"
					+ "<a class='picto' href=\"EditeurDeTexte.jsp?idFichier="+rs.getString("idFichier")+"\">"
					+ "✍</a>"
					+ "<form class='form_supprimer' action='SupprimeFichier' method='post'>"
						+ "<input type='hidden' name='idFichier' value='"+rs.getString("idFichier")+"'/>"
						+ "<input class='picto' type='submit' value='×'/>"
					+ "</form>"
					+ "</td>";
				s += "</tr>";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		s += "</table>";
		return(s);
	}
	
	public static String nomFichier(ResultSet rs) {
		String nom = null;
		try {
			rs.next();
			nom = rs.getString("nomStockage");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (nom);
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
			    // System.out.println(read);
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
	
	public static String listeContributeurs(ResultSet rs) {
		String s = "";
		String pseudo;
		
		try {
			while(rs.next()) {
				pseudo = rs.getString("pseudo");
				s += "<a href='Personne.jsp?pseudo="+pseudo+"'>"+pseudo+"</a> ";
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
