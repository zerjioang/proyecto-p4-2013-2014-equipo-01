package view.eventos;

import view.parents.CustomJFrame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EventoMaximizarDesdeBarra implements WindowListener {

	private final CustomJFrame ventana;
	
	public EventoMaximizarDesdeBarra(CustomJFrame customJFrame) {
		ventana = customJFrame;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}

	@Override
	public void windowClosing(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		ventana.setLocation(ventana.getLastPosition());
		ventana.setOpacity(1.0f);
        ventana.setVisible(true);
	}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}

}
