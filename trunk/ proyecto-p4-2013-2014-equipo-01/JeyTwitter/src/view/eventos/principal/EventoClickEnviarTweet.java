package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import view.elementos.paneles.PanelEnviarTweet;
import view.ventanas.Principal;

public class EventoClickEnviarTweet implements MouseListener {

	private PanelEnviarTweet panel;
	
	public EventoClickEnviarTweet(PanelEnviarTweet panelEnviarTweet) {
		panel = panelEnviarTweet;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		GUIController.getInstance().enviarTweet(panel.getMensaje());
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
