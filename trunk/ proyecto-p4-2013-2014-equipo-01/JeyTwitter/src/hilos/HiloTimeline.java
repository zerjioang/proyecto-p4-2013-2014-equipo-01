package hilos;

import java.awt.Color;
import java.util.ArrayList;

import model.Tweet;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import view.ventanas.Principal;
import controller.GUIController;
import controller.TwitterService;

public class HiloTimeline extends Thread {
	
	private TwitterService t;

	public HiloTimeline(TwitterService t) {
		this.t = t;
	}

	public void run(){
		Principal p = GUIController.getInstance().getGui();
		try {
			p.setTextoMensajeInformativo("Actualizando timeline...");
			p.mostrarSpinWheelInformativa(false);
			p.mostrarMensajeInformativo();
			ResponseList<Status> listaTL = t.getTimeline();
			ArrayList<Tweet> timeline = new ArrayList<Tweet>();
			for (Status each : listaTL) {
				Tweet t;
				t = new Tweet(each);
				timeline.add(t);
			}
			new HiloGuardarCache(timeline).start();
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
