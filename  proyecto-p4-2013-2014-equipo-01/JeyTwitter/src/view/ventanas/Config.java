package view.ventanas;

import util.Util;
import view.elementos.botones.CoolBlueButton;
import view.eventos.config.EventoCerrarConfig;
import view.eventos.config.EventoConfirmarConfig;
import view.eventos.settings.EventoTablaSettings;
import view.models.ModeloTablaSettings;
import view.models.tablasConfig.EventoCambiarSettings;
import view.models.tablasConfig.ModeloTablaConfigGeneral;
import view.parents.CustomJDialogWithBar;
import view.renderers.BinaryButtonRenderer;
import view.renderers.MenuButtonRenderer;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Color;

import javax.swing.JTable;

import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Config extends CustomJDialogWithBar{
	
	private JTable tablaDer;
	private JTable tablaIzq;
	private static final Color COLOR_FONDO = new Color(24,22,23);

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

	public static void definirColumnas(int i, int j, JTable table) {
		table.getColumnModel().getColumn(i).setPreferredWidth(j);
		table.getColumnModel().getColumn(i).setMinWidth(j);
		table.getColumnModel().getColumn(i).setMaxWidth(j);
	}

	private void init() {
		
		setDisposeWindow(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(168, 0, 393, 543);
		scrollPane.setBackground(COLOR_FONDO);
		scrollPane.getViewport().setBackground(COLOR_FONDO); 
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		
		tablaDer = new JTable();
		tablaDer.setFocusable(false);
		tablaDer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaDer.setTableHeader(null);
		tablaDer.setRowSelectionAllowed(false);
		tablaDer.setShowHorizontalLines(false);
		tablaDer.setShowVerticalLines(false);
		tablaDer.setShowGrid(false);
		tablaDer.setRowHeight(40);
		tablaDer.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 18));
		tablaDer.setForeground(Color.WHITE);
		tablaDer.setBackground(COLOR_FONDO);
		tablaDer.setBorder(null);
		tablaDer.setModel(new ModeloTablaConfigGeneral());
		
		//se define el render de la tabla
		tablaDer.getColumnModel().getColumn(1).setCellRenderer(new BinaryButtonRenderer());
		definirColumnas(1, 100, tablaDer);
		scrollPane.setViewportView(tablaDer);
		
		//se define el listener de la tabla
		tablaDer.addMouseListener(new EventoTablaSettings(tablaDer));
		
		JScrollPane panelLateral = new JScrollPane();
		panelLateral.setBackground(COLOR_FONDO);
		panelLateral.getViewport().setBackground(COLOR_FONDO);
		panelLateral.setBorder(null);
		panelLateral.setBounds(0, 0, 162, 543);
		
		tablaIzq = new JTable();
		tablaIzq.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaIzq.setRowHeight(40);
		tablaIzq.setForeground(Color.WHITE);
		tablaIzq.setFont(Util.getFont("Roboto-regular", Font.PLAIN, 15));
		tablaIzq.setBorder(null);
		tablaIzq.setBackground(COLOR_FONDO);
		tablaIzq.setModel(new ModeloTablaSettings());
		tablaIzq.setTableHeader(null);
		panelLateral.add(tablaIzq);
		panelLateral.setViewportView(tablaIzq);
		
		//se define el render de la tabla
		tablaIzq.getColumnModel().getColumn(0).setCellRenderer(new MenuButtonRenderer());
		
		//se define el listener de la tabla de la izquierda 
		// que cambia la tabla de opciones
		tablaIzq.addMouseListener(new EventoCambiarSettings(this));
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBounds(10, 549, 551, 24);
		panelInferior.setBackground(COLOR_FONDO);
		getMainPanel().setLayout(null);
		getMainPanel().add(panelLateral);
		getMainPanel().add(scrollPane);
		getMainPanel().add(panelInferior);
		
		JPanel panelInferiorBotones = new JPanel();
		panelInferiorBotones.setBackground(COLOR_FONDO);
		panelInferiorBotones.setLayout(new BorderLayout(0, 0));
		
		CoolBlueButton lblCancelar = new CoolBlueButton("Cancelar");
		CoolBlueButton lblAceptar = new CoolBlueButton("Aceptar");
		
		panelInferiorBotones.add(lblCancelar, BorderLayout.WEST);
		panelInferiorBotones.add(lblAceptar, BorderLayout.EAST);
		
		//Evento del boton cancelar
		lblCancelar.addMouseListener(new EventoCerrarConfig(this));
		//Evento del boton aceptar
		lblCancelar.addMouseListener(new EventoConfirmarConfig(this));
		
		JLabel lblResize = new JLabel("");
		lblResize.setIcon(new ImageIcon(Config.class.getResource("/res/images/miniResizeIcon.png")));
		GroupLayout gl_panelInferior = new GroupLayout(panelInferior);
		gl_panelInferior.setHorizontalGroup(
			gl_panelInferior.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelInferior.createSequentialGroup()
					.addComponent(panelInferiorBotones, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblResize))
		);
		gl_panelInferior.setVerticalGroup(
			gl_panelInferior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferior.createSequentialGroup()
					.addGroup(gl_panelInferior.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panelInferiorBotones, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panelInferior.createSequentialGroup()
							.addGap(5)
							.addComponent(lblResize)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelInferior.setLayout(gl_panelInferior);
	}

	public JTable getTableConfigDerecha() {
		return tablaDer;
	}

	public void setTableConfigDerecha(JTable table) {
		this.tablaDer = table;
	}

	public JTable getTablaOpcionesIzq() {
		return tablaIzq;
	}

	public void setTablaOpcionesIzq(JTable tablaOpciones) {
		this.tablaIzq = tablaOpciones;
	}
}
