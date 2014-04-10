package monitorizacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeriesCollection;

import twitter4j.*;


public class Grafica {

	@SuppressWarnings("deprecation")
	public static void crearGrafica(String usuario, Twitter t) throws TwitterException, FileNotFoundException, ParseException {

		
		//Abrimos un txt
		//PrintStream DDescritor = new PrintStream("tuits.txt");

		//iniciamos la grafica
		org.jfree.data.time.TimeSeries pop = new org.jfree.data.time.TimeSeries("Historial de tuits", Day.class);

		//calculo: obtener cuantas paginas hay
		ResponseList<Status> timeline = t.getUserTimeline(usuario, new Paging(1));
		int numT = timeline.get(1).getUser().getStatusesCount();
		
		int div = (int) (numT/200) + 1;

		
		
		int contador =1;
		for(int j=1; j<=div; j++){
		ResponseList<Status> tuits = t.getUserTimeline(usuario, new Paging(j, 200));
		
		for(int i=0; i<tuits.size()-1;i++){
			//por cada tuí
			System.out.println(tuits.get(i).getText());
			Date primera = new Date(tuits.get(i).getCreatedAt().getYear() + 1900, tuits.get(i).getCreatedAt().getMonth(), tuits.get(i).getCreatedAt().getDate());
			Date segunda = new Date(tuits.get(i+1).getCreatedAt().getYear() + 1900, tuits.get(i+1).getCreatedAt().getMonth(), tuits.get(i+1).getCreatedAt().getDate());

			if(segunda.equals(primera)){
				contador++;				
			}else{
				//DDescritor.println((primera.getDate())+"-"+(1 + primera.getMonth())+"-"+primera.getYear()+ ": " + contador);
				
				pop.add(new Day(primera.getDate(), primera.getMonth()+1, primera.getYear()), contador);
				contador = 1;

			}
			
			

			
			
		}
		
		}
					
					
				
		//cerramos la grafica y el fitxero
		//DDescritor.close();
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(pop);
		JFreeChart chart = ChartFactory.createTimeSeriesChart("Historial de tuits de: " + usuario,"Fecha","Numero de Tuits",dataset,true,true,false);

		try {
			ChartUtilities.saveChartAsPNG(new File("grafica_de_@"+usuario+".jpg"), chart, 2000, 1200);
		}catch (IOException e){
			System.err.println("Error creando grafico.");
		}
	}




	/*esto da tuits infinitos :3
	
	Query query = new Query("#live");
	query.setCount(100);
	QueryResult result=t.search(query);
	do{
	          List<Status> tweets = result.getTweets();
	          for(Status tweet: tweets){
	               System.out.println("Tweet: "+tweet.getText());
	          }
	          query=result.nextQuery();
	          if(query!=null)
	               result=t.search(query);
	}while(query!=null);
	
	
	*/












}


