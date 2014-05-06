package view.elementos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import model.Tweet;
import util.Util;
import view.elementos.botones.BotonDosEstados;
import view.elementos.botones.Button;
import view.eventos.celdaTweet.EventoClickBtnFavorito;
import view.eventos.celdaTweet.EventoClickBtnReTweet;
import view.eventos.celdaTweet.EventoClickBtnResponder;
import view.eventos.celdaTweet.EventoClickImagenTweet;
import view.eventos.celdaTweet.EventoClickImagenUsuario;
import view.models.tablasPrincipal.TablaTweetsUsuarios;

public class GUITweet extends JPanel implements ObjetoCelda{
	
	private static final int ALTO = 70;
	private static final int SIZE_IMAGEN = 50;
	private static final int REDONDEO = 15;
	private JLabel lblTiempo, lblImagenusuario, lblNombreReal, lblnombreUsuario;
	private BotonDosEstados btnResponder, btnRetweet, btnFavorito;
	private JTextArea txtMensaje;
	private JLabel lblImagenTweet;
	private JPanel panelImagenTweet;
	private Tweet tweet;
	
	public GUITweet(String fecha, Tweet t) {
		super();
		
		this.lblImagenusuario = new JLabel();
		this.lblNombreReal = new JLabel();
		this.lblnombreUsuario = new JLabel();
		this.txtMensaje = new JTextArea();
		this.lblTiempo = new JLabel();
		
		setTiempo(fecha);
		lblImagenusuario.setSize(ALTO, ALTO);
		setImagenUsuario(new ImageIcon(t.getImagenUsuario()));
		setNombreReal(t.getNombreReal());
		setNombreUsuario(t.getNombreUsuario());
		setMensaje(t.getTexto());
		
		btnRetweet = new BotonDosEstados();
		btnFavorito = new BotonDosEstados();
		btnResponder = new BotonDosEstados();
		
		tweet = t;
		init();
	}
	/**
	 * @param lblTiempo
	 * @param lblImagenusuario
	 * @param lblNombreReal
	 * @param lblnombreUsuario
	 * @param txtMensaje
	 */
	@Deprecated
	public GUITweet(String tiempo, ImageIcon imagenusuario,String nombreReal, String nombreUsuario, String mensaje, Tweet t) {
		
		super();
		this.lblImagenusuario = new JLabel();
		this.lblNombreReal = new JLabel();
		this.lblnombreUsuario = new JLabel();
		this.txtMensaje = new JTextArea();
		this.lblTiempo = new JLabel();
		
		this.lblTiempo.setText(tiempo);
		this.lblImagenusuario.setIcon(imagenusuario);
		this.lblNombreReal.setText(nombreReal);
		setNombreUsuario(nombreUsuario);
		this.txtMensaje.setText(mensaje);
		
		btnRetweet = new BotonDosEstados();
		btnFavorito = new BotonDosEstados();
		btnResponder = new BotonDosEstados();
		
		tweet = t;
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
		btnRetweet.setImagenClicado("/res/botones/opcionesTweet/reTweetClicked.png");
		
		btnFavorito.setImagenClick("/res/botones/opcionesTweet/favoritoHover.png");
		btnFavorito.setImagenHover("/res/botones/opcionesTweet/favoritoHover.png");
		btnFavorito.setImagenNormal("/res/botones/opcionesTweet/favoritoNormal.png");
		btnFavorito.setImagenClicado("/res/botones/opcionesTweet/favoritoClicked.png");
		
		btnResponder.setImagenClick("/res/botones/opcionesTweet/responderHover.png");
		btnResponder.setImagenHover("/res/botones/opcionesTweet/responderHover.png");
		btnResponder.setImagenNormal("/res/botones/opcionesTweet/responderNormal.png");
		btnResponder.setImagenClicado("/res/botones/opcionesTweet/responderClicked.png");
		
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

		lblImagenusuario.setSize(ALTO, ALTO);
		setImagenUsuario((ImageIcon)lblImagenusuario.getIcon());
		lblImagenusuario.setBorder(new MatteBorder(5, 5, 0, 5, new Color(1.0f,1.0f,1.0f,0.0f)));
		lblImagenusuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblImagenusuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagenusuario.setHorizontalTextPosition(SwingConstants.CENTER);
		lblImagenusuario.setVerticalAlignment(SwingConstants.TOP);
		lblImagenusuario.setVerticalTextPosition(SwingConstants.BOTTOM);
		
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
		flowLayout.setAlignOnBaseline(true);
		panelCentro.add(panelInferior, BorderLayout.SOUTH);
		
		panelInferior.add(btnResponder);
		panelInferior.add(btnRetweet);
		panelInferior.add(btnFavorito);
		
		JPanel panelCentroMensaje = new JPanel(new BorderLayout());
		panelCentro.add(panelCentroMensaje, BorderLayout.CENTER);
		txtMensaje.setLineWrap(true);
		txtMensaje.setWrapStyleWord(true);
		txtMensaje.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		txtMensaje.setEditable(false);
		txtMensaje.setFocusable(true);
		txtMensaje.setOpaque(false);
		txtMensaje.setBorder(null);
		panelCentroMensaje.add(txtMensaje,BorderLayout.CENTER);
		panelCentroMensaje.setBorder(new MatteBorder(0, 0, 0, 1, new Color(1.0f,1.0f,1.0f,0.0f)));
		
		if(/*hay imagen en el tweet*/true){
			panelImagenTweet = new JPanel();
			panelImagenTweet.setLayout(new BorderLayout(0, 0));
			panelCentroMensaje.add(panelImagenTweet, BorderLayout.EAST);
			
			lblImagenTweet = new JLabel();
			lblImagenTweet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblImagenTweet.setSize(SIZE_IMAGEN, SIZE_IMAGEN);
			lblImagenTweet.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagenTweet.setHorizontalTextPosition(SwingConstants.CENTER);
			setBordeImagenTweet(3,3,3,3, new Color(255, 255, 255));
			setImagenTweet(new ImageIcon(GUITweet.class.getResource("/res/images/a.jpg")));
			panelImagenTweet.add(lblImagenTweet, BorderLayout.SOUTH);
			
			//evento al clicar encima de la imagen del tweet
			lblImagenTweet.addMouseListener(new EventoClickImagenTweet(this));
			//evento al clicar la imagen del usuario
			lblImagenusuario.addMouseListener(new EventoClickImagenUsuario(this));
		}
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
	public BotonDosEstados getBtnResponder() {
		return btnResponder;
	}

	/**
	 * @param establece el boton responder
	 */
	public void setBtnResponder(BotonDosEstados btnResponder) {
		this.btnResponder = btnResponder;
	}

	/**
	 * @return devuelve el boton btnRetweet
	 */
	public BotonDosEstados getBtnRetweet() {
		return btnRetweet;
	}

	/**
	 * @param establece el boton btnRetweet
	 */
	public void setBtnRetweet(BotonDosEstados btnRetweet) {
		this.btnRetweet = btnRetweet;
	}

	/**
	 * @return devuelve el boton btnFavorito
	 */
	public BotonDosEstados getBtnFavorito() {
		return btnFavorito;
	}

	/**
	 * @param establece el boton btnFavorito
	 */
	public void setBtnFavorito(BotonDosEstados btnFavorito) {
		this.btnFavorito = btnFavorito;
	}

	/**
	 * @return devuelve la imagen del usuario en forma de un objeto Icon
	 */
	public Icon getImagenUsuario() {
		return lblImagenusuario.getIcon();
	}

	/**
	 * @param establece la imagen del usuario
	 */
	public void setImagenUsuario(ImageIcon imagenUsuario) {
		lblImagenusuario.setIcon(Util.getImagenRedondeada(imagenUsuario, REDONDEO));
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
		this.lblnombreUsuario.setText("@"+nombreUsuario);
	}

	@Override
	public int tipoObjeto() {
		return TablaTweetsUsuarios.SOLO_TWEETS;
	}

	/**
	 * @return the lblImagenTweet
	 */
	public Icon getImagenTweet() {
		return lblImagenTweet.getIcon();
	}

	/**
	 * @param lblImagenTweet the lblImagenTweet to set
	 */
	public void setImagenTweet(ImageIcon imagenTweet) {
		lblImagenTweet.setIcon(imagenTweet);
		lblImagenTweet.setIcon(Util.escalarImagen(lblImagenTweet));
	}

	/**
	 * @return the lblImagenTweet
	 */
	public JLabel getLblImagenTweet() {
		return lblImagenTweet;
	}

	/**
	 * @param lblImagenTweet the lblImagenTweet to set
	 */
	public void setLblImagenTweet(JLabel lblImagenTweet) {
		this.lblImagenTweet = lblImagenTweet;
	}

	public void setBordeImagenTweet(int i, int j, int k, int l, Color color) {
		lblImagenTweet.setBorder(new MatteBorder(i,j,k,l, color));
	}
	
	public static void main(String [] args){
		JFrame j = new JFrame();
		j.setSize(500, 500);
		GUITweet g = new GUITweet("3d", new Tweet(1L, "yo", "yomismo", new Date(3L), new ImageIcon(GUITweet.class.getResource("/res/images/usertest.jpg")).getImage(), "hola mundo", false, false));
		j.getContentPane().add(g);
		j.setLocationRelativeTo(null);
		j.setVisible(true);
	}
	/**
	 * @return the tweet
	 */
	public Tweet getTweet() {
		return tweet;
	}
	/**
	 * @param tweet the tweet to set
	 */
	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}
}
