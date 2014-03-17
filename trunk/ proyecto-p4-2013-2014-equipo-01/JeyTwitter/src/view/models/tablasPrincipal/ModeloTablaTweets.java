package view.models.tablasPrincipal;

import javax.swing.JPanel;

import view.models.DataGenerate;
import view.models.ModeloTablaLateral;

public class ModeloTablaTweets extends ModeloTablaLateral implements DataGenerate {

	private int total;
	public ModeloTablaTweets() {
		super();
		columnNames = new String[1];
		columnNames[0] = "Tweet";
		rowData=generarDatos();
	}

	public ModeloTablaTweets(int i) {
		super();
		columnNames = new String[1];
		columnNames[0] = "Tweet";
		total = i;
		rowData=generarDatos();
	}

	@Override
	/**
	 * Genera los datos de la tabla
	 */
	public Object[][] generarDatos() {
		Object[][] datos = new Object[total][1];	//Una sola columna en la tabla con 'total' filas
		for (int i = 0; i < total; i++) {
			datos[i][0]= new GUITweet();
		}
		return datos;
	}

	public Class getColumnClass(int column) {
			return GUITweet.class;
	}
}
