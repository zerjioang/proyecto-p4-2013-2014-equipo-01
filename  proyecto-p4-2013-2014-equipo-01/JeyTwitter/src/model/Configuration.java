package model;

import java.awt.Font;
import java.util.ArrayList;
/**
 * Clase modelo que representa las configuraciones que hay establecidas para la cuenta
 * @author Fiser
 *
 */
public class Configuration {
	private Credential user;
	private boolean sounds;
	private boolean notifications;
	private boolean launchWithSystem;
	private int timeNotifications;
	private int timeRefresh;
	private Font fontSelected;
	private ArrayList<String> censureWords;
	
	public Configuration(Credential user, boolean sounds,
			boolean notifications, boolean launchWithSystem,
			int timeNotifications, int timeRefresh, Font fontSelected,
			ArrayList<String> censureWords) {
		super();
		this.user = user;
		this.sounds = sounds;
		this.notifications = notifications;
		this.launchWithSystem = launchWithSystem;
		this.timeNotifications = timeNotifications;
		this.timeRefresh = timeRefresh;
		this.fontSelected = fontSelected;
		this.censureWords = censureWords;
	}

	public Credential getUser() {
		return user;
	}

	public void setUser(Credential user) {
		this.user = user;
	}

	public boolean isSounds() {
		return sounds;
	}

	public void setSounds(boolean sounds) {
		this.sounds = sounds;
	}

	public boolean isNotifications() {
		return notifications;
	}

	public void setNotifications(boolean notifications) {
		this.notifications = notifications;
	}

	public boolean isLaunchWithSystem() {
		return launchWithSystem;
	}

	public void setLaunchWithSystem(boolean launchWithSystem) {
		this.launchWithSystem = launchWithSystem;
	}

	public int getTimeNotifications() {
		return timeNotifications;
	}

	public void setTimeNotifications(int timeNotifications) {
		this.timeNotifications = timeNotifications;
	}

	public int getTimeRefresh() {
		return timeRefresh;
	}

	public void setTimeRefresh(int timeRefresh) {
		this.timeRefresh = timeRefresh;
	}

	public Font getFontSelected() {
		return fontSelected;
	}

	public void setFontSelected(Font fontSelected) {
		this.fontSelected = fontSelected;
	}

	public ArrayList<String> getCensureWords() {
		return censureWords;
	}

	public void setCensureWords(ArrayList<String> censureWords) {
		this.censureWords = censureWords;
	}
	
}
