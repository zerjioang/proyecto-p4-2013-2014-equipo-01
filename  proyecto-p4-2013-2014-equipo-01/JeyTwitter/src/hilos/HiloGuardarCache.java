package hilos;

import java.io.IOException;
import java.util.ArrayList;

import controller.GUIController;
import model.Tweet;

public class HiloGuardarCache extends Thread{
	
	private ArrayList<Tweet> timeline;
	
	public HiloGuardarCache(ArrayList<Tweet> timeline) {
		this.timeline = timeline;
	}

	public void run(){
		try {
			GUIController.getInstance().guardarCache(timeline);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
