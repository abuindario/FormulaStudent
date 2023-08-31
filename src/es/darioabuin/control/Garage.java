package es.darioabuin.control;

import java.util.ArrayList;
import java.util.List;

public class Garage {
	private String garageName;
	private List<Car> carList = new ArrayList<Car>();
	private int score = 0;
	
	public Garage(String garageName) {
		this.garageName = garageName;
	}

	public String getGarageName() {
		return garageName;
	}
	
	public void setGarageName(String garageName) {
		this.garageName = garageName;
	}

	public List<Car> getCarList() {
		return carList;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void registerCarInGarage(Car c, Garage g) {
		c.registerGarage(g);
		this.carList.add(c);
	}
	
	
}
