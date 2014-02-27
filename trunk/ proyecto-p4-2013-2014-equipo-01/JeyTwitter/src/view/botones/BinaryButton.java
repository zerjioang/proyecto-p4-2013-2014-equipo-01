package view.botones;

import view.eventos.EventoClickBinaryButton;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class BinaryButton extends Button{
	
	private boolean estado;
	
	private static final String IMAGEN_ON = "/res/botones/botonOn.png";
	private static final String IMAGEN_OFF = "/res/botones/botonOff.png";
	
	public BinaryButton(){
		super();
		init();
	}

	private void init() {
		anchoBoton = 161;
		altoBoton = 85;
		
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIcon(getImagenOff());
		setSize(anchoBoton, altoBoton);
		//Listener
		addMouseListener(new EventoClickBinaryButton(this));
	}

	/**
	 * @return the imagenOn
	 */
	public  ImageIcon getImagenOn() {
		return new ImageIcon(BinaryButton.class.getResource(IMAGEN_ON));
	}

	/**
	 * @return the imagenOff
	 */
	public  ImageIcon getImagenOff() {
		return new ImageIcon(BinaryButton.class.getResource(IMAGEN_OFF));
	}

	/**
	 * @return the estado
	 */
	public boolean getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
		if(estado)
			setIcon(getImagenOn());
		else
			setIcon(getImagenOff());
	}
}
