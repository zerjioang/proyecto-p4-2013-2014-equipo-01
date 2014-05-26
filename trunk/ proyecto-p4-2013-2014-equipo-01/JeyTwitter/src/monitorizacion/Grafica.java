package monitorizacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import model.Tweet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import controller.GUIController;
import twitter4j.Paging;
import twitter4j.TwitterException;



public class Grafica {
	@SuppressWarnings("deprecation")

	
	
	public static void iniciar(String usuario, int numPaginas, String ruta) throws MalformedURLException, IOException{
		GUIController.stalker(usuario, numPaginas, ruta);
	}
	
	
	
}