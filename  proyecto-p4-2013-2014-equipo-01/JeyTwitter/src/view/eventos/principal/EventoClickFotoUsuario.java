package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ventanas.Principal;

public class EventoClickFotoUsuario implements MouseListener {

	private final Principal v;
	
	public EventoClickFotoUsuario(Principal principal) {
		v = principal;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Click foto usuario");
		v.setPanelActual(v.getPaneles()[0]);
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
