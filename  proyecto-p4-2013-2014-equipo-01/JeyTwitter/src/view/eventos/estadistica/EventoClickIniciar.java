package view.eventos.estadistica;

import hilos.HiloEstadistica;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import controller.GUIController;
import twitter4j.TwitterException;
import util.Util;
import view.elementos.paneles.PanelEstadistica;
import monitorizacion.Grafica;

public class EventoClickIniciar implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		//Aqui viene el codigo a ejecutar al clicar en iniciar

		String ruta = "";

		if(GUIController.existeUsuario(PanelEstadistica.getTxt()) && PanelEstadistica.getTxt()!=null && PanelEstadistica.getTxt().length()>0){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int seleccion = fileChooser.showSaveDialog(null);
			if (seleccion == JFileChooser.APPROVE_OPTION)
			{
				ruta = fileChooser.getCurrentDirectory().getAbsolutePath();

				System.err.println(ruta);
				try {
					if(!new File(ruta+"/estadistica_JeyTuiter").exists()){
						boolean a = new File(ruta+"/estadistica_JeyTuiter").mkdir();
					}
					String nombre = PanelEstadistica.getTxt();

					int numPaginas = 13;
					if(!new File(ruta+"/estadistica_JeyTuiter/"+nombre).exists()){
						boolean b = new File(ruta+"/estadistica_JeyTuiter/"+nombre).mkdir();
					}
					PanelEstadistica.rutaDestino = ruta+"/estadistica_JeyTuiter/"+nombre;
					Grafica.iniciar(nombre, numPaginas, ruta+"/estadistica_JeyTuiter/"+nombre);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

			}
		}
		else{
			Util.showError(null, "Error", "Nombre de usuario no existe", "Cancelar", "Aceptar");
		}


	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
