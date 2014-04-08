package view.eventos.principal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import view.elementos.paneles.PanelEnviarTweet;

public class EventoKeyListenerTweet implements KeyListener {
	
	private final PanelEnviarTweet panel;
	
	public EventoKeyListenerTweet(PanelEnviarTweet panelEnviarTweet) {
		panel = panelEnviarTweet;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
