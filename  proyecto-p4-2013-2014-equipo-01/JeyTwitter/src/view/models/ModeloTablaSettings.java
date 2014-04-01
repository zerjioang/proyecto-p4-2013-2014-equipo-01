package view.models;

import util.Util;
import view.elementos.botones.MenuButton;

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
		
		MenuButton b = new MenuButton(nombresSetting[0]);
		b.click();
		datos[0][0]= b;
		
		for (int i = 1; i < nombresSetting.length; i++) {
			datos[i][0]= new MenuButton(nombresSetting[i]);
		}
		return datos;
	}

	@Override
	public Class getColumnClass(int column) {
		return MenuButton.class;
	}

	@Override
	public int getRowCount() {
		return rowData.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return rowData[row][column];
	}
}
