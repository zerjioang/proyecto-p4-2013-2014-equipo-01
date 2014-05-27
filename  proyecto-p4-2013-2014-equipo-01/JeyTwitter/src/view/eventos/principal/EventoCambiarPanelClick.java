package view.eventos.principal;

import hilos.HiloTimeline;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import controller.GUIController;
import view.ventanas.Principal;

public class EventoCambiarPanelClick implements MouseListener {

	private final Principal v;
	
	public EventoCambiarPanelClick(Principal principal) {
		v = principal;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable t = v.getTablaMenu();
		int opcion = t.getSelectedRow();
		v.setPanelActual(v.getPaneles()[opcion+1]);
	/*	if(opcion==1)
		{
			HiloTimeline recargar = new HiloTimeline(GUIController.getInstance().getGui().getPanelTimeLine());
			recargar.start();
		}*/
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
