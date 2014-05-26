package view.eventos.welcome;

import hilos.HiloCargarDatos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.elementos.input.CampoCodeAuth;
import view.ventanas.Bienvenida;

public class EventoKeyListenerAuthCode implements KeyListener {
	
	private CampoCodeAuth field;
	private Bienvenida ventana;

	public EventoKeyListenerAuthCode(CampoCodeAuth inputField, Bienvenida bienvenida) {
		field = inputField;
		ventana = bienvenida;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		boolean estado = false;
		if(key.getKeyCode()==KeyEvent.VK_ENTER)
			estado = field.evaluate();
		else
			field.setModoNormal();
		
		if(estado){
			new HiloCargarDatos(ventana).start();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
