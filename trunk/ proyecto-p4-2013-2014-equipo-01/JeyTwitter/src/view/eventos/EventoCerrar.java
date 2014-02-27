package view.eventos;

import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EventoCerrar implements MouseListener {
	
	private final Window ventana;
	
	public EventoCerrar(Window o){
		ventana = o;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		ventana.dispose();
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
