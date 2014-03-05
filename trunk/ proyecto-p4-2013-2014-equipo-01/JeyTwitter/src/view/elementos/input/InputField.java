package view.elementos.input;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import util.Util;

public abstract class InputField extends JLabel{

	private JTextField inputCode;
	private JLabel imagenFondo;
	
	/**
	 * 
	 */
	public InputField() {
		super();
		inputCode = new JTextField();
		imagenFondo = new JLabel();
		init();
	}
	/**
	 * Inicializa el objeto y sus propiedades
	 */
	private void init() {
		inputCode.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		inputCode.setOpaque(false);
		inputCode.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 40));
		inputCode.setBorder(null);
		inputCode.setAutoscrolls(false);
		inputCode.setBounds(88, 165, 300, 48);
		
		imagenFondo.setIcon(new ImageIcon(InputField.class.getResource("/res/images/textInput/IntroCodeField_Normal.png")));
		imagenFondo.setBounds(80, 155, 308, 58);
		
		inputCode.addKeyListener(new EventoKeyListenerAuthCode(this));
	}
	@Override
	public void setBounds(int x, int y, int w, int h){
		super.setBounds(x, y, 308, 58); //300, 48
		inputCode.setBounds(x+10, y+5, 300, 48);
		imagenFondo.setBounds(x, y, 308, 58);
	}
	@Override
	public void setLocation(int x, int y){
		super.setLocation(x, y);
		inputCode.setLocation(x+10, y+5);
		imagenFondo.setLocation(x, y);
	}

	/**
	 * @return the inputCode
	 */
	public JTextField getInputField() {
		return inputCode;
	}

	/**
	 * @param inputCode the inputCode to set
	 */
	public void setInputField(JTextField inputCode) {
		this.inputCode = inputCode;
	}
	
	public JLabel getImagenFondo() {
		return imagenFondo;
	}
	
	public void setImagenFondo(JLabel imagenFondo) {
		this.imagenFondo = imagenFondo;
	}
	
	public void setModoError(){
		getImagenFondo().setIcon(new ImageIcon(InputField.class.getResource("/res/images/textInput/IntroCodeField_Error.png")));
	}
	
	public void setModoCorrecto(){
		getImagenFondo().setIcon(new ImageIcon(InputField.class.getResource("/res/images/textInput/IntroCodeField_Ok.png")));
	}
	
	public void setModoNormal(){
		getImagenFondo().setIcon(new ImageIcon(InputField.class.getResource("/res/images/textInput/IntroCodeField_Normal.png")));
	}

	public abstract boolean evaluate();
}
