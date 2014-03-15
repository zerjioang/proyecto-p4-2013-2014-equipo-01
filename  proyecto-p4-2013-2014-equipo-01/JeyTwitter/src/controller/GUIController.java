package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;

import model.SQLiteManager;

import com.sun.jndi.ldap.ManageReferralControl;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
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
	private static GUIController instance = null; 
	
	private TwitterService t;
	private String token;
	
	/* Metodos para el funcionamiento del singleton */
	public GUIController() {}
	
	private synchronized static void createInstance() {
        if (instance == null) { 
            instance = new GUIController();
            try {
				instance.authenticate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
	
	public static GUIController getInstance() {
		createInstance();
		return instance;
	}
	/* Fin de los metodos para el funcionamiento del singleton */
	
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
	
	private void authenticate() throws Exception {
		t = new TwitterService(CONSUMER_KEY, CONSUMER_KEY_SECRET);
		/*
		// Recuperacion del token de la BBDD
		ResultSet result = SQLiteManager.getInstance().select("SELECT code FROM credential");
		if (result.first()) {
			// Hay resultados, aunque solo esperamos una fila.
			// Asignamos el token y a otra cosa
			token = result.getString(0);
			setPin(token);
		}
		*/
		if (token == null) {
			// No esta registrado el usuario en la BBDD
			try {
				t.requestToken();
			} catch (Exception e) {
				// Error al recuperar el token
				Util.debug("Error al recuperar el token: "+e.getMessage());
				throw e;
			}
		}
	}
	
	public boolean hasValidToken() {
		if (token.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	public void setPin(String pin) throws TwitterException {
		t.setAccessToken(pin);
		showTimeline();
	}
	
	/**
	 * Tuitea usando el texto de el componente de la GUI correspondiente
	 */
	public void sendTweet(String texto) {
		// Se supone que recoge el texto de el textfield de turno
		try {
			t.tweet(texto);
		} catch (TwitterException e) {
			// Error al tuitear, mostrar mensaje
			e.printStackTrace();
		}
	}
	
	public void menuConsole() {
		try {
			this.authenticate();
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
			System.out.print("Seleciona una opción:");
			try {
				input = reader.readLine();
				switch(input) {
					case "1":showTimeline();
					break;
					
					case "2": //System.out.println("adios");
						
						long cursor =  -1;
						try{
						t.friends("_somega", cursor);
						}catch(Exception e){
							System.out.println("Este mensaje esta en el catch del GUIcontroller. Tu código es una mierda, Sergio Ruiz :'( ");
						}
					break;
					
					case "3":System.out.println("adios");
					break;
					
					case "4":System.out.println("adios");
					break;
					
					case "5":
						System.out.print("Introduce el tweet: ");
						String texto = reader.readLine();
						sendTweet(texto);
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
		gui.menuConsole();
	}

}
