package projet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EnvoiMP
 */
@WebServlet("/EnvoiMP")
public class EnvoiMP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnvoiMP() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudoDest");
		String msgPrivate = request.getParameter("msgPrivate");
		if(Tracker.chercheIP(pseudo)!= null) {
			new Client(pseudo, msgPrivate);
			response.sendRedirect("Perso.jsp");
		} else {
			System.out.println("L'utilisateur "+pseudo+" n'est pas connecté !");
			response.sendRedirect("ErreurMP.html");
		}
		
	}

}
