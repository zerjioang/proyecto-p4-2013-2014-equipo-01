package view.eventos.welcome;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.parents.CustomJFrame;
import view.ventanas.Principal;

public class EventoWelcomeContinuar implements MouseListener {

	private CustomJFrame ventana;
	public EventoWelcomeContinuar(CustomJFrame welcome) {
		ventana = welcome;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Principal p = new Principal();
		p.setLocationRelativeTo(ventana);
		p.setVisible(true);
		ventana.cerrar();
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
