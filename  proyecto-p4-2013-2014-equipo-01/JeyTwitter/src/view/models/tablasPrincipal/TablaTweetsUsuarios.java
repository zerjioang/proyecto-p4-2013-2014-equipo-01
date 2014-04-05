package view.models.tablasPrincipal;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import util.Util;
import view.elementos.paneles.GUITweet;
import view.elementos.paneles.GuiTwitterUsuario;
import view.elementos.paneles.ObjetoCelda;
import view.models.ModeloTablaLateral;
import view.renderers.TweetEditor;
import view.renderers.TweetRenderer;
import view.renderers.UsuarioEditor;
import view.renderers.UsuarioRenderer;

public class TablaTweetsUsuarios extends JTable {
	
	//Constantes
	public static final int SOLO_USUARIOS = 0;
	public static final int SOLO_TWEETS = 1;
	public static final int ALTO_FILA = 90;
	
	private ArrayList<ObjetoCelda> listaObjetos;
	private int tipoTabla;

	public TablaTweetsUsuarios(int tipo) {
		super();
		tipoTabla = tipo;
		init();
	}
	
	public TablaTweetsUsuarios(ArrayList<ObjetoCelda> objeto) {
		super();
		this.listaObjetos = objeto;
		init();
	}

	private void init() {;
		setFocusable(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setTableHeader(null);
		setRowSelectionAllowed(true);
		setShowHorizontalLines(false);
		setShowVerticalLines(false);
		setCellSelectionEnabled(true);
		setShowGrid(false);
		setRowHeight(ALTO_FILA);
		setFont(Util.getFont("Roboto-Light", Font.PLAIN, 18));
		setBorder(null);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setFocusTraversalPolicyProvider(true);
		setFocusCycleRoot(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBackground(Color.BLACK);
		
		if(listaObjetos!=null && listaObjetos.size()>0){
			actualizarTabla(new ModeloTablaTweetUsuarios(listaObjetos));
		}
		else
			setRenderCelda();
	}

	/**
	 * 
	 */
	private void actualizarTabla(ModeloTablaTweetUsuarios modeloTabla) {
		setModel(modeloTabla);
		tipoTabla = listaObjetos.get(0).tipoObjeto();
		setRenderCelda();
	}
	
	private void setRenderCelda(){
		if(tipoTabla == SOLO_TWEETS){
			setDefaultRenderer(GUITweet.class, new TweetRenderer());
			setDefaultEditor(GUITweet.class, new TweetEditor());
		}
		else if(tipoTabla == SOLO_USUARIOS){
			setDefaultRenderer(GuiTwitterUsuario.class, new UsuarioRenderer());
			setDefaultEditor(GuiTwitterUsuario.class, new UsuarioEditor());
		}
		repaint();
	}
	
	public boolean isCellEditable(int row, int column){
		return true;
	}
	
	public void insertarNuevo(ObjetoCelda o){
		ModeloTablaTweetUsuarios modelo;
		modelo = new ModeloTablaTweetUsuarios(listaObjetos);
		
		modelo.insertarElemento(o);
		
		listaObjetos = modelo.getLista();
		actualizarTabla(modelo);
	}

	public void insertarLista(ArrayList<ObjetoCelda> l) {
		ModeloTablaTweetUsuarios modelo;
		modelo = new ModeloTablaTweetUsuarios(listaObjetos);
		
		for (ObjetoCelda o : l) {
			modelo.insertarElemento(o);
			modelo.actualizarContenidoTabla();
		}
		
		
		listaObjetos = modelo.getLista();
		actualizarTabla(modelo);
	}
}
