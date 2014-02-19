package data.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import data.Util;

/**
 * Guarda la configuracion de la aplicacion en un fichero XML
 * @author Sergio Anguita
 *
 */
public class XML {

	private Properties pr;
	private String nombre;
	private static XML config;
	
	/**
	 * Main de prueba
	 * @param args
	 */
	public static void main(String []args){
		XML x = new XML("prueba.xml");
		x.añadir("tamaño", "10");
		x.añadir("tamaño2", "100");
		x.mostrarProp("tamaño");
		x.eliminar("tamaño2");
		x.guardarXML();
	}
	
	public XML(String nombre){
		this.nombre = nombre;
		setProperties(new Properties());
	}
	
	/**
	 * Añade un dato al fichero
	 * @param nombre	nombre del dato (clave)
	 * @param dato		dato a añadir
	 */
	public void añadir(String nombre, String dato){
		pr.setProperty(nombre, dato);
		Util.debug("Clave ("+nombre+") añadida correctamente");
	}
	
	/**
	 * Elimina el elemento que conicida con el parametro de entrada
	 * @param nombre	nombre del elemento que se quiere eliminar
	 */
	public void eliminar(String nombre){
		pr.remove(nombre);
		Util.debug("Clave ("+nombre+") borrada correctamente");
	}
	
	/**
	 * Elimina el contenido del fichero XML
	 */
	public void eliminar(){
		pr.clear();
		Util.debug("Contenido del objeto XML borrado");
	}
	
	/**
	 * Lee el contenido del fichero XML
	 */
	public void leerXML() {
		try {
			pr.loadFromXML(new FileInputStream(nombre));
			Util.debug("Fichero leido correctamente");
		} catch (Exception e) {
			Util.debug("Error al leer el fichero XML - "+e.getMessage());
		}
	}
	
	/**
	 * Guarda los cambios realizados al fichero XML
	 */
	public void guardarXML(){
		try {
			File f = new File(nombre);
			FileOutputStream out = new FileOutputStream(f);
			pr.storeToXML(out, "Fichero de configuracion - No modificar");
			Util.debug("Fichero de configuracion XML guardado correctamente en\n"+f.getAbsolutePath());
		} catch (IOException e) {
			Util.debug("Ocurrio un error al intentar guardar el fichero de configuracion XML\n" + e.getMessage());
		}
	}
	
	/**
	 * Muestra los datos de una clave del fichero XML
	 * @param nombreProp	nombre de la clave a mostrar
	 */
	public void mostrarProp(String nombreProp){
		String dato = pr.getProperty(nombreProp);
		System.out.println(nombreProp+": "+dato);
	}
	
	public Properties getProperties() {
		return pr;
	}
	
	public void setProperties(Properties pr) {
		this.pr = pr;
	}

	/**
	 * Añade un dato al fichero config.XML
	 * @param id	nombre de la clave quq se va a añadir
	 * @param o		dato que se va a añadir
	 */
	public static void añadirDato(String id, Object o){
		String dato = o.toString();
		if(config==null)
			config = new XML(Util.FICHERO_XML);
		else if(new File(Util.FICHERO_XML).exists() && new File(Util.FICHERO_XML).length() > 0);
			config.leerXML();
			
		config.añadir(id, dato);
		config.guardarXML();
	}
	
	/**
	 * Lee un dato del fichero config.XML
	 * @param id	clave del dato que se quiere leer
	 * @return devuelve el dato asociado a la clave id
	 */
	public static String leerDato(String id){
		if(config==null)
			config = new XML(Util.FICHERO_XML);
		if(new File(Util.FICHERO_XML).exists() && new File(Util.FICHERO_XML).length() > 0);
			config.leerXML();
			String str = config.getProperties().getProperty(id);
		if(str==null)
			return null;
		else
			return str;
	}
	
	/**
	 * 
	 * @return devuelve el nº de datos guardados en el fichero
	 */
	public static int size(){
		return config.getProperties().size();
	}
	
	/**
	 * @return deuelve un objeto XML asociado al fichero config.xml
	 */
	public static XML file(){
		return config;
	}
	
}
