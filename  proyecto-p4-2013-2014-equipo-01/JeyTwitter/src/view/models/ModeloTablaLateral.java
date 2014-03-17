package view.models;

import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;


public abstract class ModeloTablaLateral extends AbstractTableModel{
	
	protected String[] nombresSetting;
	protected Object rowData[][];
	protected String columnNames[];
	protected LinkedList<JPanel> lista;

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
	
	public void insertarElemento(JPanel e) {
        // Añade un elemento en la primera posicion de la tabla
		lista.addFirst(e);
        fireTableRowsInserted(0, lista.size()-1);
    }
	
	public LinkedList<JPanel> getLista(){
		return lista;
	}
	
	public void setLista(LinkedList<JPanel> l){
		lista.clear();
		lista.addAll(l);
		fireTableRowsInserted(0, lista.size()-1);
	}

}
