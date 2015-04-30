package projet;

import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class SearchU
 */
@WebServlet("/SearchU")
public class SearchU extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchU() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("SERVLET SEARCHU");
		// HttpSession session = request.getSession();	
		String pseudo=(String)request.getParameter("ru");
		// session.setAttribute("psdR",pseudo);
		try {
			if(!(RequetesSQL.verifUtilisateur(pseudo))){	
				JOptionPane.showMessageDialog(null,"Aucun Utilisateur trouvé");
				response.sendRedirect("/Projet/Perso.jsp");
			} else{
				response.sendRedirect("/Projet/Personne.jsp?pseudo="+pseudo);
			}
		} catch (HeadlessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
