import util.Util;
import view.ventanas.Splash;
import view.ventanas.Welcome;


public class Launcher {

	public static void main(String[] args) {
		Splash spl = new Splash();
		spl.setVisible(true);
		Util.pausar(1000);
		spl.dispose();
		Welcome wc = new Welcome();
		wc.setVisible(true);
	}
}
