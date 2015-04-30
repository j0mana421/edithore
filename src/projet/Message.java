package projet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 * un message de chatte
 */
public class Message {
	private int idMessage;
	private String pseudo;
	private String message;
	private Date date;
	
	/**
	 * @param idMessage
	 * @param pseudo
	 * @param message
	 * @param date
	 */
	Message(int idMessage, String pseudo, String message, Date date) {
		this.idMessage = idMessage;
		this.pseudo = pseudo;
		this.message = message;
		this.date = date;
	}
	
	Message(String pseudo, String message) {
		this.pseudo = pseudo;
		this.message = message;
		this.date = new Date();
	}

	public String toString() {
		return ("["+date+"] "+pseudo+" : "+message);
	}
}
