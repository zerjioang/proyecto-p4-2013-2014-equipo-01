package view.elementos;

import java.util.ArrayList;
import java.util.HashMap;

import view.elementos.paneles.PanelPerfilUsuario;

public class Cache {

	private static Cache c;
	
	private HashMap<String, PanelPerfilUsuario> panelesUsuario;
	private HashMap<String, ArrayList<ObjetoCelda>> resultadosBusquedaTweet;
	private HashMap<String, ArrayList<ObjetoCelda>> resultadosBusquedaUsuario;
	
	public static Cache getInstance(){
		if(c==null){
			c = new Cache();
		}
		return c;
	}
	
	private Cache(){
		panelesUsuario = new HashMap<String, PanelPerfilUsuario>();
		resultadosBusquedaTweet = new HashMap<String, ArrayList<ObjetoCelda>>();
		resultadosBusquedaUsuario = new HashMap<String, ArrayList<ObjetoCelda>>();
	}
	
	public void addPanelUsuario(String nombreUsuario, PanelPerfilUsuario pu){
		panelesUsuario.put(nombreUsuario, pu);
	}
	
	public PanelPerfilUsuario getPanelPerfilUsuario(String nombreUsuario){
		return panelesUsuario.get(nombreUsuario);
	}

	public ArrayList<ObjetoCelda> getResultadosBusquedaTweet(String cadenaTexto) {
		return resultadosBusquedaTweet.get(cadenaTexto);
	}

	public void addResultadosBusquedaTweet(String cadenaTexto, ArrayList<ObjetoCelda> tablaResultado) {
		resultadosBusquedaTweet.put(cadenaTexto, tablaResultado);
	}

	public ArrayList<ObjetoCelda> getResultadosBusquedaUsuarios(String cadenaTexto) {
		return resultadosBusquedaUsuario.get(cadenaTexto);
	}

	public void addResultadosBusquedaUsuario(String cadenaTexto, ArrayList<ObjetoCelda> tablaResultado) {
		resultadosBusquedaUsuario.put(cadenaTexto, tablaResultado);
	}
}
