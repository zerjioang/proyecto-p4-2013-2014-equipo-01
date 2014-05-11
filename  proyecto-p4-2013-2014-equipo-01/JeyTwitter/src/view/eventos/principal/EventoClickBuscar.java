package view.eventos.principal;

import hilos.HiloBuscar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import model.Tweet;
import controller.GUIController;
import twitter4j.ResponseList;
import twitter4j.User;
import view.elementos.GUITweet;
import view.elementos.GuiTwitterUsuario;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelBusqueda;
import view.ventanas.Principal;

public class EventoClickBuscar implements MouseListener {

	private PanelBusqueda pb;
	
	public EventoClickBuscar(PanelBusqueda panelBusqueda) {
		pb = panelBusqueda;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		//new HiloBuscar(pb.getBusqString()).start();
		String str = pb.getBusqString();
		if(str.startsWith("@")){
			//se buscaran usuarios
			buscarUsuarios(str);
		}
		else{
			//se buscaran tweets
			buscarTweets(str);
		}
	}
	
	private void buscarTweets(String str) {
		GUIController.getInstance().buscarTweets(str);
		
		ArrayList<ObjetoCelda> tuits = GUIController.getInstance().buscarUsuarios(str, 2);
		pb = new PanelBusqueda(tuits);
		GUIController.getInstance().getGui().setPanelBusqueda(pb);
		GUIController.getInstance().getGui().setPanelActual(pb);
	}

	private void buscarUsuarios(String str) {
		GUIController.getInstance().buscarUsuarios(str, 50);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
