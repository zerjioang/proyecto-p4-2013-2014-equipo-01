package hilos;

import java.awt.Color;
import java.util.ArrayList;

import model.Tweet;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import util.Util;
import view.elementos.GUITweet;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelPerfilUsuario;
import view.ventanas.Principal;
import controller.GUIController;
import controller.TwitterService;

public class HiloTimelineUsuario extends Thread {
	
	private TwitterService t;
	private String usuario;
	private Paging paging;
	private PanelPerfilUsuario panelPerfilUsuario;

	public HiloTimelineUsuario(TwitterService t, String usuario, Paging paging, PanelPerfilUsuario panelPerfilUsuario) {
		this.t = t;
		this.paging = paging;
		this.usuario = usuario;
		this.panelPerfilUsuario = panelPerfilUsuario;
	}

	public void run(){
		Principal p = GUIController.getInstance().getGui();
		try {
			p.setTextoMensajeInformativo("Actualizando timeline...");
			p.mostrarSpinWheelInformativa(false);
			p.mostrarMensajeInformativo();
			ResponseList<Status> listaTL = t.getTimelineFromUser(usuario, paging);
			ArrayList<Tweet> timeline = new ArrayList<Tweet>();
			for (Status each : listaTL) {
				Tweet t;
				t = new Tweet(each);
				timeline.add(t);
			}
			
		} catch (TwitterException e) {
			// Error al recuperar el timeline
			p.setTextoMensajeInformativo("No hay conexi��n a internet");
			p.setColorFondoMensajeInformativo(Color.RED);
			p.setColorTextoMensajeInformativo(Color.WHITE);
			p.mostrarSpinWheelInformativa(false);
			p.mostrarMensajeInformativo();
			System.err.println("Error al recuperar el timeline "+e.getMessage());
		}
	}
}
