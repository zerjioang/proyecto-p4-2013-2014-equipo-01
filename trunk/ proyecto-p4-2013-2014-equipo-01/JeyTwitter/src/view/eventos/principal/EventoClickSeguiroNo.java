package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import controller.TwitterService;
import view.elementos.botones.BotonSeguir;

public class EventoClickSeguiroNo implements MouseListener {

	private final BotonSeguir b;
	private final String id;
	public EventoClickSeguiroNo(BotonSeguir botonSeguir, String id) {
		b = botonSeguir;
		this.id = id;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Cambia el icono del boton de seguir a dejar de seguir
		if(b.isSiguiendo())
			b.setIcon(b.getImagenOff());
		else
			b.setIcon(b.getImagenOn());
		b.setSiguiendo(!b.isSiguiendo());
		
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
