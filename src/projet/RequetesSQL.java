package projet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 * 
 */
public class RequetesSQL {  
	private static Connect c;

	/**
	 * @param pseudo
	 * @param mail
	 * @param mdp
	 * @return la requête
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static String creerUtilisateur(final String pseudo, final String mail, final String mdp) throws SQLException, ClassNotFoundException{
		c=new Connect();
		String chaine;
		ResultSet rs =c.reqSQL("SELECT MAX(id) FROM Utilisateurs",'s');
		rs.next();
		int num = Integer.parseInt((rs.getString(1)==null?"0":rs.getString(1)))+1;
		chaine= "INSERT INTO Utilisateurs VALUES ('"+num+"', '"+pseudo+"','"+mail+"','"+mdp+"')";
		c.reqSQL(chaine, 'm');
		c.close();
		return chaine;
	}
	
	/**
	 * @param psd
	 * @param motdp
	 * @return true si le SELECT a fonctionné, false sinon
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static boolean verifUtilisateur(String psd, String motdp) throws SQLException, ClassNotFoundException{
		c=new Connect();
		ResultSet rs = c.reqSQL("SELECT id FROM Utilisateurs WHERE pseudo='"+psd+"' AND mdp='"+motdp+"'", 's');
		if(rs.first()){
			c.close();
			return true;
		}
		c.close();
		return false;
	}
	
	public static boolean verifUtilisateur(String psd) throws SQLException, ClassNotFoundException{
		c=new Connect();
		ResultSet rs = c.reqSQL("SELECT id FROM Utilisateurs WHERE pseudo='"+psd+"'", 's');
		if(rs.first()){
			c.close();
			return true;
		}
		c.close();
		return false;
	}
	
//	public static ResultSet searchU(String pseudo) throws ClassNotFoundException, SQLException{
//		c = new Connect();
//		ResultSet rs = c.reqSQL("SELECT id_u FROM utilisateurs WHERE pseudo='"+pseudo+"'", 's');
//		c.close();
//		return rs;
//	}

	private static int getIdUser(String pseudo) {
		int id = 1;
		try {
			c=new Connect();
			String req = "SELECT id FROM Utilisateurs WHERE pseudo='"+pseudo+"'";
			System.out.println("PSEUDO : "+pseudo);
			ResultSet rs = c.reqSQL(req, 's');
			rs.next();
			id = rs.getInt("id");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		c.close();
		return(id);
	}
	
	/**
	 * @param pseudo
	 * @param nomAff
	 * @param nomStock 
	 * @param description
	 * @param fichier
	 * @param type
	 * @param date
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void addFichier(String pseudo, String nomAff, String nomStock, String description, String type, java.sql.Date dateCreation) throws SQLException, IOException{
		try {
			c=new Connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// insertion dans Fichiers
		ResultSet rs =c.reqSQL("SELECT MAX(id) FROM Fichiers",'s');
		rs.next();
		int idFic = Integer.parseInt((rs.getString(1)==null?"0":rs.getString(1)))+1;
		rs = c.reqSQL("SELECT id FROM Utilisateurs WHERE pseudo=\""+pseudo+"\"",'s');
		rs.next();
		int idCreat = Integer.parseInt(rs.getString("id"));
		int idDernierUser = idCreat;
		String req= "INSERT INTO Fichiers VALUES ('"+idFic+"', '"+idCreat+"','"+idDernierUser+"','"+nomAff+"','"+nomStock+"','"+description+"','"+type+"','"+dateCreation+"',NULL)";
		c.reqSQL(req, 'm');
		
		// création du droit au fichier		
		rs =c.reqSQL("SELECT MAX(id) FROM DroitsFichiers",'s');
		rs.next();
		int idDroit = Integer.parseInt((rs.getString(1)==null?"0":rs.getString(1)))+1;
		req = "INSERT INTO DroitsFichiers VALUES ("+idDroit+",'"+idCreat+"','"+idFic+"')";
		c.reqSQL(req, 'm');
		c.close();
	}
	
	/**
	 * met à jour de dernier modificateur d'un fichier dans la BD (table FICHIERS)
	 * @param idFichier id du fichier dans la table fichier
	 * @param nomDernierUser 
	 */
	public static void majDernierUser(int idFichier, String nomDernierUser) {
		
		// TODO + date ??
		try {
			c = new Connect();
			String req = "SELECT id FROM Utilisateurs WHERE pseudo=\""+nomDernierUser+"\"";
			ResultSet rs = c.reqSQL(req,'s');
			rs.next();
			int idDernierUser = rs.getInt("id");
			
			req = "UPDATE Fichiers SET idDernierUser="+idDernierUser+" WHERE id="+idFichier;
			System.out.println("REQ: "+req);
			c.reqSQL(req, 'm');
			
			req = "UPDATE Fichiers SET dateModification=LOCALTIMESTAMP WHERE id="+idFichier;
			System.out.println("REQ: "+req);
			c.reqSQL(req, 'm');
			c.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * suppression d'un fichier dans la BD
	 * @param idFichier
	 */
	public static void supprimeFichier(int idFichier) {
		try {
			c = new Connect();
			c.reqSQL("DELETE FROM Fichiers WHERE id="+idFichier,'m');
			c.reqSQL("DELETE FROM DroitsFichiers WHERE idFichier="+idFichier,'m');
			c.reqSQL("DELETE FROM CommentairesFichiers WHERE idFichier="+idFichier,'m');
			c.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param user le nom de l'utilisateur
	 * @return le tableau des fichiers accessibles à un utilisateur
	 */
	public static ArrayList<Fichier> listeFichiers(String user) {
		String req = "SELECT *"
			+ "FROM Fichiers, DroitsFichiers, Utilisateurs"
			+ " WHERE Utilisateurs.pseudo = '"+user+"'"
			+ " AND DroitsFichiers.idUser = Utilisateurs.id"
			+ " AND DroitsFichiers.idFichier = Fichiers.id";
		// System.out.println(req);
		try {
			c = new Connect();
			ArrayList<Fichier> fichiers = new ArrayList<Fichier>();
			ResultSet rs = c.reqSQL(req, 's');
			while(rs.next()) {
				int idFichier = Integer.parseInt(rs.getString("idFichier"));
				String nomAffichage = rs.getString("nomAffichage");
				int idCreateur = rs.getInt("idCreateur");
				String nomCreateur = RequetesSQL.getNomUtilisateur(idCreateur);
				String dateCreation = rs.getTimestamp("dateCreation").toString();
				String nomModificateur = RequetesSQL.getNomUtilisateur(rs.getInt("idDernierUser"));
				String dateModification;
				try {
					dateModification = rs.getTimestamp("dateModification").toString();
				} catch (java.lang.NullPointerException e) {
					dateModification = "(pas de modif)";
				}
				String description = rs.getString("description");
				Fichier fichier = new Fichier(idFichier, nomAffichage, nomCreateur, dateCreation, nomModificateur, dateModification, description);
				System.out.println(fichier);
				fichiers.add(fichier);
			}
			c.close();
			return(fichiers);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return(null);
	}
	
	public static String getNomUtilisateur(int idUser) {
		String req = "SELECT pseudo FROM Utilisateurs WHERE id="+idUser;
		try {
			c = new Connect();
			ResultSet rs = c.reqSQL(req, 's');
			rs.next();
			String pseudo = rs.getString("pseudo");
			c.close();
			return(pseudo);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return(null);
	}
	
	public static String getNomStockage(int idFichier) {
		String req = "SELECT nomStockage FROM Fichiers WHERE id="+idFichier;
		ResultSet rs = null;
		String s = null;
		try {
			c = new Connect();
			rs = c.reqSQL(req, 's');
			rs.next();
			s = rs.getString("nomStockage");
			c.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return(s);
	}
	
	public static String getNomAff(int idFichier) {
		String req = "SELECT nomAffichage FROM Fichiers WHERE id="+idFichier;
		ResultSet rs = null;
		String s = null;
		try {
			c = new Connect();
			rs = c.reqSQL(req, 's');
			rs.next();
			s = rs.getString("nomAffichage");
			c.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return(s);
	}
	
	public static void ajouteConstributeur(int idFichier, String nomContributeur) {
		try {
			c = new Connect();
			String req = "SELECT id FROM Utilisateurs WHERE pseudo=\""+nomContributeur+"\"";
			ResultSet rs = c.reqSQL(req,'s');
			rs.next();
			int idContributeur = Integer.parseInt(rs.getString("id"));
			
			req = "INSERT INTO DroitsFichiers VALUES (NULL, "+idContributeur+","+idFichier+")";
			c.reqSQL(req, 'm');
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> listeContributeurs(int idFichier) {
		ArrayList<String> liste = new ArrayList<String>();
		try {
			c = new Connect();
			String req = "SELECT pseudo FROM Fichiers, DroitsFichiers, Utilisateurs"
				+ " WHERE Fichiers.id = DroitsFichiers.idFichier"
				+ " AND Utilisateurs.id = DroitsFichiers.idUser"
				+ " AND Fichiers.id = "+idFichier;
			ResultSet rs = c.reqSQL(req,'s');
			String pseudo;
			while(rs.next()) {
				pseudo = rs.getString("pseudo");
				System.out.println("contributeur : "+pseudo);
				liste.add(pseudo);
			}
			c.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return(liste);
	}
	
	/**
	 * @param nb
	 * @return la liste de Message du chat par ordre de date décroissant avec au maximum nb message
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Message> recupMessages(int nbMax) throws ClassNotFoundException, SQLException{
		ArrayList<Message> listeMessages = new ArrayList<Message>();
		c = new Connect();
		ResultSet rs = c.reqSQL("SELECT * FROM ChatApplication,Utilisateurs"
				+ " WHERE ChatApplication.idUser = Utilisateurs.id"
				+ " ORDER BY date DESC"
				+ " LIMIT "+nbMax,'s');
		while(rs.next()){
			int idMessage = rs.getInt("id");
			String nomUser = rs.getString("pseudo");
			String contenu = rs.getString("message");
			Date date = rs.getDate("date");
			Message message = new Message(idMessage, nomUser, contenu, date);
			listeMessages.add(message);
		}
		c.close();

		return listeMessages;
		
	}
	
	/**
	 * @param idFichier
	 * @param nbMax
	 * @return la liste des messages
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static ArrayList<Message> recupCommentaires(int idFichier, int nbMax) throws ClassNotFoundException, SQLException{
		ArrayList<Message> listeMessages = new ArrayList<Message>();
		c = new Connect();
		ResultSet rs = c.reqSQL("SELECT * FROM CommentairesFichiers,Utilisateurs"
				+ " WHERE CommentairesFichiers.idUser = Utilisateurs.id"
				+ " AND idFichier="+idFichier
				+ " ORDER BY date DESC"
				+ " LIMIT "+nbMax,'s');
		while(rs.next()){
			int idMessage = Integer.parseInt(rs.getString("id"));
			String nomUser = rs.getString("pseudo");
			String contenu = rs.getString("message");
			Date date = rs.getDate("date");
			Message message = new Message(idMessage, nomUser, contenu, date);
			listeMessages.add(message);
		}
		c.close();

		return listeMessages;
	}

	public static void ajouteMessage(String pseudo, String txt) {
		try {
			c = new Connect();
			ResultSet rs =c.reqSQL("SELECT MAX(id) FROM ChatApplication",'s');
			rs.next();
			int idMessage = Integer.parseInt((rs.getString(1)==null?"0":rs.getString(1)))+1;
			int idUser = getIdUser(pseudo);
			
			String req = "INSERT INTO ChatApplication VALUES ("+idMessage+", "+idUser+",'"+txt+"',LOCALTIMESTAMP)";
			System.out.println("REQ INSERT : \n"+req);
			c.reqSQL(req, 'm');
			c.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
