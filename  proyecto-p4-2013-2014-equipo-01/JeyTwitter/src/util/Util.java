package util;

import view.ventanas.MensajeWindow;
import view.ventanas.VentanaError;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
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

	public static boolean DEBUG = true;

	public static final int anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int altoPantalla = Toolkit.getDefaultToolkit().getScreenSize().height;

	public static final String 
	APP_TITULO = "JeyTuiter",
	APP_VERSION = "1.0",
	APP_ICONO = "/res/images/icon.png",
	FICHERO_XML = "config.xml",
	SQLITE_NOMBRE_BBDD = "JeyTuiterSQL.sqlite";

	public static final String URL_SVN = "https://code.google.com/p/proyecto-p4-2013-2014-equipo-01/";

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
		"Mostrar splash al iniciar",
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
		"Habilitar notificaciones",
		"Habilitar sonido de notificacion",
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
		"Estadistica"
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
	 * @param mensaje	Mensaje que monstrar�� la ventana en su interior
	 * @param textoAceptar	Texto del boton aceptar
	 * @param textoCancelar Texto del boton cancelar
	 * @return	devuelve un valor booleano dependiendo de la accion realizada. Devuelve true si se ha pulsado el
	 * boton de aceptar o false si no se ha hecho
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
	 * Redimensiona el tama��o de una imagen al tama��o del componente que recibe
	 * @param comp	componente que tiene la imagen original no escalada
	 * @return devuelve un objeto imageIcon que contiene la imagen redimensionada
	 */
	public static ImageIcon escalarImagen(Component comp){
		ImageIcon fot = (ImageIcon) ( (JLabel) comp ).getIcon();
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(comp.getWidth(), comp.getHeight(), Image.SCALE_SMOOTH));
		return (ImageIcon) icono;
	}

	/**
	 * Actualmente muestra un efecto de desvanecimiento inverso en la ventana JFrame
	 * que recibe como parametro. Aumenta la opacidad de la ventana desde 0f hasta 1f
	 *
	 * @param comp	componente al que se le aplicara el efecto de difusion
	 */
	public static void mostrarImagenDifuso(Component comp) {
		mostrarImagenDifuso(comp, 25);
	}
	/**
	 * Actualmente muestra un efecto de desvanecimiento inverso en la ventana JFrame
	 * que recibe como parametro. Aumenta la opacidad de la ventana desde 0f hasta 1f
	 * Ademas puede configurar la velocidad con el parametro time
	 * @param comp	componente al que se le aplicara el efecto de difusion
	 * @param time	tiempo de espera entre los grados de opacidad.
	 * A mayor tiempo, mas tardar�� en visualizarse la ventana
	 */
	public static void mostrarImagenDifuso(Component comp, int time) {
		comp.setVisible(false);
		float opacidad=0.f;
		((JFrame) comp).setOpacity(opacidad);
		comp.setVisible(true);
		for (opacidad = 0.f; opacidad < 1.0f; opacidad+=0.1f ) {
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
	 * @param comp	componente que tiene la imagen original no escalada
	 */
	public static void ocultarImagenDifuso(Component comp) {
		ocultarImagenDifuso(comp, 25);
	}

	/**
	 * Actualmente muestra un efecto de desvanecimiento en la ventana JFrame
	 * que recibe como parametro. Disminuye la opacidad de la ventana desde 1f hasta 0f
	 * Ademas puede configurar la velocidad con el parametro time
	 * @param comp	componente al que se le aplicara el efecto de difusion
	 * @param time	tiempo de espera entre los grados de opacidad.
	 * A mayor tiempo, mas tardar�� en desvanecerse la ventana
	 */
	public static void ocultarImagenDifuso(Component comp, int time) {
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
	 * @param i	tiempo que se debe pausar la ejecucion de un proceso
	 */
	public static void pausar(int i) {
		try {Thread.sleep(i);} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * Asigna el tema del sistema operativo a la aplicacion
	 */
	public static void asignarLFSO() {
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
	 * @param tamano	tama��o de la fuente
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
	 * Redondea los bordes de una imagen
	 * @param image		imagen a redondear
	 * @param cornerRadius	grado de redondeo
	 * @return	devuelve la imagen redondeada
	 */
	private static BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
	    int w = image.getWidth();
	    int h = image.getHeight();
	    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2 = output.createGraphics();

	    // This is what we want, but it only does hard-clipping, i.e. aliasing
	    // g2.setClip(new RoundRectangle2D ...)

	    // so instead fake soft-clipping by first drawing the desired clip shape
	    // in fully opaque white with antialiasing enabled...
	    g2.setComposite(AlphaComposite.Src);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

	    // ... then compositing the image on top,
	    // using the white shape from above as alpha source
	    g2.setComposite(AlphaComposite.SrcAtop);
	    g2.drawImage(image, 0, 0, null);

	    g2.dispose();

	    return output;
	}
	
	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	private static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	/**
	 * Redondea los bordes de una imagen
	 * @param imagenOriginal	imagen original que sa ha ser redondeada
	 * @param redondeo			indice de redondeo
	 * @return					devuelve un objeto imageIcon con los bordes redondeados
	 */
	public static ImageIcon getImagenRedondeada(ImageIcon imagenOriginal, int redondeo){
		BufferedImage imagenRedondeada = Util.toBufferedImage(imagenOriginal.getImage());
		imagenRedondeada = Util.makeRoundedCorner(imagenRedondeada, redondeo);
		return new ImageIcon(imagenRedondeada);
	}

	/**
	 * Muestra informacion en consola cuanto el parametro 
	 * DEBUG es igual a TRUE
	 * @param string	String que se quiere visualizar por pantalla
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

	/**
	 * Muestra una ventana de error
	 * @param parent	Ventana padre de la ventana de error
	 * @param lblTitulodeLaVentana	Titulo de la ventana
	 * @param lblMensajeAMostrar	Mensaje de la ventan
	 * @param textoBotonBlanco		Texto del boton blanco
	 * @param textoBotonRojo		Texto del boton rojo
	 * @return	devuelve un valor booleano dependiendo de la accion ralizada por el usuario.
	 * Devuelve true si se ha pulsado el boton rojo, false si se ha pulsado el boton blanco y
	 * Null si no se ha pulsado ninguno de los dos.
	 */
	public static Boolean showError(Component parent, String lblTitulodeLaVentana, String lblMensajeAMostrar, String textoBotonBlanco, String textoBotonRojo) {
		VentanaError error = new VentanaError(parent, lblTitulodeLaVentana,lblMensajeAMostrar,textoBotonBlanco,textoBotonRojo);
		error.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		error.setVisible(true);
		return error.getEstado();
	}

	/**
	 * 
	 * @return Devuelve el nombre del sistema operativo en el que se esta ejecutando la aplicacion.
	 */
	public static String getOS(){
		return System.getProperty("os.name").toLowerCase();
	}

	/**
	 * 
	 * @return devuelve true si el sistema operativo es Mac OS. De lo contrario, devuelve false.
	 */
	public static boolean isMac(){
		return getOS().contains("mac os");
	}

	/**
	 * @return devuelve true si el sistema operativo es Microsoft Windows. De lo contrario, devuelve false
	 */
	public static boolean isWin(){
		return getOS().contains("windows");
	}

	/**
	 * @return devuelve true si el sistema operativo es alguno basado Linux. De lo contrario, devuelve false
	 */
	public static boolean isNix(){
		return getOS().contains("nix") || getOS().contains("ubuntu") || getOS().contains("debian");
	}
}
