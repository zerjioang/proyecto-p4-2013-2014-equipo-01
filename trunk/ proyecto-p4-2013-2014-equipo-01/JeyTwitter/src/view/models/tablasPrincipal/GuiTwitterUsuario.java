package view.models.tablasPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import util.Util;
import view.elementos.botones.BotonSeguir;

public class GuiTwitterUsuario extends JPanel {

	public GuiTwitterUsuario(){
		super();
		init();
	}

	private void init() {
		//setBounds(10, 11, 476, 110);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelmagen = new JPanel();
		add(panelmagen, BorderLayout.WEST);
		panelmagen.setLayout(new BorderLayout(0, 0));
		
		JLabel lblImagenUsuario = new JLabel("");

		lblImagenUsuario.setSize(100, 100);
		lblImagenUsuario.setIcon(Util.getImagenRedondeada(new ImageIcon(GuiTwitterUsuario.class.getResource("/res/images/userTest.jpg")), 20));
		lblImagenUsuario.setIcon(Util.escalarImagen(lblImagenUsuario));
		panelmagen.add(lblImagenUsuario, BorderLayout.CENTER);
		
		JPanel panelTexto = new JPanel();
		add(panelTexto, BorderLayout.CENTER);
		panelTexto.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_inferior = new JPanel();
		panelTexto.add(panel_inferior, BorderLayout.SOUTH);
		panel_inferior.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Superior = new JPanel();
		panelTexto.add(panel_Superior, BorderLayout.NORTH);
		panel_Superior.setLayout(new BorderLayout());
		
		JPanel panel_supDer = new JPanel();
		panel_Superior.add(panel_supDer, BorderLayout.EAST);
		panel_supDer.setLayout(new BorderLayout(0, 0));
		
		BotonSeguir btnSeguir = new BotonSeguir();
		panel_supDer.add(btnSeguir, BorderLayout.CENTER);
		btnSeguir.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(1.0f,1.0f,1.0f,0.0f)));
		
		JPanel panel_supIzq = new JPanel();
		panel_Superior.add(panel_supIzq, BorderLayout.WEST);
		panel_supIzq.setLayout(new BorderLayout(0, 0));
		
		JLabel lblusuario = new JLabel("@Usuario");
		panel_supIzq.add(lblusuario, BorderLayout.NORTH);
		lblusuario.setBorder(new MatteBorder(4, 4, 4, 0, (Color) new Color(1.0f,1.0f,1.0f,0.0f)));
		lblusuario.setFont(Util.getFont("mirda", Font.BOLD, 14));
		
		JLabel lblNombreReal = new JLabel("Nombre Real");
		panel_supIzq.add(lblNombreReal, BorderLayout.SOUTH);
		lblNombreReal.setBorder(new MatteBorder(0, 4, 4, 0, (Color) new Color(1.0f,1.0f,1.0f,0.0f)));
		lblNombreReal.setFont(Util.getFont("mirda", Font.PLAIN, 14));
		
		JTextArea txtrBiografia = new JTextArea();
		panelTexto.add(txtrBiografia, BorderLayout.CENTER);
		txtrBiografia.setBorder(new MatteBorder(0,5,5,0, new Color(1.0f,1.0f,1.0f,0.0f)));
		txtrBiografia.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		txtrBiografia.setOpaque(false);
		txtrBiografia.setEditable(false);
		txtrBiografia.setWrapStyleWord(true);
		txtrBiografia.setLineWrap(true);
		txtrBiografia.setFocusable(false);
		txtrBiografia.setFont(Util.getFont("mirda", Font.PLAIN, 12));
		txtrBiografia.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et felis vestibulum, laoreet ipsum vel. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et felis vestibulum, laoreet ipsum vel");
		System.out.println("TOTAL "+"scing elit. Nullam et felis vestibulum, laoreet ipsum vel. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et felis vestibulum, laoreet ipsum vel".length());
	}
}
