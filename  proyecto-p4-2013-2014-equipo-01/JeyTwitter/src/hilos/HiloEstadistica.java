package hilos;

import java.util.ArrayList;

import model.Tweet;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import view.ventanas.Principal;
import controller.GUIController;
import controller.TwitterService;

public class HiloEstadistica extends Thread {
	
	private TwitterService t;

	public HiloEstadistica(TwitterService t) {
		this.t = t;
	}

	public void run(){
		try {
			Principal p = GUIController.getInstance().getGui();
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
			System.err.println("Error al recuperar el timeline "+e.getMessage());
		}
	}
}
