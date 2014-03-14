import util.Util;
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
		
		System.out.println(Util.getOS());
		
		Welcome wc = new Welcome();
		wc.setVisible(true);
	}
}
