package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Fiser
 * Clase encargada de conectarse a una base de datos de diversos tipos
 */
public class SQLiteManager
{
	private Connection connection;
	private Statement query;
	private String dir;
	private boolean connect;
	private static SQLiteManager instance = null;
	
	private static final String PATH = "JeyTuiterSQL";
	
	/* Metodos para el funcionamiento del singleton */
	public SQLiteManager()
	{
		this.dir = PATH;
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
				connect = true;
			}catch (SQLException e1) {
				connect = false;
				System.out.print(e1.getMessage());
			}
		}catch (ClassNotFoundException e) {
			connect = false;
			System.out.print(e.getMessage());
		}
	}
	private void disconnet()
	{
		try{
			query.close();
			connection.close();
			connect = false;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public boolean consulta(String sql){
		boolean funciona = true;
		connect();	 
		try {
			query.executeUpdate(sql);
		} catch (SQLException e) {
			funciona = false;
			System.out.print(e.getMessage());
		}
		finally{  
			disconnet();
		}
		return funciona;
	}
	public ResultSet select(String sql){
		connect();
		ResultSet resultado = null;
		try {
			resultado = query.executeQuery(sql);
		}catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		finally{
			disconnet();
		}
		return resultado;
	}
	
}
