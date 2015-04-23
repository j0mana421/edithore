package projet;

/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 *
 */
public class Utilisateur{  
	  
    @SuppressWarnings("unused")
	private int id;  
    
    @SuppressWarnings("unused")
	private String pseudo;
    
    @SuppressWarnings("unused")
	private String mail;
    
    @SuppressWarnings("unused")
	private String mdp;
    
    private static int num=0;
    private final int NUM_PERSONNE;
  
  
    /**
     * crée un nouvel utilisateur
     * @param pseudo
     * @param mail
     * @param mdp
     */
    public Utilisateur (final String pseudo, final String mail, final String mdp) {  
        this.pseudo = pseudo;  
        this.mail = mail;  
        this.mdp = mdp;
    	num++;
    	NUM_PERSONNE=num; 
    }  
    
    /**
     * @return le numéro de l'utilisateur en cours
     */
    public int getNumero(){
    	System.out.println(NUM_PERSONNE);
    	return NUM_PERSONNE;
    }

}