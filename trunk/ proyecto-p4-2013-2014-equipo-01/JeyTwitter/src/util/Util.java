package util;

import view.parents.CustomJFrame;
import view.ventanas.MensajeWindow;
import view.ventanas.VentanaError;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.io.InputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Util {
	
	public static boolean DEBUG = false;

	public static final String 
	APP_TITULO = "JeyTuiter",
	APP_VERSION = "1.0",
	APP_ICONO = "/res/images/icon.png",
	FICHERO_LOG = "log.txt",
	FICHERO_XML = "config.xml",
	SQLITE_NOMBRE_BBDD = "db.sqlite";

	public static final String[] configs = 
		{
		"configuraciones"
		};

	public static String[] principal =
		{
		"Inicio",
		"Timeline",
		"Menciones",
		"Retweets",
		"Favoritos",
		"Busqueda"
		};

	/**
	* Se encarga de cerrar la ventana al hacer click en el boton cerrar
	* de la barra superior. Muestra un dialogo de confirmacion para
	* asegurar la accion.
	*/
	public static void cerrarVentana(Component parent) throws InvalidInputException{
		showMessage(parent, "Cerrar JeyTwitter", "Desea realmente cerrar?", "Si", "No");
	}

	/**
	* Muestra un mensaje en pantalla del mismo estilo que la clase JOPtionPane
	* pero este metodo tiene la posibilidad de definir el texto de los botones.
	* Ademas la interfaz visual no viene determinada por swing
	*/
	public static boolean showMessage(Component parent, String titulo, String mensaje, String textoAceptar, String textoCancelar) throws InvalidInputException{
		MensajeWindow mw = new MensajeWindow();
		mw.setTituloVentana(titulo);
		mw.setMensaje(mensaje);
		mw.setBotonPositivo(textoAceptar);
		mw.setBotonNegativo(textoCancelar);
		mw.setLocationRelativeTo(parent);
		if(titulo.length()>50 || mensaje.length()>50 || textoAceptar.length()>11 || textoCancelar.length()>11)
			throw new InvalidInputException("Unexpected input string length. Check input Strings");
		mw.setVisible(true);
		//System.out.println("Estado de la respuesta: "+mw.getEstado());
		return mw.getEstado();
	}

	/**
	* Redimensiona el tamaño de una imagen al tamaño del componente que recibe
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
		comp.setVisible(false);
		float opacidad=0.f;
		((JFrame) comp).setOpacity(opacidad);
		comp.setVisible(true);
		for (opacidad = 0.f; opacidad < 1.0f; opacidad+=0.01f ) {
			pausar(25);
			((JFrame) comp).setOpacity(opacidad);
		}
		pausar(25);
		((JFrame) comp).setOpacity(1.0f);
		((JFrame) comp).setVisible(true);
	}

	/**
	* Actualmente muestra un efecto de desvanecimiento en la ventana JFrame
	* que recibe como parametro. Disminuye la opacidad de la ventana desde 1f hasta 0f
	*/
	public static void ocultarImagenDifuso(Component comp) {
		float opacidad=1.0f;
		((JFrame) comp).setOpacity(opacidad);
		comp.setVisible(true);
		for (opacidad = 1.0f; opacidad > 0.0f; opacidad-=0.1f ) {
			pausar(25);
			((JFrame) comp).setOpacity(opacidad);
		}
		pausar(25);
		((JFrame) comp).setOpacity(0.0f);
		((JFrame) comp).setVisible(false);
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
	* Asigna el tema Nimbus Look and Feel a la aplicación
	*/
	public static void asignarNimbus() {
		try {
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
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
	 * @param tamano	tamaño de la fuente
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

	public static void showError(Component parent, String lblTitulodeLaVentana, String lblMensajeAMostrar, String textoBotonBlanco, String textoBotonRojo) {
		VentanaError error = new VentanaError(parent, lblTitulodeLaVentana,lblMensajeAMostrar,"Cancelar","Reiniciar");
		error.setVisible(true);
	}
	
}
