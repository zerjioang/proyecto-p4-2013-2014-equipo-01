package view.eventos.celdaTweet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import twitter4j.TwitterException;
import view.elementos.GUITweet;

public class EventoClickBtnReTweet implements MouseListener {

	private final GUITweet t;
	
	public EventoClickBtnReTweet(GUITweet guiTweet) {
		t = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		long codigo = t.getTweet().getCodigo();
		
		if(GUIController.getInstance().marcarRetuit(codigo)) {
			if(!t.getBtnRetweet().isClicado()){
				System.out.println("Retuit con éxito");
				t.getBtnRetweet().setClicado(true);
			}
			else{
				System.out.println("Des-retuit con exito");
				t.getBtnRetweet().setClicado(false);
			}
		} else {
			System.out.println("Ha ocurrido un error al retuitear");
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
