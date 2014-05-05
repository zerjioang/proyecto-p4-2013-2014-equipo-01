package view.eventos.celdaTweet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import twitter4j.TwitterException;
import view.elementos.GUITweet;

public class EventoClickBtnReTweet implements MouseListener {

	private final GUITweet t;
	
	public EventoClickBtnReTweet(GUITweet guiTweet) {
		t = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		try {
			long codigo = t.getTweet().getCodigo();
			GUIController.getInstance().getT().retweetear(codigo);
		} catch (TwitterException e) {
			System.err.println("Ha ocurrido un error al procesar el tweet para retweetear");
			System.err.println("Detalles: "+e.getMessage());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
