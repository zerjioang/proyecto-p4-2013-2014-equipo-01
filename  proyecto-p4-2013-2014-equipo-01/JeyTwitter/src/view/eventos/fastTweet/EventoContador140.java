package view.eventos.fastTweet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.ventanas.TweetRapido;

public class EventoContador140 implements KeyListener {

	private TweetRapido fastTuit;

	public EventoContador140(TweetRapido fastTuit) {
		this.fastTuit = fastTuit;
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		fastTuit.setContador(140-fastTuit.getMensaje().length());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
