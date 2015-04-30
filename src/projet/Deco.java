package projet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 *
 */
public class Deco extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   

	/**
	 * déconnexion à l'application
	 */
	public Deco() {
		super();
 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out= res.getWriter();
		ConnexionSocket cs = ((ConnexionSocket) req.getSession().getAttribute("ConnexionSocket"));
		cs.killServeur();
		req.getSession().invalidate();
		try{
			res.sendRedirect("index.html");
		} finally {out.close();}
	}

}
