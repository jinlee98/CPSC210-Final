package model;

public class RegularDeparture extends AirportDeparture {

    @Override
    public boolean makeNewDeparture(Plane c, int departureTime) {
        if (outsideTime(departureTime)) {
            return false;
        }

        if (departures.get(departureTime) != null) {
            System.out.println("That departure time is already booked.");
            System.out.println("Sorry for the inconvenience! Please pick another time.");
            return false;
        }
        System.out.println("Flight " + c.getName() + " is departing at at " + departureTime);
        departures.put(departureTime, c);
        c.setDepartureTime(departureTime);
        return true;
    }
}
