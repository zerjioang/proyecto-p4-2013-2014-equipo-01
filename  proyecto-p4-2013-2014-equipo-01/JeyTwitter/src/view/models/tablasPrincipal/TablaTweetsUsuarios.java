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
	
	private ArrayList<ObjetoCelda> listaObjetos;

	public TablaTweetsUsuarios() {
		super();
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
		setRowHeight(110);
		setFont(Util.getFont("Roboto-Light", Font.PLAIN, 18));
		setBorder(null);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setFocusTraversalPolicyProvider(true);
		setFocusCycleRoot(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBackground(Color.BLACK);
		setShowGrid(false);
		setBorder(null);
		
		if(listaObjetos!=null && listaObjetos.size()>0){
			actualizarTabla(new ModeloTablaTweetUsuarios(listaObjetos));
		}
	}

	/**
	 * 
	 */
	private void actualizarTabla(ModeloTablaTweetUsuarios modeloTabla) {
		setModel(modeloTabla);
		int tipo = listaObjetos.get(0).tipoObjeto();
		if(tipo == SOLO_TWEETS){
			setDefaultRenderer(GUITweet.class, new TweetRenderer());
			setDefaultEditor(GUITweet.class, new TweetEditor());
		}
		if(tipo == SOLO_USUARIOS){
			setDefaultRenderer(GuiTwitterUsuario.class, new UsuarioRenderer());
			setDefaultEditor(GuiTwitterUsuario.class, new UsuarioEditor());
		}
	}
	
	public boolean isCellEditable(int row, int column){
		return true;
	}
	
	public void insertarNuevo(ObjetoCelda o){
		ModeloTablaTweetUsuarios modelo;
		modelo = new ModeloTablaTweetUsuarios(listaObjetos);
		System.out.println(modelo.getRowCount());
		modelo.insertarElemento(o);
		listaObjetos = modelo.getLista();
		System.out.println(modelo.getRowCount());
		actualizarTabla(modelo);
	}
}
