package view.eventos.barraMenu;

import view.parents.CustomJDialogWithBar;
import view.parents.CustomJFrame;

import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import util.Util;
/**
 * Evento que controla la accion a realizar cuando el usuario clica en el boton cerrar de la barra superior
 * @author Sergio Anguita
 */

public class EventoClickCerrar implements MouseListener {

	private Window parent;
	
	public EventoClickCerrar(Window parent){
		this.parent = parent;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		boolean dispose = false;
		if(parent instanceof CustomJFrame)
			dispose = ((CustomJFrame) parent).isDisposeWindow();
		else if(parent instanceof CustomJDialogWithBar)
			dispose = ((CustomJDialogWithBar) parent).isDisposeWindow();
		if(dispose){
			Util.ocultarImagenDifuso(parent);
			parent.dispose();
		}
		else{
			try {
				boolean respPositiva = Util.showMessage(parent, "Cerrar","Desea realmente cerrar "+Util.APP_TITULO+"?", "Cerrar", "Cancelar");
				if(respPositiva){
					//Util.ocultarImagenDifuso(parent);
					parent.dispose();
					System.exit(0);
				}
			} catch (Exception e) {}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(parent instanceof CustomJFrame)
			((CustomJFrame) parent).setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/CloseHover.png")));
		else if(parent instanceof CustomJDialogWithBar)
			((CustomJDialogWithBar) parent).setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/CloseHover.png")));	
			
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if(parent instanceof CustomJFrame)
			((CustomJFrame) parent).setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/botonesNormales.png")));
		else if(parent instanceof CustomJDialogWithBar)
			((CustomJDialogWithBar) parent).setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/botonesNormales.png")));	
		}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if(parent instanceof CustomJFrame)
			((CustomJFrame) parent).setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/closeClick.png")));
		else if(parent instanceof CustomJDialogWithBar)
			((CustomJDialogWithBar) parent).setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/closeClick.png")));	
		}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if(parent instanceof CustomJFrame)
			((CustomJFrame) parent).setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/botonesNormales.png")));
		else if(parent instanceof CustomJDialogWithBar)
			((CustomJDialogWithBar) parent).setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/botonesNormales.png")));	
		}

}
