package model;

import java.sql.Date;
import java.util.LinkedList;
/**
 * Clase modelo que representa una lista de Tweets ordenada
 * @author Fiser
 *
 */
public class Timeline {
	private LinkedList<Tweet> listaTweets;
	private Date lastDateUpdate;
	public Timeline(LinkedList<Tweet> listaTweets, Date lastDateUpdate) {
		super();
		this.listaTweets = listaTweets;
		this.lastDateUpdate = lastDateUpdate;
	}
	public LinkedList<Tweet> getListaTweets() {
		return listaTweets;
	}
	public void setListaTweets(LinkedList<Tweet> listaTweets) {
		this.listaTweets = listaTweets;
	}
	public Date getLastDateUpdate() {
		return lastDateUpdate;
	}
	public void setLastDateUpdate(Date lastDateTweet) {
		this.lastDateUpdate = lastDateTweet;
	}
}
