package projet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Stack;

public class ConnexionSocket implements Runnable{

		private PrintWriter out = null;
		private BufferedReader in = null;
		private ServerSocket serveur = null;
		private Socket socket = null;
		public Thread t1;
		public Thread t2;
		public String pseudo= null;
		
		private Stack<String> pileMessages = new Stack<String>();
		
		public ConnexionSocket(ServerSocket ss, String pseudo){
		 serveur = ss;
		 this.pseudo=pseudo;
		}
		
		
		public void run(){
			try {
				while(true) {
				socket = serveur.accept();
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				String ligne = in.readLine();
				pileMessages.add(ligne);
				out.println(ligne);
				out.flush();
				
				}
			} catch (IOException e) {            
				System.out.println("Serveur de "+pseudo+" déconnecté");
			}
			
		}
		
		public String getMessage() {
			if (!pileMessages.isEmpty()) return pileMessages.pop();
			return null;
		}


		public void killServeur() throws IOException {
			serveur.close();
		}
		
}
