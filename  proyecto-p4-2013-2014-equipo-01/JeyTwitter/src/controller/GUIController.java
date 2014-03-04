package controller;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import view.ventanas.Welcome;

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
	private Welcome welcomeWindow;
	
	public GUIController() {
		t = new TwitterService(CONSUMER_KEY, CONSUMER_KEY_SECRET);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Welcome welcomeWindow = new Welcome();
					//welcomeWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
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
		String input = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("*****JeyTwitter V0.1*****");
			System.out.println();
			System.out.println();
			System.out.println("1. Ver mi Timeline");
			System.out.println("2. Ver mis menciones");
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
					
					case "2":System.out.println("adios");
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
