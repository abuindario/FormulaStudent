package es.darioabuin.control;

import java.util.ArrayList;
import java.util.List;

public class Race {
	private String raceName;
	private List<Car> carList = new ArrayList<Car>();
	private List<Garage> garageList = new ArrayList<Garage>();
	private int totalLength;
	private boolean terminatedRace = false;
	private int lapLength;
	private int laps;
	
	public Race(String raceName, int lapLength, int laps) {
		this.raceName = raceName;
		this.lapLength = lapLength;
		this.laps = laps;
		this.totalLength = lapLength * laps;
	}

	public String getRaceName() {
		return raceName;
	}

	public void setRaceName(String raceName) {
		this.raceName = raceName;
	}

	public List<Car> getCarList() {
		return carList;
	}

	public List<Garage> getGarageList() {
		return garageList;
	}

	public int getLapLength() {
		return lapLength;
	}

	public void setLapLength(int lapLength) {
		this.lapLength = lapLength;
	}
	
	public int getTotalLength() {
		return totalLength;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public void registerCarInRace(Car car) {
		this.carList.add(car);
	}

	public void registerGarageInRace(Garage garage) {
		this.garageList.add(garage);
	}

	public boolean isTerminatedRace() {
		return terminatedRace;
	}

	public void setTerminatedRace(boolean terminatedRace) {
		this.terminatedRace = terminatedRace;
	}

	public void resetRace(Race race) {
		race.setTerminatedRace(false);
		List<Car> carList = race.getCarList();
		for(Car c: carList) {
			c.setDistance(0);
			c.setSpeedometer(0);
			c.setFinished(false);
			c.setTotalRaceTime(0);
			c.setIteration(0);
		}
	}
}
