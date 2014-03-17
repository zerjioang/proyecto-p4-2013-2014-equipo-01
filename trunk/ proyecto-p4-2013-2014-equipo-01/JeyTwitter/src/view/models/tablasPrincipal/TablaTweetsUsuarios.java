package view.models.tablasPrincipal;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import util.Util;
import view.renderers.TweetEditor;
import view.renderers.TweetRenderer;
import view.renderers.UsuarioEditor;
import view.renderers.UsuarioRenderer;

public class TablaTweetsUsuarios extends JTable {
	
	//Constantes
	public static final int SOLO_USUARIOS = 0;
	public static final int SOLO_TWEETS = 1;

	private int total;
	private int tipo;
	
	public TablaTweetsUsuarios(){
		super();
		total = 2; //por default
		init();
	}

	public TablaTweetsUsuarios(int i, int tipo) {
		super();
		total = i;
		this.tipo = tipo;
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
		setModel(new ModeloTablaTweetUsuarios(total, tipo));
		System.out.println("tipo "+tipo);
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
}
