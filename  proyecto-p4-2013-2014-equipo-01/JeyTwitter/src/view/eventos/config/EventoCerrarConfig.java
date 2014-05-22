package view.eventos.config;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ventanas.Config;

public class EventoCerrarConfig implements MouseListener {

	private final Config c;
	
	public EventoCerrarConfig(Config config) {
		c = config;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		c.cerrar();
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
