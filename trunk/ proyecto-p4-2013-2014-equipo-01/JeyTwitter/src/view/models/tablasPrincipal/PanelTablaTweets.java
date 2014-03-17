package view.models.tablasPrincipal;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PanelTablaTweets extends JPanel {
	
	private JScrollPane scrollpane;
	private TablaTweet tabla;

	public PanelTablaTweets(TablaTweet tabla){
		super();
		scrollpane = new JScrollPane();
		this.tabla = tabla;
		init();
	}

	private void init() {
		scrollpane = new JScrollPane();
		//scrollpane.setBackground(COLOR_FONDO);
		//scrollpane.getViewport().setBackground(COLOR_FONDO); 
		scrollpane.setViewportBorder(null);
		setLayout(new BorderLayout(0, 0));
		scrollpane.setBorder(null);
	
		//se muestra en el scrollpane
		scrollpane.setViewportView(tabla);
		add(scrollpane, BorderLayout.CENTER);
	}

	/**
	 * @return the tabla
	 */
	public TablaTweet getTabla() {
		return tabla;
	}

	/**
	 * @param tabla the tabla to set
	 */
	public void setTabla(TablaTweet tabla) {
		this.tabla = tabla;
	}
}
