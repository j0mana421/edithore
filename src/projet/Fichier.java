package projet;

import java.util.Date;

public class Fichier {
	int idFichier;
	String nomAffichage;
	String nomCreateur;
	String dateCreation;
	String nomModificateur;
	String dateModification;
	String description;
	
	Fichier(int idFichier, String nomAffichage, String nomCreateur, String dateCreation, String nomModificateur, String dateModification, String description) {
		this.idFichier = idFichier;
		this.nomAffichage = nomAffichage;
		this.nomCreateur = nomCreateur;
		this.dateCreation = dateCreation;
		this.nomModificateur = nomModificateur;
		this.dateModification = dateModification;
		this.description = description;
	}
	
	public String toString() {
		String s = "<div class='fichier conteneur'>"
				+ "<div class='fichier titre'>"+nomAffichage+"</div>"
				+ "<div class='fichier boutons'>"
					+"<a class='picto' href=\"EditeurDeTexte.jsp?idFichier="+idFichier+"\">✍</a>"
					+ "<form class='form_supprimer' action='SupprimeFichier' method='post'>"
						+ "<input type='hidden' name='idFichier' value='"+idFichier+"'/>"
						+ "<input class='picto' type='submit' value='×'/>"
					+ "</form>"
				+ "</div>"
			+ "<hr>"
				+ "créé par "+nomCreateur
				+ ", le "+dateCreation
				+ "<br>"
				+ "modifié par "+nomModificateur
				+ ", le "+dateModification
			+ "<hr>"
				+ "<div class='fichier description'>"+description+"</div>"
		+ "</div>"; 
		return(s);
	}
}
