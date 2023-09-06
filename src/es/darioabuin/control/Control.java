package es.darioabuin.control;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Control {
	public List<Garage> garages = new ArrayList<Garage>();
	public List<Race> races = new ArrayList<Race>();
	public List<Tournament> tournaments = new ArrayList<Tournament>();

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
		Race race1 = new Race("Silverglass", 2000, 3);
		Race race2 = new Race("Circuit of Manzo", 1400, 4);

		// Add races to race's list
		control.races.add(race1);
		control.races.add(race2);

		// Create new tournament
		Tournament tour1 = new Tournament("Piston Cup");

		// Add tournament to tournament's list
		control.tournaments.add(tour1);

		// Add races to tournament
		tour1.getTournamentRaces().add(race1);
		tour1.getTournamentRaces().add(race2);

		// Add garages to tournament
		tour1.getTournamentGarages().add(garage1);
		tour1.getTournamentGarages().add(garage2);

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
				opt = 99;
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
				this.tournamentMenu();
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
				opt = 99;
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
		try {
			int select = scan.nextInt();
			try {
			removeCarFromRace(garage.getCarList().get(select - 1));
			garage.getCarList().remove(select - 1);
			} catch (IndexOutOfBoundsException e) { 
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void removeGarage() {
		System.out.println("Remove garage: ");
		listGarage();
		Scanner scan = new Scanner(System.in);
		try {
			int select = scan.nextInt();
			try {
				removeGarageFromRace(garages.get(select - 1));
				for (Tournament t : tournaments) {
					t.getTournamentGarages().remove(garages.get(select - 1));
				}
				garages.remove(select - 1);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
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
				opt = 99;
				System.out.println("You must enter a number");
			}
			switch (opt) {
			case 0:
				break;
			case 1:
				this.getRaces();
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

	private void getRaces() {
		int i = 1;
		for (Race r : races) {
			System.out.println(i + ". " + r.getRaceName() + ", Length: " + r.getLapLength() + ", Laps: " + r.getLaps());
			i++;
		}
	}

	private void showRaceDetails() {
		System.out.println("Select an option: ");
		getRaces();
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
		getRaces();
		System.out.println("Select a race: ");
		Scanner scan = new Scanner(System.in);
		try {
			int select = scan.nextInt();
			try {
				Race race = races.get(select - 1);
				if (!race.getCarList().isEmpty()) {
					race.resetRace(race);
					race.startRace(race);
					race.givePoints(race);
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
		getRaces();
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
					System.out.println("0. Go back");
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

	private void removeGarageFromRace(Garage g) {
		for (Race r : races) {
			r.getGarageList().remove(g);
		}
		for (Car c : g.getCarList()) {
			removeCarFromRace(c);
		}
	}

	private void removeCarFromRace(Car c) {
		for (Race r : races) {
			r.getCarList().remove(c);
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
		getRaces();
		Scanner scan = new Scanner(System.in);
		try {
			int select = scan.nextInt();
			try {
				for (Tournament t : tournaments) {
					t.getTournamentRaces().remove(races.get(select - 1));
				}
				races.remove(select - 1);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void tournamentMenu() {
		int opt = 99;
		do {
			System.out.println("\n=======================");
			System.out.println("== Manage tournaments ==");
			System.out.println("=======================");
			System.out.println("1. Get tournaments");
			System.out.println("2. Show tournament details");
			System.out.println("3. Start tournament");
			System.out.println("4. Add tournament");
			System.out.println("5. Edit tournament");
			System.out.println("6. Remove tournament");
			System.out.println("0. Go back");
			Scanner scan = new Scanner(System.in);
			try {
				opt = scan.nextInt();
			} catch (InputMismatchException e) {
				opt = 99;
				System.out.println("You must enter a number");
			}
			switch (opt) {
			case 0:
				break;
			case 1:
				this.getTournaments();
				break;
			case 2:
				this.showTournamentDetails();
				break;
			case 3:
				this.startTournament();
				break;
			case 4:
				this.addTournament();
				break;
			case 5:
				this.editTournament();
				break;
			case 6:
				this.removeTournament();
				break;
			case 99:
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
		} while (opt != 0);
	}

	private void getTournaments() {
		int i = 1;
		for (Tournament t : tournaments) {
			System.out.println(i + ". " + t.getTournamentName());
			i++;
		}
	}

	private void showTournamentDetails() {
		getTournaments();
		Scanner scan = new Scanner(System.in);
		Tournament t = null;
		try {
			int select = scan.nextInt();
			try {
				t = tournaments.get(select - 1);
				System.out.println(t.getTournamentName() + ": \n");
				int i = 1;
				if (!t.getTournamentRaces().isEmpty()) {
					System.out.println("Races of the tournament: ");
					for (Race r : t.getTournamentRaces()) {
						System.out
								.println("\t" + i + ". '" + r.getRaceName() + "', Length:" + r.getTotalLength() + "m.");
						i++;
					}
				}
				if (!t.getTournamentGarages().isEmpty()) {
					i = 1;
					System.out.println("Garages of the tournament: ");
					for (Garage g : t.getTournamentGarages()) {
						int j = 1;
						System.out.println("\t" + i + ". " + g.getGarageName());
						for (Car c : g.getCarList()) {
							System.out.println("\t\t" + j + ". " + c.getBrand() + " " + c.getModel());
							j++;
						}
						i++;
					}
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void startTournament() {
		getTournaments();
		Tournament t = null;
		Scanner scan = new Scanner(System.in);
		try {
			int select = scan.nextInt();
			try {
				t = tournaments.get(select - 1);
				// Remove all garages from races
				for (Garage g : garages) {
					this.removeGarageFromRace(g);
				}
				for (Race r : t.getTournamentRaces()) {
					// Add garages from the tournament to the race
					r.getGarageList().addAll(t.getTournamentGarages());
					// Add cars from the garages of the tournament to the race
					for (Garage g : r.getGarageList()) {
						r.getCarList().addAll(g.getCarList());
					}
					System.out.println("Start of race '" + r.getRaceName() + "' \n === * ===");
					if (!r.getCarList().isEmpty()) {
						r.resetRace(r);
						r.startRace(r);
						r.givePoints(r);
					}
					if ((t.getTournamentRaces().indexOf(r) + 1) < t.getTournamentRaces().size()) {
						System.out.println("\nPress any button and enter to continue with the tournament");
						scan.next();
					}
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		} finally {
			// Remove all garages and cars from races, so if we run later a single race
			// those garages and cars are not registered in the race
			for (Garage g : garages) {
				this.removeGarageFromRace(g);
			}
		}
	}

	private void addTournament() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter tournament's name: ");
		String name = scan.nextLine();
		Tournament tournament = new Tournament(name);
		tournaments.add(tournament);
	}

	private void editTournament() {
		Scanner scan = new Scanner(System.in);
		Tournament t = null;
		getTournaments();
		try {
			int select = scan.nextInt();
			try {
				t = tournaments.get(select - 1);
				int opt = 99;
				do {
					System.out.println("\n======================");
					System.out.println("== Manage tournament \"" + t.getTournamentName() + "\" ==");
					System.out.println("======================");
					System.out.println("1. Modify tournament name");
					System.out.println("2. Add race to tournament");
					System.out.println("3. Remove race from tournament");
					System.out.println("4. Add garage to tournament");
					System.out.println("5. Remove garage from tournament");
					System.out.println("0. Go back");
					opt = scan.nextInt();
					switch (opt) {
					case 0:
						break;
					case 1:
						this.editTournamentName(t);
						break;
					case 2:
						this.addRaceToTournament(t);
						break;
					case 3:
						this.removeRaceFromTournament(t);
						break;
					case 4:
						this.addGarageToTournament(t);
						break;
					case 5:
						this.removeGarageFromTournament(t);
						break;
					case 99:
						break;
					default:
						System.out.println("Invalid option");
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

	private void editTournamentName(Tournament tournament) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter new tournament name: ");
		String newName = scan.nextLine();
		tournament.setTournamentName(newName);
	}

	private void addRaceToTournament(Tournament tournament) {
		int i = 1;
		Scanner scan = new Scanner(System.in);
		System.out.println("Select one option: ");
		for (Race r : races) {
			System.out.println(i + ": " + r.getRaceName());
			i++;
		}
		try {
			int select = scan.nextInt();
			try {
				tournament.getTournamentRaces().add(races.get(select - 1));
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void removeRaceFromTournament(Tournament tournament) {
		int i = 1;
		Scanner scan = new Scanner(System.in);
		for (Race r : tournament.getTournamentRaces()) {
			System.out.println(i + ". " + r.getRaceName());
			i++;
		}
		try {
			int select = scan.nextInt();
			try {
				tournament.getTournamentRaces().remove(select - 1);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void addGarageToTournament(Tournament tournament) {
		int i = 1;
		Scanner scan = new Scanner(System.in);
		boolean isIn = false;
		System.out.println("Select one option: ");
		for (Garage g : garages) {
			System.out.println(i + ": " + g.getGarageName());
			i++;
		}
		try {
			int select = scan.nextInt();
			try {
				Garage garage = garages.get(select - 1);
				if (tournament.getTournamentGarages().isEmpty()) {
					tournament.getTournamentGarages().add(garage);
					isIn = true;
				} else {
					for (Garage g : tournament.getTournamentGarages()) {
						if (garage.getGarageName().equals(g.getGarageName())) {
							isIn = true;
							System.out.println("This garage is already in this tournament");
						} else {
						}
					}
				}
				if (!isIn) {
					tournament.getTournamentGarages().add(garage);
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void removeGarageFromTournament(Tournament tournament) {
		int i = 1;
		Scanner scan = new Scanner(System.in);
		for (Garage g : tournament.getTournamentGarages()) {
			System.out.println(i + ". " + g.getGarageName());
			i++;
		}
		try {
			int select = scan.nextInt();
			try {
				tournament.getTournamentGarages().remove(select - 1);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}

	private void removeTournament() {
		getTournaments();
		Scanner scan = new Scanner(System.in);
		try {
			int select = scan.nextInt();
			try {
				tournaments.remove(select - 1);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
			}
		} catch (InputMismatchException e) {
			System.out.println("You must enter a number");
		}
	}
}
