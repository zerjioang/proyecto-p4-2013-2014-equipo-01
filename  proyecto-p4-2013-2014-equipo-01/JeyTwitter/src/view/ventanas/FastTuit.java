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

public class FastTuit extends InvisibleJFrame {

	//Constantes
	private static final String TITULO = "Enviar Tweet";

	private JLabel lblnombre;
	private JLabel lblContador;
	private JTextArea txtMensaje;
	private BotonAzulCuadrado btnEnviar;
	private BotonNegroCuadrado btnAtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FastTuit frame = new FastTuit();
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
	public FastTuit() {
		super("/res/images/fastTweet/FastTuit.png");
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
		txtMensaje.setFont(new Font("Roboto Lt", Font.PLAIN, 14));
		txtMensaje.setOpaque(false);
		txtMensaje.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		txtMensaje.setBorder(new LineBorder(SystemColor.scrollbar));
		txtMensaje.setBounds(25, 49, 307, 87);

		lblContador = new JLabel("140");
		lblContador.setHorizontalAlignment(SwingConstants.CENTER);
		lblContador.setFont(new Font("Roboto Lt", Font.PLAIN, 15));
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
	}

	/**
	 * @return the lblnombre
	 */
	public JLabel getLblnombre() {
		return lblnombre;
	}

	/**
	 * @param lblnombre the lblnombre to set
	 */
	public void setLblnombre(String lblnombre) {
		this.lblnombre.setText(lblnombre);
	}

	/**
	 * @return the lblContador
	 */
	public int getLblContador() {
		return txtMensaje.getText().length();
	}

	/**
	 * @param lblContador the lblContador to set
	 */
	public void setLblContador(int valor) {
		lblContador.setText(String.valueOf(valor));
	}

	/**
	 * @return the txtMensaje
	 */
	public String getTxtMensaje() {
		return txtMensaje.getText();
	}

	/**
	 * @param txtMensaje the txtMensaje to set
	 */
	public void setTxtMensaje(String txtMensaje) {
		this.txtMensaje.setText(txtMensaje);
	}

	/**
	 * @return the btnEnviar
	 */
	public BotonAzulCuadrado getBtnEnviar() {
		return btnEnviar;
	}

	/**
	 * @param btnEnviar the btnEnviar to set
	 */
	public void setBtnEnviar(BotonAzulCuadrado btnEnviar) {
		this.btnEnviar = btnEnviar;
	}

	/**
	 * @return the btnAtras
	 */
	public BotonNegroCuadrado getBtnAtras() {
		return btnAtras;
	}

	/**
	 * @param btnAtras the btnAtras to set
	 */
	public void setBtnAtras(BotonNegroCuadrado btnAtras) {
		this.btnAtras = btnAtras;
	}
}
