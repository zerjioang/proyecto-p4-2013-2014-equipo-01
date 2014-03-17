package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.elementos.botones.BotonSeguir;

public class EventoClickSeguiroNo implements MouseListener {

	private final BotonSeguir b;
	public EventoClickSeguiroNo(BotonSeguir botonSeguir) {
		b = botonSeguir;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Cambia el icono del boton de seguir a dejar de seguir
		if(b.getEstado())
			b.setIcon(b.getImagenOff());
		else
			b.setIcon(b.getImagenOn());
		b.setEstado(!b.getEstado());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
