package projet;

import java.net.*;
import java.io.*;

/*
* Usage: Client <filename>
* Lookup a file across one or more server(s), and bring it here.
* List of server IP addresses should be in local "server.in" text file
* (one IP per line)
*/
/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 *
 */
public class Client {
	
	/**
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 * lancement de l'app
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		final int PORT =8888;
		Socket s = new Socket("localhost", PORT);
		InputStream in= s.getInputStream();
		OutputStream out = s.getOutputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		PrintWriter writer = new PrintWriter(out);
	    String commande ="5\n";
		System.out.print("Envoi : "+commande);
		writer.print(commande);
		writer.flush();
		String reponse = reader.readLine();
		System.out.println("Recu : "+ reponse);
		commande ="xyz\n";
		System.out.print("Envoi : "+commande); 
		writer.print(commande);
		writer.flush();
		s.close();
	}
}
