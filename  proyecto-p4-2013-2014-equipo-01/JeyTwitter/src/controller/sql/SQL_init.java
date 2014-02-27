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
	public static void crearTablas(){
		ServidorSQL server = ServidorSQL.getInstance();
        try 
        {   // Comando para crear las tablas
           //Aqui se crean las tablas SQL en caso de no estar creadas
               	server.enviarComando(sql);
        } 
        catch (Exception e)
        {  
            System.err.println("Warning: CREATE TABLE: " + e.getMessage());   
        }
    }
}
