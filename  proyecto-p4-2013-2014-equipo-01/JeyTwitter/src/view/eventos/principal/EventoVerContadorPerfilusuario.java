package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import view.elementos.paneles.PanelPerfilUsuario;
import view.ventanas.Contador;

public class EventoVerContadorPerfilusuario implements MouseListener, MouseMotionListener {

	private final PanelPerfilUsuario p;
	private static Contador c;

	public EventoVerContadorPerfilusuario(PanelPerfilUsuario panelPerfilUsuario, Contador c) {
		p = panelPerfilUsuario;
		this.c = c;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		c.setCantidad(500);
		mover(e);
		c.setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		c.setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mover(e);
	}

	/**
	 * @param e
	 */
	private void mover(MouseEvent e) {
		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		c.setLocation(x-30, y-c.getHeight());
	}

}
