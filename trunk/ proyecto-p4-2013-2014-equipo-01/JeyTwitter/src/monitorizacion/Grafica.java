/**
 * Clase encargada de dibujar una grafica simple. Esta clase crea una imagen JPG con la gr��fica
 * 
 * PD: perdon por el uso de m��todos deprecados :(
 */


package monitorizacion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import java.io.*;
import java.util.Vector;

import org.jfree.data.time.*;

public class Grafica {
	
	
	/**
	 * Metodo que crea la grafica. Necesita un vector de "Datos"
	 * 
	 * @author sergio
	 * @param vector
	 */
	public static void crearGrafica(Vector<Dato> vector) {
		
	org.jfree.data.time.TimeSeries pop = new org.jfree.data.time.TimeSeries("Linea de Crecimiento", Day.class);
	
	/*
	 * El tema es este: no puedes a��adir 1-1-14 si ya hay un 1-1-14. Asi que hay que usar el metodo uddOrUpdate()
	 * sin embargo, este m��todo sobreescribe.
	 * 
	 * Para que no den datos raros, tiene que haber al menos 2 fechas
	 */
	


	for(int i=0; i<vector.size() ; i++){
		
		Dato datos = vector.elementAt(i);
		
		int dia = datos.dia;
		int mes = datos.mes;
		int ano = datos.ano;
		
		pop.addOrUpdate(new Day(dia, mes, ano), 1);			
		//pop.add(new Day(dia, mes, ano), 1);
				
	}
	
	pop.add(new Day(1, 4, 114), 1);			
	pop.add(new Day(2, 4, 114), 2);			


	
	String nom = vector.firstElement().getNom();

	
	
	TimeSeriesCollection dataset = new TimeSeriesCollection();
	dataset.addSeries(pop);
	JFreeChart chart = ChartFactory.createTimeSeriesChart("Historial de tuits de "+ nom,"Fecha","Numero de Tuits",dataset,true,true,false);
	
	try {
		ChartUtilities.saveChartAsJPEG(new File("grafica.jpg"), chart, 500, 300);
		}catch (IOException e){
			System.err.println("Error creando grafico.");
			}
		}
	
	
	
	
	public static void main(String[] args){

		org.jfree.data.time.TimeSeries pop = new org.jfree.data.time.TimeSeries("Linea de Crecimiento", Day.class);
		
		

		pop.addOrUpdate(new Day(1, 2, 2014), 1);
		pop.addOrUpdate(new Day(2, 2, 2014), 2);
		pop.addOrUpdate(new Day(3, 2, 2014), 5);
		pop.addOrUpdate(new Day(4, 2, 2014), 2);


		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(pop);
		JFreeChart chart = ChartFactory.createTimeSeriesChart("Historial de tuits de PRUEBAAAA","Fecha","Numero de Tuits",dataset,true,true,false);
		
		try {
			ChartUtilities.saveChartAsJPEG(new File("rafica.png"), chart, 2000, 1200);
			}catch (IOException e){
				System.err.println("Error creando grafico.");
				}
	}
}
	
