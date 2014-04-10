package view.eventos.botonesGenericos;

/**
 * Evento que controla las acciones a realizar en los diferentes estado de un boton (normal - click - hover)
 * @author Sergio Anguita
 */
import view.elementos.botones.Button;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class EventosButton implements MouseListener {
	
	Button boton;
	
	public EventosButton(Button button) {
		boton = button;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		boton.setIcon(new ImageIcon(EventosButton.class.getResource(boton.getImagenHover())));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		boton.setIcon(new ImageIcon(EventosButton.class.getResource(boton.getImagenNormal())));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		boton.setIcon(new ImageIcon(EventosButton.class.getResource(boton.getImagenClick())));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		boton.setIcon(new ImageIcon(EventosButton.class.getResource(boton.getImagenNormal())));
	}

}
