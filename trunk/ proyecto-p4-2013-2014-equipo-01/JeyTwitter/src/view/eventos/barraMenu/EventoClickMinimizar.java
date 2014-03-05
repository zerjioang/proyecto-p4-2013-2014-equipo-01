package view.eventos.barraMenu;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import view.parents.CustomJFrame;


public class EventoClickMinimizar implements MouseListener {
	
	private final CustomJFrame ventana;

	public EventoClickMinimizar(CustomJFrame ventana) {
		this.ventana = ventana;
	}

	@Override
	public void mouseClicked(MouseEvent arg0){
		Point currentLocation = ventana.getLocation();
		((CustomJFrame)ventana).setLastPosition(currentLocation);
		Dimension lowerBorder = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Float op = 1.0f;
		for (int i = currentLocation.y; i < lowerBorder.height+ventana.getHeight(); i++) {
			currentLocation = new Point(currentLocation.x, i);
			op = op - 0.001f;
			if(op > 0f)
				ventana.setOpacity(op);
			ventana.setLocation(currentLocation);
		}
		ventana.setExtendedState(JFrame.ICONIFIED);
		ventana.setLocation(ventana.getLastPosition());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		ventana.setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/minimizarHover.png")));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		ventana.setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/botonesNormales.png")));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		ventana.setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/minimizarClick.png")));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		ventana.setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/botonesNormales.png")));
	}

}
