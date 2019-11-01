package model;

import exceptions.OutsideTimeRangeException;

public class UrgentFlight extends Airport {

    @Override
    public boolean makeNewDeparture(Plane c, int departureTime) {
        if (departureTime >= departures.size()) {
            try {
                throw new exceptions.OutsideTimeRangeException();
            } catch (OutsideTimeRangeException e) {
                System.out.println("That departure time is outside allotted time slots.");
                return false;
            }
        }
        System.out.println("Flight " + c.getName() + " is departing at at " + departureTime);
        departures.remove(departureTime);
        departures.put(departureTime, c);
        c.setDepartureTime(departureTime);
        return true;
    }
}