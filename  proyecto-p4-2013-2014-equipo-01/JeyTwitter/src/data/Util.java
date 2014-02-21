package data;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import data.io.XML;
import model.sql.ServidorSQL;
/**
 * Almacena todos los datos configurables del programa en 
 * una sola clase que puede ser referenciada desde cualquier parte
 * @author Sergio Anguita
 *
 */
public class Util {

	public static boolean DEBUG = false;

	/*public static final String 

	NOMBRE_CARPETA = "mini_server_11",
	RUTA_SERVER = new File("").getAbsolutePath()+File.separator+NOMBRE_CARPETA+File.separator,
	APP_TITULO = "Watson Searcher",
	APP_VERSION = "2.6",
	APP_ICONO = "/res/imagenes/WSE_icon.png",

	RUTA_IMAGEN_SPLASH = "/res/imagenes/spl26-3.png",
	LOADER = "/res/imagenes/loader.gif",
	SYSTEM_TRAY_ICON = "/res/imagenes/WSE_icon.png",
	ELIMINAR_LISTA_ICONO = "/res/imagenes/EliminarListaIcono.png",
	ICONO_ABRIR = "/res/imagenes/abrirIcono.png",
	PAPELERA_ICONO = "/res/imagenes/papeleraIcono.png",
	VER_DETALLES_ICON = "/res/imagenes/infoIcon.png",

	MENSAJE_SIN_CONEXION = "/res/imagenes/SinConexion.png",
	MENSAJE_SIN_COINCIDENCIAS = "/res/imagenes/SinCoincidencias.png",
	MENSAJE_SIN_TEXTO = "/res/imagenes/SinTexto.png",
	MENSAJE_SQL_INYECCION = "/res/imagenes/SqlInyeccion.png",
	MENSAJE_POCO_TEXTO= "/res/imagenes/pocoTexto.png",

	MYSQL_LETRA_SERVIDOR = leerLetraServidor(),
	MYSQL_SILENT_START = RUTA_SERVER+"mysql_start_noConsole.bat",
	MYSQL_STOP = RUTA_SERVER+"mysql_stop_noWarning.bat",
	MYSQL_RESTART = RUTA_SERVER+"mysql_Restart.bat",
	MYSQL_RUTA_SERVER = "jdbc:mysql://localhost:",	//MySQL //3311/
	//MYSQL_RUTA_SERVER = "jdbc:sqlite:content.db",	//SQLite
	MYSQL_NOMBRE_BBDD = "WSE",
	MYSQL_LOGIN_USUARIO = "root",
	MYSQL_LOGIN_PASS = "root",
	MYSQL_PUERTO = "3311",
	MYSQL_COLUMNAS = "nombre, extension, ruta, bytes, tamano, visible, lectura, escritura, fecha, hora, owner",

	FICHERO_LOG = "log.txt",
	FICHERO_XML = "config.xml",

	FUENTE_ROBOTO =	"/res/fonts/Roboto-Light.ttf";

	public static final int 
	MYSQL_NUMERO_COLUMNAS = NOMBRE_COLUMNAS.length,
	MYSQL_NUMERO_TABLAS = MYSQL_NOMBRE_TABLAS.length,*/

	/**
	 * Realiza una consulta a la BBDD
	 * @param server			conexion con la BBDD
	 * @param comandoSQL		consulta a realizar
	 * @return					datos que devuelve la consulta (en caso que retorne algo)
	 */
	public static ArrayList<ArrayList<String>> realizarConsultaSQL(ServidorSQL server, String comandoSQL){
		ArrayList<ArrayList<String>> filasRecuperadas = new ArrayList<ArrayList<String>>();
		try{
			if(!server.isConnected())
				server.openResultSet();
			server.enviarComando(comandoSQL);
			Util.debug(comandoSQL);
			ResultSet res = server.getResultSet();
			ArrayList<String> filaLeida = new ArrayList<String>();
			while(res.next()){
				filaLeida = new ArrayList<String>();
				filaLeida= leerFila(res, 1, filaLeida);//lee la fila entera
				filasRecuperadas.add(filaLeida);//guarda la fila
			}
		}
		catch (SQLException e){
			Util.debug("ERROR: "+e.getMessage());
		}
		return filasRecuperadas;
	}

	/**
	 * Muestra en consola los datos obtenidos
	 * @param resp		Respuesta obtenida al realizar una consulta
	 */
	public static void mostrarConsulta(ArrayList<ArrayList<String>> resp){
		for (int i = 0; i < resp.size(); i++) {
			for (int j = 0; j < resp.get(i).size(); j++) {
				System.out.print(resp.get(i).get(j)+"\t");
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * @param res 	Objeto ResultSet 
	 * @param i		indice de la columna actual
	 * @param l		datos leidos y guardados de la consulta de esa fila
	 * @return devuelve los datos de una fila
	 */
	public static ArrayList<String> leerFila(ResultSet res, int i, ArrayList<String>l) {
		String rec="";
		try {
			while((rec=res.getString(i))!=null){
				l.add(rec);
				i++;
			}
		} catch (SQLException e) {
			return l;
		}
		return l;
	}

	/**
	 * Metodo que muestra la informacion en pantalla dependiendo del
	 * estado de la variable DEBUG
	 * @param o		Objeto a mostrar
	 */
	public static void debug(Object o){
		if(DEBUG)
			if(o==null)
				System.out.println("null");
			else
				System.out.println(o.toString());
	}

	//borrar esto, GRACIAS
	/**
	 * Lee el fichero de configuracion XML y establece una conexion con el servidor con
	 * los datos del fichero. En caso de ser erroneos, se establece la configuracion por defecto.
	 * @return devuelve una conexion con el servidor utilizando el objeto ServidorSQL
	 */
	public static ServidorSQL cargarConfigServidor() {
		ServidorSQL server = null;
		if(!new File(Util.FICHERO_XML).exists()){
			Util.debug("Configuracion del servidor SQL personalizada por el usuario no encontrada");
			//por defecto
			return ServidorSQL.getInstance(Util.MYSQL_LOGIN_USUARIO, Util.MYSQL_LOGIN_PASS);
		}
		else{
			String jdbc = XML.leerDato("txtDir");
			String us = XML.leerDato("txtUser");
			String pass = XML.leerDato("passwordField");
			String puerto = XML.leerDato("txtPort");
			Util.debug(jdbc);
			Util.debug(puerto);
			Util.debug(us);
			Util.debug(pass);
			boolean correctos = us!=null && jdbc!=null && pass!=null && puerto!=null;
			Util.debug("Datos correctos? "+correctos);
			if(correctos){
				Util.debug(us+" "+pass);
				server = ServidorSQL.getInstance(us, pass);
				server.conectarComo(jdbc, puerto, Util.MYSQL_NOMBRE_BBDD);
				Util.debug(jdbc+":"+puerto+"/");
				Util.debug("Configuracion del Servidor cargada correctamente");
				return server;
			}
			else{
				if(ServidorSQL.numeroDeConexiones==0){
					Util.debug("Datos de configuracion del servidor erroneos. Usando configuracion por defecto");
					server = ServidorSQL.getInstance(Util.MYSQL_LOGIN_USUARIO, Util.MYSQL_LOGIN_PASS);
				}
				else if (ServidorSQL.numeroDeConexiones==1)
					Util.debug("No se realizar� ningun cambio en la conexion");
			}
			return server;
		}
	}

	/**
	 * Borra todos los datos de la BBDD y la configuracion del usuario guardada en el fichero XML
	 * @param c
	 */
	public static void borrarTodo(Component c) {
		int paso1 = ServidorSQL.getInstance().enviarComando("DROP DATABASE IF EXISTS "+Util.MYSQL_NOMBRE_BBDD+";") ? 1 : 0;
		int paso2 = new File(Util.FICHERO_XML).delete() ? 1 : 0;
		String texto="";
		if(paso1==1)
			texto="los datos han sido borrados";
		if(paso2==1)
			texto="la configuracion de usuario ha sido borrada";
		if(paso1==1 && paso2==1)
			texto="los datos y la configuracion de usuario han sido borrados";
		if(paso1+paso2>=1)
			JOptionPane.showMessageDialog(c, "VALORES POR DEFECTO REESTABLECIDOS\r\n"+texto,"Datos borrados",JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	/**
	 * Carga una fuente que no esta instalada en el sistema para usarla
	 * @param name	ruta de la fuente en el repostorio
	 * @param tipo	tipo de fuente: negrita, cursiva
	 * @param tamano	tama�o de la fuente
	 * @return devuelve un objeto fuente con la fuente seleccionada
	 */
	public static Font getFont(String name, int tipo, float tamano) {
		Font font = new JLabel().getFont();	//carga la fuente por defecto
		if (name != null) {
			try {
				InputStream is = Util.class.getResourceAsStream(name);
				font = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(tipo, tamano);
			} catch (Exception ex) {
				Util.debug(name + " not loaded.  Using default font.");
				//font = new Font("serif", Font.PLAIN, 24);
			}
		}
		return font;
	}
	/**
	 * Asigna a las ventanas emergentes la fuente de texto especificada en los
	 * parametros
	 * @param name		nombre de la fuente
	 * @param tipo		tipo de fuante: normal, negrita, cursiva
	 * @param tamano	tama�o de la fuente
	 */
	public static void setTipografia(String name, int tipo, float tamano){
		UIManager.put("OptionPane.messageFont", new FontUIResource(Util.getFont(name, tipo, tamano)));		
	}

	/**
	 * Reescala una imagen a las medidas necesarias
	 * @param srcImg		imagen original que se va a reescalar
	 * @param w				numero de pixeles en ancho
	 * @param h				numero de pixeles en alto
	 * @return				devuelve la imagen reescalada a las medidas indicadas
	 */
	public static ImageIcon getScaledImage(ImageIcon srcImg, int w, int h){
		if(w<0 || h<0)
			return srcImg;
		else{

			//fuente http://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
			BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
			Graphics2D g2 = resizedImg.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(srcImg.getImage(), 0, 0, w, h, null);
			g2.dispose();
			return new ImageIcon(resizedImg);

		}
	}
}
