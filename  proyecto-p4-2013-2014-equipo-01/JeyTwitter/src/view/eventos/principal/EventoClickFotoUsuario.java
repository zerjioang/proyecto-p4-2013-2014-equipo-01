package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import controller.GUIController;
import view.elementos.paneles.PanelPerfilUsuario;
import view.ventanas.Principal;

public class EventoClickFotoUsuario implements MouseListener {

	private final Principal v;
	private final String nombreUsuario;
	
	public EventoClickFotoUsuario(Principal principal, String nombreUsuario) {
		v = principal;
		this.nombreUsuario = nombreUsuario;
	}
	public EventoClickFotoUsuario(Principal principal) {
		v = principal;
		nombreUsuario = null;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		v.setPanelActual(v.getPaneles()[0]);
		if (nombreUsuario != null){
			try {
				GUIController.getInstance().getGui().setPanelActual(new PanelPerfilUsuario(GUIController.getInstance().getUsuario(nombreUsuario)));
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
