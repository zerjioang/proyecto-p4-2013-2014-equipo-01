package controller;

import hilos.HiloFavoritos;
import hilos.HiloMenciones;
import hilos.HiloPerfil;
import hilos.HiloRetuits;
import hilos.HiloTimeline;
import hilos.HiloTimelineUsuario;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

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
import view.elementos.paneles.PanelPerfilUsuario;
import view.elementos.paneles.PanelTablaTweets;
import view.models.tablasPrincipal.TablaTweetsUsuarios;
import view.ventanas.Principal;
import _launcher.Launcher;
import controller.sql.Interaccion;


public class GUIController {
	// Keys de la API console
	private final static String CONSUMER_KEY = "KRiUVHsXKNRDHVIGxYJ7w";
	private final static String CONSUMER_KEY_SECRET = "BDwwg2NBUjY48OcTB2818sp7E7L32AzhNLdgt82ZVQ";
	private static final String HOST = "http://www.twitter.com";
	private static GUIController instancia = null; 

	private static TwitterService t;
	private boolean online;
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

	
	public static GUIController getInstance() {
		crearInstancia();
		return instancia;
	}
	/* Fin de los metodos para el funcionamiento del singleton */

	
	public void autenticar() throws Exception {
		t = new TwitterService(CONSUMER_KEY, CONSUMER_KEY_SECRET);
	}

	
	public ArrayList<Tweet> mostrarTimeline() throws IOException {
		ArrayList<Tweet> timeline = new ArrayList<Tweet>();
		
		timeline = Interaccion.extraerTweets(getUsuarioRegistrado().getNombreUsuario());
		
		if (hayConexion()) {
			new HiloTimeline(t).start();		
		}
		
		return timeline;
	}
	
	public void obtenerTimelineDeUsuario(String usuario, Paging paging, PanelPerfilUsuario panelPerfilUsuario) throws MalformedURLException, IOException {
		if (hayConexion()) {
			new HiloTimelineUsuario(t, usuario, paging, panelPerfilUsuario).start();
		}
	}
	
	
	public void mostrarMenciones() throws MalformedURLException, IOException {
		if (hayConexion()) {
			new HiloMenciones(t).start();
		}
	}
	
	public void guardarCache(ArrayList<Tweet> tl) throws IOException {
		Interaccion.insertarTweets(tl, getUsuarioRegistrado().getNombreUsuario(), "png");
	}
	
	public void mostrarRetuits() throws IOException {
		if (hayConexion()) {
			new HiloRetuits(t).start();
		}
	}

	
	public BufferedImage pedirImagen(URL url) throws IOException {
		return ImageIO.read(url);
	}
	
	
	public void mostrarPerfil() throws IOException {
		if (hayConexion()) {
			new HiloPerfil(t).start();
		}
	}
	
	
	public void mostrarFavoritos() throws IOException {
		if (hayConexion()) {
			new HiloFavoritos(t).start();
		}
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
				u = new Usuario(user);
			} else {
				ArrayList<Usuario> credenciales = Interaccion.extraerUsuarios();
				u = (Usuario)credenciales.get(0);
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
				u = new Usuario(user);
			} else {
				ArrayList<Usuario> credenciales = Interaccion.extraerUsuarios();
				u = (Usuario)credenciales.get(0);
			}
		} catch (IllegalStateException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return u;
	}
	
	
	public Usuario getUsuarioRegistrado() throws IOException {
		User user = null;
		Usuario u = null;
		try {
			Launcher.mostrarMensaje("Obteniendo usuario...");
			user = t.getUsuarioRegistrado();
			
			if (hayConexion()) {
				u = new Usuario(user);
			} else {
				ArrayList<Usuario> credenciales = Interaccion.extraerUsuarios();
				u = (Usuario)credenciales.get(0);
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

	
	public void solicitarCodigo() {
		try {
			t.pedirCodigo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public boolean recuperarTokenUsuarioGuardado() {
		ArrayList<Usuario> usuariosAlmacenados = Interaccion.extraerUsuarios();
		System.out.println("El tama��o de la tabla usuarios es: "+usuariosAlmacenados.size());
		Launcher.mostrarMensaje("Recuperando credenciales...");
		if (usuariosAlmacenados.size() > 0) {
			// Hay resultados, aunque solo esperamos una fila.
			// Asignamos el token y a otra cosa
			System.out.println("El token de la BBDD es "+usuariosAlmacenados.get(0).getToken());
			t.recuperarToken(usuariosAlmacenados.get(0).getToken(), usuariosAlmacenados.get(0).getTokenSecreto());

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

			Usuario u = new Usuario(t.getUsuarioRegistrado(), accessToken.getToken(), accessToken.getTokenSecret());
			// Guardar en la BBDD
			Interaccion.introducirUsuario(u);		
		} catch (TwitterException e) {
			System.out.println("Error al autenticarse");
			e.printStackTrace();
		}
	}

	
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
	
	
	private void actualizarPanelBusqueda(ArrayList<ObjetoCelda> users) {
		PanelBusqueda pb = new PanelBusqueda(users);
		gui.setPanelBusqueda(pb);
	}

	public boolean isAmigo(String user1, String user2) {
		return t.esSeguidor(user1, user2);
	}
	
	public void iniciarStreaming(){
		t.iniciarStreaming();
	}
	
	public void seguirUsuario(String nombreUsuario) throws TwitterException {
		t.seguirUsuario(nombreUsuario);
	}
	
	public void dejarDeSeguirUsuario(String nombreUsuario) throws TwitterException {
		t.dejarDeSeguirUsuario(nombreUsuario);
	}

	
	public Principal getGui() {
		return gui;
	}

	
	public void setGui(Principal gui) {
		this.gui = gui;
	}
}
