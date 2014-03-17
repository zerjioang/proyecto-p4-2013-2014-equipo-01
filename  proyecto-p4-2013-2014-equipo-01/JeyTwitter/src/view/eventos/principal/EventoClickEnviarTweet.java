package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import view.elementos.paneles.PanelEnviarTweet;
import view.ventanas.Principal;

public class EventoClickEnviarTweet implements MouseListener {

	public EventoClickEnviarTweet(PanelEnviarTweet panelEnviarTweet) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Tu nombre de usuario es: "+GUIController.getInstance().dimeNombre());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
