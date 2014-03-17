package view.elementos.botones;

import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.eventos.principal.EventoClickSeguiroNo;

/**
* Clase que simula un boton de dos posiciones. El boton puede estar encendido o apagado.
* Ideal para operaciones binarias.
* @author Sergio Anguita
*/
public class BotonSeguir extends JLabel{
	
	private boolean estado;
	
	private static final String IMAGEN_SEGUIR = "/res/botones/seguir/followingIcon.png";
	private static final String IMAGEN_DEJAR_SEGUIR = "/res/botones/seguir/UnfollowIcon.png";
	
	public BotonSeguir(){
		super();
		init();
	}
	/**
	 * Inicializa el contenido
	 */
	private void init() {
		int anchoBoton = 161;
		int altoBoton = 85;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setIcon(getImagenOff());
		setSize(anchoBoton, altoBoton);
		//Listener
		addMouseListener(new EventoClickSeguiroNo(this));
	}

	/**
	 * @return devuelve la imagen asignada al estado ON
	 */
	public  ImageIcon getImagenOn() {
		return new ImageIcon(BotonSeguir.class.getResource(IMAGEN_SEGUIR));
	}

	/**
	 * @return devuelve la imagen asignada al estado OFF
	 */
	public  ImageIcon getImagenOff() {
		return new ImageIcon(BotonSeguir.class.getResource(IMAGEN_DEJAR_SEGUIR));
	}

	/**
	 * @return devuelve el estado actual del boton: TRUE - FALSE, ON - OFF
	 */
	public boolean getEstado() {
		return estado;
	}

	/**
	 * @param establece el estado del boton
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
		if(estado)
			setIcon(getImagenOn());
		else
			setIcon(getImagenOff());
	}
}
