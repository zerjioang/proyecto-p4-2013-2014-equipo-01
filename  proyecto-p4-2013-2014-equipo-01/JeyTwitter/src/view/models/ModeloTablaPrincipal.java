package view.models;

import view.elementos.botones.MenuButton;

import util.Util;

public class ModeloTablaPrincipal extends ModeloTablaLateral implements DataGenerate {

	public ModeloTablaPrincipal() {
		super();
		nombresSetting = Util.principal;
		columnNames = new String[1];
		columnNames[0] = "Accion";
		rowData=generarDatos();
	}

	public Object[][] generarDatos() {
		Object[][] datos = new Object[nombresSetting.length][columnNames.length];
		for (int i = 0; i < nombresSetting.length; i++) {
			System.out.println(nombresSetting[i]);
			MenuButton b = new MenuButton();
			b.setText(nombresSetting[i]);
			datos[i][0]= b;
		}
		return datos;
	}
}
