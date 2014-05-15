package monitorizacion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import controller.GUIController;
import controller.TwitterService;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import view.elementos.ObjetoCelda;



public class Grafica {
	@SuppressWarnings("deprecation")
	public static void crearGrafica(String nombre, int alto, int ancho) throws TwitterException { 

		
		

		//inicializamos la grafica
		TimeSeries pop = new TimeSeries("Tuits", Day.class);

		//obtenemos la primera lista de tuits
		ArrayList<ObjetoCelda> tuits = GUIController.getInstance().obtenerTimelineDeUsuario(nombre, new Paging(1, 199));
		System.out.println("El tamaño de la lista de tuits es de: "+ tuits.size());

		
		//calculo: obtener cuantas paginas hay (esto es aproximadamente)
		ArrayList<ObjetoCelda> temporal = GUIController.getInstance().obtenerTimelineDeUsuario(nombre, new Paging(1, 200));
		int numT = temporal.get(1).getUser().getStatusesCount();
		int div = (int) (numT/200) + 1;
		
		//establecemos un tope
		if(div>21){
			div = 21;
		}

		
		
		//obtenemos el resto de tuits
		for(int i = 2; i<=div; i++){
			Paging p = new Paging(i, 200);
			System.out.println("Recibo por " + p.getPage() + " vez los tuits");
			ResponseList<Status> timeline = GUIController.getInstance().obtenerTimelineDeUsuario(nombre, p);
			//System.out.println("El tamaño de la lista de tuits es de: "+ timeline.size());

			//añadimos la timeline a la lista de tuits
			for(int j=0; j<timeline.size(); j++){	
				tuits.add(timeline.get(j));
			}

		}

		System.out.println("El tamaño de la lista de tuits es de: "+ tuits.size());




		//obtenemos las dos fechas rango. Se hace asi para que todas las fechas tengal la misma hora (las 00:00)
		Date tuit = tuits.get(tuits.size() -1).getCreatedAt();
		Date primer = new Date(tuit.getYear(), tuit.getMonth(), tuit.getDate());
		tuit = tuits.get(0).getCreatedAt();
		Date ultimo = new Date(tuit.getYear(), tuit.getMonth(), tuit.getDate());

		System.out.println("Dia primero: "+  primer.getDate() + " mes: " + (primer.getMonth() +1) + " año: "+ (primer.getYear() +1900));
		System.out.println("Dia ultimo: "+  ultimo.getDate() + " mes: " + (ultimo.getMonth() +1) + " año: "+ (ultimo.getYear() +1900));






		//añadimos dias vacios entre ambas fechas
		while(compara(primer, ultimo) == false){
			System.err.println("Añadimos los dias vacios");
			primer = sumarRestarDiasFecha(primer, 1);			
			System.out.println("Dia primero: "+  primer.getDate() + " mes: " + (primer.getMonth() +1) + " año: "+ (primer.getYear() +1900));
			pop.addOrUpdate(new Day(primer.getDate(), primer.getMonth()+1, primer.getYear()+1900), 0);
		}

		//añadimos los dias que se ha tuiteado:

		//este es dificil de explicar :S
		int contadorMagico = 1;


		//se obtiene el la fecha del tuit mas viejuno
		tuit = tuits.get(tuits.size() - contadorMagico).getCreatedAt();
		primer = new Date(tuit.getYear(), tuit.getMonth(), tuit.getDate());
		tuit = tuits.get(tuits.size() - (contadorMagico +1)).getCreatedAt();
		ultimo = new Date(tuit.getYear(), tuit.getMonth(), tuit.getDate());

		//esto no creo que haga falta que explique lo que es x-)
		int contador = 1;


		//para toda la lista de tuits...
		for(int i=tuits.size(); i>2; i--){
			contadorMagico++;
			//si las fechas son iguales, entonces, hay mas de un tuit
			if(compara(primer, ultimo) == true){
				contador++;
			}else{
				pop.addOrUpdate(new Day(primer.getDate(), primer.getMonth()+1, primer.getYear()+1900), contador);
				contador =1;
			}

			//avanzamos un dia en nuestra maravillosa odisea 
			tuit = tuits.get(tuits.size() - contadorMagico).getCreatedAt();
			primer = new Date(tuit.getYear(), tuit.getMonth(), tuit.getDate());
			tuit = tuits.get(tuits.size() - (contadorMagico +1)).getCreatedAt();
			ultimo = new Date(tuit.getYear(), tuit.getMonth(), tuit.getDate());
		}




		//cerramos la grafica y hacemos el png
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(pop);

		JFreeChart chart = ChartFactory.createTimeSeriesChart("Historial de tuits de " + nombre ,"Fecha","Cantidad de tuits",dataset,true,true,false);

		try {
			ChartUtilities.saveChartAsPNG(new File("grafica/grafica.png"), chart, alto, ancho);
		} catch (IOException e) {
			System.err.println("Problem occurred creating chart.");
		}



	}


	public static Date sumarRestarDiasFecha(Date fecha, int dias){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
		return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos

	}

	public static  boolean compara(Date fecha1, Date fecha2){
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();

		calendar1.setTime(fecha1); // Configuramos la fecha que se recibe
		calendar2.setTime(fecha2); // Configuramos la fecha que se recibe

		boolean iguales = calendar1.equals(calendar2);

		return iguales;

	}
}

