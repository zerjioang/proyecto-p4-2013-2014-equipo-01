package view.models;

import util.Util;
import view.elementos.botones.BotonUI;

public class ModeloTablaSettings extends ModeloTablaLateral implements DataGenerate {

	public ModeloTablaSettings() {
		super();
		nombresSetting = Util.settings;
		columnNames = new String[1];
		columnNames[0] = "Ajustes";
		rowData=generarDatos();
	}

	public Object[][] generarDatos() {
		Object[][] datos = new Object[nombresSetting.length][columnNames.length];
		for (int i = 0; i < nombresSetting.length; i++) {
			datos[i][0]= new BotonUI(nombresSetting[i]);//nombresSetting[i]
		}
		return datos;
	}
}
