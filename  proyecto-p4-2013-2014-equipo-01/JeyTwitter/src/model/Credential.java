package model;
/**
 * Clase que representa la tabla que se almacena en la BBDD con los credenciales del usuario
 * @author aitor
 *
 */
public class Credential {
	private String code;
	private String username;
	
	Credential() {
		code = null;
		username = null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
