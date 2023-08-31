package es.darioabuin.util;

import es.darioabuin.control.Car;

public class CarComparator implements java.util.Comparator<Car> {

	@Override
	public int compare(Car o1, Car o2) {
		double time1 = o1.getTotalRaceTime();
		double time2 = o2.getTotalRaceTime();

		return (int) (time1 - time2);
	}

}
