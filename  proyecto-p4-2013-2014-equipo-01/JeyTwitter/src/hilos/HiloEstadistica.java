
package hilos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import model.Tweet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;
import util.Util;
import view.elementos.GUITweet;
import controller.GUIController;
import controller.TwitterService;

public class HiloEstadistica extends Thread {

	private TwitterService t;
	private String nombre;
	private int numPaginas;
	private static String ruta;

	//contador de dias
	//	private static int numeroDeDias =0;

	//creamos una lista vacia donde irán todos los tuits que vamos a sacar
	private static	ArrayList<Tweet> tuits = new ArrayList<Tweet>();

	public HiloEstadistica(TwitterService t, String nombre, int numPaginas, String ruta) {
		this.t = t;
		this.numPaginas = numPaginas;
		this.nombre = nombre;
		this.ruta = ruta;
	}

	public void run(){
		//muestr un mensaje en la aplicacion
		GUIController.getInstance().getGui().mostrarMensaje("Generando estadistica...");
		try {
			generarTuits();
			System.out.println("Archivo de tuits");
			crearGrafica(nombre, 1500, 900);
			System.out.println("Grafica");
			generarMenciones();
			generarHashTags();
			generarListaTuits();
			System.out.println("Lista");
			diaMasTuiteado();
			GUIController.getInstance().getGui().mostrarMensaje("Estadistica finalizada");
			Util.pausar(1000);
			GUIController.getInstance().getGui().ocultarMensajeInformativo();
		} catch (Exception e) {
			Util.showError(null, "Error", "Error generando las graficas", "Cancelar", "Aceptar");
		}
		GUIController.getInstance().getGui().ocultarMensajeInformativo();
	}

	private void generarHashTags() throws FileNotFoundException {
		HashMap<String, Integer> counter = new HashMap<String, Integer>();
		//abrimos el archivo
		PrintStream DDescritor = new PrintStream(ruta+ "/lista_HashTags.txt");
		for (Tweet t : tuits) {
			GUITweet gt = new GUITweet(t);
			ArrayList<String> hash = gt.getHashtagTweet();
			for (String s : hash) {
				Integer i = counter.get(s);
				if(i==null || i==0){
					counter.put(s, 1);
				}
				else{
					counter.put(s, i.intValue()+1);
				}
			}
		}
		Set<String> keys = counter.keySet();
		String[] k = keys.toArray(new String[keys.size()]);
		Collection<Integer> values = counter.values();
		Integer[] v = values.toArray(new Integer[values.size()]);
		for (int i=0; i<k.length;i++) {
			DDescritor.println(k[i]+"\t\t\t\t "+v[i].intValue());
		}
		//cerramos
		DDescritor.close();
				
	}

	private void generarMenciones() throws FileNotFoundException {
		HashMap<String, Integer> counter = new HashMap<String, Integer>();
		//abrimos el archivo
		PrintStream DDescritor = new PrintStream(ruta+ "/lista_menciones.txt");
		for (Tweet t : tuits) {
			GUITweet gt = new GUITweet(t);
			ArrayList<String> hash = gt.getUsuarioMencionado();
			for (String s : hash) {
				Integer i = counter.get(s);
				if(i==null || i==0){
					counter.put(s, 1);
				}
				else{
					counter.put(s, i.intValue()+1);
				}
			}
		}
		Set<String> keys = counter.keySet();
		String[] k = keys.toArray(new String[keys.size()]);
		Collection<Integer> values = counter.values();
		Integer[] v = values.toArray(new Integer[values.size()]);
		for (int i=0; i<k.length;i++) {
			DDescritor.println(k[i]+"\t\t\t\t "+v[i].intValue());
		}
		//cerramos
		DDescritor.close();
				
	}
	
	public void generarTuits() throws TwitterException {


		//ESTO ES CRUCIAL!!! limpia la lista de tuits antes de recibir nuevos. Si no, no funcionará una segunda vez
		tuits.clear();
		System.out.println("Obtenemos tuits...");
		//obtenemos los tuits
		for(int i = 1; i<=numPaginas; i++){
			//necesitamos una paginacion
			Paging p = new Paging(i, 199);
			System.out.println("Pagina "+ i);

			//creamos una lista temporal donde metemos los tuits que hemos pedido con esta pagina. Usamos la paginacion anterior
			ResponseList<Status> timelineStatus = t.getTimelineFromUser(nombre, p);

			//metemos todos los Status en Tweets
			for(int j=0; j<timelineStatus.size();j++){
				Status s = timelineStatus.get(j);
				Tweet t = new Tweet(s.getId(), s.getUser().getScreenName(), s.getUser().getName(), s.getCreatedAt(), null, s.getText(), s.isRetweet(), s.isFavorited(), null);
				//añadimos los tuits al ArrayList
				tuits.add(t);
				System.out.println("Tuit añadido");
			}
		}		
	}

	public static void generarListaTuits() throws FileNotFoundException {
		//creamos el archivo txt
		PrintStream DDescritor = new PrintStream(ruta+ "/Lista_De_Tuits.txt");
		//recorremos los tuits, les damos formato y los metemos en el TXT
		for(int i=0; i<tuits.size(); i++){
			String nombreReal = tuits.get(i).getNombreReal();
			String arroba = tuits.get(i).getNombreUsuario();
			String fecha = ""+ tuits.get(i).getUltimaFechaActualizacion();
			String mensaje = tuits.get(i).getTexto();

			DDescritor.println(nombreReal + " | "+"@"+ arroba + " - "+ fecha);
			DDescritor.println(mensaje);
			DDescritor.println("\n===========================================================================\n");
		}
		//cerramos
		DDescritor.close();
	}

	public static void crearGrafica(String nombre, int alto, int ancho) throws IOException {
		//inicializamos la grafica
		TimeSeries pop = new TimeSeries("Tuits", Day.class);

		//obtenemos las dos fechas rango. Se hace asi para que todas las fechas tengal la misma hora (las 00:00) crenado una fecha con hora 00:00 a partir de las fechas
		Date tuit = tuits.get(tuits.size() -1).getUltimaFechaActualizacion();
		Date primer = new Date(tuit.getYear(), tuit.getMonth(), tuit.getDate());
		tuit = tuits.get(0).getUltimaFechaActualizacion();
		Date ultimo = new Date(tuit.getYear(), tuit.getMonth(), tuit.getDate());

		System.out.println("Dia primero: "+  primer.getDate() + " mes: " + (primer.getMonth() +1) + " año: "+ (primer.getYear() +1900));
		System.out.println("Dia ultimo: "+  ultimo.getDate() + " mes: " + (ultimo.getMonth() +1) + " año: "+ (ultimo.getYear() +1900));

		//añadimos dias vacios a la grafica entre ambas fechas. El array solo contiene dias en los que has tuiteado, asi que necesitas calcular los dias en los que no
		System.out.println("Añadimos los dias...");
		while(compara(primer, ultimo) == false){
			primer = sumarRestarDiasFecha(primer, 1);			
			System.out.println("Dia primero: "+  primer.getDate() + " mes: " + (primer.getMonth() +1) + " año: "+ (primer.getYear() +1900));
			pop.addOrUpdate(new Day(primer.getDate(), primer.getMonth()+1, primer.getYear()+1900), 0);
			//numeroDedias++
		}
		//contador se usa para extraer el tuit de un dia y el tuit del dia siguiente
		int contadorMagico = 1;

		//se obtiene el la fecha del tuit mas viejuno
		tuit = tuits.get(tuits.size() - contadorMagico).getUltimaFechaActualizacion();
		primer = new Date(tuit.getYear(), tuit.getMonth(), tuit.getDate());
		tuit = tuits.get(tuits.size() - (contadorMagico +1)).getUltimaFechaActualizacion();
		ultimo = new Date(tuit.getYear(), tuit.getMonth(), tuit.getDate());

		//esto no creo que haga falta que explique lo que es x-)
		int contador = 1;

		//para toda la lista de tuits...
		for(int i1=tuits.size(); i1>2; i1--){
			contadorMagico++;
			//si las fechas son iguales, entonces, hay mas de un tuit
			if(compara(primer, ultimo) == true){
				contador++;
			}else{
				pop.addOrUpdate(new Day(primer.getDate(), primer.getMonth()+1, primer.getYear()+1900), contador);
				contador =1;
			}

			//avanzamos un dia en nuestra maravillosa odisea 
			tuit = tuits.get(tuits.size() - contadorMagico).getUltimaFechaActualizacion();
			primer = new Date(tuit.getYear(), tuit.getMonth(), tuit.getDate());
			tuit = tuits.get(tuits.size() - (contadorMagico +1)).getUltimaFechaActualizacion();
			ultimo = new Date(tuit.getYear(), tuit.getMonth(), tuit.getDate());
		}

		//cerramos la grafica y hacemos el png
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(pop);
		JFreeChart chart = ChartFactory.createTimeSeriesChart("Historial de tuits de " + nombre ,"Fecha","Cantidad de tuits",dataset,true,true,false);
		ChartUtilities.saveChartAsPNG(new File(ruta+"/grafica.png"), chart, alto, ancho);
	}

	//Calcula el número total de tuits por dias de la semana
	public static void diaMasTuiteado() throws IOException {
		// (0 = Sunday, 1 = Monday, 2 = Tuesday, 3 = Wednesday, 4 = Thursday, 5 = Friday, 6 = Saturday)

		int lunes =0;
		int martes =0;
		int miercoles =0;
		int jueves =0;
		int viernes =0;
		int sabado =0;
		int domingo =0;

		for(int i=0; i<tuits.size(); i++){

			//Recorre los tuits, obten el dia de la semana que se ha tuiteado y sumalo
			int dia = tuits.get(i).getUltimaFechaActualizacion().getDay();

			switch(dia){
			case 0:
				domingo++;
				break;
			case 1:
				lunes++;
				break;
			case 2:
				martes++;
				break;
			case 3:
				miercoles++;
				break;
			case 4:
				jueves++;
				break;
			case 5:
				viernes++;
				break;
			case 6:
				sabado++;
				break;
			}
		}

		//configuramos y creamos la grafica: 

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(lunes, "tuits", "lunes");
		dataset.setValue(martes, "tuits", "martes");
		dataset.setValue(miercoles, "tuits", "miercoles");
		dataset.setValue(jueves, "tuits", "jueves");
		dataset.setValue(viernes, "tuits", "viernes");
		dataset.setValue(sabado, "tuits", "sabado");
		dataset.setValue(domingo, "tuits", "domingo");


		JFreeChart chart = ChartFactory.createBarChart3D("Cantidad total de tuits por dia","dias de la semana", "Tweets",	dataset, PlotOrientation.VERTICAL, true, true, false);
		//obtenemos el splash del fondo de JeyTuiter
		BufferedImage img = ImageIO.read(new File("src/res/images/Splash.png"));
		chart.setBackgroundImage(img);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundImage(img);
		BarRenderer render = (BarRenderer)plot.getRenderer();
		render.setSeriesPaint(0, Color.darkGray);
		render.setSeriesPaint(1, Color.RED);
		chart.setBackgroundPaint(Color.WHITE);
		try{
			ChartUtilities.saveChartAsPNG(new File(ruta+"/barras.png"), chart, 1000, 800);
		}
		catch(IOException e){
			System.out.println("Error al crear el archivo");
			Util.showError(null, "Error :(", "No se ha podido crear el grafico en la ruta especificada", "Cancelar", "Aceptar");
		}
		ChartFrame frame = new ChartFrame("Gráfico de Barras", chart);
		frame.pack();
		frame.setVisible(true);
	}	

	//metodo para sumar dias de una fecha
	public static Date sumarRestarDiasFecha(Date fecha, int dias){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
		return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	}

	//metodo para comparar dos fechas
	public static  boolean compara(Date fecha1, Date fecha2){
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(fecha1); // Configuramos la fecha que se recibe
		calendar2.setTime(fecha2); // Configuramos la fecha que se recibe
		boolean iguales = calendar1.equals(calendar2);
		return iguales;
	}
}


