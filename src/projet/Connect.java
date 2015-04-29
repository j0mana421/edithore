package projet;
import java.sql.*;

/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 * connexion à la BD (locale)
 */
public class Connect {
	Connection c;
	Statement st;
	Connect() throws ClassNotFoundException, SQLException{
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e){
		    e.printStackTrace();
		} 
		String url="jdbc:mysql://localhost:8889/projet";
		//String url ="jdbc:mysql://sql105.byethost7.com/b7_16124912_0";
		try{
			//c = DriverManager.getConnection(url, "b7_16124912", "moumoutte42rpz");
			c = DriverManager.getConnection(url, "root", "root");
			System.out.println("Connexion réussie.\n");
			st = c.createStatement();
		}catch (SQLException e){
			System.out.println("Connexion échouée.\n");
			e.printStackTrace();
		}
	}
	
	/**
	 * @param query
	 * @param type
	 * @return le résultat de la requête
	 * @throws SQLException
	 */
	public ResultSet reqSQL(String query, char type) throws SQLException{
		if(type=='s'){
			try {
				ResultSet rs = st.executeQuery(query);
				return rs;
			}catch (SQLException e){
				e.printStackTrace();
				System.err.println("REQUETTE : "+query);
			}
		}
		else if (type=='m'){
			try{
				int ur = st.executeUpdate(query);
				System.out.println("Nombre de ligne(s) modifiée(s) " +ur);
				return null;
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * ferme la connexion à al BD
	 */
	public void close(){
			try {
				st.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * @param rs
	 * @return le résultat de la requête
	 * @throws SQLException
	 */
	public String afficherRes(ResultSet rs) throws SQLException{
		ResultSetMetaData metadata = rs.getMetaData();
		String resultat="";
		while(rs.next()){
			for(int i=1; i<=metadata.getColumnCount();i++){
				System.out.print(rs.getString(i) +" ");
				resultat+=rs.getString(i) +" ";
			}
			System.out.println();
		}
		return resultat;
	}
}

