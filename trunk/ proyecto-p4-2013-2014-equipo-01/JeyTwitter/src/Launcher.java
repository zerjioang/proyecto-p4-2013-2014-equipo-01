import util.Util;
import view.ventanas.Splash;
import view.ventanas.Welcome;

public class Launcher {

	public static void main(String[] args) {
		Splash spl = new Splash();
		spl.mostrar(5);
		Util.pausar(800);
		spl.dispose();
		Welcome wc = new Welcome();
		wc.setVisible(true);
	}
}
