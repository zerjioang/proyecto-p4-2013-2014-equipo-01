package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * Clase encargada de encapsular los usos de la API de Twitter
 * @author aitor
 *
 */
public class TwitterService {
	private Twitter tw;
	
	/**
	 * Crea un nuevo servicio de Twitter
	 * @param key Constante clave dada por el panel de la api
	 * @param keySecret Constante clave secreta dada por el panel de la api
	 */
	public TwitterService(String key, String keySecret) {
		tw = new TwitterFactory().getInstance();
		tw.setOAuthConsumer(key, keySecret);
	}
	
	/**
	 * Inicia el proceso de autenticación y recupera el token para 
	 * usar Twitter.
	 * @throws Exception
	 */
	public void requestToken() throws Exception {
		RequestToken requestToken = tw.getOAuthRequestToken();
		
		if(Desktop.isDesktopSupported()){
		  Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
		}
		
		AccessToken accessToken = null;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (null == accessToken) {
			try {
				System.out.print("Input PIN here: ");
				String pin = br.readLine();

				accessToken = tw.getOAuthAccessToken(requestToken, pin);

			} catch (TwitterException te) {

				System.out.println("Failed to get access token, caused by: "
						+ te.getMessage());

				System.out.println("Retry input PIN");

			}
		}

		System.out.println("Access Token: " + accessToken.getToken());
		System.out.println("Access Token Secret: "
				+ accessToken.getTokenSecret());
	}
	
	/**
	 * Se obtiene el timeline
	 * @return
	 * @throws TwitterException
	 */
	public ResponseList<Status> getTimeline() throws TwitterException {
		ResponseList<Status> list = null;
		
		if (tw != null) {
			list = tw.getHomeTimeline();						
		}
		
		return list;
	}
	
	/**
	 * Tuitea el mensaje por parámetro
	 * @param message
	 * @throws TwitterException 
	 */
	public void tweet(String message) throws TwitterException {
		tw.updateStatus(message);
	}
}
