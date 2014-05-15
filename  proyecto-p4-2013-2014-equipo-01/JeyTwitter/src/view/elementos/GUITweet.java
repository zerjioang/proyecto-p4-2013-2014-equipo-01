package view.elementos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import controller.GUIController;
import model.Tweet;
import sun.font.SunFontManager.FamilyDescription;
import twitter4j.MediaEntity;
import twitter4j.TwitterException;
import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;
import util.Util;
import view.elementos.botones.BotonDosEstados;
import view.elementos.botones.Button;
import view.eventos.URL.EventoEscucharClickURL;
import view.eventos.celdaTweet.EventoClickBtnFavorito;
import view.eventos.celdaTweet.EventoClickBtnReTweet;
import view.eventos.celdaTweet.EventoClickBtnResponder;
import view.eventos.celdaTweet.EventoClickImagenTweet;
import view.eventos.celdaTweet.EventoClickImagenUsuario;
import view.eventos.principal.EventoClickFotoUsuario;
import view.models.tablasPrincipal.TablaTweetsUsuarios;

public class GUITweet extends JPanel implements ObjetoCelda{
	
	private static final int ALTO = 70;
	private static final int SIZE_IMAGEN = 50;
	private static final int REDONDEO = 15;
	private JLabel lblTiempo, lblImagenusuario, lblNombreReal, lblnombreUsuario;
	private BotonDosEstados btnResponder, btnRetweet, btnFavorito;
	private JEditorPane txtMensaje;
	private JLabel lblImagenTweet;
	private JPanel panelImagenTweet;
	private Tweet tweet;
	private ImageIcon[] img;
	private String imagenTuit;
	
	private ArrayList<String> usuarioMencionado;
	private ArrayList<String> hashtagTweet;
	private ArrayList<String> urlsTweet;
	
	public static void main(String [] args){
		JFrame j = new JFrame();
		j.setSize(500, 500);
		GUITweet g = new GUITweet(Util.calcularFecha(new Date(System.currentTimeMillis()-50000000)), new Tweet(1L, "yo", "yomismo", new Date(3L), new ImageIcon(GUITweet.class.getResource("/res/images/usertest.jpg")).getImage(), "Que tal, @kronosnhz? te paso un link http://goo.gl/ #EstoNoSeToca", false, false, null));
		j.getContentPane().add(g);
		j.setLocationRelativeTo(null);
		j.setVisible(true);
	}
	
	public GUITweet(String fecha, Tweet t) {
		super();
		
		usuarioMencionado = new ArrayList<String>();
		hashtagTweet = new ArrayList<String>();
		urlsTweet = new ArrayList<String>();
		
		this.lblImagenusuario = new JLabel();
		this.lblNombreReal = new JLabel();
		this.lblnombreUsuario = new JLabel();
		lblImagenTweet = new JLabel();
		this.txtMensaje = procesarMensaje(t);
		this.lblTiempo = new JLabel();
		
		setTiempo(fecha);
		lblImagenusuario.setSize(ALTO, ALTO);
		setImagenUsuario(new ImageIcon(t.getImagenUsuario()));
		setNombreReal(t.getNombreReal());
		setNombreUsuario(t.getNombreUsuario());
		
		btnRetweet = new BotonDosEstados();
		btnFavorito = new BotonDosEstados();
		btnResponder = new BotonDosEstados();
		
		img = new ImageIcon[2];
		tweet = t;
		
		lblImagenusuario.addMouseListener(new EventoClickFotoUsuario(GUIController.getInstance().getGui(), getNombreUsuario()));
		init();
	}
	private JEditorPane procesarMensaje(Tweet t) {
		// TODO Auto-generated method stub
		Util.debug("Parseando contenido del tweet...");
		//habia que especificar por HTML el tama���o y las demas caracteristicas del texto si no se usa putClientProperty
		//String fontfamily = Util.getFont("Roboto-Light", Font.PLAIN, 12).getFamily();
		//"<html><font face=\"" + font.getFamily() + "\" size=\"" + font.getSize() + "\"></font>This is some text!</html>"
		String mensajeFormateado = t.getTexto();
		String linkAntes = "<a href=\"";
		String linkMedio = "\" style=\"text-decoration:none; color: #005996\">";
		String linkDespues = " </a>";

		/*if(GUIController.getInstance().hayConexion()){
			try {
				MediaEntity[] media = GUIController.getInstance().getMedias(t.getCodigo());
				UserMentionEntity[] menciones = GUIController.getInstance().getMenciones(t.getCodigo());
				URLEntity[] urls = GUIController.getInstance().getURLs(t.getCodigo());
			
				for (URLEntity u : urls) {
					String url = u.getDisplayURL();
					String html = linkAntes+url+linkMedio+url+linkDespues;
					mensajeFormateado = mensajeFormateado.replaceAll(url, html);
					Util.debug("Reemplazando URL "+url+" por "+html);
				}
				for (UserMentionEntity m : menciones) {
					String nombreArroba = "@"+m.getScreenName();
					String html = linkAntes+nombreArroba+linkMedio+nombreArroba+linkDespues;
					mensajeFormateado = mensajeFormateado.replaceAll(nombreArroba, html);
					Util.debug("Reemplazando nombre "+nombreArroba+" por "+html);
				}
				
				lblImagenTweet.setVisible(false);
				Util.debug("Numero de imagenes detectadas: "+media.length);
				for (MediaEntity m : media) {
					String urlAntes = m.getURL();
					Util.debug("Eliminando URL Imagen "+urlAntes);
					mensajeFormateado = mensajeFormateado.replaceAll(urlAntes, "");
					imagenTuit = m.getMediaURLHttps();
					lblImagenTweet.setVisible(true);
					//solo se procesa una
					break;
				}
			} catch (TwitterException e) {
				System.err.println("Error al procesar los datos - "+e.getMessage());
			}
		}
		else{
			//Recuperar mensaje de la BD
			mensajeFormateado = "Como lo llevas? "+linkAntes+"@zerjioAng"+linkMedio+"@zerjioAng"+linkDespues+"Date cuenta <a href=\"@kronosnhz\" style=\"text-decoration:none\"> @kronosnhz </a> del 'como' sin tilde jaja";
		}*/
		
		Pattern p = Pattern.compile("(?<!\\w)@[\\w]+");
		//@zerjioAng @_somega @ruben_fiser @kronosnhz
		Matcher m = p.matcher(mensajeFormateado);
		
		while(m.find()) {
			usuarioMencionado.add(0, m.group());
		}
		for (String s : usuarioMencionado) {
			System.out.println("usuarios mencionados: "+s);
			String html = linkAntes+s+linkMedio+s+linkDespues;
			mensajeFormateado = mensajeFormateado.replaceAll(s, html);
			Util.debug("Reemplazando nombre "+s+" por "+html);
		}
		String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";
		p = Pattern.compile(regex);
		m = p.matcher(t.getTexto());
		
		while(m.find()) {
			urlsTweet.add(0, m.group());
		}
		for (String s : urlsTweet) {
			System.out.println("urls encontradas: "+s);
			String html = linkAntes+s+linkMedio+s+linkDespues;
			mensajeFormateado = mensajeFormateado.replaceAll(s, html);
			Util.debug("Reemplazando url "+s+" por "+html);
		}
		
		//generar las mediaEntity para las imagenes.
		Util.debug("Obteniendo imagen...");
		if(GUIController.getInstance().hayConexion()){
			try {
				MediaEntity[] media = GUIController.getInstance().getMedias(t.getCodigo());
				for (MediaEntity im : media) {
					String urlAntes = im.getURL();
					urlsTweet.add(0, urlAntes);
					Util.debug("Eliminando URL Imagen "+urlAntes);
					mensajeFormateado = mensajeFormateado.replaceAll(urlAntes, "");
					imagenTuit = im.getMediaURLHttps();
					lblImagenTweet.setVisible(true);
					//solo se procesa una
					break;
				}
			} catch (TwitterException e) {}
		}
		
		String hashtagRE = "#(\\w+)";//"#\\w+";
		p = Pattern.compile(hashtagRE);
		m = p.matcher(t.getTexto());
		System.out.println("Tweet: "+t.getTexto());
		while(m.find()) {
			hashtagTweet.add(m.group());
		}
		for (String s : hashtagTweet) {
			System.out.println("hashtags encontrados: "+s);
			String hashtag = linkAntes+s+"\" style=\"text-decoration:none; color: #f04400\">"+s+linkDespues;
			mensajeFormateado = mensajeFormateado.replaceAll(s, hashtag);
			Util.debug("Reemplazando hashtag "+s+" por "+hashtag);
		}
		//Contruir el objeto para visualizarlo en pantalla una vez se tienen los datos
		
		/*JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane);*/
		Util.debug(mensajeFormateado);
		
		JEditorPane editor = new JEditorPane();
		editor.setEditable(false);
		editor.setFocusable(true);
		editor.setContentType("text/html"); 
		editor.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		editor.setEditable(false);
		editor.setFocusable(true);
		editor.setOpaque(false);
		editor.setBorder(null);
		editor.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
		//Permite establecer la fuente del objeto mediante java en vez de mediante sintaxis HTML empebida en el texto
		editor.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		editor.setFont(Util.getFont("Roboto-regular", Font.PLAIN, 12));
		editor.setText(mensajeFormateado);
		
		//añadir evento de click
		editor.addHyperlinkListener(new EventoEscucharClickURL());
	    
		//scrollPane.setViewportView(editor);
		return editor;
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
		this.txtMensaje = new JEditorPane();
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
		lblTiempo.setFont(Util.getFont("roboto-light", Font.PLAIN, 12));

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
		panelCentroMensaje.add(txtMensaje,BorderLayout.CENTER);
		panelCentroMensaje.setBorder(new MatteBorder(0, 0, 0, 1, new Color(1.0f,1.0f,1.0f,0.0f)));
		
		if(imagenTuit!=null && imagenTuit.length()>0){
			panelImagenTweet = new JPanel();
			panelImagenTweet.setLayout(new BorderLayout(0, 0));
			panelCentroMensaje.add(panelImagenTweet, BorderLayout.EAST);
			
			lblImagenTweet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblImagenTweet.setSize(SIZE_IMAGEN, SIZE_IMAGEN);
			lblImagenTweet.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagenTweet.setHorizontalTextPosition(SwingConstants.CENTER);
			setBordeImagenTweet(3,3,3,3, new Color(255, 255, 255));
			//asignar imagenTweet;
			setImagenTweet(imagenTuit);
			panelImagenTweet.add(lblImagenTweet, BorderLayout.SOUTH);
			
			//evento al clicar encima de la imagen del tweet
			lblImagenTweet.addMouseListener(new EventoClickImagenTweet(this));
			//evento al clicar la imagen del usuario
			lblImagenusuario.addMouseListener(new EventoClickImagenUsuario(this));
		}
	}

	private void setImagenTweet(String imagenTuit) {
		BufferedImage bufferImg;
		try {
			bufferImg= GUIController.getInstance().pedirImagen(new URL(imagenTuit));
			Util.debug("Pidiendo imagen: "+imagenTuit+" resultado = "+bufferImg.toString());
			//se guarda la original
			img[0]=new ImageIcon(bufferImg);
			//se guarda la miniatura
			setImagenTweet(new ImageIcon(bufferImg));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public ImageIcon getImagenTweetMiniatura(){
		return img[1];
	}

	public ImageIcon getImagenTweetOriginal(){
		return img[0];
	}
	/**
	 * @param lblImagenTweet the lblImagenTweet to set
	 */
	public void setImagenTweet(ImageIcon imagenTweet) {
		lblImagenTweet.setIcon(imagenTweet);
		lblImagenTweet.setIcon(Util.escalarImagen(lblImagenTweet));
		img[1]=(ImageIcon) lblImagenTweet.getIcon();
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

	/**
	 * @return the usuarioMencionado
	 */
	public ArrayList<String> getUsuarioMencionado() {
		return usuarioMencionado;
	}

	/**
	 * @param usuarioMencionado the usuarioMencionado to set
	 */
	public void setUsuarioMencionado(ArrayList<String> usuarioMencionado) {
		this.usuarioMencionado = usuarioMencionado;
	}

	/**
	 * @return the hashtagTweet
	 */
	public ArrayList<String> getHashtagTweet() {
		return hashtagTweet;
	}

	/**
	 * @param hashtagTweet the hashtagTweet to set
	 */
	public void setHashtagTweet(ArrayList<String> hashtagTweet) {
		this.hashtagTweet = hashtagTweet;
	}

	/**
	 * @return the urlsTweet
	 */
	public ArrayList<String> getUrlsTweet() {
		return urlsTweet;
	}

	/**
	 * @param urlsTweet the urlsTweet to set
	 */
	public void setUrlsTweet(ArrayList<String> urlsTweet) {
		this.urlsTweet = urlsTweet;
	}
}
