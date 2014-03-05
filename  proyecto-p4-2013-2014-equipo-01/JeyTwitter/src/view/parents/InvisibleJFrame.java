package view.parents;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import util.Util;

public class InvisibleJFrame extends JFrame {

	protected JPanel contentPane;
	private static final String IMG_ICON = Util.APP_ICONO;
	protected JLabel fondo;
	private String imagenfondo;
	private ImageIcon icono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvisibleJFrame frame = new InvisibleJFrame("/res/images/splash.png");
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
	public InvisibleJFrame(String imagenFondo) {
		this.imagenfondo=imagenFondo;
		setUndecorated(true);
		setType(Type.POPUP);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fondo = new JLabel();
		fondo.setIcon(icono);
		contentPane.add(fondo);
		
		init();
	}

	private void init() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InvisibleJFrame.class.getResource(IMG_ICON)));
		setBackground(new Color(1.0f,1.0f,1.0f,0.0f)); //Lo hace transparente el ultimo valor es el nivel de transparencia
		ajustarImagen();
	}

	private void ajustarImagen() {
		icono = new ImageIcon(InvisibleJFrame.class.getResource(imagenfondo));
		if(icono!=null){
			fondo.setIcon(icono);
			fondo.setBounds(0, 0, icono.getIconWidth(), icono.getIconHeight());
			setBounds(0, 0, icono.getIconWidth(), icono.getIconHeight());
		}
		setLocationRelativeTo(null);
	}

	public void setImagenFondo(String ruta){
		fondo.setIcon(new ImageIcon(InvisibleJFrame.class.getResource(ruta)));
	}
}
