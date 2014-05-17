import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import view.elementos.paneles.PanelEstadistica;
import view.parents.CustomJFrame;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import java.awt.List;
import javax.swing.JButton;
import java.awt.Panel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class temp extends CustomJFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					temp frame = new temp();
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
	public temp() {
		super(600, 700);
		
		getMainPanel().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getMainPanel().add(tabbedPane);
		
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
		tabbedPane.addTab("Resultados", null, scrollPaneResultados, null);
		
		JPanel panel_1 = new JPanel();
		scrollPaneResultados.setViewportView(panel_1);
		
		/*PanelEstadistica pe = new PanelEstadistica();
		getMainPanel().add(pe);
		*/
	}
}
