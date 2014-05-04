package view.elementos.paneles;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JCheckBoxMenuItem;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JButton;

import twitter4j.TwitterException;
import controller.GUIController;
import controller.TwitterService;
import monitorizacion.Grafica;

public class PanelEstadistica extends JPanel{
	public PanelEstadistica(){
		JLabel label = new JLabel();
		
		
		
		try {
			Grafica.crearGrafica(GUIController.getInstance().getT().getT().getScreenName(),GUIController.getInstance().getT().getT(), 460, 600);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
		label.setIcon(new ImageIcon(new File("grafica/grafica.png").getAbsolutePath()));
		}catch(Exception e){
			System.out.println("error a la hora de mostrar");
		}
		
		
		
		add(label);
	}
}
