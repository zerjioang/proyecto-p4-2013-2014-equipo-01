package view.eventos;

import view.parents.CustomJFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventoClickMaximizar implements MouseListener {
	
	private final CustomJFrame ventana;

	public EventoClickMaximizar(CustomJFrame ventana) {
		this.ventana = ventana;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
