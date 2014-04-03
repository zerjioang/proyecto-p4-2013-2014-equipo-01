import java.sql.Date;

import javax.swing.ImageIcon;

import model.Usuario;
import controller.GUIController;
import util.Util;
import view.ventanas.Principal;
import view.ventanas.Splash;
import view.ventanas.Welcome;
/**
 * Clase principal que inicia el programa
 * @author JeyTuiter Dev Team
 *
 */
public class Launcher {

	public static void main(String[] args) {
		Splash spl = new Splash();
		spl.mostrar(5);
		if (GUIController.getInstance().esTokenValido()) {
			// Tenemos token, lanzamos la ventana principal
			Usuario u = new Usuario("Nombre Apellido1 Apellido2", "5768745", "9864598", "Jey", "Hola me yamo J y mi padr me puso el nombre en onor a los ombres de nejro.", new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), new Date(1L), 10, 0, 2);
			//Como el usuario no existe el programa da error de ejecucion.
			Principal p = new Principal(u);
			p.setPanelActual(p.getPaneles()[1]);
			p.setVisible(true);
		} else {
			if (GUIController.getInstance().hayConexion()) {
				Welcome wc = new Welcome();
				wc.setVisible(true);							
			} else {
				Util.showError(null, "Error de conexion", "Para poder usar JeyTuiter necesitas estar conectado a internet.", "Aceptar", "Cerrar");
			}
		}
		spl.dispose();
	}
}
