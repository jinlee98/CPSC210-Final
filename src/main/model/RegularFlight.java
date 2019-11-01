package model;

import exceptions.AlreadyBookedException;

public class RegularFlight extends Airport {

    @Override
    public boolean makeNewDeparture(Plane c, int departureTime) {
        if (departureTime >= departures.size()) {
            System.out.println("Time not available");
            return false;
        }

        if (departures.get(departureTime) != null) {
            try {
                throw new AlreadyBookedException();
            } catch (AlreadyBookedException e) {
                System.out.println("That departure time is already booked.");
                return false;
            } finally {
                System.out.println("Sorry for the inconvenience! Please pick another time.");
            }
        }
        System.out.println("Flight " + c.getName() + " is departing at at " + departureTime);
        departures.put(departureTime, c);
        c.setDepartureTime(departureTime);
        return true;
    }
}
