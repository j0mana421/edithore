package projet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.net.ftp.FTPClient;

/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 *
 */
@WebServlet(name = "AjoutFic", urlPatterns = {"/AjoutFic"})
@MultipartConfig
public class AjoutFic extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * ajout de fichier
	 */
	public AjoutFic() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out= res.getWriter();
		HttpSession session = req.getSession();
		String pseudo = (String) session.getAttribute("nom");
		Part part = req.getPart("fichier");
		String description = req.getParameter("description");
		String nomAff = getNomFichier(part);
		InputStream in = part.getInputStream();
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		String[] tab = nomAff.split("\\.");
		String type = tab[1].substring(0,tab[1].length()-1);
		String nomStock = UUID.randomUUID().toString()+".txt";
		
		// AJOUT DE LA REFERENCE DU FICHIER DANS LA BD
		try{
			RequetesSQL.addFichier(pseudo,nomAff,nomStock, description, type, sqlDate);
			res.sendRedirect("Perso.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {out.close();}        

		
		// UPLOAD DU FICHIER PHYSIQUE SUR LE SERVEUR DISTANT 
		System.out.println("connexion au serveur...");
		
		FTPClient client = new FTPClient();
		client.connect("ftp.byethost15.com");
		if (client.login("b7_16124912", "moumoutte42rpz")) System.out.println(" done."); 
		else System.out.println(" ECHEC !");
		
		System.out.println("upload du fichier...");
		if (client.storeFile("htdocs/tomcat_files/"+nomStock, in)) System.out.println(" done.");
		else System.out.println(" ECHEC !");		
		
		client.logout();


	}

	private static String getNomFichier( Part part ) {
		for (String contentDisposition : part.getHeader( "content-disposition" ).split( ";" )) {
			if (contentDisposition.trim().startsWith("filename")) {
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1);
			}
		}
		return null;
	}
	
}
