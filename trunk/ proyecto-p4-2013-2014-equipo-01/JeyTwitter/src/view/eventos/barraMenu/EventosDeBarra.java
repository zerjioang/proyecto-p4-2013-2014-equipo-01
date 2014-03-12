package view.eventos.barraMenu;

import view.parents.CustomJDialog;
import view.parents.CustomJDialogWithBar;
import view.parents.CustomJFrame;
import view.ventanas.AcercaDe;
import view.ventanas.Config;

import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;


public class EventosDeBarra implements MouseListener, MouseMotionListener {

	private Window ventana;
	
	public EventosDeBarra(Window window){
		ventana = window;
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
			else if(ventana instanceof CustomJDialog)
				((CustomJDialog) ventana).setInitialClick(e.getPoint());
			else if(ventana instanceof CustomJDialogWithBar)
				((CustomJDialogWithBar) ventana).setInitialClick(e.getPoint());
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
			else if(ventana instanceof CustomJDialogWithBar | ventana instanceof AcercaDe | ventana instanceof Config){
				if(((CustomJDialogWithBar) ventana).getInitialClick()!=null){
					// get location of Window
		            int thisX = ventana.getLocation().x;
		            int thisY = ventana.getLocation().y;

		            // Determine how much the mouse moved since the initial click
		            int xMoved = (thisX + e.getX()) - (thisX + ((CustomJDialogWithBar) ventana).getInitialClick().x);
		            int yMoved = (thisY + e.getY()) - (thisY + ((CustomJDialogWithBar) ventana).getInitialClick().y);

		            // Move window to this position
		            int X = thisX + xMoved;
		            int Y = thisY + yMoved;
		            ventana.setLocation(X, Y);
				}
				
			}
		}
	}
}
