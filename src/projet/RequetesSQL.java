package projet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 * 
 */
public class RequetesSQL {  
	Connect c;

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
		ResultSet rs =c.reqSQL("SELECT MAX(id_u) FROM utilisateurs",'s');
		rs.next();
		int num =Integer.parseInt(rs.getString(1))+1;
		chaine= "INSERT INTO utilisateurs VALUES ('"+num+"', '"+pseudo+"','"+mail+"','"+mdp+"')";
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
	public boolean verifUtilisateur(String psd, String motdp) throws SQLException, ClassNotFoundException{
		c=new Connect();
		ResultSet rs = c.reqSQL("SELECT id_u FROM utilisateurs WHERE pseudo='"+psd+"' AND mdp='"+motdp+"'", 's');
		if(rs.first()){
			c.close();
			return true;
		}
		c.close();
		return false;
	}

	/**
	 * @param pseudo
	 * @param nomFichier
	 * @param description
	 * @param fichier
	 * @param type
	 * @param date
	 * @throws SQLException
	 * @throws IOException
	 */
	public void addFichier(String pseudo, String nomFichier, String description,InputStream fichier,String type, java.sql.Date date) throws SQLException, IOException{
		try {
			c=new Connect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs =c.reqSQL("SELECT MAX(id) FROM documents",'s');
		rs.next();
		int num =Integer.parseInt(rs.getString(1))+1;;
		String chaine= "INSERT INTO documents VALUES ('"+num+"', '"+pseudo+"', '"+nomFichier+"','"+description+"','"+fichier+"','"+type+"','"+date+"')";
		c.reqSQL(chaine, 'm');
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSetTableModel rtm = new ResultSetTableModel(c.reqSQL("SELECT * FROM documents WHERE pseudo='"+psd+"'", 's'));
		TablePanel tablePanel = new TablePanel( rtm );
		return tablePanel;
	}
	
}
