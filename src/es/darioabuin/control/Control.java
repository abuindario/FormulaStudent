package es.darioabuin.control;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import es.darioabuin.util.CarComparator;

public class Control {
	public List<Garage> garages = new ArrayList<Garage>();
	public List<Race> races = new ArrayList<Race>();

	public static void main(String[] args) {
		Control control = new Control();

		// Garage creation
		Garage garage1 = new Garage("Blue Bull");
		Garage garage2 = new Garage("Bitter Kos");

		// Add garages to garage's List
		control.garages.add(garage1);
		control.garages.add(garage2);

		// Car creation
		Car car1 = new Car("McLoren", "Alpha");
		Car car2 = new Car("McLoren", "Beta");
		Car car3 = new Car("Ferraro", "Gamma");
		Car car4 = new Car("Ferraro", "Gemma");

		// Register cars in their garage
		garage1.registerCarInGarage(car1, garage1);
		garage1.registerCarInGarage(car2, garage1);
		garage2.registerCarInGarage(car3, garage2);
		garage2.registerCarInGarage(car4, garage2);

		// Race creation: name + length of each lap + number of laps
		Race race1 = new Race("Race 1", 2000, 3);
		Race race2 = new Race("Race 2", 1500, 4);

		// Add races to race's list
		control.races.add(race1);
		control.races.add(race2);

		// Register garages and cars in race
		race1.registerGarageInRace(garage1);
		race1.registerGarageInRace(garage2);
		race1.registerCarInRace(car1);
		race1.registerCarInRace(car2);
		race1.registerCarInRace(car3);
		race1.registerCarInRace(car4);

		control.start();
	}

	public void start() {
		int opt = 99;
		do {
			System.out.println("\n==================");
			System.out.println("== Formula Student ==");
			System.out.println("==================");
			System.out.println("1. Manage garages");
			System.out.println("2. Manage races");
			System.out.println("3. Manage tournaments");
			System.out.println("0. Exit");
			Scanner scan = new Scanner(System.in);
			try {
				opt = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter a number");
			}
			switch (opt) {
			case 0:
				System.out.println("Exit");
				break;
			case 1:
				this.garageMenu();
				break;
			case 2:
				this.raceMenu();
				break;
			case 3:
				// this.tournamentMenu();
				break;
			case 99:
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
		} while (opt != 0);
	}

	private void garageMenu() {
		int opt = 99;
		do {
			System.out.println("\n==========================");
			System.out.println("== Manage garages ==");
			System.out.println("==========================");
			System.out.println("1. Get garages");
			System.out.println("2. Garage details");
			System.out.println("3. Add garage");
			System.out.println("4. Edit garage");
			System.out.println("5. Remove garage");
			System.out.println("0. Go back");
			Scanner scan = new Scanner(System.in);
			try {
				opt = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter a number");
			}
			switch (opt) {
			case 0:
				break;
			case 1:
				this.listGarage();
				break;
			case 2:
				this.showGarageDetails();
				break;
			case 3:
				this.addGarage();
				break;
			case 4:
				this.menuEditGarage();
				break;
			case 5:
				this.removeGarage();
				break;
			case 99:
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
		} while (opt != 0);
	}

	private void listGarage() {
		System.out.println("Garages: ");
		int i = 1;
		for (Garage g : garages) {
			System.out.println(i + ". " + g.getGarageName());
			i++;
		}
	}

	private void showGarageDetails() {
		listGarage();
		Scanner scan = new Scanner(System.in);
		int select = 0;
		try {
			select = scan.nextInt();
			Garage g = null;
			try {
				g = garages.get(select - 1);
				System.out.println(g.getGarageName() + ":");
				int i = 1;
				for (Car c : g.getCarList()) {
					System.out.println(i + ". " + c.getBrand() + " " + c.getModel());
					i++;
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void addGarage() {
		System.out.println("Write the garage's name: ");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		Garage garage = new Garage(name);
		garages.add(garage);
	}

	private void menuEditGarage() {
		listGarage();
		Scanner scan = new Scanner(System.in);
		int select = 0;
		try {
			select = scan.nextInt();
			try {
				Garage garage = garages.get(select - 1);
				int opt = 99;
				do {
					System.out.println("\n=========================");
					System.out.println("== Edit garage \"" + garage.getGarageName() + "\" ==");
					System.out.println("=========================");
					System.out.println("1. Edit garage's name");
					System.out.println("2. Add car to garage");
					System.out.println("3. Remove car from garage");
					System.out.println("0. Go back");
					opt = scan.nextInt();
					switch (opt) {
					case 1:
						this.renameGarage(garage);
						break;
					case 2:
						this.addCarToGarage(garage);
						break;
					case 3:
						this.removeCarFromGarage(garage);
						break;
					case 99:
						break;
					default:
						break;
					}
				} while (opt != 0);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void renameGarage(Garage garage) {
		System.out.println("Enter new garage's name:");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		garage.setGarageName(name);
	}

	private void addCarToGarage(Garage garage) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter car brand:");
		String brand = scan.nextLine();
		System.out.println("Enter car model:");
		String model = scan.nextLine();
		Car car = new Car(brand, model);
		garage.registerCarInGarage(car, garage);
	}

	private void removeCarFromGarage(Garage garage) {
		Scanner scan = new Scanner(System.in);
		int i = 1;
		for (Car c : garage.getCarList()) {
			System.out.println(i + ". " + c.getBrand() + " " + c.getModel());
			i++;
		}
		int select = scan.nextInt();
		garage.getCarList().remove(select - 1);
	}

	private void removeGarage() {
		System.out.println("Remove garage: ");
		listGarage();
		Scanner scan = new Scanner(System.in);
		int select = scan.nextInt();
		garages.remove(select - 1);
	}

	private void raceMenu() {
		int opt = 99;
		do {
			System.out.println("\n========================");
			System.out.println("== Manage races ==");
			System.out.println("========================");
			System.out.println("1. Get races");
			System.out.println("2. Show race details");
			System.out.println("3. Start race");
			System.out.println("4. Add race");
			System.out.println("5. Edit race");
			System.out.println("6. Remove race");
			System.out.println("0. Go back");
			try {
				Scanner scan = new Scanner(System.in);
				opt = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("You must enter a number");
			}
			switch (opt) {
			case 0:
				break;
			case 1:
				this.listRaces();
				break;
			case 2:
				this.showRaceDetails();
				break;
			case 3:
				this.startRace();
				break;
			case 4:
				this.addRace();
				break;
			case 5:
				this.editRace();
				break;
			case 6:
				this.removeRace();
				break;
			case 99:
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
		} while (opt != 0);
	}

	private void listRaces() {
		int i = 1;
		for (Race r : races) {
			System.out.println(i + ". " + r.getRaceName() + ", Length: " + r.getLapLength() + ", Laps: " + r.getLaps());
			i++;
		}
	}

	private void showRaceDetails() {
		System.out.println("Select one option: ");
		listRaces();
		Scanner scan = new Scanner(System.in);
		int select = 0;
		try {
			select = scan.nextInt();
			try {
				Race race = races.get(select - 1);
				int i = 1;
				int j = 1;
				for (Garage g : race.getGarageList()) {
					System.out.println("Garage " + i + ". " + g.getGarageName() + "\n\tCars of the garage: ");
					i++;
					for (Car c : g.getCarList()) {
						System.out.println("\t" + j + ". " + c.getBrand() + " " + c.getModel());
						j++;
					}
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void startRace() {
		listRaces();
		System.out.println("Select a race: ");
		Scanner scan = new Scanner(System.in);
		DecimalFormat dfor = new DecimalFormat("#.00");
		try {
			int select = scan.nextInt();
			try {
				Race race = races.get(select - 1);
				race.resetRace(race);
				do {
					int finished = 0;
					for (Car c : race.getCarList()) {
						if (!c.isFinished()) {
							c.run(race.getTotalLength());
							System.out
									.println(c.getModel() + " " + c.getSpeedometer() + "km/h " + c.getDistance() + "m");
						} else {
							int minutes = (int) (c.getTotalRaceTime() / 60);
							int seconds = (int) (c.getTotalRaceTime() % 60);
							System.out.println(
									c.getModel() + " finished the race in " + minutes + ":" + seconds + "; average: "
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

				// Give points to the cars and garages
				Collections.sort(race.getCarList(), new CarComparator());
				System.out.println("Standings: ");
				int carScore = 5;
				int garageScore = 20;
				for (Car c : race.getCarList()) {
					if (carScore > 0) {
						int newScore = c.getScore() + carScore;
						c.setScore(newScore);
						System.out.println(c.getBrand() + " " + c.getModel() + " wins " + carScore
								+ " points, total points: " + c.getScore());
						carScore -= 2;
					}
					if (garageScore > 0) {
						int newGarageScore = c.getGarage().getScore() + garageScore;
						c.getGarage().setScore(newGarageScore);
						garageScore -= 10;
					}
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void addRace() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter race name: ");
		String name = scan.nextLine();
		try {
			System.out.println("Enter lap length (meters): ");
			int lapLength = scan.nextInt();
			System.out.println("Enter number of laps: ");
			int laps = scan.nextInt();
			Race newRace = new Race(name, lapLength, laps);
			races.add(newRace);
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void editRace() {
		listRaces();
		Race race = null;
		Scanner scan = new Scanner(System.in);
		try {
			int select = scan.nextInt();
			try {
				race = races.get(select - 1);
				int opt = 99;
				do {
					System.out.println("\n=======================");
					System.out.println("== Edit race \"" + race.getRaceName() + "\" ==");
					System.out.println("=======================");
					System.out.println("1. Modify race name");
					System.out.println("2. Modify lap length");
					System.out.println("3. Modify number of laps");
					System.out.println("4. Add garage to race");
					System.out.println("5. Remove garage from race");
					System.out.println("6. Show race details");
					System.out.println("0. Volver");
					opt = scan.nextInt();
					switch (opt) {
					case 0:
						break;
					case 1:
						this.renameRace(race);
						break;
					case 2:
						this.modifyLapLength(race);
						break;
					case 3:
						this.modifyLaps(race);
						break;
					case 4:
						this.addGarageToRace(race);
						break;
					case 5:
						this.removeGarageFromRace(race);
						break;
					case 6:
						this.showRaceDetails(race);
					case 99:
						break;
					default:
						break;
					}

				} while (opt != 0);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void renameRace(Race race) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter new race's name:");
		String name = scan.nextLine();
		race.setRaceName(name);
	}

	private void modifyLapLength(Race race) {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter new lap length: ");
			int lapLength = scan.nextInt();
			race.setLapLength(lapLength);
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void modifyLaps(Race race) {
		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter new number of laps: ");
			int laps = scan.nextInt();
			race.setLaps(laps);
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void addGarageToRace(Race race) {
		listGarage();
		try {
			try {
				Scanner scan = new Scanner(System.in);
				int select = scan.nextInt();
				Garage garage = garages.get(select - 1);
				boolean isIn = false;
				for (Garage g : race.getGarageList()) {
					String name = g.getGarageName();
					if (name.equals(garage.getGarageName())) {
						isIn = true;
					}
				}
				if (!isIn) {
					race.getGarageList().add(garage);
					for (Car c : garage.getCarList()) {
						race.getCarList().add(c);
					}
				} else {
					System.out.println("Garage is already in the race");
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void removeGarageFromRace(Race race) {
		listGarage();
		try {
			try {
				Scanner scan = new Scanner(System.in);
				int select = scan.nextInt();
				Garage garage = garages.get(select - 1);
				race.getGarageList().remove(garage);
				for (Car c : garage.getCarList()) {
					race.getCarList().remove(c);
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void showRaceDetails(Race race) {
		System.out.println(race.getRaceName() + " - Lap length: " + race.getLapLength() + " - Laps: " + race.getLaps()
				+ "\nGarages:");
		int i = 1;
		int j = 1;
		for (Garage g : race.getGarageList()) {
			System.out.println(i + ". " + g.getGarageName() + "\n\tCars of the garage: ");
			i++;
			for (Car c : g.getCarList()) {
				System.out.println("\t" + j + ". " + c.getBrand() + " " + c.getModel());
				j++;
			}
		}
	}

	private void removeRace() {
		listRaces();
		Scanner scan = new Scanner(System.in);
		try {
			int select = scan.nextInt();
			try {
				races.remove(select - 1);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}
}
