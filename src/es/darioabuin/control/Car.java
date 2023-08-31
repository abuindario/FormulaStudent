package es.darioabuin.control;

public class Car {
	private String brand;
	private String model;
	private Garage garage;
	private int speedometer = 0;
	private int distance = 0;
	private double totalRaceTime = 0;
	private int iteration = 0;
	// Car status update time in the run() method
	private final int ITERATION_SECONDS = 20;
	private boolean finished = false;
	private final int MAX_SPEED = 200;
	private int score = 0;

	public Car(String brand, String model) {
		this.brand = brand;
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public void setIteration(int it) {
		this.iteration = it;
	}
	
	public int getSpeedometer() {
		return speedometer;
	}

	public void setSpeedometer(int speed) {
		this.speedometer = speed;
	}
	
	public double getTotalRaceTime() {
		return totalRaceTime;
	}

	public void setTotalRaceTime(double totalRaceTime) {
		this.totalRaceTime = totalRaceTime;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Garage getGarage() {
		return garage;
	}

	public void registerGarage(Garage garage) {
		this.garage = garage;
	}

	public void accelerate() {
		if (speedometer < MAX_SPEED) {
			this.speedometer += 10;
		}
	}

	public void brake() {
		if (speedometer > 0) {
			this.speedometer -= 10;
		}
	}
	
	public double run(int totalLength) {
		// The car accelerates or brakes randomly
		int random = (int) (Math.random()*6);
		if (random == 0) {
			brake();
		} else {
			accelerate();
		}
		int oldDistance = distance;
		distance += (int) (((double) speedometer*1000/3600) * ITERATION_SECONDS);
		iteration += 1;
		// Calculation of total race time
		if (distance > totalLength) {
			int pending = totalLength - oldDistance;
			double metsec = speedometer *1000/3600;
			this.totalRaceTime = (pending / metsec) + ((iteration - 1)* ITERATION_SECONDS);
			finished = true;
		}
		return this.totalRaceTime;
	}

}
