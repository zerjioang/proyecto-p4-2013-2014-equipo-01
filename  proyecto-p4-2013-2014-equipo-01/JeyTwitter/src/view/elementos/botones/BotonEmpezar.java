package view.elementos.botones;

import javax.swing.ImageIcon;

import view.ventanas.MensajeWindow;

public class BotonEmpezar extends BotonUI{
	
	public BotonEmpezar(){
		super();
		init();
	}

	/**
	 * Inicializa el contenido
	 */
	private void init() {
		//se definen las imagenes de cada estado del boton
		setImagenClick("/res/botones/botonUI_EmpezarClick.png");
		setImagenHover("/res/botones/botonUI_EmpezarHover.png");
		setImagenNormal("/res/botones/botonUI_EmpezarNormal.png");
		
		setIcon(new ImageIcon(MensajeWindow.class.getResource(getImagenNormal())));
	}
	
	public void setVisible(boolean b){
		if(b){
			setImagenNormal("/res/botones/botonUI_EmpezarNormal.png");
		}
		else{
			setImagenNormal(null);
		}
		super.setVisible(b);
	}
}
