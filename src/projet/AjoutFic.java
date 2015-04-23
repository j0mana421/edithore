package projet;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 *
 */
@WebServlet(name = "AjoutFic", urlPatterns = {"/AjoutFic"})
@MultipartConfig
public class AjoutFic extends HttpServlet {
	private static final long serialVersionUID = 1L;
    RequetesSQL dbc= new RequetesSQL();
   
    /**
     * ajout de fichier
     */
    public AjoutFic() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}


	public void doPost( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException{
		res.setContentType("text/html; charset=UTF-8");	
		PrintWriter out= res.getWriter();	
		HttpSession session = req.getSession();
		String pseudo = (String) session.getAttribute("nom");
		Part part = req.getPart("fichier");
		String description = req.getParameter("description");
		String nomFic = getNomFichier(part);
		InputStream in = part.getInputStream();
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		String[] tab = nomFic.split("\\.");
		String type = tab[1].substring(0,tab[1].length()-1);
		try{
			dbc.addFichier(pseudo,nomFic,description, in, type, sqlDate);
			res.sendRedirect("Perso.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {out.close();}
		 
	}
		
	private static String getNomFichier( Part part ) {
	    for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	        if ( contentDisposition.trim().startsWith("filename") ) {
	            return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 );
	        }
	    }
	    return null;
	}
	
}
