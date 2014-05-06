package view.eventos.celdaTweet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import twitter4j.TwitterException;
import view.elementos.GUITweet;

public class EventoClickBtnFavorito implements MouseListener {

	private final GUITweet t;
	
	public EventoClickBtnFavorito(GUITweet guiTweet) {
		t = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		long codigo = t.getTweet().getCodigo();
		
		if(GUIController.getInstance().marcarFavorito(codigo)) {
			if(!t.getBtnFavorito().isClicado()){
				System.out.println("Favorito marcado con éxito");
				t.getBtnFavorito().setClicado(true);
			}
			else{
				System.out.println("Favorito desmarcado con éxito");
				t.getBtnFavorito().setClicado(false);
			}
		} else {
			System.err.println("Ha ocurrido un error al marcar favorito");
		}
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
