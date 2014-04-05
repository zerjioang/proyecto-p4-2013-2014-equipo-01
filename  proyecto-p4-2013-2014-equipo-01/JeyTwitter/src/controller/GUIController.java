package controller;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import controller.sql.Interaccion;
import model.Tweet;
import model.Usuario;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;

/**
 * Clase encargada de intermediar entre la GUI y la API de Twitter
 * @author aitor
 *
 */
public class GUIController {
	// Keys de la API console
	private final static String CONSUMER_KEY = "KRiUVHsXKNRDHVIGxYJ7w";
	private final static String CONSUMER_KEY_SECRET = "BDwwg2NBUjY48OcTB2818sp7E7L32AzhNLdgt82ZVQ";
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

		try {
			listaTL = t.getTimeline();
			for (Status each : listaTL) {
				Tweet t = new Tweet(each.getId(), each.getUser().getName(), each.getUser().getScreenName(), each.getCreatedAt() , each.getUser().getOriginalProfileImageURL(), each.getText(), each.isRetweet(), each.isFavorited());
				timeline.add(t);
			}
		} catch (TwitterException e) {
			// Error al recuperar el timeline
			e.printStackTrace();
		}
		return timeline;
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

			URL urlImage = new URL(t.getUsuarioRegistrado().getBiggerProfileImageURL());
			Image imageProfile = ImageIO.read(urlImage);

			System.out.println(user.toString());
			u = new Usuario(user.getScreenName(),"","", user.getName(), user.getDescription(), imageProfile, user.getCreatedAt(), user.getStatusesCount(), user.getFriendsCount(), user.getFollowersCount());
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
			address = InetAddress.getByName("www.twitter.com");
			System.out.println("Comprobado. Esperando resultado...");
			online = true;
			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			online = false;
			e.printStackTrace();
			return false;
		} 
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
		LinkedList<Usuario> credenciales = Interaccion.extraerCredenciales();
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
			t.tweet(texto);
			
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
