package view.eventos.barraMenu;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import view.parents.CustomJDialogWithBar;
import view.parents.CustomJFrame;


public class EventoClickMinimizar implements MouseListener {
	
	private final Window ventana;

	public EventoClickMinimizar(Window ventana) {
		this.ventana = ventana;
	}

	@Override
	public void mouseClicked(MouseEvent arg0){
		Point currentLocation = ventana.getLocation();
		if(ventana instanceof CustomJFrame)
			((CustomJFrame)ventana).setLastPosition(currentLocation);
		else if(ventana instanceof CustomJDialogWithBar)
			((CustomJDialogWithBar)ventana).setLastPosition(currentLocation);

		if(ventana instanceof CustomJFrame){
			
			Dimension lowerBorder = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			Float op = 1.0f;
			for (int i = currentLocation.y; i < lowerBorder.height+ventana.getHeight(); i++) {
				currentLocation = new Point(currentLocation.x, i);
				op = op - 0.001f;
				if(op > 0f)
					ventana.setOpacity(op);
				ventana.setLocation(currentLocation);
			}
			
			ventana.setLocation(((CustomJFrame) ventana).getLastPosition());
			((Frame) ventana).setExtendedState(JFrame.ICONIFIED);
		}
		else if(ventana instanceof CustomJDialogWithBar){
			ventana.setLocation(((CustomJDialogWithBar) ventana).getLastPosition());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(ventana instanceof CustomJFrame)
			((CustomJFrame) ventana).setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/minimizarHover.png")));
		else if(ventana instanceof CustomJDialogWithBar)
			((CustomJDialogWithBar) ventana).setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/minimizarHover.png")));	
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if(ventana instanceof CustomJFrame)
			((CustomJFrame) ventana).setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/botonesNormales.png")));
		else if(ventana instanceof CustomJDialogWithBar)
			((CustomJDialogWithBar) ventana).setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/botonesNormales.png")));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(ventana instanceof CustomJFrame)
			((CustomJFrame) ventana).setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/minimizarClick.png")));
		else if(ventana instanceof CustomJDialogWithBar)
			((CustomJDialogWithBar) ventana).setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/minimizarClick.png")));	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(ventana instanceof CustomJFrame)
			((CustomJFrame) ventana).setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/botonesNormales.png")));
		else if(ventana instanceof CustomJDialogWithBar)
			((CustomJDialogWithBar) ventana).setImagenIconos(new ImageIcon(EventoClickMaximizar.class.getResource("/res/images/botonera/botonesNormales.png")));	
	}

}
