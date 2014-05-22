package hilos;

import java.awt.Color;
import java.util.ArrayList;

import model.Tweet;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import util.Util;
import view.elementos.GUITweet;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelTablaTweets;
import view.models.tablasPrincipal.TablaTweetsUsuarios;
import view.ventanas.Principal;
import controller.GUIController;
import controller.TwitterService;

public class HiloMenciones extends Thread {
	
	private TwitterService t;

	public HiloMenciones(TwitterService t) {
		this.t = t;
	}

	public void run(){
		Principal p = GUIController.getInstance().getGui();
		try {
			p.setTextoMensajeInformativo("Actualizando menciones...");
			p.mostrarSpinWheelInformativa(false);
			p.mostrarMensajeInformativo();
			ResponseList<Status> listaTL = t.getMentions();
			ArrayList<Tweet> timeline = new ArrayList<Tweet>();
			ArrayList<ObjetoCelda> listaTweets = new ArrayList<ObjetoCelda>();
			for (Status each : listaTL) {
				Tweet t;
				t = new Tweet(each);
				timeline.add(t);
				listaTweets.add(new GUITweet(Util.calcularFecha(t.getUltimaFechaActualizacion()), t));
			}
			PanelTablaTweets panel = p.getPanelMenciones();
			TablaTweetsUsuarios tabla = panel.getTabla();
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
