package model;
import java.awt.Image;
import java.util.HashMap;

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
	private Timeline tweetsSends;
	private HashMap<String, User> siguiendo;
	private HashMap<String, User> seguidores;
	
	public Perfil(String username, String description, Image profileImage,
			Timeline tweetsSends, HashMap<String, User> siguiendo,
			HashMap<String, User> seguidores) {
		super();
		this.username = username;
		this.description = description;
		this.profileImage = profileImage;
		this.tweetsSends = tweetsSends;
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

	public Timeline getTweetsSends() {
		return tweetsSends;
	}

	public void setTweetsSends(Timeline tweetsSends) {
		this.tweetsSends = tweetsSends;
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
}
