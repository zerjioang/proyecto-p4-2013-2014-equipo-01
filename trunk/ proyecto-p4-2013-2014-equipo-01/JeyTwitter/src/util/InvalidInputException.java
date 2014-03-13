package util;
/**
 * Excepcion que se genera cuando un campo contiene mas caracteres de los que se pueden visualizar
 * por pantalla
 * @author Sergio Anguita
 *
 */
public class InvalidInputException extends Exception {
	/**
	 * Lanza la excepcion cuando se sobrepasa el limite establecido
	 * @param msg	Texto que se mostrara en detalle del error
	 */
	public InvalidInputException(String msg){
		 super(msg);
	}
}
