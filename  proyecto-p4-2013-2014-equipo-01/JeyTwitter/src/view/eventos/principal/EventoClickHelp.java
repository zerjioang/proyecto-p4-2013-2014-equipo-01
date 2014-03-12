package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import util.InvalidInputException;
import util.Util;
import view.ventanas.Principal;

public class EventoClickHelp implements MouseListener {

	private final Principal p;
	
	public EventoClickHelp(Principal principal) {
		p = principal;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			Util.showMessage(p, "Hahahaha", "Picaste!", "Lo se", "Lo se");
			Util.showMessage(p, "Tranquilo", "Era broma", "Lo sabia", "Lo sabia");
			Util.showMessage(p, "Ayuda", "Puede consultar Twitter.com para obtener más información", "Continuar", "Atras");
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
