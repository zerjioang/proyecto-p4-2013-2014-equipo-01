package view.ventanas;

import util.Util;
import view.eventos.EventoCerrar;
import view.eventos.EventosDeBarra;
import view.parents.CustomJDialog;
import view.parents.CustomJFrame;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Cursor;

public class MensajeWindow extends CustomJDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MensajeWindow frame = new MensajeWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void initMensajeWindow(){
		imagenBackground = "/res/images/mensajeVentanaNormal.png";
	}

	/**
	 * Create the frame.
	 */
	public MensajeWindow(CustomJFrame parent) {
		initMensajeWindow();
		ventanaPadre = parent;
		iniciarElementos();
	}
	
	public MensajeWindow(CustomJFrame parent, String tipo){
		initMensajeWindow();
		ventanaPadre = parent;
		imagenBackground = tipo;
		iniciarElementos();
	}
	
	public MensajeWindow(CustomJFrame parent, String tipo, String textoPositivo, String textoNegativo){
		initMensajeWindow();
		ventanaPadre = parent;
		imagenBackground = tipo;
		setBotonNegativo(textoNegativo);
		setBotonPositivo(textoPositivo);
		iniciarElementos();
	}
	
	/**
	 * Create the frame.
	 */
	public MensajeWindow() {
		initMensajeWindow();
		setBotonNegativo("Cancelar");
		setBotonPositivo("Aceptar");
		iniciarElementos();
	}

	/**
	 * @throws SecurityException
	 */
	private void iniciarElementos() {
		
		disposeWindow = true;
		
		setAlwaysOnTop(true);
		setBounds(100, 100, 443, 177);
		
		//Construccion de objetos
		JLabel lblFondo = new JLabel();
		
		//COnfiguracion de los objetos
		
		btnAceptar.setLocation(315, 125);
		btnCancelar.setLocation(212, 125);
		
		lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrar.setBounds(393, 12, 29, 23);

		lblFondo.setIcon(new ImageIcon(MensajeWindow.class.getResource(imagenBackground)));
		lblFondo.setBounds(0, 0, 443, 174);
		
		
		lblMensajeAMostrar.setForeground(Color.DARK_GRAY);
		lblMensajeAMostrar.setFont(Util.getFont("Roboto-Regular", Font.PLAIN, 14));
		lblMensajeAMostrar.setBounds(21, 46, 397, 71);
		
		
		lblTitulodeLaVentana.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulodeLaVentana.setBounds(21, 18, 362, 15);
		lblTitulodeLaVentana.setFont(Util.getFont("Roboto-Regular", Font.PLAIN, 14));
		lblTitulodeLaVentana.setForeground(Color.WHITE);
		
		JLabel lblArrastrar = new JLabel();
		lblArrastrar .setBounds(10, 12, 373, 23);
		lblArrastrar.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));	
		
		//Se añaden al panel principal que tiene layout null
		
		getContentPane().add(lblArrastrar);	
		getContentPane().add(lblMensajeAMostrar);
		getContentPane().add(lblTitulodeLaVentana);
		getContentPane().add(lblCerrar);
		getContentPane().add(lblFondo);
		
		//Listeners
		lblCerrar.addMouseListener(new EventoCerrar(this));
		lblArrastrar.addMouseListener(new EventosDeBarra(this));
		lblArrastrar.addMouseMotionListener(new EventosDeBarra(this));
		
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f)); //Lo hace transparente el ultimo valor es el nivel de transparencia
		setLocationRelativeTo(ventanaPadre);
	}
}
