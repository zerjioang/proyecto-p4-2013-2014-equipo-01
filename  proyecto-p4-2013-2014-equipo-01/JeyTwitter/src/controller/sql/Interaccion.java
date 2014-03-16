package controller.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import controller.sql.model.Usuario;

public class Interaccion {
	static SQLiteManager gestor = SQLiteManager.getInstance();
	/**
	 * Permite introducir un nuevo credencial en la base de datos
	 * @param usuario
	 * @param codigo
	 * @return
	 */
	public static boolean introducirCredenciales(String usuario, String codigo)
	{
		return 	gestor.enviarComando("INSERT INTO Usuarios (nombreUsuario, token) VALUES ('"+usuario+"','"+codigo+"')");
	}
	/**
	 * Borra el credencial de la base de datos
	 * @param usuario
	 * @return
	 */
	public static boolean borrarCredenciales(String usuario)
	{
		return 	gestor.enviarComando("DELETE FROM Usuarios WHERE nombreUsuario = '"+usuario+"'");
	}
	/**
	 * Extrae de la base de datos todos los credenciales de los usuarios registrados en la aplicación
	 * @return
	 */
	public static LinkedList<Usuario> extraerCredenciales()
	{
		gestor.enviarComando("SELECT * FROM Usuarios");
		try {
			ResultSet extraidos = gestor.getResultSet();
			LinkedList<Usuario> temporal = new LinkedList<Usuario>();
			while(extraidos.next())
			{
				Usuario tempUsuario = new Usuario(extraidos.getString("nombreUsuario"), extraidos.getString("token"));
				temporal.add(tempUsuario);
			}
			gestor.disconnet();
			return temporal;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[]args)
	{
		LinkedList<Usuario>temp = extraerCredenciales();
		for(Usuario temporal: temp)
			System.out.println("Usuario: " + temporal.getNombreUsuario() + "	Token: " + temporal.getToken());
	}
}
