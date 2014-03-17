package view.models.tablasConfig;

import view.elementos.botones.BinaryButton;
import view.models.DataGenerate;
import view.models.ModeloTablaLateral;
import util.Util;

public class ModeloTablaConfigGeneral extends ModeloTablaLateral implements DataGenerate{

	public ModeloTablaConfigGeneral() {
		super();
		nombresSetting = Util.settingsGeneral;
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

	@Override
	public Class getColumnClass(int column) {
		return BinaryButton.class;
	}

}
