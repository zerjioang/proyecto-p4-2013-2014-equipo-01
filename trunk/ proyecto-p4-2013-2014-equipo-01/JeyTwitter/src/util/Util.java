package util;

import view.ventanas.MensajeWindow;
import view.ventanas.VentanaError;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.InputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Almacena todas las variables estaticas de configuracion que el programa necesita.
 * @author JeyTuiter Dev Team
 *
 */
public class Util {
	
	public static boolean DEBUG = false;
	
	public static final int anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int altoPantalla = Toolkit.getDefaultToolkit().getScreenSize().height;

	public static final String 
	APP_TITULO = "JeyTuiter",
	APP_VERSION = "1.0",
	APP_ICONO = "/res/images/icon.png",
	FICHERO_LOG = "log.txt",
	FICHERO_XML = "config.xml",
	SQLITE_NOMBRE_BBDD = "db.sqlite";
	
	//Este array representa las diferentes areas de configuracion
	public static String[] settings =
		{
		"General",
		"Cuenta",
		"Notificaciones"
		};
	/*
	 * Cada uno de los siguientes arrays representa las configuraciones disponibles
	 * dentro de cada area de configuracion
	 */
	public static String[] settingsGeneral =
		{
		"Activar modo silencio",
		"No mostrar splash al iniciar",
		"Minimizar a la barra de tareas",
		"Frecuencia de actualizacion",
		"Personalizar fuente",
		"Activar filtros de busqueda",
		"Iniciar con el sistema",
		"Reiniciar configuracion"
		};
	
	public static String[] settingsCuenta =
		{
		"Permitir multiples usuarios",
		"Cerrar sesion al salir",
		"Permitir edicion offline",
		//"Activar modo Streaming",
		"Desautorizar cliente",
		};
	
	public static String[] settingsNotif =
		{
		"Deshabilitar notificaciones",
		"Deshabilitar sonido de notificacion",
		"Personalizar posicion",
		"Traer notificaciones al frente",
		"Definir tiempo de espera"
		};

	public static String[] principal =
		{
		"Timeline",
		"Menciones",
		"Retweets",
		"Favoritos",
		"Busqueda",
		"Data Mining"
		};

	/**
	* Se encarga de cerrar la ventana al hacer click en el boton cerrar
	* de la barra superior. Muestra un dialogo de confirmacion para
	* asegurar la accion.
	 * @param parent	Ventana padre desde la que se ha llamado. Null en caso de no ser ninguna
	 * @throws InvalidInputException	Excepcion que se lanca en caso de error
	 */
	public static void cerrarVentana(Component parent) throws InvalidInputException{
		showMessage(parent, "Cerrar "+APP_TITULO, "Desea realmente cerrar?", "Si", "No");
	}

	/**
	* Muestra un mensaje en pantalla del mismo estilo que la clase JOPtionPane
	* pero este metodo tiene la posibilidad de definir el texto de los botones.
	* Ademas la interfaz visual no viene determinada por swing
	 * @param parent	Ventana padre desde la que se ha llamado. Null en caso de no ser ninguna
	 * @param titulo	Titulo principal de la ventana
	 * @param mensaje	Mensaje que monstrará la ventana en su interior
	 * @param textoAceptar	Texto del boton aceptar
	 * @param textoCancelar Texto del boton cancelar
	 * @return	devuelve un valor booleano dependiendo de la accion realizada. Devuelve true si se ha pulsado el
	 * boton de aceptar o false si se
	 * @throws InvalidInputException
	 */
	public static boolean showMessage(Component parent, String titulo, String mensaje, String textoAceptar, String textoCancelar) throws InvalidInputException{
		MensajeWindow mw = new MensajeWindow();
		mw.setTituloVentana(titulo);
		mw.setMensaje(mensaje);
		mw.setBotonPositivo(textoAceptar);
		mw.setBotonNegativo(textoCancelar);
		mw.setLocationRelativeTo(parent);
		mw.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		if(titulo.length()>50 || mensaje.length()>60 || textoAceptar.length()>11 || textoCancelar.length()>11)
			throw new InvalidInputException("Unexpected input string length. Check input Strings");
		mw.setVisible(true);
		//System.out.println("Estado de la respuesta: "+mw.getEstado());
		return mw.getEstado();
	}

	/**
	* Redimensiona el tama�o de una imagen al tama�o del componente que recibe
	*/
	public static ImageIcon escalarImagen(Component comp){
		ImageIcon fot = (ImageIcon) ( (JLabel) comp ).getIcon();
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(comp.getWidth(), comp.getHeight(), Image.SCALE_DEFAULT));
		return (ImageIcon) icono;
	}

	/**
	* Actualmente muestra un efecto de desvanecimiento inverso en la ventana JFrame
	* que recibe como parametro. Aumenta la opacidad de la ventana desde 0f hasta 1f
	*/
	public static void mostrarImagenDifuso(Component comp) {
		mostrarImagenDifuso(comp, 25);
	}
	/**
	* Actualmente muestra un efecto de desvanecimiento inverso en la ventana JFrame
	* que recibe como parametro. Aumenta la opacidad de la ventana desde 0f hasta 1f
	* Ademas puede configurar la velocidad con el parametro time
	*/
	public static void mostrarImagenDifuso(Component comp, int time) {
		comp.setVisible(false);
		float opacidad=0.f;
		((JFrame) comp).setOpacity(opacidad);
		comp.setVisible(true);
		for (opacidad = 0.f; opacidad < 1.0f; opacidad+=0.01f ) {
			pausar(time);
			((JFrame) comp).setOpacity(opacidad);
		}
		pausar(time);
		((JFrame) comp).setOpacity(1.0f);
		((JFrame) comp).setVisible(true);
	}

	/**
	* Actualmente muestra un efecto de desvanecimiento en la ventana JFrame
	* que recibe como parametro. Disminuye la opacidad de la ventana desde 1f hasta 0f
	*/
	public static void ocultarImagenDifuso(Component comp) {
		ocultarImagenDifuso(comp, 25);
	}
	
	/**
	* Actualmente muestra un efecto de desvanecimiento en la ventana JFrame
	* que recibe como parametro. Disminuye la opacidad de la ventana desde 1f hasta 0f
	* Ademas puede configurar la velocidad con el parametro time
	*/
	public static void ocultarImagenDifuso(Component comp, int time) {
		System.out.println("Cerrarnod");
		float opacidad=1.0f;
		((Window) comp).setOpacity(opacidad);
		comp.setVisible(true);
		for (opacidad = 1.0f; opacidad > 0.0f; opacidad-=0.1f ) {
			pausar(time);
			((Window) comp).setOpacity(opacidad);
		}
		pausar(time);
		((Window) comp).setOpacity(0.0f);
		((Window) comp).setVisible(false);
	}
	/**
	 * Pausa la ejecucion durante un tiempo determinado
	 * @param i	tiempo que se debe pausar
	 */
	public static void pausar(int i) {
		try {Thread.sleep(i);} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	* Asigna el tema Nimbus Look and Feel a la aplicaci�n
	*/
	public static void asignarNimbus() {
		//Al tener activado el look and feel de Nimbus algunas ventanas con
		//transparencia se volvian opacas. Ahora esta desactivado
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Carga una fuente que no esta instalada en el sistema para usarla
	 * @param name	nombre de la fuente a cargar situada en la carpeta /res/fonts. omitir la extension
	 * @param tipo	tipo de fuente: normal, negrita, cursiva
	 * @param tamano	tama�o de la fuente
	 * @return devuelve un objeto fuente con la fuente seleccionada
	 */
	public static Font getFont(String name, int tipo, float tamano) {
		Font font = new JLabel().getFont();	//carga la fuente por defecto
		if (name != null) {
			try {
				InputStream is = Util.class.getResourceAsStream("/res/fonts/"+name+".ttf");
				//System.out.println("Leyendo "+"/res/fonts/"+name+".ttf");
				//System.out.println(is);
				font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(tipo, tamano);
			} catch (Exception ex) {
				Util.debug(name + " not loaded.  Using default font.");
				font.deriveFont(tipo, tamano);
			}
		}
		if(font==null)
			font = new JLabel().getFont().deriveFont(tipo, tamano);
		return font;
	}

	/**
	* Muestra informacion en consola cuanto el parametro 
	* DEBUG es igual a TRUE
	*/
	public static void debug(String string) {
		if(DEBUG)
			if (string!=null) {
				System.out.println(string);
			}
			else {
				System.err.println("[Object NULL]");
			}
	}

	public static boolean showError(Component parent, String lblTitulodeLaVentana, String lblMensajeAMostrar, String textoBotonBlanco, String textoBotonRojo) {
		VentanaError error = new VentanaError(parent, lblTitulodeLaVentana,lblMensajeAMostrar,"Cancelar","Reiniciar");
		error.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		error.setVisible(true);
		return error.getEstado();
	}
	
	public static String getOS(){
		return System.getProperty("os.name").toLowerCase();
	}
	
	public static boolean isMac(){
		return getOS().contains("mac os");
	}
	
	public static boolean isWin(){
		return getOS().contains("windows");
	}
	
	public static boolean isNix(){
		return getOS().contains("nix");
	}
}
