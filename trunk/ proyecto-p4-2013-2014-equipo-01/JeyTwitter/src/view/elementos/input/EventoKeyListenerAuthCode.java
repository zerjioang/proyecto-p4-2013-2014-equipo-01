package view.elementos.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EventoKeyListenerAuthCode implements KeyListener {
	
	private InputField field;

	public EventoKeyListenerAuthCode(InputField inputField) {
		field = inputField;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if(key.getKeyCode()==KeyEvent.VK_ENTER)
			field.evaluate();
		else
			field.setModoNormal();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
