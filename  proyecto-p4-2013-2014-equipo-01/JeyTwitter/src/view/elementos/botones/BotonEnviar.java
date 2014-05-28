package view.elementos.botones;


@SuppressWarnings("serial")
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
		setImagenClick("/res/images/botonEnviarTweet_pressed.png");
		setImagenHover("/res/images/botonEnviarTweet_hover.png");
		setImagenNormal("/res/images/botonEnviarTweet_normal.png");
		setNormal();
	}
}
