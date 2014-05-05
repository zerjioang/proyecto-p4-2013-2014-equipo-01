package view.eventos.celdaTweet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import twitter4j.TwitterException;
import view.elementos.GUITweet;
import view.ventanas.TweetRapido;

public class EventoClickBtnResponder implements MouseListener {

	private final GUITweet t;
	
	public EventoClickBtnResponder(GUITweet guiTweet) {
		t = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		long codigo = t.getTweet().getCodigo();
		TweetRapido tr = new TweetRapido(t.getTweet());
		tr.setLocation(t.getLocation());
		tr.setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
