package view.eventos.principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import controller.GUIController;
import twitter4j.ResponseList;
import twitter4j.User;
import view.elementos.GuiTwitterUsuario;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelBusqueda;

public class EventoClickBuscar implements MouseListener {

	private PanelBusqueda pb;
	
	public EventoClickBuscar(PanelBusqueda panelBusqueda) {
		// TODO Auto-generated constructor stub
		pb = panelBusqueda;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
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
		// TODO Auto-generated method stub
		
	}

	private void buscarUsuarios(String str) {
		ArrayList<ObjetoCelda> users = GUIController.getInstance().buscarUsuarios(str, 50);
		pb = new PanelBusqueda(users);
		/*pb.setObjeto(users);
		pb.actualizarTabla();*/
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
