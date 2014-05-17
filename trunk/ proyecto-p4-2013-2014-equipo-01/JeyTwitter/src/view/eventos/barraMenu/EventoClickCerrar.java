package view.eventos.barraMenu;

import view.elementos.GUITweet;
import view.elementos.ObjetoCelda;
import view.elementos.paneles.PanelTablaTweets;
import view.models.tablasPrincipal.TablaTweetsUsuarios;
import view.parents.Moveable;
import view.ventanas.Principal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Tweet;
import controller.GUIController;
import controller.sql.Interaccion;
import util.Util;
/**
 * Evento que controla la accion a realizar cuando el usuario clica en el boton cerrar de la barra superior
 * @author Sergio Anguita
 */

public class EventoClickCerrar implements MouseListener {

	private Moveable parent;
	
	public EventoClickCerrar(Moveable parent){
		this.parent = parent;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		boolean dispose = false;
		dispose = parent.isDisposeWindow();
		System.out.println(dispose);
		if(dispose){
			Util.ocultarImagenDifuso(parent);
			parent.dispose();
		}
		else{
			try {
				boolean respPositiva = Util.showMessage(parent, "Cerrar","Desea realmente cerrar "+Util.APP_TITULO+"?", "Cerrar", "Cancelar");
				if(respPositiva){
					if(parent instanceof Principal){
						System.out.println("1");
						Util.ocultarImagenDifuso(parent);
						System.out.println("2");
						Principal p = GUIController.getInstance().getGui();
						System.out.println("3");
						String u = p.getUsuarioActual().getNombreUsuario();
						System.out.println("4");
						JPanel[] panel = p.getPaneles();
						System.out.println("5");
						PanelTablaTweets panelTimeline = (PanelTablaTweets) panel[Principal.TIMELINE];
						System.out.println("6");
						TablaTweetsUsuarios tabla = panelTimeline.getTabla();
						System.out.println("7");
						int i = 0;
						LinkedList<Tweet> cargaBase = new LinkedList<Tweet>();
						System.out.println("8");
						System.out.println("Iniciando guardado de datos");
						System.out.println("9");
						while(i!=tabla.getRowCount())
						{
							System.out.println("I: " + i);
							GUITweet temp = (GUITweet) tabla.getValueAt(i, 0);
							System.out.println("- Guardando tweets en lista "+i);
							cargaBase.add(0, temp.getTweet());
							i++;
						}
						long a = System.currentTimeMillis();
						System.out.println("escribiendo contenido en BD");
						Interaccion.insertarTweets(cargaBase, u, "png");
						System.out.println("Tiempo guardar BD: "+(System.currentTimeMillis()-a));
						parent.dispose();
					}
					else{
						parent.dispose();
					}
				}
			} catch (Exception e) {}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		parent.setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/CloseHover.png")));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		parent.setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/botonesNormales.png")));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		parent.setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/closeClick.png")));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		parent.setImagenIconos(new ImageIcon(EventoClickCerrar.class.getResource("/res/images/botonera/botonesNormales.png")));
	}
}
