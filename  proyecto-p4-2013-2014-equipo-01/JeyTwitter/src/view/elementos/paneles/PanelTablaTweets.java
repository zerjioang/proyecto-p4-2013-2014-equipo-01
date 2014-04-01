package view.elementos.paneles;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.models.tablasPrincipal.TablaTweetsUsuarios;
import view.ventanas.Principal;

public class PanelTablaTweets extends JPanel {
	
	private JScrollPane scrollpane;
	private TablaTweetsUsuarios tabla;

	public PanelTablaTweets(TablaTweetsUsuarios tabla){
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
	public TablaTweetsUsuarios getTabla() {
		return tabla;
	}

	/**
	 * @param tabla the tabla to set
	 */
	public void setTabla(TablaTweetsUsuarios tabla) {
		this.tabla = tabla;
	}

	public void insertarNuevo() {
		TablaTweetsUsuarios t = getTabla();
		t.insertarNuevo(new GUITweet("2d", new ImageIcon(Principal.class.getResource("/res/images/userTest.jpg")), "Fernando lu", "El colgao", "Esto es una fiezzzta"));
	}
}
