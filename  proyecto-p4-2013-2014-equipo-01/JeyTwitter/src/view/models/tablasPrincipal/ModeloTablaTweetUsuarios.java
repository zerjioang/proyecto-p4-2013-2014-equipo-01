package view.models.tablasPrincipal;

import view.models.DataGenerate;
import view.models.ModeloTablaLateral;

public class ModeloTablaTweetUsuarios extends ModeloTablaLateral implements DataGenerate {

	private int total;
	private int tipo;
	
	public ModeloTablaTweetUsuarios() {
		super();
		columnNames = new String[1];
		columnNames[0] = "Resultados";
		total = 2;
		rowData=generarDatos();
	}

	public ModeloTablaTweetUsuarios(int total, int tipo) {
		super();
		columnNames = new String[1];
		columnNames[0] = "Resultados";
		this.total = total;
		this.tipo = tipo;
		rowData=generarDatos();
	}

	@Override
	/**
	 * Genera los datos de la tabla
	 */
	public Object[][] generarDatos() {
		Object[][] datos = new Object[total][1];	//Una sola columna en la tabla con 'total' filas
		if(tipo == TablaTweetsUsuarios.SOLO_TWEETS){
			for (int i = 0; i < total; i++) {
				datos[i][0]= new GUITweet();
			}
		}
		else if(tipo == TablaTweetsUsuarios.SOLO_USUARIOS){
			for (int i = 0; i < total; i++) {
				datos[i][0]= new GuiTwitterUsuario();
			}
		}

		return datos;
	}

	public Class getColumnClass(int column) {
		if(tipo == TablaTweetsUsuarios.SOLO_TWEETS)
			return GUITweet.class;
		return GuiTwitterUsuario.class;
	}
}
