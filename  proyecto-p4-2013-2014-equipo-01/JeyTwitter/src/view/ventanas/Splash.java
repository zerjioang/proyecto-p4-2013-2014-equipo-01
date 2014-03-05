package view.ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import util.Util;

import java.awt.Color;
import java.awt.Toolkit;

public class Splash extends JFrame {

	//Constantes
	private static final String TITULO = "Iniciando "+Util.APP_TITULO+"...";
	private static final String IMG_SPLASH = "/res/images/Splash.png";
	private static final String IMG_ICON = Util.APP_ICONO;
			
	private JPanel contentPane;

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
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Splash.class.getResource(IMG_ICON)));
		setUndecorated(true);
		setTitle(TITULO);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		ImageIcon imagenSplash = new ImageIcon(Splash.class.getResource(IMG_SPLASH));
		JLabel lblImagenSplash = new JLabel();
		lblImagenSplash.setBounds(0, 0, imagenSplash.getIconWidth(), imagenSplash.getIconHeight());
		lblImagenSplash.setIcon(imagenSplash);
		contentPane.add(lblImagenSplash);
		
		setBounds(0, 0, imagenSplash.getIconWidth(), imagenSplash.getIconHeight());
		setLocationRelativeTo(null);
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f)); //Lo hace transparente el ultimo valor es el nivel de transparencia
	}

	public void mostrar(){
		Util.mostrarImagenDifuso(this);
		setVisible(true);
	}
	@Override
	public void dispose(){
		Util.ocultarImagenDifuso(this);
		super.dispose();
	}
}
