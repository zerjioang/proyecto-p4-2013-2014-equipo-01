package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import util.Util;
import view.ventanas.Principal;
import controller.sql.Interaccion;

public class EventoClickConfig implements MouseListener {
	
	private final Principal p;
	
	public EventoClickConfig(Principal principal) {
		p = principal;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Boolean b = Util.showError(p, "Desautorizar cuenta", "Desea cerrar sesion?", "No", "Si");
		if(b.booleanValue()){
			Interaccion.reiniciarBase();
			p.cerrar();
			new File(Util.SQLITE_NOMBRE_BBDD).delete();
			System.exit(1);
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