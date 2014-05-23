package hilos;

import java.io.IOException;
import java.util.ArrayList;

import view.elementos.Cache;
import view.elementos.ObjetoCelda;
import controller.GUIController;

public class HiloBuscar extends Thread {

	private String strBusqueda;

	public HiloBuscar(String string) {
		strBusqueda = string;
	}

	public HiloBuscar() {
		
	}

	public void run(){
		strBusqueda = strBusqueda.toLowerCase();
		if(strBusqueda.startsWith("@")){
			//se buscaran usuarios
			buscarUsuarios(strBusqueda);
		}
		else{
			buscarTweets(strBusqueda);
		}
	}
	
	private void buscarTweets(String str) {
		mostrarMensaje("Buscando tweets...");
		try {
			//si la busqueda ha sido realizada y esta en la cache 'dinamica' mostrarla
			ArrayList<ObjetoCelda> listaTweets;
			listaTweets = Cache.getInstance().getResultadosBusquedaTweet(str);
			if(listaTweets==null || listaTweets.size()<=0){
				listaTweets = GUIController.getInstance().buscarTweets(str);
				Cache.getInstance().addResultadosBusquedaTweet(str, listaTweets);
			}
			else{
				GUIController.getInstance().actualizarPanelBusqueda(listaTweets);
			}
			mostrarMensaje("Tweets encontrados: "+listaTweets.size());
			sleep(2000);
			GUIController.getInstance().getGui().ocultarMensajeInformativo();
		} catch (IOException e) {
		} catch (InterruptedException e) {}
	}



	private void buscarUsuarios(String str) {
		mostrarMensaje("Buscando usuarios...");
		
		//si la busqueda ha sido realizada y esta en la cache 'dinamica' mostrarla
		ArrayList<ObjetoCelda> listaUsuarios;
		listaUsuarios = Cache.getInstance().getResultadosBusquedaUsuarios(str);
		if(listaUsuarios==null || listaUsuarios.size()<=0){
			listaUsuarios = GUIController.getInstance().buscarUsuarios(str, 2);
			Cache.getInstance().addResultadosBusquedaTweet(str, listaUsuarios);
		}
		else{
			GUIController.getInstance().actualizarPanelBusqueda(listaUsuarios);
		}
		mostrarMensaje("Usuarios encontrados: "+listaUsuarios.size());
	}

	private void mostrarMensaje(String string) {
		GUIController.getInstance().getGui().mostrarMensaje(string);
	}

	public void setStrBusqueda(String str) {
		strBusqueda = str;
	}

}
