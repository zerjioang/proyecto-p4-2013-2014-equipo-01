package view.elementos.botones;

import javax.swing.ImageIcon;

import view.ventanas.MensajeWindow;

public class BotonEnviar extends BotonUI{
	
	public BotonEnviar(){
		super();
		init();
	}

	/**
	 * Inicializa el contenido
	 */
	private void init() {
		//se definen las imagenes de cada estado del boton
		setImagenClick("/res/images/principal/botonEnviarTweet_pressed.png");
		setImagenHover("/res/images/principal/botonEnviarTweet_hover.png");
		setImagenNormal("/res/images/principal/botonEnviarTweet_normal.png");
		
		setIcon(new ImageIcon(MensajeWindow.class.getResource(getImagenNormal())));
	}
	
	
}
