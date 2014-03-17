package view.models.tablasPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import util.Util;
import view.elementos.botones.Button;
import view.eventos.celdaTweet.EventoClickBtnFavorito;
import view.eventos.celdaTweet.EventoClickBtnReTweet;
import view.eventos.celdaTweet.EventoClickBtnResponder;

public class GUITweet extends JPanel{
	
	private JLabel lblTiempo, lblImagenusuario, lblNombreReal, lblnombreUsuario;
	private Button btnResponder, btnRetweet, btnFavorito;
	private JTextArea txtMensaje;
	
	public GUITweet(){
		super();
		lblImagenusuario = new JLabel();
		lblNombreReal = new JLabel("Cliente de Twitter");
		lblnombreUsuario = new JLabel("@JeyTuiter");
		txtMensaje = new JTextArea("Este es un mensaje de prueba en el nuevo cliente @JeyTuiter. La visualizacion de Tweet todavia es beta");
		lblTiempo = new JLabel("1d");
		btnRetweet = new Button();
		btnFavorito = new Button();
		btnResponder = new Button();
		
		init();
	}
	/**
	 * Inicializa el contenido del tweet
	 */
	public void init(){
		setLayout(new BorderLayout(0, 0));
		//definicion de los botones
		btnRetweet.setImagenClick("/res/botones/opcionesTweet/reTweetHover.png");
		btnRetweet.setImagenHover("/res/botones/opcionesTweet/reTweetHover.png");
		btnRetweet.setImagenNormal("/res/botones/opcionesTweet/reTweetNormal.png");
		
		btnFavorito.setImagenClick("/res/botones/opcionesTweet/favoritoHover.png");
		btnFavorito.setImagenHover("/res/botones/opcionesTweet/favoritoHover.png");
		btnFavorito.setImagenNormal("/res/botones/opcionesTweet/favoritoNormal.png");
		
		btnResponder.setImagenClick("/res/botones/opcionesTweet/responderHover.png");
		btnResponder.setImagenHover("/res/botones/opcionesTweet/responderHover.png");
		btnResponder.setImagenNormal("/res/botones/opcionesTweet/responderNormal.png");
		
		btnRetweet.setIcon(new ImageIcon(GUITweet.class.getResource(btnRetweet.getImagenNormal())));
		btnFavorito.setIcon(new ImageIcon(GUITweet.class.getResource(btnFavorito.getImagenNormal())));
		btnResponder.setIcon(new ImageIcon(GUITweet.class.getResource(btnResponder.getImagenNormal())));
		
		btnRetweet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFavorito.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnResponder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//Eventos de los botones
		btnRetweet.addMouseListener(new EventoClickBtnReTweet(this));
		btnFavorito.addMouseListener(new EventoClickBtnFavorito(this));
		btnResponder.addMouseListener(new EventoClickBtnResponder(this));
		
		lblNombreReal.setFont(Util.getFont("roboto-light", Font.BOLD, 12));
		lblnombreUsuario.setFont(Util.getFont("roboto-light", Font.PLAIN, 12));
		lblTiempo.setFont(Util.getFont("trebuc", Font.PLAIN, 12));
		txtMensaje.setFont(Util.getFont("roboto-light", Font.PLAIN, 12));

		
		lblImagenusuario.setSize(100, 100);
		lblImagenusuario.setIcon(Util.getImagenRedondeada(new ImageIcon(GUITweet.class.getResource("/res/images/userTest.jpg")), 50));
		lblImagenusuario.setBorder(new MatteBorder(0, 5, 0, 5, new Color(1.0f,1.0f,1.0f,0.0f)));
		lblImagenusuario.setIcon(Util.escalarImagen(lblImagenusuario));
		add(lblImagenusuario, BorderLayout.WEST);
		
		JPanel panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperior = new JPanel();
		panelCentro.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperiorNombres = new JPanel();
		panelSuperior.add(panelSuperiorNombres, BorderLayout.WEST);
		
		panelSuperiorNombres.add(lblNombreReal);
		panelSuperiorNombres.add(lblnombreUsuario);
		
		JPanel panelSuperiorTiempo = new JPanel();
		panelSuperior.add(panelSuperiorTiempo, BorderLayout.EAST);
		
		panelSuperiorTiempo.add(lblTiempo);
		
		JPanel panelInferior = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelInferior.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelCentro.add(panelInferior, BorderLayout.SOUTH);
		
		panelInferior.add(btnResponder);
		System.out.println(btnResponder.getSize());
		panelInferior.add(btnRetweet);
		panelInferior.add(btnFavorito);
		
		JPanel panelCentroMensaje = new JPanel(new BorderLayout());
		panelCentro.add(panelCentroMensaje, BorderLayout.CENTER);
		txtMensaje.setLineWrap(true);
		txtMensaje.setWrapStyleWord(true);
		txtMensaje.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		txtMensaje.setEditable(false);
		txtMensaje.setFocusable(false);
		txtMensaje.setOpaque(false);
		panelCentroMensaje.add(txtMensaje,BorderLayout.CENTER);
	}

	/**
	 * @return devuelve el tiempo desde que se ha mandado el tweet
	 */
	public String getTiempo() {
		return lblTiempo.getText();
	}

	/**
	 * @param establece el tiempo indicado 
	 */
	public void setTiempo(String tiempo) {
		this.lblTiempo.setText(tiempo);
	}

	/**
	 * @return devuelve el mensaje del tweet
	 */
	public String getMensaje() {
		return txtMensaje.getText();
	}

	/**
	 * @param establece el mensaje del tweet
	 */
	public void setMensaje(String mensaje) {
		this.txtMensaje.setText(mensaje);
	}

	/**
	 * @return devuelve el boton responder
	 */
	public Button getBtnResponder() {
		return btnResponder;
	}

	/**
	 * @param establece el boton responder
	 */
	public void setBtnResponder(Button btnResponder) {
		this.btnResponder = btnResponder;
	}

	/**
	 * @return devuelve el boton btnRetweet
	 */
	public Button getBtnRetweet() {
		return btnRetweet;
	}

	/**
	 * @param establece el boton btnRetweet
	 */
	public void setBtnRetweet(Button btnRetweet) {
		this.btnRetweet = btnRetweet;
	}

	/**
	 * @return devuelve el boton btnFavorito
	 */
	public Button getBtnFavorito() {
		return btnFavorito;
	}

	/**
	 * @param establece el boton btnFavorito
	 */
	public void setBtnFavorito(Button btnFavorito) {
		this.btnFavorito = btnFavorito;
	}

	/**
	 * @return devuelve la imagen del usuario en forma de un objeto Icon
	 */
	public Icon getImagenusuario() {
		return lblImagenusuario.getIcon();
	}

	/**
	 * @param establece la imagen del usuario
	 */
	public void setImagenusuario(ImageIcon imagenUsuario) {
		lblImagenusuario.setIcon(Util.getImagenRedondeada(imagenUsuario, 50));
		lblImagenusuario.setBorder(new MatteBorder(0, 5, 0, 5, new Color(1.0f,1.0f,1.0f,0.0f)));
		lblImagenusuario.setIcon(Util.escalarImagen(lblImagenusuario));
	}

	/**
	 * @return devuelve el nombre real del usuario
	 */
	public String getNombreReal() {
		return lblNombreReal.getText();
	}

	/**
	 * @param establece el nombre real del usuario
	 */
	public void setNombreReal(String nombreReal) {
		this.lblNombreReal.setText(nombreReal);
	}

	/**
	 * @return devuelve el nombre del usuario
	 */
	public String getNombreUsuario() {
		return lblnombreUsuario.getText();
	}

	/**
	 * @param establece el nombre del usuario
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.lblnombreUsuario.setText(nombreUsuario);
	}
}
