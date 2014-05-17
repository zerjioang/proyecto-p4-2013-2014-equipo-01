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
import controller.GUIController;
import controller.TwitterService;
import monitorizacion.Grafica;

public class PanelEstadistica extends JPanel{
	
	private JTable table;
	
	public PanelEstadistica(){
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		JScrollPane scrollPaneOpciones = new JScrollPane();
		tabbedPane.addTab("Opciones", null, scrollPaneOpciones, null);
		
		JPanel panel = new JPanel();
		scrollPaneOpciones.setViewportView(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Iniciar");
		panel.add(btnNewButton, BorderLayout.SOUTH);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
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
		panel.add(table, BorderLayout.CENTER);
		
		JScrollPane scrollPaneResultados = new JScrollPane();
		scrollPaneResultados.getVerticalScrollBar().setUnitIncrement(1);
		tabbedPane.addTab("Resultados", null, scrollPaneResultados, null);
		
		JPanel panel_1 = new JPanel();
		scrollPaneResultados.setViewportView(panel_1);
	}
}
