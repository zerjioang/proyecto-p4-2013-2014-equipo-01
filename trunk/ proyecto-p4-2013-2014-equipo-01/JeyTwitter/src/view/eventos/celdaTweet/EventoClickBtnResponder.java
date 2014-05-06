package view.eventos.celdaTweet;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import twitter4j.TwitterException;
import util.Util;
import view.elementos.GUITweet;
import view.ventanas.TweetRapido;

public class EventoClickBtnResponder implements MouseListener {
	
	private final GUITweet t;
	
	public EventoClickBtnResponder(GUITweet guiTweet) {
		t = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		t.getBtnResponder().setClicado(true);
		String usuario = t.getNombreUsuario();
		
		TweetRapido tr = new TweetRapido(t, usuario);
		tr.setMensaje(t.getNombreUsuario()+" ");
		tr.colocarVentana(tr, event.getXOnScreen(), event.getYOnScreen());
		tr.setVisible(true);
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
