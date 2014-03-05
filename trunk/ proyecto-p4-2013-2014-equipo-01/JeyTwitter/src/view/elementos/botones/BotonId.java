package view.elementos.botones;

import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import view.eventos.EventosButton;
import view.ventanas.MensajeWindow;

public class BotonId extends Button {
	
	private String imagenNormal, imagenHover;

	public BotonId(String normal, String hover){
		imagenHover = hover;
		imagenNormal = normal;
		init();
	}
	
	public void init(){
		anchoBoton = 250;
		altoBoton = 50;
		
		//Caracteristicas del boton
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setSize(anchoBoton, altoBoton);
		setHorizontalAlignment(SwingConstants.CENTER);
		
		//se definen las imagenes de cada estado del boton
		setImagenClick(imagenNormal);
		setImagenHover(imagenHover);
		setImagenNormal(imagenNormal);
		
		setIcon(new ImageIcon(MensajeWindow.class.getResource(getImagenNormal())));
		//Se le añade el listener que controlara las imagenes dependiendo del estado del raton
		addMouseListener(new EventosButton(this));
	}

}
