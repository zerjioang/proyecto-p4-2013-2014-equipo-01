package view.eventos.celdaTweet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.GUIController;
import util.InvalidInputException;
import util.Util;
import view.elementos.GUITweet;
import view.elementos.paneles.PanelPerfilUsuario;
import view.ventanas.Principal;

public class EventoClickImagenUsuario implements MouseListener {

	private final GUITweet g;
	//private final Principal p;
	
	public EventoClickImagenUsuario(GUITweet guiTweet) {
		g = guiTweet;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		/*PanelPerfilUsuario pu = new PanelPerfilUsuario(GUIController.getInstance().getUsuario(g.getNombreUsuario()));
		p.setPanelUsuario(pu);
		p.setPanelActual(p.getPanelUsuario());*/
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
