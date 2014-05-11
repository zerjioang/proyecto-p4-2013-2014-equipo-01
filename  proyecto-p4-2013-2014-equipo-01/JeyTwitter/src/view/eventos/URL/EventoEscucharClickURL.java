package view.eventos.URL;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import util.InvalidInputException;
import util.Util;


public class EventoEscucharClickURL implements HyperlinkListener {

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            if (Desktop.isDesktopSupported()) {
                try {
                	String url = e.getDescription();
                	System.out.println(url);
                	if(url.startsWith("@"))
                		abrirPerfil(url);
                	else if(url.startsWith("#"));
                		//do something
                	else
                		abrirHTTP(url);
                    
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InvalidInputException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }
    }

	private void abrirPerfil(String url) throws InvalidInputException {
		Util.showMessage(null, "En desarrollo", "Cargar  perfil de "+url, "Lo programo", "Paso");
	}

	private void abrirHTTP(String url) throws IOException, URISyntaxException {
		Desktop.getDesktop().browse(new URI(url));
	}
}
