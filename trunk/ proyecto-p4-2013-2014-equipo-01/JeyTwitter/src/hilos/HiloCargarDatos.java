package hilos;

import util.Util;
import view.systray.SystemTrayLogo;
import view.ventanas.Bienvenida;
import view.ventanas.Principal;
import controller.GUIController;
import controller.TwitterService;

public class HiloCargarDatos extends Thread{

	private final Bienvenida ventana;
	
	public HiloCargarDatos(Bienvenida ventana) {
		this.ventana = ventana;
	}

	public void run(){
		if(ventana.getCodeField().evaluate()){
			try {
				/*
				 * esto deberia ser
				 * 1 mostrar mensaje de cargar datos
				 * 2 descargar datos de twitter
				 * 3 mostrar ventana principal
				 */
				GUIController.getInstance().guardarUsuario(ventana.getCodigo());
				Principal p = new Principal(GUIController.getInstance().getUsuarioRegistrado());
				GUIController.getInstance().setGui(p);
				p.setLocationRelativeTo(ventana);
				p.setPanelActual(p.getPaneles()[1]);
				p.setVisible(true);
				//la ventana se minimiza y aparece un globo emergente
				//Util.showMessage(ventana, "Autentificacion OAuth", "Usuario autentificado correctamente", "Continuar", "Cancelar");
				ventana.cerrar();
				//aparece el globo emergente
				SystemTrayLogo l = SystemTrayLogo.getInstace();
				l.enviarMensaje("Descargando datos", Util.APP_TITULO+" esta descargando los datos mas recientes");
				l.setTitulo("descargando datos");
			} catch (IllegalStateException /*| InvalidInputException*/ e) {
				ventana.setVisible(true);
				Util.showError(ventana, "Error de autentificacion", "El token no es valido", "Cancelar", "Aceptar");
			}
		}
	}
}
