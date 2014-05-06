import java.io.File;

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

	public static void main(String[] args) {
		
		if(new File(Util.SQLITE_NOMBRE_BBDD).exists()){
			TerminosCondiciones t = new TerminosCondiciones();
			t.setLocationRelativeTo(null);
			t.setVisible(true);
		}
		Splash spl = new Splash();
		spl.mostrar(5);
		
		GUIController g = new GUIController();
		Interaccion.crearEstructura();//Crea la estructura de la BD si no está el archivo *.sqlite
		if(!g.hayConexion()){
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
			if (GUIController.getInstance().recuperarTokenUsuarioGuardado())
				mostrarPrincipal();
			else
				mostrarBienvenida();
		}
		spl.dispose();
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
	 */
	private static void mostrarPrincipal() {
		// Tenemos token, lanzamos la ventana principal
		//Este usuario es el usuario que tiene la sesion de twitter abierta y que tiene que ser cargado
		//de la bd o online dependiendo de si esta conectado o no
		Principal p = new Principal(GUIController.getInstance().getUsuarioRegistrado());
		p.setPanelActual(p.getPaneles()[1]);
		p.setVisible(true);
	}
}
