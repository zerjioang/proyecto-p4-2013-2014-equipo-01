package view.ventanas;

import util.Util;
import view.elementos.botones.CoolBlueButton;
import view.eventos.settings.EventoTablaSettings;
import view.models.ModeloTablaSettings;
import view.models.tablasConfig.EventoCambiarSettings;
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
		BinaryButtonRenderer render = new BinaryButtonRenderer();
		tablaDer.getColumnModel().getColumn(1).setCellRenderer(render);
		definirColumnas(1, 100, tablaDer);
		scrollPane.setViewportView(tablaDer);
		
		//se define el listener de la tabla
		tablaDer.addMouseListener(new EventoTablaSettings(tablaDer));
		
		JScrollPane panelLateral = new JScrollPane();
		panelLateral.setBorder(null);
		panelLateral.setBounds(0, 0, 162, 543);
		
		tablaIzq = new JTable();
		tablaIzq.setRowHeight(40);
		tablaIzq.setForeground(Color.WHITE);
		tablaIzq.setFont(Util.getFont("Roboto-regular", Font.PLAIN, 15));
		tablaIzq.setBorder(null);
		tablaIzq.setBackground(COLOR_FONDO);
		tablaIzq.setModel(new ModeloTablaSettings());
		panelLateral.add(tablaIzq);
		panelLateral.setViewportView(tablaIzq);
		
		//se define el render de la tabla
		UIButtonRenderer render2 = new UIButtonRenderer();
		tablaIzq.getColumnModel().getColumn(0).setCellRenderer(render2);
		
		//se define el listener de la tabla de la izquierda 
		// que cambia la tabla de opciones
		tablaIzq.addMouseListener(new EventoCambiarSettings(this));
		
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
