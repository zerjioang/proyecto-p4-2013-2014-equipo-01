package view.models.tablasConfig;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import view.elementos.botones.BinaryButton;
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
		/*
		 * 	public static String[] settings =
		{
		"General",
		"Cuenta",
		"Notificaciones"
		};
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
		//tabla.repaint();
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
