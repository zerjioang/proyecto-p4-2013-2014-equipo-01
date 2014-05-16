package model;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.imageio.ImageIO;

import twitter4j.Status;
/**
 * Clase que representa un tweet en la base de datos
 * @author Fiser
 *
 */
public class Tweet {
	private long codigo;
	private String nombreUsuario;
	private String nombreReal;
	private Date ultimaFechaActualizacion;
	private Image imagenUsuario;
	private Image imagenDelTweet;
	private String texto;
	private boolean esRetweet;
	private boolean esFavorito;
	
	public Tweet(long codigo, String nombreUsuario, String nombreReal,
			Date ultimaFechaActualizacion, Image imagenUsuario, String texto,
			boolean esRetweet, boolean esFavorito, Image imagenDelTweet) {
		super();
		this.codigo = codigo;
		this.nombreUsuario = nombreUsuario;
		this.nombreReal = nombreReal;
		this.ultimaFechaActualizacion = ultimaFechaActualizacion;
		this.imagenUsuario = imagenUsuario;
		this.texto = texto;
		this.imagenDelTweet = imagenDelTweet;
		this.esRetweet = esRetweet;
		this.esFavorito = esFavorito;
	}
	
	public Tweet(long codigo, String nombreUsuario, String nombreReal,
			Date ultimaFechaActualizacion, Image imagenUsuario, String texto,
			boolean esRetweet, boolean esFavorito) {
		this(codigo, nombreUsuario, nombreReal, ultimaFechaActualizacion, imagenUsuario, texto, esRetweet, esFavorito, null);
	}
	
	public Tweet(Status s) {
		this.codigo = s.getId();
		this.nombreUsuario = s.getUser().getScreenName();
		this.nombreReal = s.getUser().getName();
		this.ultimaFechaActualizacion = s.getCreatedAt();
		this.imagenDelTweet = null;
		this.texto = s.getText();
		this.esRetweet = s.isRetweet();
		this.esFavorito = s.isFavorited();
		try {
			this.imagenUsuario = ImageIO.read(new URL(s.getUser().getBiggerProfileImageURL()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreReal() {
		return nombreReal;
	}

	public void setNombreReal(String nombreReal) {
		this.nombreReal = nombreReal;
	}

	public Date getUltimaFechaActualizacion() {
		return ultimaFechaActualizacion;
	}

	public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
		this.ultimaFechaActualizacion = ultimaFechaActualizacion;
	}

	public Image getImagenUsuario() {
		return imagenUsuario;
	}

	public void setImagenUsuario(Image imagenUsuario) {
		this.imagenUsuario = imagenUsuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int esRetweet() {
		if(esRetweet)
			return 1;
		else
			return 0;
	}

	public void setEsRetweet(boolean esRetweet) {
		this.esRetweet = esRetweet;
	}

	public int esFavorito() {
		if(esFavorito)
			return 1;
		else
			return 0;
	}

	public void setEsFavorito(boolean esFavorito) {
		this.esFavorito = esFavorito;
	}

	public Image getImagenDelTweet() {
		return imagenDelTweet;
	}

	public void setImagenDelTweet(Image imagenDelTweet) {
		this.imagenDelTweet = imagenDelTweet;
	}
	
}
