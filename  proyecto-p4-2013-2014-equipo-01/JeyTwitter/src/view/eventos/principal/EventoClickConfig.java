package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.ventanas.AcercaDe;
import view.ventanas.Config;
import view.ventanas.Principal;

public class EventoClickConfig implements MouseListener {
	
	private final Principal p;
	
	public EventoClickConfig(Principal principal) {
		p = principal;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Config c = new Config();
		c.setLocationRelativeTo(p);
		c.setVisible(true);
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
