package view.renderers;

import view.botones.BotonUI;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class UIButtonRenderer extends DefaultTableCellRenderer{
	
	/**
	 * Se sobreescribe el metodo que se encarga de visualizar los datos en las celdas del JTable y se le obliga
	 * a que muestre un icono
	 */
	public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column){
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		BotonUI l = (BotonUI)value;
		//JLabel l = new JLabel((ImageIcon)value);
		//l.setBorder( BorderFactory.createLineBorder( Color.RED ));
		return l;
	}
}
