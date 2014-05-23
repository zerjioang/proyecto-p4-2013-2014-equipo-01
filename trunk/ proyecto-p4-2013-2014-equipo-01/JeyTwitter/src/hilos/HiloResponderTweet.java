package hilos;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;

import model.Tweet;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import util.Util;
import view.elementos.GUITweet;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelEnviarTweet;
import view.elementos.paneles.PanelPerfilUsuario;
import view.elementos.paneles.PanelTablaTweets;
import view.models.tablasPrincipal.TablaTweetsUsuarios;
import view.ventanas.Principal;
import view.ventanas.TweetRapido;
import controller.GUIController;
import controller.TwitterService;

public class HiloResponderTweet extends Thread {
	
	private TweetRapido tuit;

	public HiloResponderTweet(TweetRapido fastTuit) {
		tuit = fastTuit;
	}

	public void run(){
		GUIController.getInstance().responderTuit(tuit.getTuit().getCodigo(), tuit.getMensaje());
		tuit.dispose();
	}
}
