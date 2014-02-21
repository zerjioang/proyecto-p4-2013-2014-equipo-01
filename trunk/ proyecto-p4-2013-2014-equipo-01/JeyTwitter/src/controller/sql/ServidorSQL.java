package controller.sql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Util;
import controller.io.Fichero;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Clase necesaria para realizar la conexion con el servidor SQL
 * @author Sergio Anguita
 *
 */
public class ServidorSQL{

	public static int numeroDeConexiones = 0;
	
	private static ServidorSQL servidor;
	private static Connection con;
    private static PreparedStatement st;  
    private static ResultSet res;
    private static boolean conexionPorDefecto;
    
	private String nombreDB;
	private boolean conectado;
	private String error="";
	
	/**
	 * Constructor privado
	 */
	private ServidorSQL() {
		conectado = false;
		conexionPorDefecto = true;
		nombreDB = Util.SQLITE_NOMBRE_BBDD;
	}
	
	/**
	 * 
	 * @return Devuelve un objeto de tipo ServidorSQL. En caso de no haber ninguno creado, lo crea y lo devuelve
	 */
	public static ServidorSQL getInstance() {
		if(servidor==null){
			Util.debug("OPCION A");
			servidor =  new ServidorSQL();
		}
		else{
			Util.debug("OPCION B");
			servidor = Util.cargarConfigServidor();
		}
		return servidor;
    }
	
	/**
	 * Inicia el proceso de conexión
	 */
	private void conectar(){
		 loadHSQLDB();
		 connectDB();
		 createStatement();
	}
	
	/**
	 * @return devuelve el ultimo error producido
	 */
	public String getError(){
		return error;
	}
	
	/**
	 *  Establece una nueva conexion en caso de haberse cerrado la anterior
	 */
	public boolean openResultSet(){
		conectado=false;
		setNumeroDeConexiones(0);
		if(isResultSetClosed())
			return createStatement();
		else
			return false;
	}
	
	/**
	 * Comprueba la existencia de una tabla en una BBDD
	 * @param nTabla	nombre de la tabla a comprobar
	 * @return			devuelve true si la tabla existe y false si no existe
	 */
	public boolean checkTabla(String nTabla) {
        try 
        {
        	Util.debug("Comprobando si existe: "+nTabla);
        	return enviarComando("DESC "+nTabla);
        }
        catch (Exception e){  
            error = e.getMessage();
            if(error.contains("after statement closed"))
            	conectado=false;
            Fichero.nuevaLineaLog("Error interno ([Error referente a la tabla]): \t" + e.getMessage());
            return false;
        }  
	
	}
	
	/**
	 * Borra todas las tablas de la BBDD actual
	 * @param nombres	Array con el contenido de todas las tablas
	 */
	public void borrarTodasTablas(String[] nombres){
        for (String tabla : nombres) {
        	borrarTabla(tabla);
        }
	}
	
	/**
	 * Borra la tabla seleccionada de la BBDD actual
	 * @param nombre	nombre de la tabla que se quiere borrar
	 */
	public void borrarTabla(String nombre){
        try {
            enviarComando("DROP TABLE "+nombre+";");
        }
        catch (Exception e){  
        	error = e.getMessage();
        	if(error.contains("after statement closed"))
            	conectado=false;
        	Fichero.nuevaLineaLog("Error interno ([Error al borrar la tabla]): \t" + e.getMessage());
        }  
	}
	/**
	 * Envia una orden o comando SQL al servidor
	 * @param comando	comando SQL que se quiere enviar
	 * @return	devuelve tru si se ha ejecutado correctamente y false si ha habido algun error
	 */
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
					Util.debug("Ejecutando Update...");
					st.executeUpdate(comando);
				}
				else{
					Util.debug("Ejecutando Consulta...");
					res = st.executeQuery(comando);
				}
				return true;
			}
			else{
				conectado=false;
				return false;
			}
		} catch (SQLException e) {
			error = e.getMessage();
			if(error.contains("after statement closed"))
            	conectado=false;
			Fichero.nuevaLineaLog("Error interno ([Error al realizar operacion]): \t" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Inicia la conexion con el servidor y carga el driver
	 */
    private void loadHSQLDB()
    {
        Util.debug("* Iniciando conexion ...");
        try
        {
        	//Class.forName("org.sqlite.JDBC");	//SQLite
            Class.forName("com.mysql.jdbc.Driver"); //Load HSQLDB driver for MySQL
            Util.debug("* Cargando JDBC driver...");
            conectado=true;
        } 
        catch(Exception e)
        {  
            System.err.println("ERROR: No se pudo cargar el driver correctamente.");
            error = e.getMessage();
            conectado=false;
            Fichero.nuevaLineaLog("ERROR: No se pudo cargar el driver correctamente.\t"+e.getMessage());
        } 
    }
 
    /**
     * Establece la conexion con el DriveManager
     */
    private boolean connectDB()
    {
        try
        {   // Connect to the database or create if it don't exist 
            //con = (Connection) DriverManager.getConnection(urlServidor, usuario, pass); 	//MySQL
            con = DriverManager.getConnection("jdbc:sqlite:"+Util.SQLITE_NOMBRE_BBDD);	//SQLite
            //Util.debug("* Creando conexion con MySQL...");
            Util.debug("* Creando conexion con SQLite...");
            conectado&=true;
            return true;
        }
        catch(Exception e)
        {
            System.err.println("Error de conexion: " + e.getMessage());
            error = e.getMessage();
            conectado=false;
            Fichero.nuevaLineaLog("Error de conexion:\t" + e.getMessage());
            return false;
        }
    }
 
    /**
     * Inicia la comunicacion y habilita el envio de comandos al servidor
     */
    private boolean createStatement()
    {
        try
        {
            //st  = (PreparedStatement) con.clientPrepareStatement("");	//MySQL
        	st  = con.createStatement(); 	//SQLite
            Util.debug("* Conexion realizada con exito");
            setNumeroDeConexiones(getNumeroDeConexiones() + 1);
            conectado=true;
        }
        catch (Exception e)
        {  
            System.err.println("Error: createStatement: " + e.getMessage());
            Fichero.nuevaLineaLog("Error: createStatement:\t" + e.getMessage());
            conectado=false;
        }
        return conectado;
    }
    
    /**
     * Cierra la conexion con el servidor
     * @return devuelve true si se ha cerrado con exito y false si ha habido algun problema
     */
    public boolean close(){
    	if(conectado){
        	try {
    			if (con!=null && st!=null) {
    				con.close();
    				st.close();
    				conectado=false;
    				setNumeroDeConexiones(getNumeroDeConexiones()-1);
    			}
    			if(res!=null)
    				res.close();
    			return true;
    		} catch (SQLException e) {
    			Fichero.nuevaLineaLog("SQLException Error:\t" + e.getMessage());
    			return false;
    		}
    	}
    	return false;
    }
    
    /**
     * 
     * @return devuelve el estado del servidor
     * True: 	ONLINE
     * False:	OFFLINE
     */
    public boolean isConnected(){
    	return conectado;
    }

    @Override
	public String toString() {
		return "ServidorSQL [ nombreDB= " + nombreDB + ", conectado="+ conectado+"]";
	}
    
	private static int getNumeroDeConexiones() {
		return numeroDeConexiones;
	}

	private static void setNumeroDeConexiones(int numeroDeConexiones) {
		ServidorSQL.numeroDeConexiones = numeroDeConexiones;
	}

	public String getNombreDB() {
		return nombreDB;
	}

	public void setNombreDB(String nombreDB) {
		this.nombreDB = nombreDB;
	}

	/**
	 * 
	 * @return devuelve el ResultSet
	 */
	public ResultSet getResultSet(){
		return res;
	}
	/**
	 * Asigna un nuevo ResultSet
	 * @param resul nuevo ResultSet
	 */
	public void setResultSet(ResultSet resul) {
		res = resul;
	}
	/**
	 * 
	 * @return devuelve un PreparedStatement
	 */
	public PreparedStatement getPrepStatement(){
		return st;
	}
	
	/**
	 * Cambia el servidor actual por el servidor del parametro
	 * @param s		servidor que sustituirá al viejo
	 */
	public static void setInstance(ServidorSQL s) {
		servidor = s;
	}

	/**
	 * Devuelve un valor booleano dependiendo del estado del resultset
	 * @return devuelve true si el resultset esta abierto y false si esta cerrado
	 */
	public boolean isResultSetClosed() {
		try {
			if(getResultSet()==null)
				return false;
			else
				getResultSet().isClosed();
		} catch (SQLException e) {}
		return true;
	}
}
