package view.models;

import javax.swing.table.AbstractTableModel;


public abstract class ModeloTablaLateral extends AbstractTableModel{
	
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

	public abstract Class getColumnClass(int column);

	public void setValueAt(Object value, int row, int column) {
		rowData[row][column] = value;
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
