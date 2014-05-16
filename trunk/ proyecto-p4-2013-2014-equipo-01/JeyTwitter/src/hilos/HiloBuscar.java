package hilos;

import java.io.IOException;

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
			try {
				buscarTweets(strBusqueda);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void buscarTweets(String str) throws IOException {
		GUIController.getInstance().buscarTweets(str);
	}

	private void buscarUsuarios(String str) {
		GUIController.getInstance().buscarUsuarios(str, 50);
	}

}
