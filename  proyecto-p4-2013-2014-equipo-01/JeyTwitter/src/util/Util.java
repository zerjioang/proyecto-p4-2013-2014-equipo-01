package util;

import javax.swing.UIManager;

public class Util {

	public static final String SQLITE_NOMBRE_BBDD = "db.sqlite";
	public static final boolean DEBUG = false;
	public static final String FICHERO_LOG = "log.txt";
	public static final String APP_TITULO = "JeyTuiter";
	public static final String APP_VERSION = "1.0";
	public static final String FICHERO_XML = "config.xml";

	public static void asignarNimbus() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void debug(String string) {
		if(DEBUG)
			System.out.println(string);
	}

	public static void pausar(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
