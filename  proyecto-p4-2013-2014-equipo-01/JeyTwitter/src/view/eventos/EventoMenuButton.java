package view.eventos;
/**
 * Evento que controla las acciones a realizar en un objeto 'Boton del menu'
 * @author Sergio Anguita
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.elementos.botones.MenuButton;

public class EventoMenuButton implements MouseListener {

	private MenuButton boton;
	
	public EventoMenuButton(MenuButton menuButton) {
		boton = menuButton;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("click");
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
