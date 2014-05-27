package view.eventos.estadistica;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;
import controller.GUIController;
import util.Util;
import view.elementos.paneles.PanelEstadistica;

public class EventoClickIniciar implements MouseListener {

	//numero de paginas de 199 tuits que descargará 
	int numPaginas = 13;


	public void mouseClicked(MouseEvent e) {
		//Aqui viene el codigo a ejecutar al clicar en iniciar

		String ruta = "";
		//si...            existe usuario						   y         Hay algo escrito        y 			Eso escrito tiene longitud >0
		if(GUIController.existeUsuario(PanelEstadistica.getTxt()) && PanelEstadistica.getTxt()!=null && PanelEstadistica.getTxt().length()>0){

			//Creamos un JfileChooser para que se abra la ruta donde se guardaran los datos
			//Le decimos tambien que solo seleccione directorios
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int seleccion = fileChooser.showSaveDialog(null);
			//Si el boton que pincha el usuario es el de aceptar (estan el de aceptar y cancelar, creo)
			if (seleccion == JFileChooser.APPROVE_OPTION)
			{
				//Recoge la ruta
				ruta = fileChooser.getCurrentDirectory().getAbsolutePath();
				//imprime un mensaje ROJO por consola con la ruta
				System.err.println(ruta);

				try {
					//si la ruta no existe
					if(!new File(ruta+"/estadistica_JeyTuiter").exists()){
						//crea la carpeta
						boolean a = new File(ruta+"/estadistica_JeyTuiter").mkdir();
					}
					//recoge el usuario del textfield
					String nombre = PanelEstadistica.getTxt();

					//si la carpeta del usuario no existe, creala
					if(!new File(ruta+"/estadistica_JeyTuiter/"+nombre).exists()){
						boolean b = new File(ruta+"/estadistica_JeyTuiter/"+nombre).mkdir();
					}

					//modifica la ruta de destico donde ya por fin almacena los archivos generados
					PanelEstadistica.rutaDestino = ruta+"/estadistica_JeyTuiter/"+nombre;

					//LLama a GUIcontroller
					GUIController.stalker(nombre, numPaginas, ruta+"/estadistica_JeyTuiter/"+nombre);
					//Grafica.iniciar(nombre, numPaginas, ruta+"/estadistica_JeyTuiter/"+nombre);
				} catch (Exception e1) {
					//Si algo va mal, lanzamos un mensaje de error
					Util.showError(null, "Error creando las carpetas de las rutas", "", "Cancelar", "Aceptar");
				} 
			}
		}else{
			//si la condicion no se cumple, es porque no existe el usuario o no se ha introducido uno en el textfield
			Util.showError(null, "Error", "Nombre de usuario no existe/no especificado", "Cancelar", "Aceptar");
		}
	}

	//funciones de click que no usaremos
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
