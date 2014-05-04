package view.eventos.welcome;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import controller.GUIController;
import util.Util;
import view.ventanas.Principal;
import view.ventanas.Welcome;

public class EventoWelcomeContinuar implements MouseListener {

	private Welcome ventana;
	public EventoWelcomeContinuar(Welcome welcome) {
		ventana = welcome;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(ventana.getCodeField().evaluate()){
			try {
				GUIController.getInstance().guardarUsuario(ventana.getCodigo());
				Principal p = new Principal(GUIController.getInstance().getUsuarioRegistrado());
				p.setLocationRelativeTo(ventana);
				p.setPanelActual(p.getPaneles()[1]);
				p.setVisible(true);
				ventana.cerrar();
			} catch (IllegalStateException e) {
				Util.showError(ventana, "Error de autentificacion", "No Token available", "Cancelar", "Aceptar");
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
