package view.ventanas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JLabel;

import util.Util;
import view.elementos.botones.BotonAzulCuadrado;
import view.elementos.botones.BotonNegroCuadrado;
import view.eventos.fastTweet.EventoAtrasFastTuit;
import view.eventos.fastTweet.EventoContador140;
import view.eventos.fastTweet.EventoEnviarFastTuit;
import view.parents.InvisibleJFrame;

import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.border.LineBorder;

import java.awt.SystemColor;

import javax.swing.SwingConstants;

/**
 * Muestra una ventana flotante que se encarga de mostrar un panel donde introducir un tweet de forma rapida
 * @author Sergio Anguita
 */
public class TweetRapido extends InvisibleJFrame {

	//Constantes
	private static final String TITULO = "Enviar Tweet";

	private JLabel lblnombre;
	private JLabel lblContador;
	private JTextArea txtMensaje;
	private BotonAzulCuadrado btnEnviar;
	private BotonNegroCuadrado btnAtras;

	/**
	 * Metodo main de prueba
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TweetRapido frame = new TweetRapido("@Usuario");
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
	public TweetRapido(String nombreUsuario) {
		super("/res/images/fastTweet/FastTuit.png");
		init();
		setNombre(nombreUsuario);
	}
	
	/**
	 * Create the frame.
	 */
	public TweetRapido() {
		super("/res/images/fastTweet/FastTuit.png");
		init();
	}
	
	/**
	 * Inicializa el contenido de la ventana
	 */
	public void init(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(TITULO);

		lblnombre = new JLabel("@Nombre de Usuario");
		contentPane.add(lblnombre);
		lblnombre.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		lblnombre.setBounds(25, 22, 276, 19);

		txtMensaje = new JTextArea();
		contentPane.add(txtMensaje);
		txtMensaje.addKeyListener(new EventoContador140(this));
		txtMensaje.setLineWrap(true);
		txtMensaje.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 14));
		txtMensaje.setOpaque(false);
		txtMensaje.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		txtMensaje.setBorder(new LineBorder(SystemColor.scrollbar));
		txtMensaje.setBounds(25, 49, 307, 87);

		lblContador = new JLabel("140");
		lblContador.setHorizontalAlignment(SwingConstants.CENTER);
		lblContador.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 15));
		lblContador.setForeground(Color.WHITE);
		lblContador.setBounds(123, 165, 101, 14);
		contentPane.add(lblContador);

		btnAtras = new BotonNegroCuadrado("Atras");
		btnAtras.setBounds(26, 155, 67, 33);
		contentPane.add(btnAtras);

		btnEnviar = new BotonAzulCuadrado("Tweet");
		btnEnviar.setBounds(257, 155, 67, 33);
		contentPane.add(btnEnviar);

		btnAtras.addMouseListener(new EventoAtrasFastTuit(this));
		btnEnviar.addMouseListener(new EventoEnviarFastTuit(this));

		contentPane.add(fondo);
		getContentPane().setLayout(null);
		
		getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
	
	}

	/**
	 * @return devuelve el nombre del usuario
	 */
	public String getNombre() {
		return lblnombre.getText();
	}

	/**
	 * @param nombre Asigna el nombre de usuario
	 */
	public void setNombre(String nombre) {
		this.lblnombre.setText(nombre);
	}

	/**
	 * @return devuelve el contador de caracteres con el estado actual
	 */
	public int getContador() {
		return txtMensaje.getText().length();
	}

	/**
	 * Asigna una cantidad al contador
	 * @param valor cantidad a asignar
	 */
	public void setContador(int valor) {
		lblContador.setText(String.valueOf(valor));
	}

	/**
	 * @return devuelve el mensaje introducido por el usuario
	 */
	public String getMensaje() {
		return txtMensaje.getText();
	}

	/**
	 * @param txtMensaje asigna un mensaje al campo emnsaje
	 */
	public void setMensaje(String mensaje) {
		this.txtMensaje.setText(mensaje);
	}

	/**
	 * @return Devuelve el boton de enviar
	 */
	public BotonAzulCuadrado getBtnEnviar() {
		return btnEnviar;
	}

	/**
	 * @param Asigna el boton de enviar
	 */
	public void setBtnEnviar(BotonAzulCuadrado btnEnviar) {
		this.btnEnviar = btnEnviar;
	}

	/**
	 * @return Devuelve el boton 'atras'
	 */
	public BotonNegroCuadrado getBtnAtras() {
		return btnAtras;
	}

	/**
	 * @param asigna el boton 'atras'
	 */
	public void setBtnAtras(BotonNegroCuadrado btnAtras) {
		this.btnAtras = btnAtras;
	}
}
