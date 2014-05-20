package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import util.Util;


public class XML {

	private Properties pr;
	private String nombre;
	private static XML config;
	
	
	public static void main(String []args){
		XML x = new XML("prueba.xml");
		x.anadir("tama??o", "10");
		x.anadir("tama??o2", "100");
		x.mostrarProp("??");
		x.eliminar("tama??o2");
		x.guardarXML();
	}
	
	public XML(String nombre){
		this.nombre = nombre;
		setProperties(new Properties());
	}
	
	
	public void anadir(String nombre, String dato){
		pr.setProperty(nombre, dato);
		Util.debug("Clave ("+nombre+") a?????????adida correctamente");
	}
	
	
	public void eliminar(String nombre){
		pr.remove(nombre);
		Util.debug("Clave ("+nombre+") borrada correctamente");
	}
	
	
	public void eliminar(){
		pr.clear();
		Util.debug("Contenido del objeto XML borrado");
	}
	
	
	public void leerXML() {
		try {
			pr.loadFromXML(new FileInputStream(nombre));
			Util.debug("Fichero leido correctamente");
		} catch (Exception e) {
			Util.debug("Error al leer el fichero XML - "+e.getMessage());
		}
	}
	
	
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
	
	
	public static XML getInstance(){
		if(config==null)
			config = new XML(Util.FICHERO_XML);
		return config;
	}
	
}
