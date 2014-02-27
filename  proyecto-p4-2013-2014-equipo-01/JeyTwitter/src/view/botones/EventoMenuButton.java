package view.botones;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
