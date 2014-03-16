package controller.sql;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

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
	public static boolean actualizarNombreReal(String nombreUsuario, String nombreReal)
	{
		return 	gestor.enviarComando("UPDATE Usuarios SET nombreReal = '"+nombreReal+"' WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	public static boolean actualizarBiografia(String nombreUsuario, String biografia)
	{
		return 	gestor.enviarComando("UPDATE Usuarios SET biografia = '"+biografia+"' WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	public static boolean actualizarNumSeguidores(String nombreUsuario, int numSeguidores)
	{
		return 	gestor.enviarComando("UPDATE Usuarios SET numeroSeguidores = "+numSeguidores+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	public static boolean actualizarNumSeguidos(String nombreUsuario, int numSeguidos)
	{
		return 	gestor.enviarComando("UPDATE Usuarios SET numeroSeguidos = "+numSeguidos+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	public static boolean actualizarNumTweets(String nombreUsuario, int numTweets)
	{
		return 	gestor.enviarComando("UPDATE Usuarios SET numeroTweets = "+numTweets+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	public static boolean actualizarImagenPerfil(String nombreUsuario, Image imagen, String formato)
	{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        try {
			ImageIO.write((RenderedImage)imagen, formato, baos);
		} catch (IOException e) {
			e.printStackTrace();
		}  
        byte[] data = baos.toByteArray(); 
        return gestor.enviarImagen("UPDATE Usuarios SET imagen = ? WHERE nombreUsuario = '"+nombreUsuario+"'", data);
	}
	public static void main(String[]args) throws IOException
	{

	}
	
}
