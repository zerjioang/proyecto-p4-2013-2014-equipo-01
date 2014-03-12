package view.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;

import util.Util;
import view.eventos.principal.EventoCambiarPrincipal;
import view.eventos.principal.EventoClickAcercaDe;
import view.eventos.principal.EventoClickConfig;
import view.eventos.principal.EventoClickHelp;
import view.models.ModeloTablaPrincipal;
import view.models.tablasConfig.EventoCambiarSettings;
import view.parents.CustomJFrame;
import view.renderers.UIButtonRenderer;

import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.Cursor;

import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

public class Principal extends CustomJFrame {

	//private JPanel contentPane;
	private JTable tablaMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		super(600, 700);
		setTitle(Util.APP_TITULO);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainPanel().setBackground(Color.DARK_GRAY);
		getMainPanel().setBorder(new EmptyBorder(0, 0, 0, 0));
		getMainPanel().setLayout(new BorderLayout(0, 0));
		
		JPanel panelIzq = new JPanel();
		panelIzq.setBackground(Color.DARK_GRAY);
		getMainPanel().add(panelIzq, BorderLayout.WEST);
		panelIzq.setLayout(new BorderLayout(0, 0));
		
		JLabel lblImagen = new JLabel("Nombre Apellidos");
		lblImagen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImagen.setForeground(Color.WHITE);
		lblImagen.setBackground(Color.DARK_GRAY);
		lblImagen.setFont(Util.getFont("Roboto-regular", Font.PLAIN, 14));
		lblImagen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImagen.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblImagen.setVerticalAlignment(SwingConstants.BOTTOM);
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setIcon(new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")));
		panelIzq.add(lblImagen, BorderLayout.NORTH);
		
		tablaMenu = new JTable();
		tablaMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaMenu.setBackground(Color.DARK_GRAY);
		tablaMenu.setRowHeight(40);
		tablaMenu.setModel(new ModeloTablaPrincipal());
		tablaMenu.addMouseListener(new EventoCambiarPrincipal(this));
		tablaMenu.getColumnModel().getColumn(0).setCellRenderer(new UIButtonRenderer());
		
		panelIzq.add(tablaMenu, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panelIzq.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblConfig = new JLabel("Config");
		lblConfig.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblConfig.setForeground(Color.WHITE);
		lblConfig.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblConfig.setHorizontalTextPosition(SwingConstants.CENTER);
		lblConfig.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfig.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		lblConfig.setSize(new Dimension(30,30));
		lblConfig.setIcon(new ImageIcon(Principal.class.getResource("/res/tempIcons/configIcon.png")));
		lblConfig.setIcon(Util.escalarImagen(lblConfig));
		panel.add(lblConfig, BorderLayout.NORTH);
		lblConfig.addMouseListener(new EventoClickConfig(this));
		
		JLabel lblHelp = new JLabel("Ayuda");
		lblHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHelp.setForeground(Color.WHITE);
		lblHelp.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblHelp.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		lblHelp.setSize(new Dimension(30,30));
		lblHelp.setIcon(new ImageIcon(Principal.class.getResource("/res/tempIcons/helpIcon.png")));
		lblHelp.setIcon(Util.escalarImagen(lblHelp));
		panel.add(lblHelp, BorderLayout.CENTER);
		lblHelp.addMouseListener(new EventoClickHelp(this));
		
		JLabel lblAbout = new JLabel("Acerca de");
		lblAbout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAbout.setForeground(Color.WHITE);
		lblAbout.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblAbout.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		lblAbout.setSize(new Dimension(30,30));
		lblAbout.setIcon(new ImageIcon(Principal.class.getResource("/res/tempIcons/acercaDeIcon.png")));
		lblAbout.setIcon(Util.escalarImagen(lblAbout));
		panel.add(lblAbout, BorderLayout.SOUTH);
		lblAbout.addMouseListener(new EventoClickAcercaDe(this));
		
		JPanel panelApp = new JPanel();
		panelApp.setBorder(new LineBorder(new Color(0, 0, 0)));
		getMainPanel().add(panelApp, BorderLayout.CENTER);
		panelApp.setLayout(new BorderLayout(0, 0));
		
		JPanel panelInferior = new JPanel();
		panelApp.add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new BorderLayout(0, 0));
		
		JTextPane txtMensaje = new JTextPane();
		txtMensaje.setText("escribe aqui tu tweet");
		panelInferior.add(txtMensaje, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Enviar");
		panelInferior.add(btnNewButton, BorderLayout.EAST);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		panelApp.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_inicio = new JPanel();
		tabbedPane.addTab("panel_inicio", null, panel_inicio, null);
		
		JPanel panel_timeLine = new JPanel();
		tabbedPane.addTab("panel_timeLine", null, panel_timeLine, null);
		
		JPanel panel_Menciones = new JPanel();
		tabbedPane.addTab("panel_Menciones", null, panel_Menciones, null);
		
		JPanel panel_reTweets = new JPanel();
		tabbedPane.addTab("panel_reTweets", null, panel_reTweets, null);
		
		JPanel panel_favoritos = new JPanel();
		tabbedPane.addTab("panel_favoritos", null, panel_favoritos, null);
		
		JPanel panel_busqueda = new JPanel();
		tabbedPane.addTab("panel_busqueda", null, panel_busqueda, null);
	}

	/**
	 * @return the tablaMenu
	 */
	public JTable getTablaMenu() {
		return tablaMenu;
	}

	/**
	 * @param tablaMenu the tablaMenu to set
	 */
	public void setTablaMenu(JTable tablaMenu) {
		this.tablaMenu = tablaMenu;
	}
}
