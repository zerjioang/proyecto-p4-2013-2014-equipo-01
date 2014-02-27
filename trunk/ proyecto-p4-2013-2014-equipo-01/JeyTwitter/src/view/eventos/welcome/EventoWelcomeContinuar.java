package view.eventos.welcome;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import util.InvalidInputException;
import util.Util;
import view.parents.CustomJFrame;

public class EventoWelcomeContinuar implements MouseListener {

	private CustomJFrame ventana;
	public EventoWelcomeContinuar(CustomJFrame welcome) {
		ventana = welcome;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		try {
			Util.showMessage(ventana, "Ventana principal", "Ahora se deberia abrir la ventana principal", "Lo pillo", "Atras");
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
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
