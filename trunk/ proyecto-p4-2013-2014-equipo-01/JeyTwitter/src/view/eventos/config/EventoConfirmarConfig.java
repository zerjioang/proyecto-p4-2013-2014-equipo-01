package view.eventos.config;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ventanas.Config;

public class EventoConfirmarConfig implements MouseListener {

	private final Config c;
	
	public EventoConfirmarConfig(Config config) {
		c = config;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Añadir aqui el codigo del boton aceptar
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
