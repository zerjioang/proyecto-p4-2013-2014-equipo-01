package view.elementos.botones;

import javax.swing.ImageIcon;

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
		setEnabled(true);
		setImagenClick("/res/images/principal/botonEnviarTweet_pressed.png");
		setImagenHover("/res/images/principal/botonEnviarTweet_hover.png");
		setImagenNormal("/res/images/principal/botonEnviarTweet_normal.png");
		setNormal();
	}
	
	
}
