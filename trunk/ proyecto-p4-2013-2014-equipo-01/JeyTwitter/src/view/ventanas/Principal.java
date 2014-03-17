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

import view.elementos.botones.BotonSeguir;
import view.elementos.paneles.PanelBusqueda;
import view.elementos.paneles.PanelEnviarTweet;
import view.elementos.paneles.PanelPerfilUsuario;
import view.eventos.principal.EventoCambiarColoBoton;
import view.eventos.principal.EventoCambiarPanelClick;
import view.eventos.principal.EventoClickAcercaDe;
import view.eventos.principal.EventoClickConfig;
import view.eventos.principal.EventoClickFotoUsuario;
import view.eventos.principal.EventoClickHelp;
import view.eventos.principal.EventoMaximizarVerticalmente;
import view.models.ModeloTablaPrincipal;
import view.models.tablasPrincipal.PanelTablaTweets;
import view.models.tablasPrincipal.TablaTweet;
import view.parents.CustomJFrame;
import view.renderers.UIButtonRenderer;

import java.awt.Font;
import java.awt.Cursor;

import javax.swing.border.LineBorder;

import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.border.MatteBorder;

import java.awt.Component;

import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Principal extends CustomJFrame {

	//Constantes
	private static final Color COLOR_FONDO = new Color(24,22,23);
	private static final Color COLOR_PANEL = new Color(64, 64, 64);
	
	private JTable tablaMenu;
	private BotonSeguir btnDejarDeSeguir;
	private JPanel[] panelesPrincipales;
	
	private PanelTablaTweets timeLine, menciones, retweets, favoritos;
	private PanelPerfilUsuario panelUsuario;
	private PanelEnviarTweet panelInferior;
	private PanelBusqueda panelBusqueda;
	
	private JPanel panelMostrandoActual;
	private JPanel panelVista;
	private JLabel lblImagen;

	/**
	 * Main de prueba
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setPanelActual(frame.getPaneles()[1]);
					frame.setVisible(true);
					System.out.println(((PanelBusqueda) frame.getPaneles()[5]).getTablaResultadosBusqueda().getValueAt(0, 0));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor por defecto
	 */
	public Principal() {
		super(600, 700);
		setTitle(Util.APP_TITULO);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainPanel().setBackground(Color.DARK_GRAY);
		getMainPanel().setBorder(new EmptyBorder(0, 0, 0, 0));
		
		panelesPrincipales = new JPanel[7];
		panelUsuario = new PanelPerfilUsuario();
		panelInferior = new PanelEnviarTweet();
		panelBusqueda = new PanelBusqueda();
		
		getMainPanel().setLayout(new BorderLayout(0, 0));
		
		JPanel panelIzq = new JPanel();
		panelIzq.setFocusTraversalKeysEnabled(false);
		panelIzq.setFocusable(false);
		panelIzq.setRequestFocusEnabled(false);
		panelIzq.setBackground(Color.DARK_GRAY);
		getMainPanel().add(panelIzq, BorderLayout.WEST);
		panelIzq.setLayout(new BorderLayout(0, 0));
		
		lblImagen = new JLabel("@JeyTuiter");
		lblImagen.setOpaque(true);
		lblImagen.setBorder(new MatteBorder(11, 4, 4, 4, COLOR_PANEL));
		lblImagen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImagen.setForeground(Color.WHITE);
		lblImagen.setBackground(Color.DARK_GRAY);
		lblImagen.setFont(Util.getFont("Roboto-regular", Font.PLAIN, 14));
		lblImagen.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImagen.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblImagen.setVerticalAlignment(SwingConstants.BOTTOM);
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setIcon(Util.getImagenRedondeada(new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")), 50));
		lblImagen.setSize(130,130);
		lblImagen.setIcon(Util.escalarImagen(lblImagen));
		panelIzq.add(lblImagen, BorderLayout.NORTH);
		
		lblImagen.addMouseListener(new EventoClickFotoUsuario(this));
		
		tablaMenu = new JTable();
		tablaMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaMenu.setShowHorizontalLines(false);
		tablaMenu.setShowVerticalLines(false);
		tablaMenu.setShowGrid(false);
		tablaMenu.setBackground(Color.DARK_GRAY);
		tablaMenu.setRowHeight(40);
		tablaMenu.setModel(new ModeloTablaPrincipal());
		tablaMenu.getColumnModel().getColumn(0).setCellRenderer(new UIButtonRenderer());
		
		//Eventos tabla izquierda
		tablaMenu.addMouseListener(new EventoCambiarColoBoton(this));
		tablaMenu.addMouseListener(new EventoCambiarPanelClick(this));
		
		//Evento ventana
		addMouseListener(new EventoMaximizarVerticalmente(this));
		addMouseMotionListener(new EventoMaximizarVerticalmente(this));
		
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
		panelApp.setDoubleBuffered(false);
		panelApp.setEnabled(false);
		panelApp.setFocusTraversalKeysEnabled(false);
		panelApp.setFocusable(false);
		panelApp.setRequestFocusEnabled(false);
		panelApp.setBorder(new LineBorder(new Color(0, 0, 0)));
		getMainPanel().add(panelApp);
		panelApp.setLayout(new BorderLayout(0, 0));

		panelApp.add(panelInferior, BorderLayout.SOUTH);
		
		panelVista = new JPanel();
		panelApp.add(panelVista, BorderLayout.CENTER);
		panelVista.setLayout(new BorderLayout(0, 0));
		
		generarDatos();
		
		JPanel panel_stats = new JPanel();
		
		panelesPrincipales[0] = panelUsuario;
		panelesPrincipales[1] = timeLine;
		panelesPrincipales[2] = menciones;
		panelesPrincipales[3] = retweets;
		panelesPrincipales[4] = favoritos;
		panelesPrincipales[5] = panelBusqueda;
		panelesPrincipales[6] = panel_stats;
	}

	/**
	 * 
	 */
	private void generarDatos() {
		//genera el mismo tweet  n veces
		//para probar
		timeLine = new PanelTablaTweets(new TablaTweet(10));
		menciones = new PanelTablaTweets(new TablaTweet(3));
		retweets  = new PanelTablaTweets(new TablaTweet(2));
		favoritos = new PanelTablaTweets(new TablaTweet(1));
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
	
	public void ocultarBotonSeguir(){
		btnDejarDeSeguir.setVisible(false);
	}
	
	public void mostrarBotonSeguir(){
		btnDejarDeSeguir.setVisible(true);
	}
	
	public JPanel[] getPaneles(){
		return panelesPrincipales;
	}
	
	public void setPanelActual(final JPanel p){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				if(panelMostrandoActual!=null)
					panelVista.remove(panelMostrandoActual);
				panelMostrandoActual = p;
				if(panelMostrandoActual==null)
					panelMostrandoActual = new JPanel();
				//panelMostrandoActual.setBounds(81, 0, 449, 567);
				panelVista.add(panelMostrandoActual, BorderLayout.CENTER);
				//Es posible que algunas de las siguientes sobren
				getContentPane().revalidate();
				getContentPane().repaint();
				panelVista.revalidate();
				panelVista.repaint();
				revalidate();
				repaint();
			}
		}).start();
	}
}
