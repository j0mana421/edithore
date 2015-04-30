package projet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;

/**
 * Servlet implementation class MAJFic
 */
@WebServlet("/MAJFic")
public class MAJFic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String titre = request.getParameter("titre");
		String texte = request.getParameter("texte");
		int idFichier = Integer.parseInt(request.getParameter("idFichier"));
		String nomDernierUser = request.getParameter("nomDernierUser");
		
		RequetesSQL.majDernierUser(idFichier, nomDernierUser);

		System.out.println("connexion au serveur...");
		FTPClient client = new FTPClient();
		client.connect("ftp.byethost15.com");
		if (client.login("b7_16124912", "moumoutte42rpz")) System.out.println(" done."); 
		else System.out.println(" ECHEC !");
		
		OutputStream os = client.storeFileStream("htdocs/tomcat_files/"+titre);
		os.write(texte.getBytes());
		os.close();
		
		response.sendRedirect("EditeurDeTexte.jsp?idFichier="+idFichier+"&svg=true");
		
	}

}
