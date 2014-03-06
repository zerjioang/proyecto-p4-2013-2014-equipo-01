package view.models;

import javax.swing.table.AbstractTableModel;

import view.elementos.botones.BinaryButton;

public class ModeloTablaLateral extends AbstractTableModel{
	
	protected String[] nombresSetting;
	protected Object rowData[][];
	protected String columnNames[];

	public ModeloTablaLateral() {
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public int getRowCount() {
		return rowData.length;
	}

	public Object getValueAt(int row, int column) {
		return rowData[row][column];
	}

	public Class getColumnClass(int column) {
		if(column==0)
			return String.class;
		else
			return BinaryButton.class;
	}

	public void setValueAt(Object value, int row, int column) {
		rowData[row][column] = value;
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
