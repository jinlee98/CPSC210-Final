package model;

public class UrgentFlight extends Airport {

    @Override
    public boolean makeNewDeparture(Plane c, int departureTime) {
        System.out.println("Flight " + c.getName() + " is departing at at " + departureTime);
        departuresList.set(departureTime, c);
        c.setDepartureTime(departureTime);
        return true;
    }
}
