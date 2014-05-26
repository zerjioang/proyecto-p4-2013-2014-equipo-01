package hilos;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Tweet;
import twitter4j.Paging;
import util.Util;
import view.elementos.Cache;
import view.elementos.GUITweet;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelPerfilUsuario;
import view.elementos.paneles.PanelTablaTweets;
import controller.GUIController;

public class HiloMenciones extends Thread{

	PanelTablaTweets panel;
	
	public HiloMenciones(PanelTablaTweets panelMenciones) {
		panel = panelMenciones;
	}
	

	public void run(){
		GUIController.getInstance().getGui().mostrarMensaje("Cargando menciones...");
		ArrayList<ObjetoCelda> listaObjetos = new ArrayList<ObjetoCelda>();
		try {
			ArrayList<Tweet> li = GUIController.getInstance().mostrarMenciones();
			for (Tweet tweet : li) {
				listaObjetos.add(0, new GUITweet(Util.calcularFecha(tweet.getUltimaFechaActualizacion()), tweet));
				panel.getTabla().insertarNuevo(listaObjetos.get(0));
				panel.getTabla().actualizarAltoFilas();
			}
			
			boolean activo = false;
			ArrayList<Thread> hilosActivos = AlmacenHilos.lista;
			for (Thread t : hilosActivos) {
				if(t.isAlive()) {
					activo = true;
					break;
				}
			}
			
			if(!activo) {
				GUIController.getInstance().getGui().ocultarMensajeInformativo();				
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
