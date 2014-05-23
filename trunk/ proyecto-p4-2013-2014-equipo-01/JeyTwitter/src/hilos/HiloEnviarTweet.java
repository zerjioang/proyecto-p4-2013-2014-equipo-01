package hilos;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import model.Tweet;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import util.Util;
import view.elementos.GUITweet;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelEnviarTweet;
import view.elementos.paneles.PanelPerfilUsuario;
import view.elementos.paneles.PanelTablaTweets;
import view.models.tablasPrincipal.TablaTweetsUsuarios;
import view.ventanas.Principal;
import controller.GUIController;
import controller.TwitterService;

public class HiloEnviarTweet extends Thread {
	
	private PanelEnviarTweet panel;

	public HiloEnviarTweet(PanelEnviarTweet panel) {
		this.panel = panel;
	}

	public void run(){
		Status s = GUIController.getInstance().enviarTweet(panel.getMensaje());
		if(s!=null) {
			panel.setMensaje("");
			TablaTweetsUsuarios tablaTimeLine = panel.getVentanaPadre().getPanelTimeLine().getTabla();
			TablaTweetsUsuarios tablaUsuario = panel.getVentanaPadre().getPanelUsuario().getTablaTweetsUsuario();
			Tweet t = new Tweet(s);
			GUITweet gt = new GUITweet(Util.calcularFecha(new Date(System.currentTimeMillis())), t);
			tablaTimeLine.insertarNuevo(gt);
			tablaUsuario.insertarNuevo(gt);
			tablaTimeLine.actualizarFilas();
			tablaUsuario.actualizarFilas();
		} else {
			System.out.println("Ha ocurrido un error al enviar el tweet.");
		}			
	
	}
}
