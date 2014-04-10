package view.ventanas;

import javax.imageio.ImageIO;
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
import view.elementos.GUITweet;
import view.elementos.GuiTwitterUsuario;
import view.elementos.ObjetoCelda;
import view.elementos.botones.BotonSeguir;
import view.elementos.paneles.PanelBusqueda;
import view.elementos.paneles.PanelEnviarTweet;
import view.elementos.paneles.PanelEstadistica;
import view.elementos.paneles.PanelPerfilUsuario;
import view.elementos.paneles.PanelTablaTweets;
import view.eventos.principal.EventoCambiarColoBoton;
import view.eventos.principal.EventoCambiarPanelClick;
import view.eventos.principal.EventoClickAcercaDe;
import view.eventos.principal.EventoClickConfig;
import view.eventos.principal.EventoClickFotoUsuario;
import view.eventos.principal.EventoClickHelp;
import view.eventos.principal.EventoMaximizarVerticalmente;
import view.models.ModeloTablaPrincipal;
import view.models.tablasPrincipal.TablaTweetsUsuarios;
import view.parents.CustomJFrame;
import view.renderers.UIButtonRenderer;

import java.awt.Font;
import java.awt.Cursor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import controller.GUIController;
import model.Tweet;
import model.Usuario;

public class Principal extends CustomJFrame {

	//Constantes
	private static final Color COLOR_PANEL = new Color(64, 64, 64);
	public static final int TIMELINE = 0;
	public static final int MENCIONES = 1;
	public static final int FAVORITOS = 2;
	public static final int RETUITS = 3;
	public static final int TUITSUSUARIO = 4;
	public static final int BUSQUEDA = 5;
	
	private JTable tablaMenu;
	private BotonSeguir btnDejarDeSeguir;
	private JPanel[] panelesPrincipales;
	
	private PanelTablaTweets timeLine, menciones, retweets, favoritos;
	private PanelPerfilUsuario panelUsuario;
	private PanelEnviarTweet panelInferior;
	private PanelBusqueda panelBusqueda;
	private PanelEstadistica panel_stats;
	
	private JPanel panelMostrandoActual;
	private JPanel panelVista;
	private JLabel lblImagen;
	
	private static Usuario usuarioActual;
	
	/**
	 * Constructor por defecto
	 */
	public Principal(Usuario usuario) {
		super(600, 700);
		usuarioActual = usuario;
		
		panelesPrincipales = new JPanel[7];
		
		try {
			panelUsuario = new PanelPerfilUsuario(usuarioActual, recargarTweets(TUITSUSUARIO));
			timeLine = new PanelTablaTweets(new TablaTweetsUsuarios(recargarTweets(TIMELINE)));
			menciones = new PanelTablaTweets(new TablaTweetsUsuarios(recargarTweets(MENCIONES)));
			retweets  = new PanelTablaTweets(new TablaTweetsUsuarios(recargarTweets(RETUITS)));
			favoritos = new PanelTablaTweets(new TablaTweetsUsuarios(recargarTweets(FAVORITOS)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panelInferior = new PanelEnviarTweet(this);
		System.out.println(panelInferior.getBounds());
		ArrayList<ObjetoCelda> o = new ArrayList<ObjetoCelda>();
		o.add(0, new GuiTwitterUsuario());
		o.add(0, new GuiTwitterUsuario());
		o.add(0, new GuiTwitterUsuario());
		o.add(0, new GuiTwitterUsuario());
		
		panelBusqueda = new PanelBusqueda(o);
		panel_stats = new PanelEstadistica();
		
		lblImagen = new JLabel(usuarioActual.getNombreUsuario());
		init();
		generarDatos();
	}
	
	public void init(){
		setTitle(Util.APP_TITULO);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMainPanel().setBackground(Color.DARK_GRAY);
		getMainPanel().setBorder(new EmptyBorder(0, 0, 0, 0));
		
		getMainPanel().setLayout(new BorderLayout(0, 0));
		
		JPanel panelIzq = new JPanel();
		panelIzq.setFocusTraversalKeysEnabled(false);
		panelIzq.setFocusable(false);
		panelIzq.setRequestFocusEnabled(false);
		panelIzq.setBackground(Color.DARK_GRAY);
		getMainPanel().add(panelIzq, BorderLayout.WEST);
		panelIzq.setLayout(new BorderLayout(0, 0));
		
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
		lblImagen.setSize(100,100);
		setImagenUsuario(new ImageIcon(usuarioActual.getImagen()));
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
	}
	
	public ArrayList<ObjetoCelda> recargarTweets(int tipo) throws IOException {
		ArrayList<Tweet> listaTuits = null;
		switch (tipo){
		case 0:
			listaTuits = GUIController.getInstance().mostrarTimeline();
			break;
		case 1:
			listaTuits = GUIController.getInstance().mostrarMenciones();
			break;
		case 2:
			listaTuits = GUIController.getInstance().mostrarFavoritos();
			break;
		case 3:
			listaTuits = GUIController.getInstance().mostrarRetuits();
			break;
		case 4:
			listaTuits = GUIController.getInstance().mostrarPerfil();
			break;
		case 5:
			listaTuits = GUIController.getInstance().mostrarTimeline();
			break;
		}
		ArrayList<ObjetoCelda> lista = new ArrayList<ObjetoCelda>();
		
		for(Tweet each : listaTuits){
			GUITweet guiTweet;
			guiTweet = new GUITweet("2d", new ImageIcon(each.getImagenUsuario()), each.getNombreUsuario(), each.getNombreReal(), each.getTexto());
			lista.add(lista.size(), guiTweet);
		}
		return lista;
	}

	/**
	 * 
	 */
	private void generarDatos() {
		//genera el mismo tweet  n veces
		//para probar
		//timeLine = new PanelTablaTweets(new TablaTweetsUsuarios(TablaTweetsUsuarios.SOLO_TWEETS));
		
		panelesPrincipales[0] = panelUsuario;
		panelesPrincipales[1] = timeLine;
		panelesPrincipales[2] = menciones;
		panelesPrincipales[3] = retweets;
		panelesPrincipales[4] = favoritos;
		panelesPrincipales[5] = panelBusqueda;
		panelesPrincipales[6] = panel_stats;
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
				
				panelVista.add(panelMostrandoActual, BorderLayout.CENTER);
				getContentPane().revalidate();
				getContentPane().repaint();
			}
		}).start();
	}

	/**
	 * @return the panelInferior
	 */
	public PanelEnviarTweet getPanelInferior() {
		return panelInferior;
	}

	/**
	 * @param panelInferior the panelInferior to set
	 */
	public void setPanelInferior(PanelEnviarTweet panelInferior) {
		this.panelInferior = panelInferior;
	}

	/**
	 * @return the panelBusqueda
	 */
	public PanelBusqueda getPanelBusqueda() {
		return panelBusqueda;
	}

	/**
	 * @param panelBusqueda the panelBusqueda to set
	 */
	public void setPanelBusqueda(PanelBusqueda panelBusqueda) {
		this.panelBusqueda = panelBusqueda;
	}

	/**
	 * @return the lblImagen
	 */
	public JLabel getImagenUsuario() {
		return lblImagen;
	}

	/**
	 * @param lblImagen the lblImagen to set
	 */
	public void setImagenUsuario(ImageIcon imagen) {
		// Para pruebas
		lblImagen.setIcon(Util.getImagenRedondeada(imagen, 15));
		lblImagen.setIcon(Util.escalarImagen(lblImagen));
	}

	public static Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public static void setUsuarioActual(Usuario usuarioActual) {
		Principal.usuarioActual = usuarioActual;
	}
}
