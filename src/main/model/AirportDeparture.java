package model;

import observer.Subject;
import java.util.HashMap;
import java.util.Map;

public class AirportDeparture extends Subject {

    public Map<Integer, Plane> departures;

    public AirportDeparture() {

        departures = new HashMap<>();
        for (int i = 0; i <= 23; i++) {
            departures.put(i, null);
        }
    }

    public Plane getPlane(int departureTime) {
        return departures.get(departureTime);
    }

    // EFFECTS: prints out all the departures.  If the time has not been scheduled, prints "available"
    public boolean printDepartures() {
        for (int i = 5; i < departures.size(); i++) {
            Plane c = departures.get(i);
            if (c != null) {
                System.out.print(i + "hrs: ");
                c.printName();
            } else {
                System.out.print(i + "hrs: ");
                System.out.println(" available ");
            }
        }
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

    // MODIFIES: this and Plane
    // EFFECTS: books the plane into the requested departure slot if it is available,
    //          and lets the plane know the departure time.

    public boolean makeRegDeparture(Plane c, int departureTime) {
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
        addObserver(c);
        notifyObservers(c);
        return true;
    }

    // MODIFIES: this and Plane
    // EFFECTS: books the plane into the requested departure slot regardless of availability,
    //          and lets the plane know the departure time.
    public boolean makeUrgDeparture(Plane c, int departureTime) {
        if (outsideTime(departureTime)) {
            return false;
        }
        System.out.println("Flight " + c.getName() + " is departing at at " + departureTime);
        departures.remove(departureTime);
        departures.put(departureTime, c);
        c.setDepartureTime(departureTime);
        addObserver(c);
        notifyObservers(c);
        return true;
    }
}