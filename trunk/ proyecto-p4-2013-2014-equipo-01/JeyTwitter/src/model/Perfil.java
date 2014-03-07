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
	private Date lastDateUpdate;
	
	public Perfil(String username, String description, Image profileImage, Date lastDateUpdate) {
		super();
		this.username = username;
		this.description = description;
		this.profileImage = profileImage;
		this.setLastDateUpdate(lastDateUpdate);
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
	public Date getLastDateUpdate() {
		return lastDateUpdate;
	}

	public void setLastDateUpdate(Date lastDateUpdate) {
		this.lastDateUpdate = lastDateUpdate;
	}
}
