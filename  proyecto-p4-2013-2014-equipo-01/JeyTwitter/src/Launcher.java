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
		Splash spl = new Splash();
		spl.mostrar(5);
		
		GUIController g = new GUIController();
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
	 * @throws Exception 
	 */
	private static void mostrarPrincipal() {
		// Tenemos token, lanzamos la ventana principal
		//Este usuario es el usuario que tiene la sesion de twitter abierta y que tiene que ser cargado
		//de la bd o online dependiendo de si esta conectado o no
		Principal p = new Principal(GUIController.getInstance().getUsuarioRegistrado());
		GUIController.getInstance().setGui(p);
		p.setPanelActual(p.getPaneles()[1]);
		GUIController.getInstance().setGui(p);
		p.setVisible(true);
		//GUIController.getInstance().iniciarStreaming();
	}
}
