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
		Util.pausar(800);
		spl.dispose();
		
		if (GUIController.getInstance().hasValidToken()) {
			// Tenemos token, lanzamos la ventana principal
			Principal p = new Principal();
			p.setVisible(true);
		} else {
			Welcome wc = new Welcome();
			wc.setVisible(true);			
		}
	}
}
