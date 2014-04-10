package view.renderers;

import view.elementos.GuiTwitterUsuario;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class UsuarioRenderer extends DefaultTableCellRenderer{
	
	/**
	 * Se sobreescribe el metodo que se encarga de visualizar los tweets  en las celdas del JTable.
	 */
	public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column){
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			return (GuiTwitterUsuario)value;
	}
}
