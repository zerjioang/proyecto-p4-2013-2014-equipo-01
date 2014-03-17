package view.eventos.config;

import view.elementos.botones.BinaryButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

public class EventoTablaSettings implements MouseListener {
	
	private final JTable tabla;
	
	public EventoTablaSettings(JTable table) {
		tabla = table;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = tabla.getSelectedRow();
		int col = tabla.getSelectedColumn();
		if(col==1){
			BinaryButton binaryButton = (BinaryButton) tabla.getValueAt(row, 1);
			binaryButton.setEstado(!binaryButton.getEstado());
			tabla.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
