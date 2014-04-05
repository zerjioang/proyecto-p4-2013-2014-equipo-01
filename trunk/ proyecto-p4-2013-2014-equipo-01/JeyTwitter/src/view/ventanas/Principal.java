package view.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;

import sun.swing.ImageIconUIResource;
import twitter4j.Status;
import util.Util;
import view.elementos.botones.BotonSeguir;
import view.elementos.paneles.GUITweet;
import view.elementos.paneles.ObjetoCelda;
import view.elementos.paneles.PanelBusqueda;
import view.elementos.paneles.PanelEnviarTweet;
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
	private static final int TIMELINE = 0;
	private static final int MENCIONES = 1;
	private static final int FAVORITOS = 2;
	private static final int RETUITS = 3;
	private static final int TUITSUSUARIO = 4;
	private static final int BUSQUEDA = 5;
	
	private JTable tablaMenu;
	private BotonSeguir btnDejarDeSeguir;
	private JPanel[] panelesPrincipales;
	
	private PanelTablaTweets timeLine, menciones, retweets, favoritos;
	private PanelPerfilUsuario panelUsuario;
	private PanelEnviarTweet panelInferior;
	private PanelBusqueda panelBusqueda;
	private JPanel panel_stats;
	
	private JPanel panelMostrandoActual;
	private JPanel panelVista;
	private JLabel lblImagen;
	
	private Usuario usuarioActual;

	/**
	 * Main de prueba
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal(new Usuario("@JeyTuiter", "5768745", "9864598", "Jey", "Hola me yamo J y mi padr me puso el nombre en onor a los ombres de nejro.", "/res/images/a.jpg", new Date(1L), 10, 0, 2));
					PanelTablaTweets p = (PanelTablaTweets) (frame.getPaneles()[1]);
					
					GUITweet a = new GUITweet("2d", new ImageIcon(Principal.class.getResource("/res/images/a.jpg")), "@FernandoLu", "El colgao", "Esto es una fiezzzta");
					GUITweet b = new GUITweet("3d", new ImageIcon(Principal.class.getResource("/res/images/b.jpg")), "@JeyTuiter", "Jeytuiter", "@tweetbot do you can see the text of a image tweet within the image viewer?Like official app or the 2 version?");
					
					p.insertarNuevo(a);
					p.insertarNuevo(b);
					p.insertarNuevo(a);
					p.insertarNuevo(b);
					p.insertarNuevo(a);
					p.insertarNuevo(b);
					p.insertarNuevo(a);
					p.insertarNuevo(b);
					p.insertarNuevo(a);
					p.insertarNuevo(b);
					
					frame.setPanelActual(frame.getPaneles()[1]);
					frame.setVisible(true);
					frame.getPanelInferior().getMensaje();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Constructor por defecto
	 */
	public Principal(Usuario usuario) {
		super(600, 700);
		usuarioActual = usuario;
		
		panelesPrincipales = new JPanel[7];
		
		panelUsuario = new PanelPerfilUsuario(usuarioActual, recargarTweets(TUITSUSUARIO));
		
		timeLine = new PanelTablaTweets(new TablaTweetsUsuarios(recargarTweets(TIMELINE)));
		menciones = new PanelTablaTweets(new TablaTweetsUsuarios(recargarTweets(MENCIONES)));
		retweets  = new PanelTablaTweets(new TablaTweetsUsuarios(recargarTweets(RETUITS)));
		favoritos = new PanelTablaTweets(new TablaTweetsUsuarios(recargarTweets(FAVORITOS)));
		
		
		panelInferior = new PanelEnviarTweet();
		panelBusqueda = new PanelBusqueda();
		panel_stats = new JPanel();
		
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
	
	public ArrayList<ObjetoCelda> recargarTweets(int tipo) {
		ArrayList<Tweet> listaTuits = null;
		switch (tipo){
		case 0:
			listaTuits = GUIController.getInstance().mostrarTimeline();
			break;
		case 1:
			listaTuits = GUIController.getInstance().mostrarTimeline();
			break;
		case 2:
			listaTuits = GUIController.getInstance().mostrarTimeline();
			break;
		case 3:
			listaTuits = GUIController.getInstance().mostrarTimeline();
			break;
		case 4:
			listaTuits = GUIController.getInstance().mostrarTimeline();
			break;
		case 5:
			listaTuits = GUIController.getInstance().mostrarTimeline();
			break;
		}
		ArrayList<ObjetoCelda> lista = new ArrayList<ObjetoCelda>();
		
		for(Tweet each : listaTuits){
			GUITweet guiTweet = new GUITweet("2d", new ImageIcon(Principal.class.getResource("/res/images/a.jpg")), each.getNombreUsuario(), each.getNombreReal(), each.getTexto());
			lista.add(0, guiTweet);
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
}
