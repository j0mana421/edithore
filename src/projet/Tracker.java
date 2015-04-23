package projet;

import java.util.ArrayList;



/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 * tracker pour les machines en p2p
 */
public class Tracker {
	//private static ArrayList<Machine> listeMachines;
	private static ArrayList<Machine> listeMachines = new ArrayList<Machine>();
	
	
	/**
	 * @param nom de l'user
	 * @param adresseIP de l'user
	 */
	public static void ajouteMachine(String nom, String adresseIP) {
		listeMachines.add(new Machine(nom, adresseIP));
		
		System.out.println("Liste des machines :");
		for	(int i = 0; i < listeMachines.size(); i++) {
			System.out.println(listeMachines.get(i).nom+" ["+listeMachines.get(i).adresseIP+"]");
		}
	}
	
	/**
	 * @param nom le nom à chercher
	 * @return l'IP d'un user
	 */
	public static String chercheMachine(String nom) {
		for(int i = 0; i < listeMachines.size(); i++) {
			if (listeMachines.get(i).nom.compareToIgnoreCase(nom) == 0) return listeMachines.get(i).adresseIP; 
		}
		return(null);
		
	}
	
	/**
	 * @return les options avec en value l'IP de l'user, et en txt son nom
	 */
	public static String listeMachines() {
		String s = "";
		for(int i = 0; i < listeMachines.size(); i++) {
			s += "<option value=\""+listeMachines.get(i).adresseIP+"\">"+listeMachines.get(i).nom+"</option>";
		}
		return(s);
	}
}



