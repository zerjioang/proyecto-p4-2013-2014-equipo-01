package view.eventos.celdaTweet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.elementos.GUITweet;

public class EventoClickImagenUsuario implements MouseListener {

	private final GUITweet g;
	
	public EventoClickImagenUsuario(GUITweet guiTweet) {
		g = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
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
