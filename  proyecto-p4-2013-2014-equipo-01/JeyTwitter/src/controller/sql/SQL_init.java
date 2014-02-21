package controller.sql;

import util.Util;
/**
 * Reinicia o crea el contenido de la base de datos como si fuera la primera vez
 * @author Sergio Anguita
 *
 */
public class SQL_init {

	private static String sql="";
	private static final String NOMBRE_BASE_SQL = Util.SQLITE_NOMBRE_BBDD;
    /**
     * Contruye las tablas en el servidor SQL de manera que si ya existian no hace nada.
     * Si es la primera vez que se hacen, simplemente se crean sin eliminar nada.
     * @param tablas	Contiene los nombres de las tablas que se crearan en la base de datos
     */
	public static void crearTablas(String[] tablas){
		ServidorSQL server = ServidorSQL.getInstance();
		if(tablas.length==0){
			tablas = Util.SQLITE_NOMBRE_TABLAS;
		}
		
        try 
        {   // Comando para crear las tablas
           for (int i = 0; i < tablas.length; i++) {
        	   if(!server.checkTabla(tablas[i])){
                 	/*sql = "CREATE TABLE "+tablas[i]+" ( "+
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
                            ");";*/
               	server.enviarComando(sql);
        	   }
           }
        } 
        catch (Exception e)
        {  
            System.err.println("Warning: CREATE TABLE: " + e.getMessage());   
        }
    }
}
