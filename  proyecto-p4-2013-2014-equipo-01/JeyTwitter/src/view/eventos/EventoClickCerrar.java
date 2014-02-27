package view.eventos;

import view.parents.CustomJFrame;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import util.Util;


public class EventoClickCerrar implements MouseListener {

	private CustomJFrame parent;
	
	public EventoClickCerrar(CustomJFrame parent){
		this.parent = parent;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if(parent.isDisposeWindow()){
					Util.ocultarImagenDifuso(parent);
					parent.dispose();
				}
				else{
					try {
						boolean respPositiva = Util.showMessage(parent, "Cerrar app","Desea realmente cerrar JeyTuiter?", "Cerrar", "Cancelar");
						if(respPositiva){
							Util.ocultarImagenDifuso(parent);
							System.exit(0);
						}
					} catch (Exception e) {}
				}
			}
		});
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		parent.setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/CloseHover.png")));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		parent.setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/botonesNormales.png")));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		parent.setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/closeClick.png")));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		parent.setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/botonesNormales.png")));
	}

}
