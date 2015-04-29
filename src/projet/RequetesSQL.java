package projet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	public String creerUtilisateur(final String pseudo, final String mail, final String mdp) throws SQLException, ClassNotFoundException{
		c=new Connect();
		String chaine;
		ResultSet rs =c.reqSQL("SELECT MAX(id) FROM Utilisateurs",'s');
		rs.next();
		int num =Integer.parseInt(rs.getString(1))+1;
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
	public void addFichier(String pseudo, String nomAff, String nomStock, String description, String type, java.sql.Date date) throws SQLException, IOException{
		try {
			c=new Connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs =c.reqSQL("SELECT MAX(id) FROM Fichiers",'s');
		rs.next();
		int idFic = Integer.parseInt(rs.getString(1))+1;
		
		rs = c.reqSQL("SELECT id FROM Utilisateurs WHERE pseudo=\""+pseudo+"\"",'s');
		rs.next();
		int idCreat = Integer.parseInt(rs.getString("id"));
		int idDernierUser = idCreat; // TODO
		
		// insertion dans Fichiers
		String req= "INSERT INTO Fichiers VALUES ('"+idFic+"', '"+idCreat+"','"+idDernierUser+"','"+nomAff+"','"+nomStock+"','"+description+"','"+type+"','"+date+"')";
		c.reqSQL(req, 'm');
		
		// création du droit au fichier
		req = "INSERT INTO DroitsFichiers VALUES (NULL,'"+idCreat+"','"+idFic+"')";
		c.reqSQL(req, 'm');
		c.close();
	}
	
	/**
	 * @param psd
	 * @return un tablepanel correpondant aux docs
	 * @throws SQLException
	 */
	public TablePanel afficheFichier(String psd) throws SQLException{
		try {
			c=new Connect();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		ResultSetTableModel rtm = new ResultSetTableModel(c.reqSQL("SELECT * FROM documents WHERE pseudo='"+psd+"'", 's'));
		TablePanel tablePanel = new TablePanel( rtm );
		return tablePanel;
	}
	
	/**
	 * @param user le nom de l'utilisateur
	 * @return le tableau des fichiers accessibles à un utilisateur
	 */
	public static ResultSet listeFichiers(String user) {
		String req = "SELECT * FROM Fichiers, DroitsFichiers, Utilisateurs"
			+ " WHERE Utilisateurs.pseudo = '"+user+"'"
			+ " AND DroitsFichiers.idUser = Utilisateurs.id"
			+ " AND DroitsFichiers.idFichier = Fichiers.id";
		ResultSet rs = null;
		try {
			c = new Connect();
			rs = c.reqSQL(req, 's');
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return(rs);
	}
	
}
