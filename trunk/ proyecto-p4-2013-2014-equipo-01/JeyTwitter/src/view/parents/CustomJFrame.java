package view.parents;

import view.eventos.EventoClickCerrar;
import view.eventos.EventoClickMinimizar;
import view.eventos.EventoMaximizarDesdeBarra;
import view.eventos.EventosDeBarra;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import util.Util;

import java.awt.Toolkit;
import java.awt.BorderLayout;

import javax.swing.SwingConstants;

public class CustomJFrame extends JFrame {
	
	//Constantes
	protected static final int altoBarra = 26;
	private final static int tamBoton = 16;
	private final static String RUTA_ICONO = Util.APP_ICONO;
	
	private Point initialClick, lastPosition;
	private JLabel lblCerrar;
	private JLabel tituloVentana;
	private JLabel lblMaximizar;
	private JLabel lblMinimizar;
	private JLabel lblImagenFondo;
	private JPanel panelContenido;
	private JPanel panelBarra;
	private JLabel lblBotonesBotonera;
	private boolean disposeWindow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomJFrame frame = new CustomJFrame(600,300);
					frame.setTitulo("Ventana de prueba");
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
	public CustomJFrame(int ancho, int alto) {
		Util.asignarNimbus();
		setBounds(0, 0, ancho, alto);
		init();
		initBarra();
	}

	private void init() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CustomJFrame.class.getResource(RUTA_ICONO)));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 638, 318);
		
		panelBarra = new JPanel();
		//panelBarra.setBackground(Color.GREEN);
		
		panelContenido = new JPanel();
		//panelContenido.setBackground(Color.YELLOW);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelBarra, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
				.addComponent(panelContenido, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelBarra, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addComponent(panelContenido, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
		);
		panelContenido.setLayout(new BorderLayout(0, 0));
		panelBarra.setLayout(null);
		getContentPane().setLayout(groupLayout);
	}

	private void initBarra() {
		//setLayout(new BorderLayout(0, 0));
		
		tituloVentana = new JLabel("");
		//tituloVentana.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14)); 
		tituloVentana.setHorizontalAlignment(SwingConstants.CENTER);
		tituloVentana.setForeground(Color.WHITE);
		tituloVentana.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		tituloVentana.setBounds(0, 0, getWidth()-70, altoBarra);
		panelBarra.add(tituloVentana);
		
		lblCerrar = new JLabel("");
		lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrar.setBounds(getWidth()-24, 3, tamBoton, tamBoton);
		panelBarra.add(lblCerrar);
		
		lblMaximizar = new JLabel("");
		lblMaximizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMaximizar.setBounds(getWidth()-45, 3, tamBoton, tamBoton);
		panelBarra.add(lblMaximizar);
		
		lblMinimizar = new JLabel("");
		lblMinimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMinimizar.setBounds(getWidth()-67, 3, tamBoton, tamBoton);
		panelBarra.add(lblMinimizar);

		lblBotonesBotonera = new JLabel("");
		//lblBotonesBotonera.setForeground(Color.WHITE);
		lblBotonesBotonera.setIcon(new ImageIcon(CustomJFrame.class.getResource("/res/images/botonera/botonesNormales.png")));
		lblBotonesBotonera.setBounds(getWidth()-71, 0, 69, 26);
		panelBarra.add(lblBotonesBotonera);
		
		lblImagenFondo = new JLabel("");
		lblImagenFondo.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		lblImagenFondo.setIcon(new ImageIcon(CustomJFrame.class.getResource("/res/images/barra.png")));
		lblImagenFondo.setBounds(0, 0, getWidth(), altoBarra);
		//lblImagenFondo.setIcon(Util.escalarImagen(lblImagenFondo));
		panelBarra.add(lblImagenFondo);
		
		//Eventos-Listeners
		lblCerrar.addMouseListener(new EventoClickCerrar(this));
		lblMinimizar.addMouseListener(new EventoClickMinimizar(this));
		lblImagenFondo.addMouseMotionListener(new EventosDeBarra(this));
		lblImagenFondo.addMouseListener(new EventosDeBarra(this));
		addWindowListener(new EventoMaximizarDesdeBarra(this));
		
		setLocationRelativeTo(null);
	}

	/**
	 * @return the initialClick
	 */
	public Point getInitialClick() {
		return initialClick;
	}

	/**
	 * @param initialClick the initialClick to set
	 */
	public void setInitialClick(Point initialClick) {
		this.initialClick = initialClick;
	}

	/**
	 * @return the lastPosition
	 */
	public Point getLastPosition() {
		if(lastPosition==null)
			setLocationRelativeTo(null);
		return lastPosition;
	}

	/**
	 * @param lastPosition the lastPosition to set
	 */
	public void setLastPosition(Point lastPosition) {
		this.lastPosition = lastPosition;
	}
	
	public JPanel getMainPanel() {
		return panelContenido;
	}
	
	public void setTitulo(String titulo){
		setTitle(titulo);
		tituloVentana.setText(titulo);
	}

	public JLabel getLblCerrar(){
		return lblCerrar;
	}
	public JLabel getLblMaximizar(){
		return lblMaximizar;
	}

	public JLabel getLblImagenFondoBarra() {
		return lblImagenFondo;
	}
	
	public JLabel getLblMinimizar() {
		return lblMinimizar;
	}

	public void setImagenIconos(ImageIcon icono) {
		lblBotonesBotonera.setIcon(icono);
	}

	/**
	 * @return the disposeWindow
	 */
	public boolean isDisposeWindow() {
		return disposeWindow;
	}

	/**
	 * @param disposeWindow the disposeWindow to set
	 */
	public void setDisposeWindow(boolean disposeWindow) {
		this.disposeWindow = disposeWindow;
	}
	
}
