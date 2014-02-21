package util;

public class Util {

	public static final int APP_VERSION = 1;
	
	public static final String APP_TITULO = "JeyTuiter";

	public static final String FICHERO_XML = "config.xml";
	public static final String FICHERO_LOG = "log.txt";
	
	public static final String SYSTEM_TRAY_ICON = null;
	
	public static final boolean DEBUG = true;

	public static final String SQLITE_NOMBRE_BBDD = "data.sql";

	public static final String[] SQLITE_NOMBRE_TABLAS = 
		{
		""
		};
	
	public static void debug(String string) {
		if(DEBUG)
			System.out.println(string);
	}

}
