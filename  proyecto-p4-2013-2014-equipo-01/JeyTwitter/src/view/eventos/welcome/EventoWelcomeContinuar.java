package view.eventos.welcome;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import view.parents.CustomJFrame;
import view.ventanas.Principal;
import view.ventanas.Welcome;

public class EventoWelcomeContinuar implements MouseListener {

	private Welcome ventana;
	public EventoWelcomeContinuar(Welcome welcome) {
		ventana = welcome;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println(ventana.getCodigo());
		if(ventana.getCodeField().evaluate()){
			GUIController.getInstance().setPin(ventana.getCodigo());
			Principal p = new Principal();
			p.setLocationRelativeTo(ventana);
			p.setVisible(true);
			ventana.cerrar();
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
