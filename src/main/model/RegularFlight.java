package model;

import exceptions.AlreadyBookedException;

public class RegularFlight extends Airport {

    @Override
    public boolean makeNewDeparture(Plane p, int departureTime) {
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
                System.out.println("Please try again.");
            }
        }
        System.out.println("Flight " + p.getName() + " is departing at at " + departureTime);
        departures.set(departureTime, p);
        p.setTime(departureTime);
        return true;
    }
}

