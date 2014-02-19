package model.sql;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import data.Util;
/**
 * Reinicia o crea el contenido de la base de datos como si fuera la primera vez
 * @author Sergio Anguita
 *
 */
public class SQL_init {

	private String sql="";
	private HashSet<String> rutas;
   
	private static final String NOMBRE_BASE_SQL = Util.MYSQL_NOMBRE_BBDD;

	/**
	 * Construye el objeto con los datos de un servidor predefinidos por el usuario
	 * @param rutasAñadir	contiene las rutas que deberan sobreescribirse en la BBDD
	 */
    public SQL_init(HashSet<String> rutasAñadir){
    	this.rutas=rutasAñadir;
    }
    /**
     * Contruye las tablas en el servidor SQL de manera que si ya existian borra los datos viejos.
     * Si es la primera vez que se hacen, simplemente se crean eliminar nada
     * @param tablas	Contiene los nombres de las tablas que se crearan en la base de datos
     */
	public void actualizarTablas(String[] tablas){
		ServidorSQL server = ServidorSQL.getInstance();
		server.cambiarBaseDatos(NOMBRE_BASE_SQL);
		String[] temp = Util.MYSQL_NOMBRE_TABLAS;
		
        try 
        {   // Comando para crear las tablas
           for (int i = 0; i < tablas.length; i++) {
        	   if(!server.checkTabla(tablas[i])){
                 	sql = "CREATE TABLE "+tablas[i]+" ( "+
                           "nombre		VARCHAR(255) NOT NULL, " +
                           "extension	VARCHAR(100) NOT NULL, "+
                           "ruta   		VARCHAR(500) NOT NULL PRIMARY KEY, " +
                           "bytes    	BIGINT NOT NULL, " +
                           "tamano    	VARCHAR(200) NOT NULL, " +
                           "visible		BOOLEAN NOT NULL, " +
                           "lectura		BOOLEAN NOT NULL, " +
                           "escritura	BOOLEAN NOT NULL, " +
                           "fecha		DATE NOT NULL, " +
                           "hora		TIME NOT NULL, " +
                           "owner      	VARCHAR(100) NOT NULL, "+
                           "CONSTRAINT 	UNIQUE_STU UNIQUE(ruta)" +
                            ");";
               	server.enviarComando(sql);
        	   }
        	   else{
        		   for (int j = 0; j < tablas.length; j++) {
        			   //borra los datos viejos
        				for (String ruta : rutas) {
        					Util.debug("Delete from "+temp[j]+" where ruta like '"+ruta.replace(File.separator, ">")+"%';");
        					server.enviarComando("Delete from "+temp[j]+" where ruta like '"+ruta.replace(File.separator, ">")+"%';");
        				}
        				//borrar los indices viejos;
        				ArrayList<ArrayList<String>> resultado = Util.realizarConsultaSQL(server, "show index from "+tablas[j]+";");
        				for (int k = 0; k < resultado.size(); k++) {
        					ArrayList<String> fila = resultado.get(k);
        					String nombreIndice = fila.get(2);
        					if(!nombreIndice.equalsIgnoreCase("primary")){
        						Util.debug("DROP INDEX "+nombreIndice+" ON "+tablas[j]+";");
        						server.enviarComando("DROP INDEX "+nombreIndice+" ON "+tablas[j]+";");
        					}
        				}
        			}
        	   }
           }
        } 
        catch (Exception e)
        {  
            System.err.println("Warning: CREATE TABLE: " + e.getMessage());   
        }
    }
}
