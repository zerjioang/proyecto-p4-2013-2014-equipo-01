import java.sql.Date;

import javax.swing.ImageIcon;

import model.Usuario;
import controller.GUIController;
import util.Util;
import view.ventanas.Principal;
import view.ventanas.Splash;
import view.ventanas.Welcome;
/**
 * Clase principal que inicia el programa
 * @author JeyTuiter Dev Team
 *
 */
public class Launcher {

	public static void main(String[] args) {
		Splash spl = new Splash();
		spl.mostrar(5);
		
		GUIController g = new GUIController();
		//Cuando no hay conexion el metodo g.hayConexion() tarda mucho en detectar si hay conexion o no
		if(!g.hayConexion()){
			//No hay conexion a internet
			boolean resp = Util.showError(null, "Error de conexion", Util.APP_TITULO+" se mostrara en modo offline", "Cancelar", "Aceptar");
			if(resp){
				if(resp /*Se debe comprobar que el usuario tiene los credenciales guardados en la bd*/)
					mostrarPrincipal();
				else{
					//El usuario no existe y no tiene Internet para autentificarse por lo tanto salir
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
			if (GUIController.getInstance().esTokenValido())
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
		Welcome wc = new Welcome();
		wc.setVisible(true);
	}

	/**
	 * Muestra la ventana de bienvenida e inmediatamente muestra la timeline
	 */
	private static void mostrarPrincipal() {
		// Tenemos token, lanzamos la ventana principal
		//Este usuario es el usuario que tiene la sesion de twitter abierta y que tiene que ser cargado
		//de la bd o online dependiendo de si esta conectado o no
		Usuario u = new Usuario("@JeyTuiter", "5768745", "9864598", "Jey", "Hola me yamo J y mi padr me puso el nombre en onor a los ombres de nejro.", new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")).getImage(), new Date(1L), 10, 0, 2);
		//Como el usuario no existe el programa da error de ejecucion.
		Principal p = new Principal(u);
		p.setPanelActual(p.getPaneles()[1]);
		p.setVisible(true);
	}
}
