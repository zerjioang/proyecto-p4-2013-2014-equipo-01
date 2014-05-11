package view.eventos.welcome;

import hilos.HiloCargarDatos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import util.Util;
import view.ventanas.Principal;
import view.ventanas.Bienvenida;

public class EventoWelcomeContinuar implements MouseListener {

	private Bienvenida ventana;
	public EventoWelcomeContinuar(Bienvenida welcome) {
		ventana = welcome;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		new HiloCargarDatos(ventana).start();
		ventana.getLblOK().setEnabled(false);
		//ventana.getLblOK().setVisible(false);
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
