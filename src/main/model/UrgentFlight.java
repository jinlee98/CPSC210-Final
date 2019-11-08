package model;

public class UrgentFlight extends AirportDeparture {

    @Override
    public boolean makeNewDeparture(Plane c, int departureTime) {
        if (outsideTime(departureTime)) {
            return false;
        }
        System.out.println("Flight " + c.getName() + " is departing at at " + departureTime);
        departures.remove(departureTime);
        departures.put(departureTime, c);
        c.setDepartureTime(departureTime);
        return true;
    }
}