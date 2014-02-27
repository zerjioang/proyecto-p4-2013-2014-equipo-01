package view.botones;

import javax.swing.JLabel;

import view.eventos.EventosButton;

public class Button extends JLabel{

	protected int anchoBoton;
	protected  int altoBoton;
	
	private String imagenNormal, imagenClick, imagenHover;
	
	public Button(String texto) {
		super(texto);
	}

	public Button() {
		super();
		addMouseListener(new EventosButton(this));
	}

	public String getImagenNormal() {
		return imagenNormal;
	}

	public void setImagenNormal(String imagenNormal) {
		this.imagenNormal = imagenNormal;
	}

	public String getImagenClick() {
		return imagenClick;
	}

	public void setImagenClick(String imagenClick) {
		this.imagenClick = imagenClick;
	}

	public String getImagenHover() {
		return imagenHover;
	}

	public void setImagenHover(String imagenHover) {
		this.imagenHover = imagenHover;
	}
}
