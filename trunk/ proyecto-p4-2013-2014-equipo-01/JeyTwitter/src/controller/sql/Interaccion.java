package controller.sql;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import model.Tweet;
import model.Usuario;

public class Interaccion {
	static SQLiteManager gestor = SQLiteManager.getInstance();
	
	public static boolean introducirCredenciales(String usuario, String codigo, String codigoSecreto)
	{
		return 	gestor.enviarComando("INSERT INTO Usuario (nombreUsuario, token, secretToken) VALUES ('"+usuario+"','"+codigo+"','"+codigoSecreto+"')");
	}
	
	public static boolean introducirUsuario(Usuario introducir)
	{
		boolean comprobar = true;
		comprobar = comprobar && gestor.enviarComando("INSERT INTO Usuario(nombreUsuario, nombreReal, token, secretToken, biografia, numeroSeguidos, numeroSeguidores, numeroTweets, numeroFavoritos, fechaActualizacion) VALUES ('"+introducir.getNombreUsuario()+"','"+introducir.getNombreReal()+"','"+introducir.getToken()+"','"+introducir.getTokenSecreto()+"','"+introducir.getBiografia()+"',"+introducir.getNumeroSiguiendo()+","+introducir.getNumeroSeguidores()+","+introducir.getNumeroTweets()+","+introducir.getNumeroFavoritos()+",'"+introducir.getUltimaFechaActualizacion().toString()+"')");
		comprobar = comprobar && actualizarImagenPerfil(introducir.getNombreUsuario(), introducir.getImagen(), "png");
		return comprobar;
	}
	
	public static boolean borrarCredenciales(String usuario)
	{
		return 	gestor.enviarComando("DELETE FROM Usuario WHERE nombreUsuario = '"+usuario+"'");
	}
	
	public static boolean borrarTweets(String usuario)
	{
		return 	gestor.enviarComando("DELETE FROM Tienen WHERE nombreUsuario = '"+usuario+"'");
	}
	
	public static ArrayList<Usuario> extraerCredenciales()
	{
		gestor.enviarComando("SELECT * FROM Usuario");
		try {
			ResultSet extraidos = gestor.getResultSet();
			ArrayList<Usuario> temporal = new ArrayList<Usuario>();

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
		return 	gestor.enviarComando("UPDATE Usuario SET nombreReal = '"+nombreReal+"' WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	
	public static boolean actualizarBiografia(String nombreUsuario, String biografia)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET biografia = '"+biografia+"' WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	
	public static boolean actualizarNumSeguidores(String nombreUsuario, int numSeguidores)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET numeroSeguidores = "+numSeguidores+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	
	public static boolean actualizarNumSeguidos(String nombreUsuario, int numSeguidos)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET numeroSeguidos = "+numSeguidos+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	
	public static boolean actualizarNumTweets(String nombreUsuario, int numTweets)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET numeroTweets = "+numTweets+" WHERE nombreUsuario = '"+nombreUsuario+"'");
	}
	
	public static boolean actualizarNumFavoritos(String nombreUsuario, int numFaves)
	{
		return 	gestor.enviarComando("UPDATE Usuario SET numeroFavoritos = "+numFaves+" WHERE nombreUsuario = '"+nombreUsuario+"'");
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
		return gestor.enviarImagen("UPDATE Usuario SET imagen = ? WHERE nombreUsuario = '"+nombreUsuario+"'", data);
	}
	
	public static boolean borrarTodosLosCredenciales() {
		return 	gestor.enviarComando("DELETE FROM Usuario");
	}
	
	public static ArrayList<Usuario> extraerUsuarios()
	{
		gestor.enviarComando("SELECT * FROM Usuario");
		try {
			ResultSet extraidos = gestor.getResultSet();
			ArrayList<Usuario> temporal = new ArrayList<Usuario>();
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
						extraidos.getInt("numeroFavoritos"),
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
	
	public static ArrayList<Tweet> extraerTweets(String cuenta)
	{
		gestor.enviarComando("SELECT T.* FROM Tweet T, Tienen TI WHERE T.codigo=TI.codigo AND TI.nombreUsuario='"+cuenta+"'");
		try {
			ResultSet extraidos = gestor.getResultSet();
			ArrayList<Tweet> temporal = new ArrayList<Tweet>();
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
						esFav,
						null);
				temporal.add(tempTweet);
			}
			cargarImagenesTweets(temporal);
			cargarImagenesTweetsContenido(temporal);
			return temporal;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	private static void cargarImagenesUsuarios(ArrayList<Usuario> usuarios)
	{
		for(Usuario temp: usuarios)
		{
			try{
				temp.setImagen(gestor.getImageUsuario(temp.getNombreUsuario()));
			}catch(Exception e){
			}
		}
	}
	private static void cargarImagenesTweets(ArrayList<Tweet> tweets)
	{
		for(Tweet temp: tweets)
		{
			try{
				temp.setImagenUsuario(gestor.getImageTweet(temp.getCodigo()));
			}catch(Exception e){
			}
		}
	}
	private static void cargarImagenesTweetsContenido(ArrayList<Tweet> tweets)
	{
		for(Tweet temp: tweets)
		{
			try{
				temp.setImagenDelTweet(gestor.getImageTweetContenido(temp.getCodigo()));
			}catch(Exception e){
			}
		}
	}
	
	public static boolean insertarTweet(Tweet añadir, String nombreUsuario, String formatoImagen)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int fav = 0;
		int ret = 0;
		if(añadir.esFavorito())
			fav = 1;
		if(añadir.esRetweet())
			ret = 1;
		System.out.println(gestor.enviarComando("INSERT INTO Tweet(codigo, fechaActualizacion, nombreUsuario, nombreReal, texto, esRetweet, esFavorito) VALUES ("+añadir.getCodigo()+",datetime('"+sdf.format(añadir.getUltimaFechaActualizacion())+"'), '"+añadir.getNombreUsuario()+"','"+añadir.getNombreReal()+"','"+añadir.getTexto()+"',"+ret+","+fav+")"));
		System.out.println(gestor.enviarComando("INSERT INTO Tienen VALUES ('"+nombreUsuario+"',"+añadir.getCodigo()+")"));
		try{
			actualizarImagenTweet(añadir.getImagenUsuario(), formatoImagen, añadir.getCodigo());
		}catch(IllegalArgumentException E)
		{
			return true;
		}
		return true;
	}
	
	public static synchronized boolean insertarTweets(List<Tweet> añadir, String nombreUsuario, String formatosImagenes)
	{
		boolean correcto = true;
		for(Tweet temp: añadir)
		{
			correcto = correcto && insertarTweet(temp, nombreUsuario, formatosImagenes);
		}
		return correcto;
	}
	
	public static boolean actualizarImagenTweetContenido(Image imagen, String formato, long codTweet)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		try {
			ImageIO.write((RenderedImage)imagen, formato, baos);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		byte[] data = baos.toByteArray(); 
		return gestor.enviarImagen("UPDATE Tweet SET imagenTweet = ? WHERE codigo = "+codTweet+"", data);
	}
	
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
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Usuario (nombreUsuario text NOT NULL,nombreReal text, token text NOT NULL, secretToken text, biografia text, imagen blob, numeroSeguidos integer, numeroSeguidores integer, numeroTweets integer, numeroFavoritos integer, fechaActualizacion Datetime,PRIMARY KEY(nombreUsuario));");		
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Tweet (codigo BIGINT NOT NULL, fechaActualizacion DATETIME, nombreUsuario TEXT, nombreReal TEXT, imagenUsuario blob, imagenTweet blob, texto TEXT, esRetweet integer, esFavorito integer, PRIMARY KEY(codigo));");
		gestor.enviarComando("CREATE TABLE IF NOT EXISTS Tienen (nombreUsuario text NOT NULL,codigo text NOT NULL,PRIMARY KEY(nombreUsuario,codigo),CONSTRAINT nombreUsuario FOREIGN KEY (nombreUsuario) REFERENCES Usuario (nombreUsuario) ON DELETE CASCADE ON UPDATE CASCADE, CONSTRAINT codigo FOREIGN KEY (codigo) REFERENCES Tweet (codigo) ON DELETE CASCADE ON UPDATE CASCADE);");
	}
	public static void main(String[]args) throws IOException
	{
		reiniciarBase();
		//Esta parte del código prueba la inserción de usuarios y extracción de los mismos con la imagen
		Usuario temp0 = new Usuario("Fiser12", "21323", "dfasdf", "Fiser", "bibliografia", ImageIO.read(new File("src/res/images/notif/notification_follower.png")), new Date(12122012), 4, 2, 2, 2);
		//System.out.println(introducirUsuario(temp3));
		/*
		JFrame temp2 = new JFrame();
		temp2.add(new JLabel(new ImageIcon(extraerUsuarios().get(0).getImagen())));
		temp2.setVisible(true);*/

//Esta parte de cóidgo prueba la insercción un tweet en la bd
//		Tweet temp = new Tweet(1L, "Prueba1", "fgd", new Date(12121987), ImageIO.read(new File("src/res/images/notif/notification_follower.png")), "fdf", true, true);
//		Tweet temp2 = new Tweet(2L, "Prueba2", "fgd", new Date(12121987), ImageIO.read(new File("src/res/images/notif/notification_follower.png")), "fdf", true, true);
//		Tweet temp3 = new Tweet(3L, "Prueba3", "fgd", new Date(12121987), ImageIO.read(new File("src/res/images/notif/notification_follower.png")), "fdf", true, true);
//		Tweet temp4 = new Tweet(4L, "Prueba4", "fgd", new Date(12121987), ImageIO.read(new File("src/res/images/notif/notification_follower.png")), "fdf", true, true);
//		ArrayList<Tweet>temporal = new ArrayList<Tweet>();
//		temporal.add(temp);
//		temporal.add(temp2);
//		temporal.add(temp3);
//		temporal.add(temp4);

		long time = System.currentTimeMillis();
		//insertarTweets(temporal, "Fiser", "png");
		System.out.println(System.currentTimeMillis()-time + " ms");
		//insertarTweetsHilos(temporal, "Fiser12", "png");
		System.out.println(extraerTweets("Fiser12").size());
//		System.out.println(temporal.toString());
//		JFrame temp2 = new JFrame();
//		temp2.add(new JLabel(new ImageIcon(extraerTweets("Fiser12").get(0).getImagenUsuario())));
//		temp2.setVisible(true);
//		borrarTodosLosCredenciales();
		
	}

}
