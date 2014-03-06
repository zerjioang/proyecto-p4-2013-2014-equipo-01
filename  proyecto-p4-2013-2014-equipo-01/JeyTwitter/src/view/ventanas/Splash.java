package view.ventanas;

import java.awt.EventQueue;

import util.Util;
import view.parents.InvisibleJFrame;

public class Splash extends InvisibleJFrame {

	//Constantes
	private static final String TITULO = "Iniciando "+Util.APP_TITULO+"...";
	private static final String IMG_SPLASH = "/res/images/Splash.png";

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
		setTitle(TITULO);
		contentPane.add(fondo);
	}
}
