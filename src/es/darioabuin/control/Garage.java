package es.darioabuin.control;

import java.util.ArrayList;
import java.util.List;

public class Garage {
	private String garageName;
	private List<Car> carList = new ArrayList<Car>();
	
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

	public void registerCarInGarage(Car c) {
		c.registerGarage(this.garageName);
		this.carList.add(c);
	}
	
	
}
