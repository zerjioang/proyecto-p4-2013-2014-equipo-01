package controller;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * Clase encargada de intermediar entre la GUI y la API de Twitter
 * @author aitor
 *
 */
public class GUIController {
	// Keys de la API console
	private final static String CONSUMER_KEY = "KRiUVHsXKNRDHVIGxYJ7w";
	private final static String CONSUMER_KEY_SECRET = "BDwwg2NBUjY48OcTB2818sp7E7L32AzhNLdgt82ZVQ";
	
	private TwitterService t;
	private String token;
	
	public GUIController() {
		t = new TwitterService(CONSUMER_KEY, CONSUMER_KEY_SECRET);
		
		if (token == null) {
			try {
				t.requestToken();
			} catch (Exception e) {
				// Error al recuperar el token
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Muestra el TL en el elemento de la GUI correspondiente
	 */
	public void showTimeline() {
		ResponseList<Status> listTL;
		try {
			listTL = t.getTimeline();
			for (Status each : listTL) {
				
				System.out.println("Sent by: @" + each.getUser().getScreenName()
						+ " - " + each.getUser().getName() + "\n" + each.getText()
						+ "\n");
			}
		} catch (TwitterException e) {
			// Error al recuperar el timeline
			e.printStackTrace();
		}
	}
	
	/**
	 * Tuitea usando el texto de el componente de la GUI correspondiente
	 */
	public void sendTweet() {
		// Se supone que recoge el texto de el textfield de turno
		try {
			t.tweet("Probando JeyTuiter desde source code!!!");
		} catch (TwitterException e) {
			// Error al tuitear, mostrar mensaje
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Inicio de el programa
		GUIController gui = new GUIController();
		
		gui.showTimeline();
		//gui.sendTweet();
	}

	public TwitterService getTwitterService() {
		return t;
	}

}
