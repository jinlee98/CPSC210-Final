package model;

import exceptions.OutsideTimeRangeException;

public class UrgentFlight extends Airport {

    @Override
    public boolean makeNewDeparture(Plane p, int departureTime) {
        if (departureTime >= departures.size()) {
            try {
                throw new exceptions.OutsideTimeRangeException();
            } catch (OutsideTimeRangeException e) {
                System.out.println("That departure time is outside allotted time slots.");
                return false;
            }
        }
        System.out.println("Flight " + p.getName() + " is departing at at " + departureTime);
        departures.set(departureTime, p);
        p.setDepartureTime(departureTime);
        return true;
    }
}
