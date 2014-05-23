package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import controller.GUIController;
import twitter4j.examples.geo.GetGeoDetails;
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
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		mover(e);
		int valor = 0;
		switch (opcion) {
		case 0://favoritos
			valor = GUIController.getInstance().getGui().getUsuarioActual().getNumeroFavoritos();
			break;
		case 1://tweets
			valor = GUIController.getInstance().getGui().getUsuarioActual().getNumeroTweets();
			break;
		case 2://Seguidores
			valor = GUIController.getInstance().getGui().getUsuarioActual().getNumeroSeguidores();
			break;
		case 3://Siguiendo
			valor = GUIController.getInstance().getGui().getUsuarioActual().getNumeroSiguiendo();
			break;
		}
		c.setCantidad(valor);
		c.setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		c.dispose();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		mover(e);
	}

	private void mover(MouseEvent e) {
		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		c.setLocation(x-30, y-c.getHeight());
	}
}
