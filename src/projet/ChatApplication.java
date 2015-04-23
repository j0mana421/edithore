package projet;

import java.util.ArrayList;

/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 * gestion du chat pour l'application
 */
public class ChatApplication {
	private static ArrayList<Message> mes = new ArrayList<Message>();
	
	/**
	 * ajoute un message aux messages de l'application (= envoi)
	 * @param pseudo le pseudo
	 * @param txt le texte du message
	 */
	public static void ajoute(String pseudo, String txt) {
		Message m = new Message(pseudo, txt);
		mes.add(m);
	}
	
	/**
	 * @return la liste des messages de l'application
	 */
	public static String affiche() {
		String s = "";
		int ms = mes.size();
		for(int i = 1; i <= ms; i++) {
			s += mes.get(ms-i)+"<br>";
		}
		//System.out.println(s);
		return(s);
	}
}
