package projet;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import java.util.UUID;
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
		
		String path = getServletContext().getRealPath("/fichiers/");
        String nomStock = UUID.randomUUID().toString()+".txt";
		try{
			dbc.addFichier(pseudo,nomAff,nomStock, description, type, sqlDate);
			res.sendRedirect("Perso.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {out.close();}
		
//		FileOutputStream fileOut = new FileOutputStream(new File("test_upload.txt"));
//		int read = 0;
//        final byte[] bytes = new byte[1024];
//        while ((read = in.read(bytes)) != -1) {
//        	fileOut.write(bytes, 0, read);
//        	System.out.println("read:"+read);
//        }
        
        
        String content = "This is the content to write into file";
        
        
		File file = new File(path+nomStock);
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		System.out.println("svg du fichier...");
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		System.out.println("file.getAbsoluteFile() = "+file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
        
		// fileOut.close();
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
