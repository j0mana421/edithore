package projet;

import java.awt.HeadlessException;
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
 * @author MAIA MARNAT MOUTRILLE STINDEL
 *
 */
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/** pseudo de l'user */
	public static String pseudo;
	
	/** mail de l'user */
	public static String mail;
	
	/** mdp de l'user */
	public static String mdp;
	RequetesSQL dbc= new RequetesSQL();
	
	
	/**
	 * inscrit un user
	 */
	public Inscription() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out= response.getWriter();		
		pseudo=request.getParameter("pseudo");
		mail=request.getParameter("addr_mail");
		mdp=request.getParameter("mdp");
			try {
				if(RequetesSQL.verifUtilisateur(pseudo)){
					JOptionPane.showMessageDialog(null,"Pseudo deja utilisé");
					response.sendRedirect("/Projet/formulaire_inscription.html");
				}
				else{
				try {
					RequetesSQL.creerUtilisateur(pseudo, mail, mdp);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				HttpSession session = request.getSession();
				session.setAttribute("nom",pseudo);
				try{
								response.sendRedirect("index.html");
				} finally {out.close();}
				}	
			} catch (HeadlessException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
