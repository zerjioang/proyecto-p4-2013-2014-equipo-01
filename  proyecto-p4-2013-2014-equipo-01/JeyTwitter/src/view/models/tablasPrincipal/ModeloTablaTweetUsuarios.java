package view.models.tablasPrincipal;

import java.util.ArrayList;

import view.elementos.paneles.GUITweet;
import view.elementos.paneles.GuiTwitterUsuario;
import view.elementos.paneles.ObjetoCelda;
import view.models.DataGenerate;
import view.models.ModeloTablaLateral;

public class ModeloTablaTweetUsuarios extends ModeloTablaLateral implements DataGenerate {

	private int total;
	private int tipo;
	private ArrayList<ObjetoCelda> objeto;
	
	public ModeloTablaTweetUsuarios() {
		super();
		columnNames = new String[1];
		columnNames[0] = "Resultados";
		total = 0;
		rowData=generarDatos();
	}

	public ModeloTablaTweetUsuarios(ArrayList<ObjetoCelda> listaObjetos) {
		super();
		columnNames = new String[1];
		columnNames[0] = "Resultados";
		this.objeto = listaObjetos;
		total = listaObjetos.size();
		rowData=generarDatos();
	}

	@Override
	/**
	 * Genera los datos de la tabla
	 */
	public Object[][] generarDatos() {
		Object[][] datos = new Object[total][1];	//Una sola columna en la tabla con 'total' filas
		tipo = objeto.get(0).tipoObjeto();
		/*if(tipo == TablaTweetsUsuarios.SOLO_TWEETS){
			for (int i = 0; i < total; i++) {
				//datos[i][0]= new GUITweet();
				datos[i][0]= objeto.get(i);
			}
		}
		else if(tipo == TablaTweetsUsuarios.SOLO_USUARIOS){
			for (int i = 0; i < total; i++) {
				//datos[i][0]= new GuiTwitterUsuario(objeto.get(i));
				datos[i][0]= objeto.get(i);
			}
		}*/
		for (int i = 0; i < total; i++) {
			datos[i][0]= objeto.get(i);
		}

		return datos;
	}

	public Class getColumnClass(int column) {
		if(tipo == TablaTweetsUsuarios.SOLO_TWEETS)
			return GUITweet.class;
		return GuiTwitterUsuario.class;
	}
}
