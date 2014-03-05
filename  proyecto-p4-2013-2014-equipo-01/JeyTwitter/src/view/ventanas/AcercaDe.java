package view.ventanas;

import util.Util;
import view.elementos.botones.BotonId;
import view.parents.CustomJFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

public class AcercaDe extends CustomJFrame {

	private static final Color COLOR_FONDO = Color.BLACK;
	private static final String TITULO = "Acerca de "+Util.APP_TITULO;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcercaDe frame = new AcercaDe();
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
	public AcercaDe() {
		super(315,418); //315,418
		init();
	}

	public AcercaDe(CustomJFrame principal) {
		this();
		setLocationRelativeTo(principal);
	}

	private void init() {
		setDisposeWindow(true);
		setAlwaysOnTop(true);
		setTitulo(TITULO);
		getMainPanel().setBackground(COLOR_FONDO);
		JLabel lblDev = new JLabel("Desarrollado por:");
		lblDev.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 18));
		lblDev.setForeground(Color.LIGHT_GRAY);
		lblDev.setHorizontalAlignment(SwingConstants.CENTER);
		getMainPanel().add(lblDev, BorderLayout.NORTH);
		
		JLabel lblJeytwitterV = new JLabel(Util.APP_TITULO+" v"+Util.APP_VERSION+" - Licencia GPL3");
		lblJeytwitterV.setFont(Util.getFont("Roboto-Light", Font.PLAIN, 18));
		lblJeytwitterV.setForeground(Color.LIGHT_GRAY);
		lblJeytwitterV.setHorizontalAlignment(SwingConstants.CENTER);
		getMainPanel().add(lblJeytwitterV, BorderLayout.SOUTH);
		
		JPanel panelNombresEscudo = new JPanel();
		panelNombresEscudo.setBackground(COLOR_FONDO);
		getMainPanel().add(panelNombresEscudo, BorderLayout.CENTER);
		panelNombresEscudo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEscudo = new JLabel();
		lblEscudo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscudo.setIcon(new ImageIcon(AcercaDe.class.getResource("/res/images/letreros/JeyTwitterVersion.png")));
		panelNombresEscudo.add(lblEscudo, BorderLayout.SOUTH);
		
		JPanel panelNombres = new JPanel();
		panelNombres.setBackground(COLOR_FONDO);
		panelNombresEscudo.add(panelNombres, BorderLayout.CENTER);
		panelNombres.setLayout(new BorderLayout(0, 0));
		
		BotonId lblAitor = new BotonId("/res/images/letreros/AB_id.png", "/res/images/letreros/tuiterAPI.png");
		panelNombres.add(lblAitor, BorderLayout.NORTH);
		
		BotonId lblRuben = new BotonId("/res/images/letreros/RG_id.png", "/res/images/letreros/SQLiteAPI.png");
		panelNombres.add(lblRuben, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(COLOR_FONDO);
		panelNombres.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		BotonId lblSergioA = new BotonId("/res/images/letreros/SA_id.png", "/res/images/letreros/GUI.png");
		panel.add(lblSergioA, BorderLayout.NORTH);
		
		BotonId lblSergioR = new BotonId("/res/images/letreros/SR_id.png", "/res/images/letreros/ErroryEvent.png");
		panel.add(lblSergioR, BorderLayout.SOUTH);
	}

}
