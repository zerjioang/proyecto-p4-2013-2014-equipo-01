package controller;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import controller.sql.Interaccion;
import model.Tweet;
import model.Usuario;
import sun.net.www.protocol.http.HttpURLConnection;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import util.Util;
import view.ventanas.Principal;

/**
 * Clase encargada de intermediar entre la GUI y la API de Twitter
 * @author aitor
 *
 */
public class GUIController {
	// Keys de la API console
	private final static String CONSUMER_KEY = "KRiUVHsXKNRDHVIGxYJ7w";
	private final static String CONSUMER_KEY_SECRET = "BDwwg2NBUjY48OcTB2818sp7E7L32AzhNLdgt82ZVQ";
	private static final String HOST = "http://www.twitter.com";
	private static GUIController instancia = null; 

	private TwitterService t;

	@SuppressWarnings("unused")
	private boolean online;

	/* Metodos para el funcionamiento del singleton */
	public GUIController() {}

	private synchronized static void crearInstancia() {
		if (instancia == null) { 
			instancia = new GUIController();
			try {        	
				instancia.autenticar();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Devuelve la ��nica instancia de esta clase que permanece al mismo tiempo
	 * en memoria, en caso de no existir, se crea.
	 * @return
	 */
	public static GUIController getInstance() {
		crearInstancia();
		return instancia;
	}
	/* Fin de los metodos para el funcionamiento del singleton */

	/**
	 * Crea la sesi��n en Twitter y recupera de la base de datos el token en caso
	 * de que exista, si no, solo crea la sesi��n.
	 * @throws Exception
	 */
	public void autenticar() throws Exception {
		t = new TwitterService(CONSUMER_KEY, CONSUMER_KEY_SECRET);
	}

	/**
	 * Muestra el TL en el elemento de la GUI correspondiente
	 * @return 
	 */
	public ArrayList<Tweet> mostrarTimeline() {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		
		if (hayConexion()) {
			try {
				listaTL = t.getTimeline();
				for (Status each : listaTL) {
					Tweet t;
					try {
						t = new Tweet(each.getId(), each.getUser().getName(), each.getUser().getScreenName(), each.getCreatedAt() , ImageIO.read(new URL(each.getUser().getBiggerProfileImageURL())), each.getText(), each.isRetweet(), each.isFavorited());
						timeline.add(t);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i<20; i++) {
				Tweet t = new Tweet(34234, "pepepalotes", "Pepe", new Date() , new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), "Este es un tweet en modo offline", false, false);
				timeline.add(t);
			}
		}
		return timeline;
	}
	
	public ResponseList<Status> obtenerTimelineDeUsuario(String usuario, Paging paging) throws TwitterException {
		
		return t.getTimelineFromUser(usuario, paging);
	}
	
	/**
	 * Muestra las menciones en el elemento de la GUI correspondiente
	 * @return 
	 */
	public ArrayList<Tweet> mostrarMenciones() throws MalformedURLException {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		
		if (hayConexion()) {
			try {
				listaTL = t.getMentions();
				for (Status each : listaTL) {
					Tweet t;
					try {
						t = new Tweet(each.getId(), each.getUser().getName(), each.getUser().getScreenName(), each.getCreatedAt() , ImageIO.read(new URL(each.getUser().getBiggerProfileImageURL())), each.getText(), each.isRetweet(), each.isFavorited());
						timeline.add(t);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i<20; i++) {
				Tweet t = new Tweet(34234, "pepepalotes", "Pepe", new Date() , new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), "Este es un tweet en modo offline", false, false);
				timeline.add(t);
			}
		}
		return timeline;
	}
	
	/**
	 * Muestra mis retweets en el elemento de la GUI correspondiente
	 * @return 
	 */
	public ArrayList<Tweet> mostrarRetuits() {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		
		if (hayConexion()) {
			try {
				listaTL = t.getRetweetsOfMe();
				for (Status each : listaTL) {
					Tweet t;
					try {
						t = new Tweet(each.getId(), each.getUser().getName(), each.getUser().getScreenName(), each.getCreatedAt() , ImageIO.read(new URL(each.getUser().getBiggerProfileImageURL())), each.getText(), each.isRetweet(), each.isFavorited());
						timeline.add(t);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i<20; i++) {
				Tweet t = new Tweet(34234, "pepepalotes", "Pepe", new Date() , new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), "Este es un tweet en modo offline", false, false);
				timeline.add(t);
			}
		}
		return timeline;
	}
	
	/**
	 * Muestra mis tuits en el elemento de la GUI correspondiente
	 * @return 
	 */
	public ArrayList<Tweet> mostrarPerfil() {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		
		if (hayConexion()) {
			try {
				listaTL = t.getProfileTuits();
				for (Status each : listaTL) {
					Tweet t;
					try {
						t = new Tweet(each.getId(), each.getUser().getName(), each.getUser().getScreenName(), each.getCreatedAt() , ImageIO.read(new URL(each.getUser().getBiggerProfileImageURL())), each.getText(), each.isRetweet(), each.isFavorited());
						timeline.add(t);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i<20; i++) {
				Tweet t = new Tweet(34234, "pepepalotes", "Pepe", new Date() , new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), "Este es un tweet en modo offline", false, false);
				timeline.add(t);
			}
		}
		return timeline;
	}
	
	/**
	 * Muestra favoritos en el elemento de la GUI correspondiente
	 * @return 
	 */
	public ArrayList<Tweet> mostrarFavoritos() {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		
		if (hayConexion()) {
			try {
				listaTL = t.getFavorites();
				for (Status each : listaTL) {
					Tweet t;
					try {
						t = new Tweet(each.getId(), each.getUser().getName(), each.getUser().getScreenName(), each.getCreatedAt() , ImageIO.read(new URL(each.getUser().getBiggerProfileImageURL())), each.getText(), each.isRetweet(), each.isFavorited());
						timeline.add(t);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i<20; i++) {
				Tweet t = new Tweet(34234, "pepepalotes", "Pepe", new Date() , new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), "Este es un tweet en modo offline", false, false);
				timeline.add(t);
			}
		}
		return timeline;
	}
	
	public boolean marcarRetuit(long codigo) {
		try {
			t.retweetear(codigo);
			return true;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean marcarFavorito(long codigo) {
		try {
			t.favorito(codigo);
			return true;
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public void responderTuit(long codigo, String texto) {
		StatusUpdate respuesta = new StatusUpdate(texto);
		respuesta.setInReplyToStatusId(codigo);
		
		try {
			t.tweet(respuesta);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Traduce a nuestra clase modelo Usuario la clase User que maneja la API
	 * @return
	 */
	public Usuario getUsuarioRegistrado() {
		User user = null;
		Usuario u = null;
		try {
			user = t.getUsuarioRegistrado();
			
			if (hayConexion()) {
				URL urlImage = new URL(t.getUsuarioRegistrado().getBiggerProfileImageURL());
				Image imageProfile = ImageIO.read(urlImage);
				
				System.out.println(user.toString());
				u = new Usuario(user.getScreenName(),"","", user.getName(), user.getDescription(), imageProfile, user.getCreatedAt(), user.getStatusesCount(), user.getFriendsCount(), user.getFollowersCount());
			} else {
				LinkedList<Usuario> credenciales = Interaccion.extraerUsuarios();
				u = (Usuario)credenciales.getFirst();
			}
		} catch (IllegalStateException | TwitterException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}

	public boolean hayConexion() {
		@SuppressWarnings("unused")
		InetAddress address;
		System.out.println("Comprobando conexion...");
		try {
			System.out.println("Comprobado mediante cabezera HTTP...");
			URL obj = new URL(HOST);
			URLConnection conn = obj.openConnection();
		 
			//get all headers
			Map<String, List<String>> map = conn.getHeaderFields();
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + 
		                 " ,Value : " + entry.getValue());
			}
		 
			//get header by 'key'
			//String server = conn.getHeaderField("Server");
            if(map.isEmpty()){
            	System.err.println("Comprobacion de cabezera HTTP fallida");
            	 System.out.println("Comprobando mediante resolucion de Host...");
            	//try resolving host
            	address = InetAddress.getByName(HOST);
            }
			online = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Comprobacion de resolucion de Host fallida");
			online = false;
		}
		System.err.println("Conexion con twitter: "+online);
		return online;
	}

	/**
	 * Hace que se abra el navegador para que el usuario autorice a JeyTuiter
	 */
	public void solicitarCodigo() {
		try {
			t.pedirCodigo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Comprueba si el token es válido
	 * @return
	 */
	public boolean recuperarTokenUsuarioGuardado() {
		LinkedList<Usuario> credenciales = Interaccion.extraerUsuarios();
		System.out.println("El tamaño de la tabla usuarios es: "+credenciales.size());

		if (credenciales.size() > 0) {
			// Hay resultados, aunque solo esperamos una fila.
			// Asignamos el token y a otra cosa
			System.out.println("El token de la BBDD es "+credenciales.get(0).getToken());
			t.recuperarToken(credenciales.get(0).getToken(), credenciales.get(0).getTokenSecreto());

			return true;
		} else {
			System.out.println("No hay usuarios guardados.");

			return false;
		}

	}

	public void guardarUsuario(String codigo){
		try {
			AccessToken accessToken = t.crearToken(codigo);
			URL urlImage = new URL(t.getUsuarioRegistrado().getBiggerProfileImageURL());
			Image imageProfile = ImageIO.read(urlImage);

			Usuario u = new Usuario(t.getUsuarioRegistrado().getScreenName(), accessToken.getToken(), accessToken.getTokenSecret(), t.getUsuarioRegistrado().getName(), t.getUsuarioRegistrado().getDescription(), imageProfile, t.getUsuarioRegistrado().getCreatedAt(), t.getUsuarioRegistrado().getStatusesCount(), t.getUsuarioRegistrado().getFriendsCount(), t.getUsuarioRegistrado().getFollowersCount());
			// Guardar en la BBDD
			Interaccion.introducirUsuario(u);		
		} catch (TwitterException | IOException e) {
			System.out.println("Error al autenticarse");
			e.printStackTrace();
		}
	}

	/**
	 * Tuitea usando el texto de el componente de la GUI correspondiente
	 */
	public boolean enviarTweet(String texto) {
		// Se supone que recoge el texto de el textfield de turno
		try {
			StatusUpdate update = new StatusUpdate(texto);
			
			t.tweet(update);
			
			return true;
		} catch (TwitterException e) {
			// Error al tuitear, mostrar mensaje
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Muestra un men�� interactivo en la consola que permite usar ciertas funciones
	 * del cliente, solo lo usamos para depurar.
	 */
	public void menuConsola() {
		try {
			this.autenticar();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String input = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("*****JeyTwitter V0.1*****");
			System.out.println();
			System.out.println();
			System.out.println("1. Ver mi Timeline");
			//			System.out.println("2. Ver mis menciones");
			System.out.println("2. PRUEBA  FOLLOWERS");

			System.out.println("3. Ver tweets que he retwitteado");
			System.out.println("4. Ver tweets que he marcado como favoritos");
			System.out.println("5. Twittear un mensaje");
			System.out.println("");
			System.out.println("Pulsa 'q' para salir.");
			System.out.println();
			System.out.print("Seleciona una opci��n:");
			try {
				input = reader.readLine();
				switch(input) {
				case "1":mostrarTimeline();
				break;

				case "2": //System.out.println("adios");

					break;

				case "3":System.out.println("adios");
				break;

				case "4":System.out.println("adios");
				break;

				case "5":
					System.out.print("Introduce el tweet: ");
					String texto = reader.readLine();
					enviarTweet(texto);
					break;
				}

			} catch(IOException e) {
				System.out.println("Error leyendo desde System.in");
				System.exit(1);
			}
		} while (!input.equals("q"));
	}

	public static void main(String[] args) {
		// Inicio de el programa
		GUIController gui = new GUIController();
		gui.menuConsola();
	}

}
