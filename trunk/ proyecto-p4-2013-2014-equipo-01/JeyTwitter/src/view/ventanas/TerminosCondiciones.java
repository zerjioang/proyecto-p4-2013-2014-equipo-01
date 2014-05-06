package view.ventanas;

import util.Util;
import view.elementos.botones.BotonEmpezar;
import view.elementos.botones.BotonUI;
import view.elementos.botones.CoolBlueButton;
import view.elementos.input.CampoCodeAuth;
import view.eventos.welcome.EventoClickEmpezar;
import view.eventos.welcome.EventoKeyListenerAuthCode;
import view.eventos.welcome.EventoWelcomeContinuar;
import view.parents.CustomJDialogWithBar;
import view.parents.CustomJFrame;

import java.awt.EventQueue;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;

public class TerminosCondiciones extends CustomJDialogWithBar {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TerminosCondiciones frame = new TerminosCondiciones();
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
	public TerminosCondiciones() {
		super(640, 480);
		setTitle("Terminos y condiciones");
		init();
	}

	public void init(){

		getMainPanel().setBorder(null);
		getMainPanel().setBackground(Color.WHITE);
		getMainPanel().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		getMainPanel().add(scrollPane, BorderLayout.CENTER);
		
		JTextArea texto = new JTextArea("terminos y condiciones");
		
		JPanel inferior = new JPanel();
		JCheckBox checkbox = new JCheckBox("He leido y acepto las condiciones de uso");
		inferior.add(checkbox, BorderLayout.WEST);
		CoolBlueButton boton = new CoolBlueButton("Aceptar");
		inferior.add(boton, BorderLayout.EAST);
		getMainPanel().add(inferior, BorderLayout.SOUTH);
	}
}
