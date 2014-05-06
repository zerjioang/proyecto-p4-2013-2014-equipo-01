package monitorizacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeriesCollection;

import controller.GUIController;
import twitter4j.*;


public class Grafica {

	@SuppressWarnings("deprecation")
	public static void crearGrafica(String usuario, int ancho, int alto) throws TwitterException, FileNotFoundException, ParseException {

		new File("grafica").mkdir();
		//Abrimos un txt
		//PrintStream DDescritor = new PrintStream("tuits.txt");

		//iniciamos la grafica
		org.jfree.data.time.TimeSeries pop = new org.jfree.data.time.TimeSeries("Historial de tuits", Day.class);

		//calculo: obtener cuantas paginas hay
		ResponseList<Status> timeline = GUIController.getInstance().obtenerTimelineDeUsuario(usuario, new Paging(1));
		int numT = timeline.get(1).getUser().getStatusesCount();

		int div = (int) (numT/200) + 1;



		int contador =1;
		for(int j=1; j<=div; j++){
			ResponseList<Status> tuits = GUIController.getInstance().obtenerTimelineDeUsuario(usuario, new Paging(j, 200));

			for(int i=0; i<tuits.size()-1;i++){
				//por cada tuí
				System.out.println(tuits.get(i).getText());
				Date primera = new Date(tuits.get(i).getCreatedAt().getYear() + 1900, tuits.get(i).getCreatedAt().getMonth(), tuits.get(i).getCreatedAt().getDate());
				Date segunda = new Date(tuits.get(i+1).getCreatedAt().getYear() + 1900, tuits.get(i+1).getCreatedAt().getMonth(), tuits.get(i+1).getCreatedAt().getDate());

				if(segunda.equals(primera)){
					contador++;				
				}else{
					//DDescritor.println((primera.getDate())+"-"+(1 + primera.getMonth())+"-"+primera.getYear()+ ": " + contador);

					pop.addOrUpdate(new Day(primera.getDate(), primera.getMonth()+1, primera.getYear()), contador);
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
			ChartUtilities.saveChartAsPNG(new File("grafica/grafica.png"), chart, ancho, alto);
		}catch (IOException e){
			System.err.println("Error creando grafico.");
			e.printStackTrace();
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