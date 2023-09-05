package es.darioabuin.control;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
	private String tournamentName;
	private List<Race> tournamentRaces = new ArrayList<Race>();
	private List<Garage> tournamentGarages = new ArrayList<Garage>();
	
	public Tournament(String name) {
		this.tournamentName = name;
	}

	public String getTournamentName() {
		return tournamentName;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public List<Race> getTournamentRaces() {
		return tournamentRaces;
	}

	public void setTournamentRaces(List<Race> tournamentRaces) {
		this.tournamentRaces = tournamentRaces;
	}

	public List<Garage> getTournamentGarages() {
		return tournamentGarages;
	}

	public void setTournamentGarages(List<Garage> tournamentGarages) {
		this.tournamentGarages = tournamentGarages;
	}
	
}
