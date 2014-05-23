package view.eventos.principal;

import hilos.HiloEnviarTweet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

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
		if(panel.getMensaje().length()<140) {
			new HiloEnviarTweet(panel).start();
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
