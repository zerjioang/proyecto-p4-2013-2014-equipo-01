package view.models;

import view.elementos.botones.BinaryButton;

import util.Util;

public class ModeloTablaConfig extends ModeloTablaLateral implements DataGenerate{

	public ModeloTablaConfig() {
		super();
		nombresSetting = Util.configs;
		columnNames = new String[2];
		columnNames[0] = "Setting";
		columnNames[1] = "Estado";
		rowData=generarDatos();
	}

	@Override
	public Object[][] generarDatos() {
		Object[][] datos = new Object[nombresSetting.length][columnNames.length];
		for (int i = 0; i < nombresSetting.length; i++) {
			datos[i][0]=nombresSetting[i];
			datos[i][1]= new BinaryButton();
		}
		return datos;
	}

}
