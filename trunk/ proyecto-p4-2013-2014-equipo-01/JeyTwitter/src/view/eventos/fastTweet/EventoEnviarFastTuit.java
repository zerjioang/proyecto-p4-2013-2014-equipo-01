package view.eventos.fastTweet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import view.ventanas.TweetRapido;

public class EventoEnviarFastTuit implements MouseListener {

	private final TweetRapido fastTuit;

	public EventoEnviarFastTuit(TweetRapido fastTuit) {
		this.fastTuit = fastTuit;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(fastTuit.getBtnEnviar().isEnabled() && fastTuit.getMensaje().length()!=0) {
			GUIController.getInstance().responderTuit(fastTuit.getTuit().getCodigo(), fastTuit.getMensaje());
			fastTuit.dispose();			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
