package controller.io;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.Util;

/**
 * Da la posibilidad de escribir datos en texto plano en ficheros del disco duro asi como de leerlos o guardar y leer objetos 
 * @author Sergio Anguita
 *
 */
public class Fichero {

	/**
	 * Abre el fichero seleccionado
	 * @param fRuta		ruta donde se encuentra el fichero que se quiere abir
	 * @param mensajeError	mensaje de error que aparecera en caso de producirse uno
	 * @return	devuelve true si el proceso se ha completado correctamente o false si ha ocurrido algun error
	 */
	public static boolean abrirArchivo(String fRuta, String mensajeError) {
		String sO = getOS();
		Util.debug("Sistema Operativo detectado: "+sO);
		try {
			Desktop.getDesktop().open(new File(fRuta));
			return true;
		} catch (IOException | IllegalArgumentException e) {
			System.err.println("ERROR al abrir el fichero "+fRuta);
			System.err.println("Detalles del error: "+e.getMessage());
			return false;
		}
	}
	/**
	 * A�ade una nueva linea al fichero log.txt que ya existia
	 */
	public static void nuevaLineaLog(String str) {
		BufferedWriter out;
		try {
			//Calculo la fecha y hora actuales
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat.getDateInstance(DateFormat.MEDIUM);
			String fechaActual = sdf.format(new Date());
			out = new BufferedWriter(new FileWriter(Util.FICHERO_LOG, true));
	        out.write(fechaActual+"\t "+str);
	        out.write("\r\n");
	        out.close();
		} catch (IOException e) {}
	}
	/**
	 * Crea un fichero de texto legible con un nombre predeterminado y a�ade la linea introducida como parametro
	 * @param nombre	nombre.extension del fichero a crear
	 * @param a�adirLinea	contenido que se va a insertar en el fichero
	 */
	public static void crearFicheroNuevo(String nombre, String anadirLinea) {
		BufferedWriter out;
		try {
			//Calculo la fecha y hora actuales
			out = new BufferedWriter(new FileWriter(nombre, true));
	        out.write(anadirLinea);
	        out.write("\r\n");
	        out.close();
		} catch (IOException e) {}
	}
	/**
	 * Borra un directorio determinado
	 * @param f		Conteniene el directorio a borrar
	 * @return		Devuelve true si la operacion se ha realizado 
	 * correctamente y false si se ha producido algun error
	 */
	public static boolean borrarDir(File f) {
		boolean estado = borrarDirRec(f, true);
		System.out.println(estado);
		return estado;
	}
	/**
	 * Obtiene el tipo de sistema operativo donde se esta ejecutando la aplicacion
	 * @return devuelve el nombre del sistema operativo
	 */
	public static String getOS(){
		return System.getProperty("os.name").toLowerCase();
	}
	/**
	 * Borra un directorio entero. Incluyendo las carpetas, subcarpetas y ficheros.
	 * @param file			Directorio o fichero que se va aprocesar
	 * @param borrado	determina si la operacion se esta realizando correctamente
	 * @return		Devuelve true si la operacion se ha realizado 
	 * correctamente y false si se ha producido algun error
	 */
	private static boolean borrarDirRec(File file, boolean borrado){
		if(file.isDirectory()){
			//directory is empty, then delete it
			if(file.list().length==0){
				borrado &=  file.delete();
				System.out.println("Directory is deleted : " + file.getAbsolutePath());
				}
			else{
				//list all the directory contents
				String files[] = file.list();
				for (String temp : files) {
					//construct the file structure
					File fileDelete = new File(file, temp);
					//recursive delete
					borrado &= borrarDirRec(fileDelete, borrado);
				}
				//check the directory again, if empty then delete it
				if(file.list().length==0){
					borrado &= file.delete();
					System.out.println("Directory is deleted : " + file.getAbsolutePath());
				}
			}
		}
		else{
			//if file, then delete it
			borrado &= file.delete();
			System.out.println("File is deleted : " + file.getAbsolutePath());
		}
		return borrado;
	}
	/**
	 * Borra el grupo de ficheros introducido
	 * y deveulve una comprobacion de la operacion
	 * @param paths	Rutas donde se encuentran los ficheros que se quieren borrar
	 * @return		Devuelve true si la operacion se ha realizado 
	 * correctamente y false si se ha producido algun error
	 */
	public static boolean borrarFichero(String[] paths){
		boolean estado = true;
		for (String str : paths) {
			File f = new File(str);
			estado&=f.delete();
		}
		return estado;
	}
	/**
	 * Borra el fichero especificado en la ruta introducida 
	 * y deveulve una comprobacion de la operacion
	 * @param ruta	ruta del ficheros que se quiere borrar
	 * @return		Devuelve true si la operacion se ha realizado 
	 * correctamente y false si se ha producido algun error
	 */
	public static boolean borrarFichero(String ruta){
		return borrarFichero(new File(ruta));
	}
	
	/**
	 * Borra el fichero especificado en la ruta introducida 
	 * y deveulve una comprobacion de la operacion
	 * @param f		Objeto File que se quiere borrar
	 * @return		Devuelve true si la operacion se ha realizado 
	 * correctamente y false si se ha producido algun error
	 */
	public static boolean borrarFichero(File f){
		return f.delete();
		}
	/**
	 * Lee un fichero que contiene texto legible y devuelve todo lo leido como una sola cadena
	 * @param ruta	Ruta del fichero que se quiere leer
	 * @return		Devuelve el texto leido del fichero si la operacion
	 * se ha completado correctamente y si no devuelve un String vacio
	 */
	public static String leerFichero(String ruta){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String contenido="";
		try {
			archivo = new File (ruta);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			// Lectura del fichero
			String linea = br.readLine();
			contenido=linea;
			while((linea=br.readLine())!=null)
				contenido+=linea+"\r\n";
			fr.close(); 
			br.close();
			return contenido;
		}
		catch(Exception e){
			return null;
		}
	}
	/**
	 * Lee un fichero que contiene un objeto de java y lo devuelve
	 * Este metodo ha sido dise�ado para leer objetos que esten en el classpath de la aplicacion
	 * @param nombreFichero	Nombre del fichero a leer
	 * @return		Devuelve el objeto si todo ha sido correcto y si no devuelve null
	 */
	public static Object leerObjeto(String nombreFichero){
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(nombreFichero);
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			ois.close();
			return o;
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Se producio un error al leer el fichero "+nombreFichero);
		}
		return null;
	}
	/**
	 * Guarda un objeto en un fichero
	 * Este metodo ha sido dise�ado para guardar objetos en el classpath de la aplicacion
	 * @param nombreFichero	Nombre del fichero a guardar
	 * @return		Devuelve true si la operacion se ha realizado 
	 * correctamente y false si se ha producido algun error
	 */
	public static boolean guardarObjeto(String nombreFichero, Object o){
		FileOutputStream fis = null;
		ObjectOutputStream ois = null;
		try {
			fis = new FileOutputStream(nombreFichero);
			ois = new ObjectOutputStream(fis);
			ois.writeObject(o);
			ois.close();
			fis.close();
			return true;
		} catch (IOException e) {
			System.err.println("Se producio un error al guardar el fichero "+nombreFichero);
			return false;
		}
	}
}
