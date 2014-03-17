package view.models.tablasPrincipal;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import util.Util;
import view.renderers.TweetEditor;
import view.renderers.TweetRenderer;

public class TablaTweet extends JTable{

	private int total;
	public TablaTweet(){
		super();
		total = 0; //por default
		init();
	}

	public TablaTweet(int i) {
		super();
		total = i;
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
		setModel(new ModeloTablaTweets(total));
		
		//se define el render de la tabla
		//getColumnModel().getColumn(0).setCellRenderer(new TweetRenderer());
		//getColumnModel().getColumn(0).setCellEditor(new TweetEditor());
		
		setDefaultRenderer(GUITweet.class, new TweetRenderer());
		setDefaultEditor(GUITweet.class, new TweetEditor());
	}
	
	public boolean isCellEditable(int row, int column){
		return true;
	}
}
