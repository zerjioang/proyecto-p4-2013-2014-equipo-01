package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import view.elementos.paneles.PanelPerfilUsuario;
import view.ventanas.Contador;
import view.ventanas.Principal;

public class EventoVerContadorPerfilusuario implements MouseListener, MouseMotionListener {

	public static final int FAVORITOS = 0;
	public static final int TWEETS = 1;
	public static final int SEGUIDORES = 2;
	public static final int SIGUIENDO = 3;
	private final PanelPerfilUsuario p;
	private Contador c;
	private int opcion;

	public EventoVerContadorPerfilusuario(PanelPerfilUsuario panelPerfilUsuario, Contador c, int opcion) {
		p = panelPerfilUsuario;
		this.c = c;
		this.opcion = opcion;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		switch (opcion) {
		case 0:
			c.setCantidad(Principal.getUsuarioActual().getNumeroTweets());
			break;
		case 1:
			c.setCantidad(Principal.getUsuarioActual().getNumeroTweets());
			break;
		case 2:
			c.setCantidad(Principal.getUsuarioActual().getNumeroSeguidores());
			break;
		case 3:
			c.setCantidad(Principal.getUsuarioActual().getNumeroSiguiendo());
			break;
		}
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
