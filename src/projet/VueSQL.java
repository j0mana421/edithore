package projet;

import java.sql.ResultSet;
import java.sql.SQLException;

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
		String s = "<table border=1>"
		  + "<tr><td>nom</td><td>description</td><td>chemin</td><td>type</td><td>date</td></tr>";
		try {
			while(rs.next()) {
				s += "<tr>";
				s += "<td>"+rs.getString("nomAffichage")+"</td>";
				s += "<td>"+rs.getString("description")+"</td>";
				s += "<td><a href=\"fichiers/"+rs.getString("nomStockage")+"\">CLICK</a></td>";
				s += "<td>"+rs.getString("type")+"</td>";
				s += "<td>"+rs.getString("date")+"</td>";
				s += "</tr>";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		s += "</table>";
		return(s);
	}
}
