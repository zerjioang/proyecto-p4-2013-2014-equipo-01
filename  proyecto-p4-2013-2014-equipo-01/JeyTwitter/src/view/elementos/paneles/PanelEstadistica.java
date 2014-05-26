package view.elementos.paneles;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.JCheckBoxMenuItem;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import twitter4j.TwitterException;
import util.Util;
import view.eventos.estadistica.EventoClickIniciar;
import controller.GUIController;
import controller.TwitterService;
import monitorizacion.Grafica;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelEstadistica extends JPanel{
	private static JTextField txtNombreUsuario;
	public static String rutaDestino ="vacio";
	
	public PanelEstadistica() throws IOException{
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		JScrollPane scrollPaneOpciones = new JScrollPane();
		tabbedPane.addTab("Opciones", null, scrollPaneOpciones, null);
		
		JPanel panelOpciones = new JPanel();
		scrollPaneOpciones.setViewportView(panelOpciones);
		panelOpciones.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelOpciones.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnIniciar = new JButton("Iniciar");
		panel.add(btnIniciar, BorderLayout.CENTER);
		
		JLabel lblNombreUsuario = new JLabel("Introduzca el nombre de usuario");
		panelOpciones.add(lblNombreUsuario, BorderLayout.NORTH);
		
		txtNombreUsuario = new JTextField(GUIController.getInstance().getUsuarioRegistrado().getNombreUsuario());
		scrollPaneOpciones.setColumnHeaderView(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);
		btnIniciar.addMouseListener(new EventoClickIniciar());
		
		JScrollPane scrollPaneResultados = new JScrollPane();
		scrollPaneResultados.getVerticalScrollBar().setUnitIncrement(1);
		tabbedPane.addTab("Resultados", null, scrollPaneResultados, null);
		
		JPanel panelResultados = new JPanel();
		scrollPaneResultados.setViewportView(panelResultados);
		panelResultados.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSinResultados = new JLabel(" ");
		lblSinResultados.setHorizontalAlignment(SwingConstants.CENTER);
		panelResultados.add(lblSinResultados, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panelResultados.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblAbrirCarpetaDe = new JLabel("Abrir carpeta de destino:");
		panel_2.add(lblAbrirCarpetaDe);
		
		JButton btnNewButton = new JButton("Abrir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rutaDestino=="vacio"){
					Util.showError(null, "error", "Ruta de destino no seleccionada/no existente", "im homo", "aceptar");
				}else{
				File myFile = new File(rutaDestino);
				String path = myFile.getAbsolutePath();
				File dir = new File(path.substring(0, path.lastIndexOf(File.separator)));
				if (Desktop.isDesktopSupported()) {
				    try {
						Desktop.getDesktop().open(dir);
					} catch (Exception e1) {
						Util.showError(null, "error", "Ruta de destino no seleccionada/no existente", "im homo", "aceptar");

					}
				}
			  }
			}
		});
		panel_2.add(btnNewButton);
		
		
		
		
		
		
	}
	
	public static String getTxt(){
		return txtNombreUsuario.getText();
	}
}
