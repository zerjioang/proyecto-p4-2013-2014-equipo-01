package controller;

import java.awt.Desktop;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.Tweet;
import twitter4j.IDs;
import twitter4j.MediaEntity;
import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import util.Util;
import view.elementos.GUITweet;
import view.elementos.GuiTwitterUsuario;
import view.elementos.ObjetoCelda;

/**
 * Clase encargada de encapsular los usos de la API de Twitter
 * @author aitor
 *
 */
public class TwitterService {
	private AccessToken accessToken = null;
	private RequestToken peticionDeCodigo = null;
	
	private static Twitter tw;
	
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
	 * Permite retweetear el tweet indicado por el código long
	 * @param codigo Es el código identificativo del tweet
	 */
	public Status retweetear(long codigo) throws TwitterException
	{
		return tw.retweetStatus(codigo);
	}
	/**
	 * Permite desfavear el tweet indicado por el código long
	 * @param codigo Es el código identificativo del tweet
	 */
	public Status desfavear(long codigo) throws TwitterException
	{
		return tw.destroyFavorite(codigo);
	}
	/**
	 * Permite hacer fav al tweet indicado por el código long
	 * @param codigo Es el código identificativo del tweet
	 */
	public Status favorito(long codigo) throws TwitterException
	{
		return tw.createFavorite(codigo);
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
	
	public MediaEntity[] getMediaEntities(long idTweet) throws TwitterException {
		Status tweet = tw.showStatus(idTweet);
		
		return tweet.getMediaEntities();
	}
	
	public URLEntity[] getURLEntities(long idTweet) throws TwitterException {
		Status tweet = tw.showStatus(idTweet);
		
		return tweet.getURLEntities();
	}
	
	public UserMentionEntity[] getMentionEntities(long idTweet) throws TwitterException {
		Status tweet = tw.showStatus(idTweet);
		
		return tweet.getUserMentionEntities();
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
	
	public User getUsuario(long id) throws TwitterException{
		return tw.showUser(id);
	}
	
	public User getUsuario(String screenName) throws TwitterException{
		return tw.showUser(screenName);
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
	
	public ResponseList<Status> getTimelineFromUser(String usuario, Paging paging) throws TwitterException {
		
		return tw.getUserTimeline(usuario, paging);
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
	 * Recupera el Timeline de mi perfil
	 * @return
	 * @throws TwitterException
	 */
	public ResponseList<Status> getProfileTuits() throws TwitterException {
		ResponseList<Status> list = null;

		if (tw != null) {
			list = tw.getUserTimeline();						
		}
		return list;
	}
	
	/**
	 * Recupera mis favotritos
	 * @return
	 * @throws TwitterException
	 */
	public ResponseList<Status> getFavorites() throws TwitterException {
		ResponseList<Status> list = null;

		if (tw != null) {
			list = tw.getFavorites();						
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
	
	public ResponseList<User> buscarUsuario(String busqueda, int maxPages){
		try {
            int page = 1;
            ResponseList<User> users;
            do {
                users = tw.searchUsers(busqueda, page);
                for (User user : users) {
					users.add(0, user);
                }
                page++;
            } while (users.size() != 0 && page < maxPages);
            return users;
        } catch (TwitterException e) {
            e.printStackTrace();
            Util.debug("Failed to search users: " + e.getMessage());
            return null;
        }
	}
	
	public ArrayList<Status> buscarTuit(String busqueda){
		ArrayList<Status> listaTweets = new ArrayList<Status>();
        try {
            Query query = new Query(busqueda);
            QueryResult result;
            do {
                result = tw.search(query);
                List<Status> tweets = result.getTweets();
                for (Status t : tweets) {
                	listaTweets.add(0, t);
                }
            } while ((query = result.nextQuery()) != null);
            return listaTweets;
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
            return null;
        }
	}
	
	public boolean esSeguidor(String user1, String user2) {
		try {
			return tw.showFriendship(user1, user2).isTargetFollowedBySource();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Tuitea el mensaje por par��metro
	 * @param message
	 * @throws TwitterException 
	 */
	public void tweet(StatusUpdate message) throws TwitterException {		
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

	public int getNumeroFavoritos(long id) {
        try {
			ResponseList<Status> statusList = tw.timelines().getUserTimeline(id);
			/*for (Status statusItem : statusList)
	        {
	         System.out.println("Tweet Id : " + statusItem.getId() + ", retweet count: " + statusItem.getRetweetCount());
	        } */
	         return statusList.get(0).getFavoriteCount();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			return -1;
		}

	}
	
	public static void debug(){
		try {
            Map<String, RateLimitStatus> r = tw.getRateLimitStatus();
            String[] keys = (String[]) r.keySet().toArray();
            RateLimitStatus[] rv = (RateLimitStatus[]) r.values().toArray();
            for (int i = 0; i < rv.length; i++) {
				Util.debug(keys[i]+" "+rv[i]);
			}
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get rate limit status: " + te.getMessage());
            System.exit(-1);
        }
	}
}
