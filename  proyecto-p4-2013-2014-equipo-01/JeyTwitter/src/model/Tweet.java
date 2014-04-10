package model;

import java.awt.Image;
import java.util.Date;
/**
 * Clase que representa un tweet en la base de datos
 * @author Fiser
 *
 */
public class Tweet {
	private String codigo;
	private String nombreUsuario;
	private String nombreReal;
	private Date ultimaFechaActualizacion;
	private Image imagenUsuario;
	private String texto;
	private boolean esRetweet;
	private boolean esFavorito;
	
	public Tweet(String codigo, String nombreUsuario, String nombreReal,
			Date ultimaFechaActualizacion, Image imagenUsuario, String texto,
			boolean esRetweet, boolean esFavorito) {
		super();
		this.codigo = codigo;
		this.nombreUsuario = nombreUsuario;
		this.nombreReal = nombreReal;
		this.ultimaFechaActualizacion = ultimaFechaActualizacion;
		this.imagenUsuario = imagenUsuario;
		this.texto = texto;
		this.esRetweet = esRetweet;
		this.esFavorito = esFavorito;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
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
	
}
