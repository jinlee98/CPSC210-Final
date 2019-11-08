package model;

import java.util.HashMap;
import java.util.Map;

public abstract class AirportDeparture implements BookingService {

    public Map<Integer, Plane> departures;
    AirportPrinter printer = new AirportPrinter();

    public AirportDeparture() {

        departures = new HashMap<>();
        for (int i = 0; i <= 23; i++) {
            departures.put(i, null);
        }
    }

    // MODIFIES: this and Plane
    // EFFECTS: books the plane into the requested departure slot if it is available,
    //          and lets the plane know the departure time.

    public abstract boolean makeNewDeparture(Plane c, int departureTime);

    public Plane getPlane(int departureTime) {
        return departures.get(departureTime);
    }

    // EFFECTS: prints out all the departures.  If the time has not been scheduled, prints "available"
    public boolean printDepartures() {
        printer.printDepartures();
        return true;
    }

    //EFFECTS: returns true if the Plane is found at the departure time.
    public boolean verifyDeparture(Plane p, int departureTime) {
        if (ifNotBooked(departureTime)) {
            return false;
        }
        return ifBooked(p, departureTime);
    }

    private boolean ifBooked(Plane p, int departureTime) {
        if (departures.get(departureTime) == p) {
            System.out.println("Yes the plane is departing at that time");
            return true;
        }
        return false;
    }

    private boolean ifNotBooked(int departureTime) {
        if (departures.get(departureTime) == null) {
            System.out.println("There is no plane departing at that time");
            return true;
        }
        return false;
    }

    public void removeDeparture(int bookingTime) {
        departures.remove(bookingTime);
    }

    public boolean findFlight(Plane p) {
        return departures.containsValue(p);
    }

    public boolean outsideTime(int departureTime) {
        if (departureTime < 0 || departureTime > 23) {
            System.out.println("That departure time is outside allotted time slots.");
            return true;
        }
        return false;
    }
}