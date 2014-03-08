package model;

import java.awt.Image;
import java.sql.Date;

/**
 * Clase que representa la tabla que se almacena en la BBDD con los credenciales del usuario
 * @author aitor
 *
 */
public class Credential {
	private String code;
	private String username;
	private String realName;
	private Image profileImage;
	private Date lastDateUpdated;
	private int tweets;
	private int siguiendo;
	private int seguidores;
	
	
	Credential() {
		code = null;
		username = null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Image getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(Image profileImage) {
		this.profileImage = profileImage;
	}

	public Date getLastDateUpdated() {
		return lastDateUpdated;
	}

	public void setLastDateUpdated(Date lastDateUpdated) {
		this.lastDateUpdated = lastDateUpdated;
	}

	public int getTweets() {
		return tweets;
	}

	public void setTweets(int tweets) {
		this.tweets = tweets;
	}

	public int getSiguiendo() {
		return siguiendo;
	}

	public void setSiguiendo(int siguiendo) {
		this.siguiendo = siguiendo;
	}

	public int getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(int seguidores) {
		this.seguidores = seguidores;
	}
}
