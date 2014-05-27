import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JPanel;

import util.InvalidInputException;
import util.Util;
import view.elementos.paneles.PanelEstadistica;
import view.parents.CustomJFrame;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class temp extends CustomJFrame {
	private JTable table;

	
	public static void main(String[] args) throws InvalidInputException {
		 
	}

	
	public temp() throws IOException {
		super(600, 700);
		
		PanelEstadistica pe = new PanelEstadistica();
		getMainPanel().add(pe);
	}
}
