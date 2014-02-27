package view.eventos;

import view.parents.CustomJDialog;
import view.parents.CustomJFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;


public class EventosDeBarra implements MouseListener, MouseMotionListener {

	private JFrame ventana;
	private JDialog dialogo;
	
	public EventosDeBarra(JFrame frame){
		ventana = frame;
	}
	
	public EventosDeBarra(JDialog dialog){
		dialogo = dialog;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent arg0) {}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(ventana!=null){
			if(ventana instanceof CustomJFrame){
				((CustomJFrame) ventana).setInitialClick(e.getPoint());
			}
		}
		else if(dialogo!=null){
			((CustomJDialog) dialogo).setInitialClick(e.getPoint());
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(ventana!=null){
			if(ventana instanceof CustomJFrame){

				if(((CustomJFrame) ventana).getInitialClick()!=null){
					// get location of Window
		            int thisX = ventana.getLocation().x;
		            int thisY = ventana.getLocation().y;

		            // Determine how much the mouse moved since the initial click
		            int xMoved = (thisX + e.getX()) - (thisX + ((CustomJFrame) ventana).getInitialClick().x);
		            int yMoved = (thisY + e.getY()) - (thisY + ((CustomJFrame) ventana).getInitialClick().y);

		            // Move window to this position
		            int X = thisX + xMoved;
		            int Y = thisY + yMoved;
		            ventana.setLocation(X, Y);
				}
			
			}
		}
		else if(dialogo!=null){
			if(dialogo instanceof CustomJDialog){
				if(((CustomJDialog) dialogo).getInitialClick()!=null){
					// get location of Window
		            int thisX = dialogo.getLocation().x;
		            int thisY = dialogo.getLocation().y;

		            // Determine how much the mouse moved since the initial click
		            int xMoved = (thisX + e.getX()) - (thisX + ((CustomJDialog) dialogo).getInitialClick().x);
		            int yMoved = (thisY + e.getY()) - (thisY + ((CustomJDialog) dialogo).getInitialClick().y);

		            // Move window to this position
		            int X = thisX + xMoved;
		            int Y = thisY + yMoved;
		            dialogo.setLocation(X, Y);
				}
			}
		}
	}
}
