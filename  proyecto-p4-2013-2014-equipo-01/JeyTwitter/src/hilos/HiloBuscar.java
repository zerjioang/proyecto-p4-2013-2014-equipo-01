package hilos;

import controller.GUIController;

public class HiloBuscar extends Thread {

	private final String strBusqueda;

	public HiloBuscar(String string) {
		strBusqueda = string;
	}

	public void run(){
		if(strBusqueda.startsWith("@")){
			//se buscaran usuarios
			buscarUsuarios(strBusqueda);
		}
		else{
			//se buscaran tweets
			buscarTweets(strBusqueda);
		}
	}

	private void buscarTweets(String str) {
		GUIController.getInstance().buscarTweets(str);
	}

	private void buscarUsuarios(String str) {
		GUIController.getInstance().buscarUsuarios(str, 50);
	}

}
