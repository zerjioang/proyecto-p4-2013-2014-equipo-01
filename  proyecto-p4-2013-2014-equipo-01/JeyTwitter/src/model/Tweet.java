package model;

import java.awt.Image;
import java.util.Date;
import twitter4j.User;
/**
 * Clase modelo que representa cómo se almacena un Tweet en la BBDD
 * @author aitor
 *
 */
public class Tweet {
	private long idStatus;
	private String textStatus;
	private Date dateCreated;
	private User user;
	private boolean isFavorite;
	private boolean isFavoriteByMe;
	private boolean isRetweet;
	private boolean isRetweetedByMe;
	private Image profileImage;
	/**
	 * @param idStatus
	 * @param textStatus
	 * @param dateCreated
	 * @param user
	 * @param isFavorite
	 * @param isRetweet
	 * @param isRetweetedByMe
	 */
	public Tweet(long idStatus, String textStatus, Date dateCreated, User user,
			boolean isFavorite, boolean isFavoriteByMe, boolean isRetweet, boolean isRetweetedByMe, Image profileImage) {
		super();
		this.idStatus = idStatus;
		this.textStatus = textStatus;
		this.dateCreated = dateCreated;
		this.user = user;
		this.isFavorite = isFavorite;
		this.isFavoriteByMe = isFavoriteByMe;
		this.isRetweet = isRetweet;
		this.isRetweetedByMe = isRetweetedByMe;
		this.profileImage = profileImage;
	}

	public long getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(long idStatus) {
		this.idStatus = idStatus;
	}

	public String getTextStatus() {
		return textStatus;
	}

	public void setTextStatus(String textStatus) {
		this.textStatus = textStatus;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	public boolean isFavoriteByMe() {
		return isFavoriteByMe;
	}

	public void setFavoriteByMe(boolean isFavoriteByMe) {
		this.isFavoriteByMe = isFavoriteByMe;
	}
	public boolean isRetweet() {
		return isRetweet;
	}

	public void setRetweet(boolean isRetweet) {
		this.isRetweet = isRetweet;
	}

	public boolean isRetweetedByMe() {
		return isRetweetedByMe;
	}

	public void setRetweetedByMe(boolean isRetweetedByMe) {
		this.isRetweetedByMe = isRetweetedByMe;
	}
	public Image getProfileImage() {
		return profileImage;
	}

	public void setUser(Image profileImage) {
		this.profileImage = profileImage;
	}
}
