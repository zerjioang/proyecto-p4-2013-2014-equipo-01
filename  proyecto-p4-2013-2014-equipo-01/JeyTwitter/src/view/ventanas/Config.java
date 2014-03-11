package view.ventanas;


import util.Util;
import view.elementos.botones.CoolBlueButton;
import view.eventos.settings.EventoTablaSettings;
import view.models.ModeloTablaSettings;
import view.models.tablasConfig.ModeloTablaConfigGeneral;
import view.parents.CustomJFrame;
import view.renderers.BinaryButtonRenderer;
import view.renderers.UIButtonRenderer;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Color;

import javax.swing.JTable;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.FlowLayout;

public class Config extends CustomJFrame{
	
	private JTable table;
	private static final Color COLOR_FONDO = new Color(24,22,23);
	private JTable tablaOpciones;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Config frame = new Config();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Config(int ancho, int alto) {
		super(ancho, alto);
		init();
	}
	
	public Config() {
		super(567,600);
		setTitle("Configuración de "+Util.APP_TITULO);
		getMainPanel().setBackground(COLOR_FONDO);
		init();
	}

	public Config(Principal principal) {
		this();
		setLocationRelativeTo(principal);
	}

	private void definirColumnas(int i, int j, JTable table) {
		table.getColumnModel().getColumn(i).setPreferredWidth(j);
		table.getColumnModel().getColumn(i).setMinWidth(j);
		table.getColumnModel().getColumn(i).setMaxWidth(j);
	}

	private void init() {
		
		setDisposeWindow(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(168, 0, 393, 543);
		scrollPane.setBackground(COLOR_FONDO);
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		
		table = new JTable();
		table.setFocusable(false);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setTableHeader(null);
		table.setRowSelectionAllowed(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setShowGrid(false);
		table.setRowHeight(40);
		table.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 18));
		table.setForeground(Color.WHITE);
		table.setBackground(COLOR_FONDO);
		table.setBorder(null);
		table.setModel(new ModeloTablaConfigGeneral());
		
		//se define el render de la tabla
		BinaryButtonRenderer render = new BinaryButtonRenderer();
		table.getColumnModel().getColumn(1).setCellRenderer(render);
		definirColumnas(1, 100, table);
		scrollPane.setViewportView(table);
		
		//se define el listener de la tabla
		table.addMouseListener(new EventoTablaSettings(table));
		
		JScrollPane panelLateral = new JScrollPane();
		panelLateral.setBorder(null);
		panelLateral.setBounds(0, 0, 162, 543);
		
		tablaOpciones = new JTable();
		tablaOpciones.setRowHeight(40);
		tablaOpciones.setForeground(Color.WHITE);
		tablaOpciones.setFont(Util.getFont("Roboto-regular", Font.PLAIN, 15));
		tablaOpciones.setBorder(null);
		tablaOpciones.setBackground(COLOR_FONDO);
		tablaOpciones.setModel(new ModeloTablaSettings());
		panelLateral.add(tablaOpciones);
		panelLateral.setViewportView(tablaOpciones);
		
		//se define el render de la tabla
		UIButtonRenderer renderLateral = new UIButtonRenderer();
		//tablaOpciones.getColumnModel().getColumn(0).setCellRenderer(render);
		//definirColumnas(0, 100, tablaOpciones);
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBounds(338, 549, 223, 24);
		panelInferior.setBackground(COLOR_FONDO);
		
		CoolBlueButton lblCancelar = new CoolBlueButton("Cancelar");
		CoolBlueButton lblAceptar = new CoolBlueButton("Aceptar");
		
		JLabel lblResize = new JLabel("");
		lblResize.setIcon(new ImageIcon(Config.class.getResource("/res/images/miniResizeIcon.png")));
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelInferior.add(lblCancelar);
		panelInferior.add(lblAceptar);
		panelInferior.add(lblResize);
		getMainPanel().setLayout(null);
		getMainPanel().add(panelLateral);
		getMainPanel().add(scrollPane);
		getMainPanel().add(panelInferior);
	}
}
