package controller.sql;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Tweet;
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
		return 	gestor.enviarComando("INSERT INTO Usuario (nombreUsuario, token, secretToken) VALUES ('"+usuario+"','"+codigo+"','"+codigoSecreto+"')");
	}
	/**
	 * Permite introducir un nuevo credencial en la base de datos
	 * @param usuario
	 * @param codigo
	 * @return
	 */
	public static boolean introducirUsuario(Usuario introducir)
	{
		boolean comprobar = true;
		comprobar = comprobar && gestor.enviarComando("INSERT INTO Usuario(nombreUsuario, nombreReal, token, secretToken, biografia, numeroSeguidos, numeroSeguidores, numeroTweets, fechaActualizacion) VALUES ('"+introducir.getNombreUsuario()+"','"+introducir.getNombreReal()+"','"+introducir.getToken()+"','"+introducir.getTokenSecreto()+"','"+introducir.getBiografia()+"',"+introducir.getNumeroSiguiendo()+","+introducir.getNumeroSeguidores()+","+introducir.getNumeroTweets()+",'"+introducir.getUltimaFechaActualizacion().toString()+"')");
		comprobar = comprobar && actualizarImagenPerfil(introducir.getNombreUsuario(), introducir.getImagen(), "png");
		return comprobar;
	}
	/**
	 * Borra el credencial de la base de datos
	 * @param usuario
	 * @return
	 */
	public static boolean borrarCredenciales(String usuario)
	{
		return 	gestor.enviarComando("DELETE FROM Usuario WHERE nombreUsuario = '"+usuario+"'");
	}
	/**
	 * Borra los Tweets del usuario indicado
	 * @param usuario
	 * @return
	 */
	public static boolean borrarTweets(String usuario)
	{
		return 	gestor.enviarComando("DELETE FROM Tienen WHERE nombreUsuario = '"+usuario+"'");
	}
	/**
	 * Extrae de la base de datos todos los credenciales de los usuarios registrados en la aplicacion
	 * @return
	 */
	public static LinkedList<Usuario> extraerCredenciales()
	{
		gestor.enviarComando("SELECT * FROM Usuario");
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
	/**
	 * Permite cambiar el nombre real del usuario en la BD
	 * @param nombreUsuario
	 * @param nombreReal
	 * @return
	 */
	public static boolean actualizarNombreReal(String nombreUsuario, String nombreReal)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET nombreReal = '"+nombreReal+"' WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	/**
	 * Permite actualizar la biografía del usuario en la BD
	 * @param nombreUsuario
	 * @param biografia
	 * @return
	 */
	public static boolean actualizarBiografia(String nombreUsuario, String biografia)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET biografia = '"+biografia+"' WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	/**
	 * Permite actualizar el numero de seguidores en la BD
	 * @param nombreUsuario
	 * @param numSeguidores
	 * @return
	 */
	public static boolean actualizarNumSeguidores(String nombreUsuario, int numSeguidores)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET numeroSeguidores = "+numSeguidores+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	/**
	 * Permite actualizar el numero de seguidos en la BD
	 * @param nombreUsuario
	 * @param numSeguidos
	 * @return
	 */
	public static boolean actualizarNumSeguidos(String nombreUsuario, int numSeguidos)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET numeroSeguidos = "+numSeguidos+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	/**
	 * Permite actualizar el numero de tweets que dispone la cuenta.
	 * @param nombreUsuario
	 * @param numTweets
	 * @return
	 */
	public static boolean actualizarNumTweets(String nombreUsuario, int numTweets)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET numeroTweets = "+numTweets+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	/**
	 * Permite cambiar la imagen del perfil del usuario
	 * @param nombreUsuario
	 * @param imagen
	 * @param formato
	 * @return
	 */
	public static boolean actualizarImagenPerfil(String nombreUsuario, Image imagen, String formato)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		try {
			ImageIO.write((RenderedImage)imagen, formato, baos);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		byte[] data = baos.toByteArray(); 
		return gestor.enviarImagen("UPDATE Usuario SET imagen = ? WHERE nombreUsuario = '"+nombreUsuario+"'", data);
	}
	/**
	 * Elimina todos los contenidos de la tabla Usuarios para poder simular
	 * que se acaba de instalar la app
	 * @return
	 */
	public static boolean borrarTodosLosCredenciales() {
		return 	gestor.enviarComando("DELETE FROM Usuario");
	}
	/**
	 * Extrae todos los usuarios de la base de datos
	 * @return
	 */
	public static LinkedList<Usuario> extraerUsuarios()
	{
		gestor.enviarComando("SELECT * FROM Usuario");
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
						0,
						extraidos.getInt("numeroSeguidos"),	
						extraidos.getInt("numeroSeguidores"));
				temporal.add(tempUsuario);
			}
			cargarImagenesUsuarios(temporal);
			return temporal;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Método que extrae los tweets de la base de datos almacenados para una cuenta en cuestión
	 * @param cuenta
	 * @return
	 */
	public static LinkedList<Tweet> extraerTweets(String cuenta)
	{
		gestor.enviarComando("SELECT T.* FROM Tweet T, Tienen TI WHERE T.codigo=TI.codigo AND TI.nombreUsuario='"+cuenta+"'");
		try {
			ResultSet extraidos = gestor.getResultSet();
			LinkedList<Tweet> temporal = new LinkedList<Tweet>();
			while(extraidos.next())
			{
				boolean esRetweet = false;
				boolean esFav = false;
				if(extraidos.getInt("esRetweet")==1)
					esRetweet = true;
				if(extraidos.getInt("esFavorito")==1)
					esFav = true;
				
				Tweet tempTweet = new Tweet(extraidos.getLong("codigo"),
						extraidos.getString("nombreUsuario"),
						extraidos.getString("nombreReal"),
						extraidos.getDate("fechaActualizacion"),	
						null,	
						extraidos.getString("texto"),	
						esRetweet,	
						esFav);
				temporal.add(tempTweet);
				cargarImagenesTweets(temporal);
			}
			return temporal;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	private static void cargarImagenesUsuarios(LinkedList<Usuario> usuarios)
	{
		for(Usuario temp: usuarios)
		{
			try{
				temp.setImagen(gestor.getImageUsuario(temp.getNombreUsuario()));
			}catch(Exception e){
			}
		}
	}
	private static void cargarImagenesTweets(LinkedList<Tweet> tweets)
	{
		for(Tweet temp: tweets)
		{
			try{
				temp.setImagenUsuario(gestor.getImageTweet(temp.getCodigo()));
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
	public static boolean insertarTweet(Tweet añadir, String nombreUsuario, String formatoImagen)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		gestor.enviarComando("INSERT INTO Tweet(codigo, fechaActualizacion, nombreUsuario, nombreReal, texto, esRetweet, esFavorito) VALUES ("+añadir.getCodigo()+",datetime('"+sdf.format(añadir.getUltimaFechaActualizacion())+"'), '"+añadir.getNombreUsuario()+"','"+añadir.getNombreReal()+"','"+añadir.getTexto()+"',"+añadir.esRetweet()+","+añadir.esFavorito()+")");
		gestor.enviarComando("INSERT INTO Tienen VALUES ('"+nombreUsuario+"',"+añadir.getCodigo()+")");
		try{
			actualizarImagenTweet(añadir.getImagenUsuario(), formatoImagen, añadir.getCodigo());
		}catch(IllegalArgumentException E)
		{
			return true;
		}
		return true;
	}
	/**
	 * Permite insertar una lista de Tweets, pero el formato de las imagenes debe de ser siempre el mismo, aun no he probado que sucede si se le indica cualquier tipo de formato.
	 * Devuelve true si se han insertado todos correctamente.
	 * @param añadir
	 * @param nombreUsuario
	 * @param formatosImagenes
	 * @return
	 */
	public static boolean insertarTweets(LinkedList<Tweet> añadir, String nombreUsuario, String formatosImagenes)
	{
		boolean correcto = true;
		for(Tweet temp: añadir)
		{
			correcto = correcto && insertarTweet(temp, nombreUsuario, formatosImagenes);
		}
		return correcto;
	}
	/**
	 * Permite actualizar la imagen del tweet
	 * @param nombreUsuario
	 * @param imagen
	 * @param formato
	 * @param codTweet
	 * @return
	 */
	public static boolean actualizarImagenTweet(Image imagen, String formato, long codTweet)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		try {
			ImageIO.write((RenderedImage)imagen, formato, baos);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		byte[] data = baos.toByteArray(); 
		return gestor.enviarImagen("UPDATE Tweet SET imagenUsuario = ? WHERE codigo = "+codTweet+"", data);
	}
	public static void reiniciarBase()
	{
		gestor.enviarComando("DROP TABLE IF EXISTS Usuario;");
		gestor.enviarComando("DROP TABLE IF EXISTS Tweet;");
		gestor.enviarComando("DROP TABLE IF EXISTS Tienen;");
		crearEstructura();
	}
	public static void crearEstructura()
	{
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Usuario (nombreUsuario text NOT NULL,nombreReal text, token text NOT NULL, secretToken text, biografia text, imagen blob, numeroSeguidos integer, numeroSeguidores integer, numeroTweets integer, fechaActualizacion Datetime,PRIMARY KEY(nombreUsuario));");		
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Tweet (codigo BIGINT NOT NULL, fechaActualizacion DATETIME, nombreUsuario TEXT, nombreReal TEXT, imagenUsuario blob, texto TEXT, esRetweet integer, esFavorito integer, PRIMARY KEY(codigo));");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Tienen (nombreUsuario text NOT NULL,codigo text NOT NULL,PRIMARY KEY(nombreUsuario,codigo),CONSTRAINT nombreUsuario FOREIGN KEY (nombreUsuario) REFERENCES Usuario (nombreUsuario) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT codigo FOREIGN KEY (codigo) REFERENCES Tweet (codigo) ON DELETE CASCADE ON UPDATE CASCADE);");
	}
	public static void main(String[]args) throws IOException
	{
		reiniciarBase();
//Esta parte del código prueba la inserción de usuarios y extracción de los mismos con la imagen
/*		Usuario temp3 = new Usuario("Fiser12", "21323", "dfasdf", "Fiser", "bibliografia", ImageIO.read(new File("src/res/images/notif/notification_follower.png")), new Date(12122012), 4, 2, 2);
		System.out.println(introducirUsuario(temp3));
		
		JFrame temp2 = new JFrame();
		temp2.add(new JLabel(new ImageIcon(extraerUsuarios().get(0).getImagen())));
		temp2.setVisible(true);*/
/////////////////////----------------------------------------//////////////////////
//Esta parte de cóidgo prueba la insercción un tweet en la bd
/*		Tweet temp = new Tweet(6554667, "jejfkef", "fgd", new Date(12121987), ImageIO.read(new File("src/res/images/notif/notification_follower.png")), "fdf", true, true);
		System.out.println(insertarTweet(temp, "Fiser12", "png"));
		System.out.println(extraerTweets("Fiser12").size());
		JFrame temp2 = new JFrame();
		temp2.add(new JLabel(new ImageIcon(extraerTweets("Fiser12").get(0).getImagenUsuario())));
		temp2.setVisible(true);*/
//		borrarTodosLosCredenciales();
		
	}

}
