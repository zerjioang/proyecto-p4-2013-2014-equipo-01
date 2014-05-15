package controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import _launcher.Launcher;
import controller.sql.Interaccion;
import model.Tweet;
import model.Usuario;
import twitter4j.MediaEntity;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import twitter4j.auth.AccessToken;
import util.Util;
import view.elementos.GUITweet;
import view.elementos.GuiTwitterUsuario;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelBusqueda;
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

	private static TwitterService t;
	private boolean online;
	private boolean hayCache;
	private Principal gui;

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
	 * Devuelve la ������nica instancia de esta clase que permanece al mismo tiempo
	 * en memoria, en caso de no existir, se crea.
	 * @return
	 */
	public static GUIController getInstance() {
		crearInstancia();
		return instancia;
	}
	/* Fin de los metodos para el funcionamiento del singleton */

	/**
	 * Crea la sesi������n en Twitter y recupera de la base de datos el token en caso
	 * de que exista, si no, solo crea la sesi������n.
	 * @throws Exception
	 */
	public void autenticar() throws Exception {
		t = new TwitterService(CONSUMER_KEY, CONSUMER_KEY_SECRET);
	}

	/**
	 * Muestra el TL en el elemento de la GUI correspondiente
	 * @return 
	 */
	public ArrayList<Tweet> mostrarTimeline() throws IOException {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		
		//el tema es que la bd nos sirve de cache. asi que los tuits que tenga descargados nos los muestre y los nuevos los pida.
		//independientemente de si hay conexion o no
		if(hayCache){
			//Caso 1: carga los datos de la cache y se actualiza con los nuevos
			if(hayConexion()){
				
			}
			//caso 2: modo offline: solo carga a cache
			else{
				for (int i = 0; i<20; i++) {
					Tweet t = new Tweet(34234, "pepepalotes", "Pepe", new Date() , new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), "Este es un tweet en modo offline", false, false, null);
					timeline.add(t);
				}
			}
		}
		else{
			//caso 3: no hay cache. (primer inicio del programa) pide todos los datos a twitter
			System.out.println("RECUPERANDO TIMELINE "+hayConexion());
			if(hayConexion()){
				try {
					listaTL = t.getTimeline();
					for (Status each : listaTL) {
						Tweet t;
						t = new Tweet(each);
						timeline.add(t);
					}
				} catch (TwitterException e) {
					// Error al recuperar el timeline
					System.err.println("Error al recuperar el timeline "+e.getMessage());
				}
			}
			//caso 4: (no hay nada) muestra mensaje de error. no internet. no cache.
			else{
				Util.showError(null, "Datos no disponibles", "Se requiere conexiona Internet para iniciar", "Aceptar", "Cancelar");
				}
		}

		return timeline;
	}
	
	public ArrayList<ObjetoCelda> obtenerTimelineDeUsuario(String usuario, Paging paging) throws MalformedURLException, IOException {
		ArrayList<ObjetoCelda> objetosTweet = new ArrayList<ObjetoCelda>();
		
		try {
			ResponseList<Status> statuses = t.getTimelineFromUser(usuario, paging);
			
			for (Status each : statuses) {
				Tweet t = new Tweet(each);
				GUITweet t2 = new GUITweet(Util.calcularFecha(t.getUltimaFechaActualizacion()),t);
				
				objetosTweet.add(t2);
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		return objetosTweet;
	}
	
	/**
	 * Muestra las menciones en el elemento de la GUI correspondiente
	 * @return 
	 */
	public ArrayList<Tweet> mostrarMenciones() throws MalformedURLException, IOException {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		
		if (hayConexion()) {
			try {
				listaTL = t.getMentions();
				for (Status each : listaTL) {
					Tweet t;
					t = new Tweet(each);
					timeline.add(t);
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i<20; i++) {
				Tweet t = new Tweet(34234, "pepepalotes", "Pepe", new Date() , new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), "Este es un tweet en modo offline", false, false, null);
				timeline.add(t);
			}
		}
		return timeline;
	}
	
	/**
	 * Muestra mis retweets en el elemento de la GUI correspondiente
	 * @return 
	 */
	public ArrayList<Tweet> mostrarRetuits() throws IOException {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		
		if (hayConexion()) {
			try {
				listaTL = t.getRetweetsOfMe();
				for (Status each : listaTL) {
					Tweet t;
					t = new Tweet(each);
					timeline.add(t);
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i<20; i++) {
				Tweet t = new Tweet(34234, "pepepalotes", "Pepe", new Date() , new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), "Este es un tweet en modo offline", false, false, null);
				timeline.add(t);
			}
		}
		return timeline;
	}

	/**
	 * @param each
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	public BufferedImage pedirImagen(URL url) throws IOException {
		return ImageIO.read(url);
	}
	
	/**
	 * Muestra mis tuits en el elemento de la GUI correspondiente
	 * @return 
	 */
	public ArrayList<Tweet> mostrarPerfil() throws IOException {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		
		if (hayConexion()) {
			try {
				listaTL = t.getProfileTuits();
				for (Status each : listaTL) {
					Tweet t;
					t = new Tweet(each);
					timeline.add(t);
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i<20; i++) {
				Tweet t = new Tweet(34234, "pepepalotes", "Pepe", new Date() , new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), "Este es un tweet en modo offline", false, false, null);
				timeline.add(t);
			}
		}
		return timeline;
	}
	
	/**
	 * Muestra favoritos en el elemento de la GUI correspondiente
	 * @return 
	 */
	public ArrayList<Tweet> mostrarFavoritos() throws IOException {
		ResponseList<Status> listaTL;
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		
		if (hayConexion()) {
			try {
				listaTL = t.getFavorites();
				for (Status each : listaTL) {
					Tweet t;
					t = new Tweet(each);
					timeline.add(t);
				}
			} catch (TwitterException e) {
				// Error al recuperar el timeline
				e.printStackTrace();
			}
		} else {
			for (int i = 0; i<20; i++) {
				Tweet t = new Tweet(34234, "pepepalotes", "Pepe", new Date() , new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), "Este es un tweet en modo offline", false, false, null);
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
	/**
	 * Intenta marcar un tweet como favorito, si falla es que lo más probable ya esté marcado y lo intenta desmarcar, si vuelve a fallar no altera nada
	 * @param codigo long que indica que tweet es
	 * @return
	 */
	public boolean marcarFavorito(long codigo) {
		try {
			t.favorito(codigo);
			return true;
		} catch (TwitterException e) {
			try {
				t.desfavear(codigo);
			} catch (TwitterException e1) {
			}
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
	
	public Usuario getUsuario(long id) throws IOException {
		User user = null;
		Usuario u = null;
		try {
			user = t.getUsuario(id);
			
			if (hayConexion()) {
				System.out.println(user.toString());
				u = new Usuario(user);
			} else {
				LinkedList<Usuario> credenciales = Interaccion.extraerUsuarios();
				u = (Usuario)credenciales.getFirst();
			}
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
	
	public Usuario getUsuario(String screenName) throws IOException {
		User user = null;
		Usuario u = null;
		try {
			user = t.getUsuario(screenName);
			
			if (hayConexion()) {
				System.out.println(user.toString());
				u = new Usuario(user);
			} else {
				LinkedList<Usuario> credenciales = Interaccion.extraerUsuarios();
				u = (Usuario)credenciales.getFirst();
			}
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
	
	/**
	 * Traduce a nuestra clase modelo Usuario la clase User que maneja la API
	 * @return
	 */
	public Usuario getUsuarioRegistrado() throws IOException {
		User user = null;
		Usuario u = null;
		try {
			Launcher.mostrarMensaje("Obteniendo usuario...");
			user = t.getUsuarioRegistrado();
			
			if (hayConexion()) {
				System.out.println(user.toString());
				u = new Usuario(user);
			} else {
				LinkedList<Usuario> credenciales = Interaccion.extraerUsuarios();
				u = (Usuario)credenciales.getFirst();
			}
		} catch (IllegalStateException | TwitterException e) {
			System.err.println("Error al obtener el usuario autorizado: "+e.getMessage());
		}

		return u;
	}

	public boolean hayConexion() {
		@SuppressWarnings("unused")
		InetAddress address;
		//Util.debug("Comprobando conexion...");
		try {
			//Util.debug("Comprobado mediante cabecera HTTP...");
			URL obj = new URL(HOST);
			URLConnection conn = obj.openConnection();
		 
			//get all headers
			Map<String, List<String>> map = conn.getHeaderFields();
			/*for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " ,Value : " + entry.getValue());
			}*/
		 
			//get header by 'key'
			//String server = conn.getHeaderField("Server");
            if(map.isEmpty()){
            	//System.err.println("Comprobacion de cabezera HTTP fallida");
            	//Util.debug("Comprobando mediante resolucion de Host...");
            	//try resolving host
            	address = InetAddress.getByName(HOST);
            }
			online = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//System.err.println("Comprobacion de resolucion de Host fallida");
			online = false;
		}
		//Util.debug("Conexion con twitter: "+online);
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
	 * Comprueba si el token es v��lido
	 * @return
	 */
	public boolean recuperarTokenUsuarioGuardado() {
		LinkedList<Usuario> credenciales = Interaccion.extraerUsuarios();
		System.out.println("El tama��o de la tabla usuarios es: "+credenciales.size());
		Launcher.mostrarMensaje("Recuperando credenciales...");
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
	
	public MediaEntity[] getMedias(long idTweet) throws TwitterException {
		return t.getMediaEntities(idTweet);
	}
	
	public URLEntity[] getURLs(long idTweet) throws TwitterException {
		return t.getURLEntities(idTweet);
	}
	
	public UserMentionEntity[] getMenciones(long idTweet) throws TwitterException {
		return t.getMentionEntities(idTweet);
	}

	public void guardarUsuario(String codigo){
		try {
			AccessToken accessToken = t.crearToken(codigo);
			URL urlImage = new URL(t.getUsuarioRegistrado().getBiggerProfileImageURL());
			Image imageProfile = ImageIO.read(urlImage);

			Usuario u = new Usuario(t.getUsuarioRegistrado(), accessToken.getToken(), accessToken.getTokenSecret());
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
	 * Muestra un men������ interactivo en la consola que permite usar ciertas funciones
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
			System.out.print("Seleciona una opci������n:");
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

	/*public ArrayList<ObjetoCelda> buscarTweets(String str){
		ArrayList<ObjetoCelda> listaTweets = new ArrayList<ObjetoCelda>();
		
		List<Status> tweets = t.buscarTuit(str);
		for (Status t : tweets) {
			try {
				URL urlImagen = new URL(t.getUser().getBiggerProfileImageURL());
				Image imagen = ImageIO.read(urlImagen);
				listaTweets.add(0, new GUITweet(Util.calcularFecha(t.getCreatedAt()), new Tweet(t.getId(), t.getUser().getName(), t.getUser().getScreenName(), t.getCreatedAt(), imagen, t.getText(), t.isRetweet(), t.isFavorited())));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return listaTweets;
	}*/
	
	public ArrayList<ObjetoCelda> buscarTweets(String str) throws IOException{
		ArrayList<ObjetoCelda> listaTweets = new ArrayList<ObjetoCelda>();
		 Util.debug("iniciando busqueda de tweets...");
		List<Status> tweets = t.buscarTuit(str);
		 Util.debug("mostrando tweets...");
		for (Status t : tweets) {
			listaTweets.add(0, new GUITweet(Util.calcularFecha(t.getCreatedAt()), new Tweet(t)));
			actualizarPanelBusqueda(listaTweets);
		}
		Util.debug("busqueda finalizada.");
		return listaTweets;
	}
	
	public ArrayList<ObjetoCelda> buscarUsuarios(String str, int maxPages) {
        ArrayList<ObjetoCelda> usuarios = new ArrayList<ObjetoCelda>();
        Util.debug("iniciando busqueda de usuarios...");
        
        ArrayList<User> users = t.buscarUsuario(str, maxPages);
        for (User user : users) {
        	ImageIcon i;
        	URL urlImage;
			try {
				urlImage = new URL(user.getBiggerProfileImageURL());
				Image imageProfile = ImageIO.read(urlImage);
				i = new ImageIcon(imageProfile);
				boolean isfriend = isAmigo(t.getUsuarioRegistrado().getScreenName(), user.getScreenName());
				usuarios.add(0, new GuiTwitterUsuario(user.getName(), user.getScreenName(), i, user.getDescription(), isfriend));
				actualizarPanelBusqueda(usuarios);
			} catch (IOException | IllegalStateException | TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        return usuarios;
	}
	
	/**
	 * @param users
	 */
	private void actualizarPanelBusqueda(ArrayList<ObjetoCelda> users) {
		PanelBusqueda pb = new PanelBusqueda(users);
		gui.setPanelBusqueda(pb);
	}

	public boolean isAmigo(String user1, String user2) {
		return t.esSeguidor(user1, user2);
	}

	public int getNumeroFavoritos(long user) {
		return t.getNumeroFavoritos(user);
	}
	
	public void iniciarStreaming(){
		t.iniciarStreaming();
	}

	/**
	 * @return the gui
	 */
	public Principal getGui() {
		return gui;
	}

	/**
	 * @param gui the gui to set
	 */
	public void setGui(Principal gui) {
		this.gui = gui;
	}
}
