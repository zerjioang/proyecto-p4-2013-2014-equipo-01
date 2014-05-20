package controller.sql;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.Util;


public class SQLiteManager
{
	private Connection connection;
	private Statement query;
	private String dir;
	private boolean conectado;
	private static SQLiteManager instance = null;
	private ResultSet resultadoDeConsulta;
	
	/* Metodos para el funcionamiento del singleton */
	public SQLiteManager()
	{
		this.dir = Util.SQLITE_NOMBRE_BBDD;
		connect();
	}
	
	private synchronized static void createInstance() {
        if (instance == null) { 
            instance = new SQLiteManager();
        }
    }
	
	public static SQLiteManager getInstance() {
		createInstance();
		return instance;
	}
	/* Fin de los metodos para el funcionamiento del singleton */
	
	private void connect(){
		try {
			Class.forName("org.sqlite.JDBC");
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:"+dir);
				query = connection.createStatement();
				conectado = true;
			}catch (SQLException e1) {
				conectado = false;
			}
		}catch (ClassNotFoundException e) {
			conectado = false;
		}
	}
	public void disconnet()
	{
		try{
			query.close();
			connection.close();
			conectado = false;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public boolean consulta(String sql){
		boolean funciona = true;
		try {
			query.executeUpdate(sql);
		} catch (SQLException e) {
			funciona = false;
		}
		return funciona;
	}
	public ResultSet select(String sql){
		ResultSet resultado = null;
		try {
			resultado = query.executeQuery(sql);
		}catch (SQLException e) {
		}
		return resultado;
	}
	
	public ResultSet getResultSet(){
		return resultadoDeConsulta;
	}
	
	public synchronized boolean enviarComando(String comando){
		try {
			if(conectado){
				if(comando.toLowerCase().startsWith("insert") 
						|| comando.toLowerCase().startsWith("update")
						|| comando.toLowerCase().startsWith("delete")
						|| comando.toLowerCase().startsWith("create")
						|| comando.toLowerCase().startsWith("alter")
						|| comando.toLowerCase().startsWith("drop")
					){
					query.executeUpdate(comando);
				}else{
					resultadoDeConsulta = query.executeQuery(comando);
				}
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}		
	}
	public boolean enviarImagen(String sql, byte [] image){
		boolean funciona = true; 
		try {
			PreparedStatement consultaPreparada = connection.prepareStatement(sql);
            consultaPreparada.setBytes(1, image);
			consultaPreparada.executeUpdate();
		} catch (SQLException e) {
			funciona = false;
		}
		return funciona;
	}
	public Image getImageUsuario(String name){
		Image img=null;
		String query="SELECT imagen FROM Usuario WHERE nombreUsuario='"+name+"'";
		Statement stmt=null;
		try{
			stmt=connection.createStatement();
			ResultSet rslt=stmt.executeQuery(query);
			if(rslt.next()){
				byte[] imgArr= rslt.getBytes("imagen");
				img=Toolkit.getDefaultToolkit().createImage(imgArr);
			}
		}catch(Exception e){
			System.out.println("No hay imagen");
		}
		return img;
	}
	public Image getImageTweet(long codigo){
		Image img=null;
		String query="SELECT imagenUsuario FROM Tweet WHERE codigo="+codigo;
		Statement stmt=null;
		try{
			stmt=connection.createStatement();
			ResultSet rslt=stmt.executeQuery(query);
			if(rslt.next()){
				byte[] imgArr= rslt.getBytes("imagenUsuario");
				img=Toolkit.getDefaultToolkit().createImage(imgArr);
			}
		}catch(Exception e){
			System.out.println("No hay imagen");
		}
		return img;
	}
	public Image getImageTweetContenido(long codigo){
		Image img=null;
		String query="SELECT imagenTweet FROM Tweet WHERE codigo="+codigo;
		Statement stmt=null;
		try{
			stmt=connection.createStatement();
			ResultSet rslt=stmt.executeQuery(query);
			if(rslt.next()){
				byte[] imgArr= rslt.getBytes("imagenTweet");
				img=Toolkit.getDefaultToolkit().createImage(imgArr);
			}
		}catch(Exception e){
			System.out.println("No hay imagen");
		}
		return img;
	}
}
