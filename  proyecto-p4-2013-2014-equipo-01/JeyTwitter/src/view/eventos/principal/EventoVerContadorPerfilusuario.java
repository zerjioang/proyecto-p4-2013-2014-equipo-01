package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import controller.GUIController;
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
	private long id;

	public EventoVerContadorPerfilusuario(PanelPerfilUsuario panelPerfilUsuario, Contador c, int opcion) {
		p = panelPerfilUsuario;
		this.c = c;
		this.opcion = opcion;
		this.id = p.getIdUsuario();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		c.setCantidad(100);
		switch (opcion) {
		case 0://Tweets
			//c.setCantidad(Principal.getUsuarioActual().getNumeroTweets());
			//System.out.println(GUIController.getInstance().getUsuario(id).getNumeroTweets());
			break;
		case 1://Favoritos
			//c.setCantidad(GUIController.getInstance().getNumeroFavoritos(id));
			//System.out.println(GUIController.getInstance().getNumeroFavoritos(id));
			break;
		case 2://Seguidores
			//c.setCantidad(Principal.getUsuarioActual().getNumeroSeguidores());
			//System.out.println(GUIController.getInstance().getUsuario(id).getNumeroSeguidores());
			break;
		case 3://Siguiendo
			//c.setCantidad(Principal.getUsuarioActual().getNumeroSiguiendo());
			//System.out.println(GUIController.getInstance().getUsuario(id).getNumeroSiguiendo());
			break;
		}
		c.setVisible(true);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		c.setVisible(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getXOnScreen();
		int y = e.getYOnScreen();
		c.setLocation(x-30, y-c.getHeight());
	}

}
