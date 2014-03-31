package controller.sql;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import model.Tweets;
import model.Usuario;
/**
 * Clase con metodos estaticos para facilitar la interaccion con la base de datos.
 * @author Fiser
 *
 */
public class Interaccion {
	static SQLiteManager gestor = SQLiteManager.getInstance();
	/**
	 * Permite introducir un nuevo credencial en la base de datos
	 * @param usuario
	 * @param codigo
	 * @return
	 */
	public static boolean introducirCredenciales(String usuario, String codigo, String codigoSecreto)
	{
		return 	gestor.enviarComando("INSERT INTO Usuarios (nombreUsuario, token, secretToken) VALUES ('"+usuario+"','"+codigo+"','"+codigoSecreto+"')");
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
	 * Extrae de la base de datos todos los credenciales de los usuarios registrados en la aplicacion
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
				Usuario tempUsuario = new Usuario(extraidos.getString("nombreUsuario"), extraidos.getString("token"), extraidos.getString("secretToken"));
				temporal.add(tempUsuario);
			}				

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
	/**
	 * Elimina todos los contenidos de la tabla Usuarios para poder simular
	 * que se acaba de instalar la app
	 * @return
	 */
	public static boolean borrarTodosLosCredenciales() {
		return 	gestor.enviarComando("DELETE FROM Usuarios");
	}
	public static LinkedList<Usuario> extraerUsuarios()
	{
		gestor.enviarComando("SELECT * FROM Usuarios");
		try {
			ResultSet extraidos = gestor.getResultSet();
			LinkedList<Usuario> temporal = new LinkedList<Usuario>();
			while(extraidos.next())
			{
				Usuario tempUsuario = new Usuario(extraidos.getString("nombreUsuario"),
						extraidos.getString("token"),
						extraidos.getString("secretToken"),
						extraidos.getString("nombreReal"),
						extraidos.getString("biografia"),	
						null,	
						extraidos.getDate("fechaActualizacion"),	
						extraidos.getInt("numeroTweets"),	
						extraidos.getInt("numeroSeguidos"),	
						extraidos.getInt("numeroSeguidores"));
				temporal.add(tempUsuario);
			}
			cargarImagenes(temporal);
			return temporal;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	private static void cargarImagenes(LinkedList<Usuario> usuarios)
	{
		for(Usuario temp: usuarios)
		{
			try{
			temp.setImagen(gestor.getImage(temp.getNombreUsuario()));
			}catch(Exception e){
			}
		}
	}
	/**
	 * Permite introducir un nuevo Tweet en la base de datos para una cuenta
	 * @param usuario
	 * @param codigo
	 * @return
	 */
	public static boolean insertarTweet(Tweets añadir, String nombreUsuario, String formatoImagen)
	{
		//Repensar
		if(gestor.enviarComando("INSERT INTO Tweets(codigo, fechaActualizacion, nombreUsuario, nombreReal, texto, esRetweet, esFavorito) VALUES ('"+añadir.getCodigo()+"','"+añadir.getUltimaFechaActualizacion().toString()+"','"+añadir.getNombreUsuario()+"','"+añadir.getNombreReal()+"','"+añadir.getTexto()+"',"+añadir.esRetweet()+","+añadir.esFavorito()))
		{
			gestor.enviarComando("INSERT INTO Tienen VALUES ('"+nombreUsuario+"','"+añadir.getCodigo()+"')");
			return actualizarImagenTweet(nombreUsuario, añadir.getImagenUsuario(), formatoImagen, añadir.getCodigo());			 
		}
		else //puede fallar la insercción porque ya esté dentro, en cuyo caso significa que está con otro usuario y hay solo que asociarlo
			if(gestor.enviarComando("INSERT INTO Tienen VALUES ('"+nombreUsuario+"','"+añadir.getCodigo()+"')")){
				return true;
			}
			else
				return false;
	}
	public static boolean actualizarImagenTweet(String nombreUsuario, Image imagen, String formato, String codTweet)
	{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        try {
			ImageIO.write((RenderedImage)imagen, formato, baos);
		} catch (IOException e) {
			e.printStackTrace();
		}  
        byte[] data = baos.toByteArray(); 
        return gestor.enviarImagen("UPDATE Tweets SET imagen = ? WHERE nombreUsuario = '"+nombreUsuario+"' AND codigo = '"+codTweet+"'", data);
	}
	public static void main(String[]args) throws IOException
	{
		System.out.println(Interaccion.extraerUsuarios());

		/*
		LinkedList<Usuario> temp = extraerUsuarios();
		System.out.println(temp.get(1).getImagen().toString());
		JFrame ventana = new JFrame();
		ventana.setSize(new Dimension(400, 200));
		JLabel temp2 = new JLabel();
		temp2.setIcon(new ImageIcon(temp.get(1).getImagen()));
		ventana.add(temp2);
		ventana.setVisible(true);*/
		// prueba
		borrarTodosLosCredenciales();
	}
	
}
