package view.eventos.barraMenu;

/**
 * Evento que controla la accion a realizar cuando el usuario clica en el boton maximizar de la barra superior
 * @author Sergio Anguita
 */
import view.parents.CustomJFrame;
import view.parents.Moveable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventoClickMaximizar implements MouseListener {
	
	private final Moveable ventana;

	public EventoClickMaximizar(Moveable ventana) {
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
