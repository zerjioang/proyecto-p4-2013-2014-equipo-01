package _launcher;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.ImageIcon;

//import com.apple.eawt.Application;








import controller.GUIController;
import controller.sql.Interaccion;
import util.Util;
import view.ventanas.Principal;
import view.ventanas.Splash;
import view.ventanas.Bienvenida;
import view.ventanas.TerminosCondiciones;
/**
 * Clase principal que inicia el programa
 * @author JeyTuiter Dev Team
 *
 */
public class Launcher {
	
	private static Splash spl;
	
	public static void main(String[] args) {
		// Icono para OSX
		if(Util.isMac()){
			try {
				//Con reflectividad
				Image icon = new ImageIcon(Launcher.class.getResource(Util.APP_ICONO)).getImage();
				Class c = Class.forName("com.apple.eawt.Application");
				Method m = c.getMethod("getApplication",null);
				Object applicationInstance = m.invoke(null);
				m = applicationInstance.getClass().getMethod("setDockIconImage", javax.swing.ImageIcon.class);
				m.invoke(applicationInstance,icon);
			} catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*Application application = Application.getApplication();
		Image image = new ImageIcon(Launcher.class.getResource("/res/images/macicon.png")).getImage();
		application.setDockIconImage(image);
		*/
		/*double media=0;
		for (int i = 0; i < 20; i++) {
			long antes = System.currentTimeMillis();
			GUIController.getInstance().hayConexion();
			long despues = System.currentTimeMillis();
			double s =  ((despues-antes)/1000.0);
			media+=s;
			System.out.println(s);
		}
		System.err.println("Valor medio: "+media/20.0);
		System.exit(0);*/
			// TODO Auto-generated catch block
		
		if(!new File(Util.SQLITE_NOMBRE_BBDD).exists()){
			TerminosCondiciones t = new TerminosCondiciones();
			t.setLocationRelativeTo(null);
			t.setModal(true);
			t.setVisible(true);
			if(!t.isCondicionesAceptadas()){
				return;
			}
			Interaccion.crearEstructura();//Crea la estructura de la BD si no está el archivo *.sqlite
		}
		spl = new Splash();
		spl.mostrar(5);
		
		GUIController g = new GUIController();
		mostrarMensaje("Conectando con Twitter");
		if(!g.hayConexion()){
			mostrarMensaje("Ejecutandose en modo Offline");
			//No hay conexion a internet
			boolean resp = Util.showError(null, "Error de conexion", Util.APP_TITULO+" se mostrara en modo offline", "Cancelar", "Aceptar");
			if(resp){
				/*Se debe comprobar que el usuario tiene los credenciales guardados en la bd*/
				if(GUIController.getInstance().recuperarTokenUsuarioGuardado())
					mostrarPrincipal();
				else{
					//El usuario no existe y no tiene Internet para autentificarse por lo tanto salir
					Util.showError(null, Util.APP_TITULO+" se cerrara", "No tiene sesion iniciada en "+Util.APP_TITULO, "Cancelar", "Aceptar");
					spl.dispose();
					System.exit(1);
				}
			}
			else{
				//No hay internet y el usuario no quiere usarlo en modo offline
				spl.dispose();
				System.exit(1);
			}
		}
		else{
			//Si hay conexion a internet
			//Se evalua el token de acceso
			mostrarMensaje("Autentificando usuario...");
			if (GUIController.getInstance().recuperarTokenUsuarioGuardado())
				mostrarPrincipal();
			else
				mostrarBienvenida();
		}
		spl.dispose();
	}

	public static void mostrarMensaje(String texto) {
		if(spl!=null && spl.isVisible()){
			spl.setTextoMensaje(texto);
		}
	}

	/**
	 * Muestra la ventana necesaria para introducir los datos de autentificacion
	 */
	private static void mostrarBienvenida() {
		Bienvenida wc = new Bienvenida();
		wc.setVisible(true);
	}

	/**
	 * Muestra la ventana de bienvenida e inmediatamente muestra la timeline
	 * @throws Exception 
	 */
	private static void mostrarPrincipal() {
		// Tenemos token, lanzamos la ventana principal
		//Este usuario es el usuario que tiene la sesion de twitter abierta y que tiene que ser cargado
		//de la bd o online dependiendo de si esta conectado o no
		Launcher.mostrarMensaje("Esperando datos...");
		Principal p = new Principal(GUIController.getInstance().getUsuarioRegistrado());
		GUIController.getInstance().setGui(p);
		p.setPanelActual(p.getPaneles()[1]);
		p.setVisible(true);
		//GUIController.getInstance().iniciarStreaming();
	}
}
