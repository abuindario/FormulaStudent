package es.darioabuin.control;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.darioabuin.util.CarComparator;

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
	
	public void startRace(Race race) {
		DecimalFormat dfor = new DecimalFormat("##.00");
		DecimalFormat dforsec = new DecimalFormat("00");
		do {
			int finished = 0;
			for (Car c : race.getCarList()) {
				if (!c.isFinished()) {
					c.run(race.getTotalLength());
					System.out
							.println(c.getModel() + " " + c.getSpeedometer() + "km/h " + c.getDistance() + "m");
				} else {
					int minutes = (int) (c.getTotalRaceTime() / 60);
					double seconds = (c.getTotalRaceTime() % 60);
					System.out.println(
							c.getModel() + " finished the race in " + dforsec.format(minutes) + ":" + dforsec.format(seconds) + "; average: "
									+ dfor.format((race.getTotalLength() / c.getTotalRaceTime() / 1000 * 3600))
									+ "km/h");
					finished += 1;
				}
			}
			if (finished == race.getCarList().size()) {
				race.setTerminatedRace(true);
			}
			System.out.println("\n");
		} while (!race.isTerminatedRace());
	}
	
	public void givePoints(Race race) {
		Collections.sort(race.getCarList(), new CarComparator());
		System.out.println("Standings: ");
		int carScore = 5;
		int garageScore = 20;
		for (Car c : race.getCarList()) {
			if (carScore > 0) {
				int newScore = c.getScore() + carScore;
				c.setScore(newScore);
				System.out.println(c.getBrand() + " " + c.getModel() + " wins " + carScore + " points, total points: "
						+ c.getScore());
				carScore -= 2;
			}
			if (garageScore > 0) {
				int newGarageScore = c.getGarage().getScore() + garageScore;
				c.getGarage().setScore(newGarageScore);
				garageScore -= 10;
			}
		}
	}
}
