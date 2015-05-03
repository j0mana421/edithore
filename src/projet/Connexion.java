package projet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 * @author MAIA MARNAT MOUTRILLE STINDEL connexion à l'application
 */
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/** l'identifiant de l'utilisateur */
	public static String identifiant;

	/** mot de passe */
	public static String mdp;

	public ConnexionSocket cs;

	/**
	 * Connexion à l'application
	 */
	public Connexion() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		identifiant = req.getParameter("identifiant");
		mdp = req.getParameter("motdepasse");
		System.out.println("REM ADR : " + req.getRemoteAddr());
		try {
			if (RequetesSQL.verifUtilisateur(identifiant, mdp)) {
				HttpSession session = req.getSession();
				session.setAttribute("nom", identifiant);
				session.setAttribute("mdp", mdp);
				Serveur srv = new Serveur(identifiant);
				this.cs = srv.cs;
				session.setAttribute("ConnexionSocket", cs);
				Tracker.ajouteMachine(identifiant, req.getRemoteAddr(), srv.getPort());
				res.sendRedirect("Perso.jsp");
			} else {
				PrintWriter out = res.getWriter();
				try {
					res.sendRedirect("ErreurConnexion.html");
				} finally {
					out.close();
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
