package view.ventanas;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import util.Util;
import view.eventos.notificacion.EventoCerrarNotificacion;
import view.parents.InvisibleJFrame;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Cursor;

public class Notificacion extends InvisibleJFrame {
	
	public static final int TWEET = 0;
	public static final int RETWEET = 1;
	public static final int MENCION = 2;
	public static final int FOLLOW = 3;
	public static final int UNFOLLOW = 4;
	
	private JTextArea txtMensaje;
	private JLabel lblHora;
	private JLabel lblusuario;
	private JLabel lblImagenUsuario;
	private JLabel lblCerrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notificacion frame = new Notificacion();
					frame.setTipoNotificacion(Notificacion.RETWEET);
					frame.ajustarApantalla();
					frame.mostrar(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Notificacion() {
		super("/res/images/notif/notification_tweet.png");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		lblCerrar = new JLabel("X");
		lblCerrar.addMouseListener(new EventoCerrarNotificacion(this));
		lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrar.setBackground(Color.DARK_GRAY);
		lblCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrar.setFont(Util.getFont("Roboto-regular", Font.PLAIN, 18));
		lblCerrar.setForeground(Color.WHITE);
		lblCerrar.setBounds(392, 11, 23, 22);
		contentPane.add(lblCerrar);
		
		txtMensaje = new JTextArea();
		txtMensaje.setFocusable(false);
		txtMensaje.setEditable(false);
		txtMensaje.setOpaque(false);
		txtMensaje.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		txtMensaje.setForeground(Color.WHITE);
		txtMensaje.setLineWrap(true);
		txtMensaje.setWrapStyleWord(true);
		txtMensaje.setFont(Util.getFont("mirda", Font.PLAIN, 16));
		txtMensaje.setText("Este es un comentario de prueba en una notificacion de JeyTuiter");
		txtMensaje.setBounds(114, 41, 301, 91);
		contentPane.add(txtMensaje);
		
		lblHora = new JLabel("13:00");
		lblHora.setFont(Util.getFont("mirda", Font.PLAIN, 18));
		lblHora.setForeground(Color.WHITE);
		lblHora.setBounds(336, 11, 44, 22);
		contentPane.add(lblHora);
		
		lblusuario = new JLabel("@Usuario");
		lblusuario.setFont(Util.getFont("mirda", Font.PLAIN, 18));
		lblusuario.setForeground(Color.WHITE);
		lblusuario.setBounds(10, 14, 316, 22);
		contentPane.add(lblusuario);
		
		lblImagenUsuario = new JLabel("Imagen");
		lblImagenUsuario.setBounds(10, 41, 94, 91);
		lblImagenUsuario.setIcon(Util.getImagenRedondeada(new ImageIcon(Notificacion.class.getResource("/res/images/userTest.jpg")), 20));
		lblImagenUsuario.setIcon(Util.escalarImagen(lblImagenUsuario));
		contentPane.add(lblImagenUsuario);

		contentPane.add(fondo);
		
		JPanel EstePanelEsSoloParaQueElFondoSeVea = new JPanel();
		EstePanelEsSoloParaQueElFondoSeVea.setBackground(Color.DARK_GRAY);
		EstePanelEsSoloParaQueElFondoSeVea.setBounds(0, 0, 425, 143);
		contentPane.add(EstePanelEsSoloParaQueElFondoSeVea);

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
	 * @return the lblHora
	 */
	public JLabel getLblHora() {
		return lblHora;
	}

	/**
	 * @param lblHora the lblHora to set
	 */
	public void setLblHora(String lblHora) {
		this.lblHora.setText(lblHora);
	}

	/**
	 * @return the lblusuario
	 */
	public String getLblusuario() {
		return lblusuario.getText();
	}

	/**
	 * @param lblusuario the lblusuario to set
	 */
	public void setLblusuario(String lblusuario) {
		this.lblusuario.setText(lblusuario);
	}

	/**
	 * @return the lblImagenUsuario
	 */
	public Icon getLblImagenUsuario() {
		return lblImagenUsuario.getIcon();
	}

	/**
	 * @param lblImagenUsuario the lblImagenUsuario to set
	 */
	public void setLblImagenUsuario(ImageIcon lblImagenUsuario) {
		this.lblImagenUsuario.setIcon(lblImagenUsuario);
	}
	
	public void setTipoNotificacion(int tipo) throws Exception{
		
		/*
		 * 	public static final int TWEET = 0;
			public static final int RETWEET = 1;
			public static final int MENCION = 2;
			public static final int FOLLOW = 3;
			public static final int UNFOLLOW = 4;
		 */
		switch (tipo) {
		case 0:
			setImagenFondo("/res/images/notif/notification_tweet.png");
			break;
		case 1:
			setImagenFondo("/res/images/notif/notification_retweet.png");
			break;
		case 2:
			setImagenFondo("/res/images/notif/notification_mencion.png");
			break;
		case 3:
			setImagenFondo("/res/images/notif/notification_follower.png");
			break;
		case 4:
			setImagenFondo("/res/images/notif/notification_unfollow.png");
			break;
		default:
			throw new Exception("El tipo de notificacion no es valido");
		}
	}
	
	public void ajustarApantalla(){
		//valores para windows
		int offsetX = 10; //cantidad de pixeles de margen vertical derecho
		int offsetY = 50; //cantidad de pixeles de margen horizontal inferior
		setBounds(
				Util.anchoPantalla-getImagenFondo().getIconWidth()-offsetX,
				Util.altoPantalla-getImagenFondo().getIconHeight()-offsetY, 
				getImagenFondo().getIconWidth(), getImagenFondo().getIconHeight()
				);
	}
}
