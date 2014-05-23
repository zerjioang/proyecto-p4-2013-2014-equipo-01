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
import view.elementos.paneles.PanelTablaTweets;
import view.models.tablasPrincipal.TablaTweetsUsuarios;
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
			p.setTextoMensajeInformativo("Actualizando favoritos...");
			p.mostrarSpinWheelInformativa(false);
			p.mostrarMensajeInformativo();
			ResponseList<Status> listaTL = t.getTimelineFromUser(usuario, paging);
			ArrayList<Tweet> timeline = new ArrayList<Tweet>();
			ArrayList<ObjetoCelda> listaTweets = new ArrayList<ObjetoCelda>();
			for (Status each : listaTL) {
				Tweet t;
				t = new Tweet(each);
				timeline.add(0,t);
				listaTweets.add(0, new GUITweet(Util.calcularFecha(t.getUltimaFechaActualizacion()), t));
			}
			PanelPerfilUsuario panel = p.getPanelUsuario();
			TablaTweetsUsuarios tabla = panel.getTablaTweetsUsuario();
			tabla.insertarLista(listaTweets);
			tabla.actualizarFilas();
			p.ocultarMensajeInformativo();
		} catch (TwitterException e) {
			// Error al recuperar el timeline
			p.setTextoMensajeInformativo("No hay conexión a internet");
			p.setColorFondoMensajeInformativo(Color.RED);
			p.setColorTextoMensajeInformativo(Color.WHITE);
			p.mostrarSpinWheelInformativa(false);
			p.mostrarMensajeInformativo();
			System.err.println("Error al recuperar el timeline "+e.getMessage());
		}
	}
}
