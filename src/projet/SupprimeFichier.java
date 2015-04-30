package projet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;

/**
 * Servlet implementation class SupprimeFichier
 */
@WebServlet("/SupprimeFichier")
public class SupprimeFichier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idFichier = Integer.parseInt((String) request.getParameter("idFichier"));
		String nomStock = RequetesSQL.getNomStockage(idFichier);
		
		// suppression sur le serveur
		System.out.println("connexion au serveur...");
		FTPClient client = new FTPClient();
		client.connect("ftp.byethost15.com");
		if (client.login("b7_16124912", "moumoutte42rpz")) System.out.println(" done."); 
		else System.out.println(" ECHEC !");
		
		System.out.println("supression du fichier...");
		if (client.deleteFile("htdocs/tomcat_files/"+nomStock)) System.out.println(" done.");
		else System.out.println(" ECHEC !");		
		
		client.logout();
		
		// suppression dans la BD
		RequetesSQL.supprimeFichier(idFichier);
		
		response.sendRedirect("Perso.jsp");
	}

}
