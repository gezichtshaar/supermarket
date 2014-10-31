package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import org.nhl.supermarket.Supermarket;

public class MainController {
	private Supermarket supermarket;
	private List<Observer> viewsList;

	public MainController(Supermarket supermarket) {
		this.supermarket = supermarket;
		this.viewsList = new ArrayList<Observer>();
	}

	public void addView(Observer view) {
		supermarket.addObserver(view);
		this.viewsList.add(view);
	}

	public String getStats() {
		return supermarket.toString();
	}
	
	public boolean isPaused() {
		return supermarket.isPaused();
	}
}
