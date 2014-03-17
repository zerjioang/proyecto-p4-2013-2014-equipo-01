package model;

import java.awt.Image;
import java.sql.Date;
/**
 * Clase que representa a un usuario en la base de datos
 * @author Fiser
 *
 */
public class Usuario {
	private String token;
	private String tokenSecreto;
	private String nombreUsuario;
	private String nombreReal;
	private String biografia;
	private Image imagen;
	private Date ultimaFechaActualizacion;
	private int numeroTweets;
	private int numeroSiguiendo;
	private int numeroSeguidores;
	public Usuario(String nombreUsuario, String token, String tokenSecreto)
	{
		this.token = token;
		this.nombreUsuario = nombreUsuario;
		this.tokenSecreto = tokenSecreto;
	}
	public Usuario(String nombreUsuario, String token, String tokenSecreto, String nombreReal,
			String biografia, Image imagen, Date ultimaFechaActualizacion,
			int numeroTweets, int numeroSiguiendo, int numeroSeguidores) {
		this.token = token;
		this.tokenSecreto = tokenSecreto;
		this.nombreUsuario = nombreUsuario;
		this.nombreReal = nombreReal;
		this.biografia = biografia;
		this.imagen = imagen;
		this.ultimaFechaActualizacion = ultimaFechaActualizacion;
		this.numeroTweets = numeroTweets;
		this.numeroSiguiendo = numeroSiguiendo;
		this.numeroSeguidores = numeroSeguidores;
	}
	public String getTokenSecreto() {
		return tokenSecreto;
	}
	public void setTokenSecreto(String tokenSecreto) {
		this.tokenSecreto = tokenSecreto;
	}
	public Date getUltimaFechaActualizacion() {
		return ultimaFechaActualizacion;
	}
	public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
		this.ultimaFechaActualizacion = ultimaFechaActualizacion;
	}
	public int getNumeroTweets() {
		return numeroTweets;
	}
	public void setNumeroTweets(int numeroTweets) {
		this.numeroTweets = numeroTweets;
	}
	public Date getLastDateUpdated() {
		return ultimaFechaActualizacion;
	}
	public void setLastDateUpdated(Date ultimaFechaActualizacion) {
		this.ultimaFechaActualizacion = ultimaFechaActualizacion;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	public Image getImagen() {
		return imagen;
	}
	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	public int getNumeroSiguiendo() {
		return numeroSiguiendo;
	}
	public void setNumeroSiguiendo(int numeroSiguiendo) {
		this.numeroSiguiendo = numeroSiguiendo;
	}
	public int getNumeroSeguidores() {
		return numeroSeguidores;
	}
	public void setNumeroSeguidores(int numeroSeguidores) {
		this.numeroSeguidores = numeroSeguidores;
	}
}
