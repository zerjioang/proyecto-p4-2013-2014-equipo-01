package util;

import view.parents.Moveable;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Util {

	public static boolean DEBUG = true;

	public static final int anchoPantalla = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int altoPantalla = Toolkit.getDefaultToolkit().getScreenSize().height;

	public static final String 
	APP_TITULO = "JeyTuiter",
	APP_VERSION = "1.0",
	APP_ICONO = "/res/images/JeyIcon.png",
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

	
	public static void cerrarVentana(Moveable parent) throws InvalidInputException{
		showMessage(parent, "Cerrar "+APP_TITULO, "Desea realmente cerrar?", "Si", "No");
	}

	
	public static boolean showMessage(Moveable parent, String titulo, String mensaje, String textoAceptar, String textoCancelar) throws InvalidInputException{
		MensajeWindow mw = new MensajeWindow();
		mw.setTituloVentana(titulo);
		mw.setMensaje(mensaje);
		mw.setBotonPositivo(textoAceptar);
		mw.setBotonNegativo(textoCancelar);
		mw.setLocationRelativeTo((Component) parent);
		mw.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		if(titulo.length()>50 || mensaje.length()>60 || textoAceptar.length()>11 || textoCancelar.length()>11)
			throw new InvalidInputException("Unexpected input string length. Check input Strings");
		mw.setVisible(true);
		//System.out.println("Estado de la respuesta: "+mw.getEstado());
		return mw.getEstado();
	}

	
	public static ImageIcon escalarImagen(Component comp){
		ImageIcon fot = (ImageIcon) ( (JLabel) comp ).getIcon();
		Icon icono = new ImageIcon(fot.getImage().getScaledInstance(comp.getWidth(), comp.getHeight(), Image.SCALE_SMOOTH));
		return (ImageIcon) icono;
	}

	
	public static void mostrarImagenDifuso(Component comp) {
		mostrarImagenDifuso(comp, 25);
	}
	
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

	
	public static void ocultarImagenDifuso(Moveable parent) {
		ocultarImagenDifuso(parent, 25);
	}

	
	public static void ocultarImagenDifuso(Moveable parent, int time) {
		float opacidad=1.0f;
		((Window) parent).setOpacity(opacidad);
		parent.setVisible(true);
		for (opacidad = 1.0f; opacidad > 0.0f; opacidad-=0.1f ) {
			pausar(time);
			((Window) parent).setOpacity(opacidad);
		}
		pausar(time);
		((Window) parent).setOpacity(0.0f);
		((Window) parent).setVisible(false);
		pausar(time);
	}
	
	public static void pausar(int i) {
		try {Thread.sleep(i);} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	public static void asignarLFSO() {
		//Al tener activado el look and feel de Nimbus algunas ventanas con
		//transparencia se volvian opacas. Ahora esta desactivado
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//Se supone que suaviza el movimiento de las scrollbars
			UIManager.put("List.lockToPositionOnScroll", Boolean.FALSE);
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
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
	
	public static ImageIcon getImagenRedondeada(ImageIcon imagenOriginal, int redondeo){
		BufferedImage imagenRedondeada = Util.toBufferedImage(imagenOriginal.getImage());
		imagenRedondeada = Util.makeRoundedCorner(imagenRedondeada, redondeo);
		return new ImageIcon(imagenRedondeada);
	}

	
	public static void debug(String string) {
		if(DEBUG)
			if (string!=null) {
				System.out.println(string);
			}
			else {
				System.err.println("[Object NULL]");
			}
	}

	
	public static Boolean showError(Component parent, String lblTitulodeLaVentana, String lblMensajeAMostrar, String textoBotonBlanco, String textoBotonRojo) {
		VentanaError error = new VentanaError(parent, lblTitulodeLaVentana,lblMensajeAMostrar,textoBotonBlanco,textoBotonRojo);
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
		return getOS().contains("nix") || getOS().contains("ubuntu") || getOS().contains("debian");
	}

	public static String calcularFecha(Date d) {
		String finalStr;
		Calendar currCal = Calendar.getInstance();
		currCal.setTime(new Date(System.currentTimeMillis()));
	    int currYear = currCal.get(Calendar.YEAR);
	    int currMonth = currCal.get(Calendar.MONTH)+1;
	    int currDay = currCal.get(Calendar.DAY_OF_MONTH);
	    int currHourDay = currCal.get(Calendar.HOUR_OF_DAY);
	    int currMin = currCal.get(Calendar.MINUTE);
	    int currSec = currCal.get(Calendar.SECOND);
	    Util.debug("Actual: "+currYear+" "+currMonth+" "+currDay+" "+currHourDay+" "+currMin+" "+currSec);
	    currCal.setTime(d);
	    int year = currCal.get(Calendar.YEAR);
	    int month = currCal.get(Calendar.MONTH)+1;
	    String nombreMes = new SimpleDateFormat("MMM").format(currCal.getTime());
	    int day = currCal.get(Calendar.DAY_OF_MONTH);
	    int hourDay = currCal.get(Calendar.HOUR_OF_DAY);
	    int min = currCal.get(Calendar.MINUTE);
	    int sec = currCal.get(Calendar.SECOND);
	    Util.debug("Fecha tweet: "+year+" "+month+" "+day+" "+hourDay+" "+min+" "+sec);
	    if(currYear==year){
	    	if(currMonth == month){
	    		if(currDay==day){
	    			if(currHourDay==hourDay){
	    				if(currMin==min){
	    					if(currSec==sec){
	    						return "ahora";
	    					}
	    					else{
	    						return "hace "+String.valueOf(currSec-sec)+" seg";
	    					}
	    				}
	    				else{
	    					return "hace "+String.valueOf(currMin-min)+" min";
	    				}
	    			}
	    			else{
	    				return" hoy, "+hourDay+"h";
	    			}
	    		}
	    		//ayer
	    		else if(currDay==day-1){
	    			return "ayer,  "+hourDay+"h";
	    		}
	    		//anteayer 
	    		else if(currDay==day-2){
	    			return "anteayer a las "+hourDay+"h";
	    		}
	    		else{
	    			return String.valueOf(day)+" de "+nombreMes;
	    		}
	    	}
	    	else{
	    		return day+" de "+nombreMes;
	    	}
	    }
	    else{
	    	return day+" de "+nombreMes+" de "+year;
	    }
	}
}
