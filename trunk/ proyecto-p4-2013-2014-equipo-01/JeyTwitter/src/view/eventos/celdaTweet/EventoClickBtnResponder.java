package view.eventos.celdaTweet;

import hilos.HiloResponder;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import controller.GUIController;
import twitter4j.TwitterException;
import util.Util;
import view.elementos.GUITweet;
import view.ventanas.TweetRapido;

public class EventoClickBtnResponder implements MouseListener {
	
	private final GUITweet t;
	
	public EventoClickBtnResponder(GUITweet guiTweet) {
		t = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		new HiloResponder(t, event).start();
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
