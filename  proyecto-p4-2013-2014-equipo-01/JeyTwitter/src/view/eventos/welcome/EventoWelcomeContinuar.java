package view.eventos.welcome;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;

import javax.swing.ImageIcon;

import model.Usuario;
import controller.GUIController;
import twitter4j.TwitterException;
import util.Util;
import view.ventanas.Principal;
import view.ventanas.Welcome;

public class EventoWelcomeContinuar implements MouseListener {

	private Welcome ventana;
	public EventoWelcomeContinuar(Welcome welcome) {
		ventana = welcome;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println(ventana.getCodigo());
		if(ventana.getCodeField().evaluate()){
			try {
				GUIController.getInstance().setCodigo(ventana.getCodigo());
				Usuario u = new Usuario("Nombre Apellido1 Apellido2", "5768745", "9864598", "Jey", "Hola me yamo J y mi padr me puso el nombre en onor a los ombres de nejro.", new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), new Date(1L), 10, 0, 2);
				Principal p = new Principal(u);
				p.setLocationRelativeTo(ventana);
				p.setVisible(true);
				ventana.cerrar();
			} catch (IllegalStateException e) {
				Util.showError(ventana, "Error de autentificacion", "No Token available", "Cancelar", "Aceptar");
			}
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
