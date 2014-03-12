package view.models.tablasConfig;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import view.elementos.botones.MenuButton;
import view.renderers.BinaryButtonRenderer;
import view.ventanas.Config;

public class EventoCambiarSettings implements MouseListener {

	private final JTable tablaOpciones;
	private final JTable tablaGeneral;

	public EventoCambiarSettings(Config config) {
		tablaOpciones = config.getTableConfigDerecha();
		tablaGeneral = config.getTablaOpcionesIzq();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int row = tablaGeneral.getSelectedRow();
		int col = tablaGeneral.getSelectedColumn();
		System.out.println(row+" "+col);
		for (int i = 0; i < tablaGeneral.getRowCount(); i++) {
			MenuButton b = (MenuButton) tablaGeneral.getValueAt(i, col);
			if(i==row)
				b.click();
			else
				b.exited();
			tablaGeneral.setValueAt(b, i, col);
		}
		tablaGeneral.repaint();
		
		/*
		"General",
		"Cuenta",
		"Notificaciones"
		 */
		switch (row) {
		case 0:
			tablaOpciones.setModel(new ModeloTablaConfigGeneral());
			break;
		case 1:
			tablaOpciones.setModel(new ModeloTablaConfigCuenta());
			break;
		case 2:
			tablaOpciones.setModel(new ModeloTablaConfigNotif());
			break;
		default:
			break;
		}
		//Se define el ancho de la segunda columna de la tabla de configuracion
		tablaOpciones.getColumnModel().getColumn(1).setCellRenderer(new BinaryButtonRenderer());
		Config.definirColumnas(1, 100, tablaOpciones);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
