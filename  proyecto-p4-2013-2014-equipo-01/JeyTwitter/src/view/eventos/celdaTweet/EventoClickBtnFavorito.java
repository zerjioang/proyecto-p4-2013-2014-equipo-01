package view.eventos.celdaTweet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import twitter4j.TwitterException;
import view.elementos.GUITweet;

public class EventoClickBtnFavorito implements MouseListener {

	private final GUITweet t;
	
	public EventoClickBtnFavorito(GUITweet guiTweet) {
		t = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		try {
			long codigo = t.getTweet().getCodigo();
			GUIController.getInstance().getT().favorito(codigo);
		} catch (TwitterException e) {
			System.err.println("Ha ocurrido un error al procesar el tweet para favorito");
			System.err.println("Detalles: "+e.getMessage());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
