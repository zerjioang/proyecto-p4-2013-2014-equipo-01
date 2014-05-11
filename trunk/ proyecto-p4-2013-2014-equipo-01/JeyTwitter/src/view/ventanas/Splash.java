package view.ventanas;

import java.awt.Color;
import java.awt.EventQueue;

import util.Util;
import view.parents.InvisibleJFrame;

public class Splash extends InvisibleJFrame {

	//Constantes
	private static final String TITULO = "Iniciando "+Util.APP_TITULO+"...";
	private static final String IMG_SPLASH = "/res/images/Splash.png";
	//solo de prueba
	public static long inicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash frame = new Splash();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Splash() {
		super(IMG_SPLASH);
		inicio = System.currentTimeMillis();
		setTitle(TITULO);
		contentPane.add(fondo);
		contentPane.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
	}
}
