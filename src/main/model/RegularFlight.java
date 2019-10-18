package model;

public class RegularFlight extends Airport {

    @Override
    public boolean makeNewDeparture(Plane c, int departureTime) {
        if (departureTime >= departures.size()) {
            System.out.println("That departure time is not available.");
            return true;
        }
        System.out.println("Flight " + c.getName() + " is departing at at " + departureTime);
        departures.set(departureTime, c);
        c.setDepartureTime(departureTime);
        return true;
    }
}

