package projet;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author MAIA MARNAT MOUTRILLE STINDEL
 * un message de chat
 */
public class Message implements Serializable {
	private static final long serialVersionUID = 653473772329107397L;

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
	
	/**
	 * @return the idMessage
	 */
	public int getIdMessage() {
		return idMessage;
	}

	/**
	 * @param idMessage the idMessage to set
	 */
	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
