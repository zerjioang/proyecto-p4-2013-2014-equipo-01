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
			Usuario u = new Usuario("", "", "", "", "", null, null, 0, 0, 0);
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
