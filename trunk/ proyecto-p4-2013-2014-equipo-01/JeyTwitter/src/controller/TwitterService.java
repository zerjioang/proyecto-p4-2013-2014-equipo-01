package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import util.Util;

/**
 * Clase encargada de encapsular los usos de la API de Twitter
 * @author aitor
 *
 */
public class TwitterService {
	private AccessToken accessToken = null;
	private RequestToken peticionDeCodigo = null;
	
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
	
	public void verificarToken() throws TwitterException {
		tw.verifyCredentials();
	}

	/**
	 * Abre una ventana del navegador en la que se le pide confirmacion al usuario 
	 * @throws Exception
	 */
	public void pedirCodigo() throws Exception {
		peticionDeCodigo = tw.getOAuthRequestToken();

		if(Desktop.isDesktopSupported()){
			Desktop.getDesktop().browse(new URI(peticionDeCodigo.getAuthorizationURL()));
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (null == accessToken) {
			try {
				System.out.print("Input PIN here: ");
				String pin = br.readLine();
				accessToken = tw.getOAuthAccessToken(peticionDeCodigo, pin);
			} catch (TwitterException te) {
				System.out.println("Failed to get access token, caused by: "+ te.getMessage());
				System.out.println("Retry input PIN");
			}
		}
		
	}
	
	/**
	 * Se crea un token con el codigo que el usuario ha recibido al autorizar
	 * la aplicaci��n en la web
	 * @param codigo
	 * @return
	 * @throws TwitterException
	 */
	public AccessToken crearToken(String codigo) throws TwitterException {
		accessToken = tw.getOAuthAccessToken(peticionDeCodigo, codigo);
		
		return accessToken;
	}
	
	/**
	 * Se crea un token en funci��n de uno que est�� previamente almacenado y se configura
	 * @param token
	 * @param tokenSecret
	 */
	public void recuperarToken(String token, String tokenSecret) {
		AccessToken t = new AccessToken(token, tokenSecret);
		tw.setOAuthAccessToken(t);
		try {
			tw.verifyCredentials();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Se obtiene el usuario actual
	 * @return
	 * @throws IllegalStateException
	 * @throws TwitterException
	 */
	public User getUsuarioRegistrado() throws IllegalStateException, TwitterException {
		return tw.showUser(tw.getId());
	}

	/**
	 * Se obtiene el timeline (20 ��ltimos pero se puede parametrizar mas)
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
	 * Recupera el Timeline de menciones
	 * @return
	 * @throws TwitterException
	 */
	public ResponseList<Status> getMentions() throws TwitterException {
		ResponseList<Status> list = null;

		if (tw != null) {
			list = tw.getMentionsTimeline();						
		}
		return list;
	}

	/**
	 * Recupera todos nuestros Tweets retwitteados por mi
	 * @return
	 * @throws TwitterException
	 */
	public ResponseList<Status> getRetweetsOfMe() throws TwitterException {
		ResponseList<Status> list = null;

		if (tw != null) {
			list = tw.getRetweetsOfMe();						
		}
		return list;
	}

	/**
	 * Tuitea el mensaje por par��metro
	 * @param message
	 * @throws TwitterException 
	 */
	public void tweet(String message) throws TwitterException {
		tw.updateStatus(message);
	}

	/**
	 * Obtiene la lista de seguidores de un usuario
	 * 
	 * @param String usuario, Long cursor
	 * @throws TwitterException 
	 * @author sergio
	 */
	public void getSiguiendo(String username, Long cursor) throws TwitterException{

		//EN IMPLEMENTACION
		//tw.getFriendssIDs(username, cursor);
	}

	/**
	 * Obtiene la personas que est�� siguiendo un usuario
	 * 
	 * @param String usuario, Long cursor
	 * @throws TwitterException 
	 * @author sergio
	 */
	public void getSeguidores(String username, Long cursor) throws TwitterException{
		//IDs ids;

		PagableResponseList<User> usuarios;
		usuarios = tw.getFollowersList(username, -1);

		System.out.println(" ");
		System.out.println(" ");
		/* System.out.println("Con el cursor a -1: ");
		 * System.out.println("el numero de usuarios es de: " + usuarios.size());
		 * System.out.println("el usuario numero 20 es: " + usuarios.get(19).getScreenName());
		 * System.out.println(" ");
		 * System.out.println(" ");
		 * usuarios = tw.getFollowersList(username, -2);
		 * System.out.println("Con el cursor a -2: ");
		 * System.out.println("el numero de usuarios es de: " + usuarios.size());
		 * System.out.println("el usuario numero 20 es: " + usuarios.get(19).getScreenName());
		 */

		for (int i=-1; i>-3; i--){
			System.out.println(" ");

			usuarios = tw.getFollowersList(username, i);

			System.out.println("Con el cursor a " +i +": ");
			System.out.println("el numero de usuarios es de: " + usuarios.size());

			for(int j=0; j<20; j++){
				System.out.println("el usuario numero "+j + " es: " + usuarios.get(j).getScreenName());
			}
		}
		
		usuarios = tw.getFollowersList(username, -3);
		System.out.println("Con el cursor a -3: ");
		System.out.println("el numero de usuarios es de: " + usuarios.size());
		for(int j=0; j<19; j++){
			System.out.println("el usuario numero "+j + " es: " + usuarios.get(j).getScreenName());
		}
	}
}
