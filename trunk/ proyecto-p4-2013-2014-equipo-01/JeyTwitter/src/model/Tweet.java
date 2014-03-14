package model;

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
	private String retweetbyUser;
	private boolean isRetweetedByMe;
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
			boolean isFavorite, boolean isFavoriteByMe, String isRetweetbyUser, boolean isRetweetedByMe) {
		super();
		this.idStatus = idStatus;
		this.textStatus = textStatus;
		this.dateCreated = dateCreated;
		this.user = user;
		this.isFavorite = isFavorite;
		this.retweetbyUser = isRetweetbyUser;
		this.isRetweetedByMe = isRetweetedByMe;
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
	
	public String isRetweetbyUser() {
		return retweetbyUser;
	}

	public void setRetweetbyUser(String isRetweetbyUser) {
		this.retweetbyUser = isRetweetbyUser;
	}

	public boolean isRetweetedByMe() {
		return isRetweetedByMe;
	}

	public void setRetweetedByMe(boolean isRetweetedByMe) {
		this.isRetweetedByMe = isRetweetedByMe;
	}
}
