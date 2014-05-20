package monitorizacion;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Ejecutar {

	/*
	 * 					inicio
	 * 						|<---------------------------
	 * 						|							|
	 * 				Hay archivo?--no--generar archivo-->
	 * 						|  					  ^	
	 * 						si					  |
	 * 						|					  |
	 * 			hay que generarlo otra vez?--si--
	 * 						|
	 * 						|
	 * 					fin de esta cosa
	 */
	
	
	public void empezarFlujo(){
		
		try{
			FileInputStream fis = new FileInputStream("timelineUsuario.jey"); 
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			
		}catch(Exception e){
			
			
		}
		
	}
	
	
}
