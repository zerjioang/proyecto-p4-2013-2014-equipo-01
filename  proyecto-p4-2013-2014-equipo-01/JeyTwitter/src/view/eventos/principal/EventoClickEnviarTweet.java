package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import view.elementos.paneles.PanelEnviarTweet;

public class EventoClickEnviarTweet implements MouseListener {

	private PanelEnviarTweet panel;
	
	public EventoClickEnviarTweet(PanelEnviarTweet panelEnviarTweet) {
		panel = panelEnviarTweet;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(GUIController.getInstance().enviarTweet(panel.getMensaje())) {
			System.out.println("Tweet enviado con exito.");
		} else {
			System.out.println("Ha ocurrido un error al enviar el tweet.");
		}
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
