package view.eventos.celdaTweet;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;

import util.Util;
import view.elementos.GUITweet;
import view.ventanas.VisorImagen;

public class EventoClickImagenTweet implements MouseListener {
	
	private final GUITweet g;
	public EventoClickImagenTweet(GUITweet guiTweet) {
		g = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		Component parent = g.getParent().getParent();
		try {
			VisorImagen vimg = new VisorImagen(parent, (ImageIcon)g.getImagenTweet());
			vimg.open();
		} catch (FileNotFoundException e) {
			Util.showError(parent, "Error al procesar", "No se puede mostrar la imagen correctamente", "Cancelar", "Aceptar");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//establece el borde de color gris
		g.setBordeImagenTweet(3,3,3,3,new Color(220,220,220));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//establece el borde de color blanco
		g.setBordeImagenTweet(3,3,3,3, new Color(255, 255, 255));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
