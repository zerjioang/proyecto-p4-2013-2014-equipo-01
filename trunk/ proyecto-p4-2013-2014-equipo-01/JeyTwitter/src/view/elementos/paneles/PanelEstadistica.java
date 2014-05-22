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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import twitter4j.TwitterException;
import view.eventos.estadistica.EventoClickIniciar;
import controller.GUIController;
import controller.TwitterService;
import monitorizacion.Grafica;
import javax.swing.SwingConstants;

public class PanelEstadistica extends JPanel{
	
	private JTable table;
	
	public PanelEstadistica(){
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		JScrollPane scrollPaneOpciones = new JScrollPane();
		tabbedPane.addTab("Opciones", null, scrollPaneOpciones, null);
		
		JPanel panelOpciones = new JPanel();
		scrollPaneOpciones.setViewportView(panelOpciones);
		panelOpciones.setLayout(new BorderLayout(0, 0));
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addMouseListener(new EventoClickIniciar());
		panelOpciones.add(btnIniciar, BorderLayout.SOUTH);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"opcion 1", new Boolean(false)},
				{"opcion 2", new Boolean(false)},
				{"opcion 3", new Boolean(false)},
				{"opcion 4", new Boolean(false)},
				{"opcion 5", new Boolean(false)},
				{"opcion 6", new Boolean(false)},
				{"opcion 7", new Boolean(false)},
			},
			new String[] {
				"Descripcion", "Estado"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		panelOpciones.add(table, BorderLayout.CENTER);
		
		JScrollPane scrollPaneResultados = new JScrollPane();
		scrollPaneResultados.getVerticalScrollBar().setUnitIncrement(1);
		tabbedPane.addTab("Resultados", null, scrollPaneResultados, null);
		
		JPanel panelResultados = new JPanel();
		scrollPaneResultados.setViewportView(panelResultados);
		panelResultados.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSinResultados = new JLabel("Sin resultados");
		lblSinResultados.setHorizontalAlignment(SwingConstants.CENTER);
		panelResultados.add(lblSinResultados, BorderLayout.CENTER);
	}
}
