package model;
import java.awt.Image;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;

import twitter4j.User;
/**
 * Clase modelo que un perfil de un usuario, inicialmente pensado para almacenar solo al usuario logeado.
 * @author Fiser
 *
 */
public class Perfil {
	private String username;
	private String description;
	private Image profileImage;
	private LinkedList<Tweet> listaTweets;
	private Date lastDateUpdate;
	private HashMap<String, User> siguiendo;
	private HashMap<String, User> seguidores;
	
	public Perfil(String username, String description, Image profileImage,
			LinkedList<Tweet> tweetsSends, HashMap<String, User> siguiendo,
			HashMap<String, User> seguidores, Date lastDateUpdate) {
		super();
		this.username = username;
		this.description = description;
		this.profileImage = profileImage;
		this.listaTweets = tweetsSends;
		this.setLastDateUpdate(lastDateUpdate);
		this.siguiendo = siguiendo;
		this.seguidores = seguidores;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(Image profileImage) {
		this.profileImage = profileImage;
	}

	public LinkedList<Tweet> getTweetsSends() {
		return listaTweets;
	}

	public void setTweetsSends(LinkedList<Tweet> listaTweets) {
		this.listaTweets = listaTweets;
	}

	public HashMap<String, User> getSiguiendo() {
		return siguiendo;
	}

	public void setSiguiendo(HashMap<String, User> siguiendo) {
		this.siguiendo = siguiendo;
	}

	public HashMap<String, User> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(HashMap<String, User> seguidores) {
		this.seguidores = seguidores;
	}

	public Date getLastDateUpdate() {
		return lastDateUpdate;
	}

	public void setLastDateUpdate(Date lastDateUpdate) {
		this.lastDateUpdate = lastDateUpdate;
	}
}
