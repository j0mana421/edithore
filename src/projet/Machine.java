package projet;

/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 * machine
 */
public class Machine {
	String nom;
	String adresseIP;
	int port;
	
	Machine(String nom, String adresseIP, int port) {
		this.nom = nom;
		this.adresseIP = adresseIP;
		this.port=port;
	}
}