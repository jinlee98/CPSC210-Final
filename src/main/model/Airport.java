package model;

import java.util.HashMap;
import java.util.Map;

public abstract class Airport implements BookingService, Printer {

    public Map<Integer, Plane> departures;

    public Airport() {

        departures = new HashMap<>();
        for (int i = 0; i <= 23; i++) {
            departures.put(i, null);
        }
    }

    // MODIFIES: this and Plane
    // EFFECTS: books the plane into the requested departure slot if it is available,
    //          and lets the plane know the departure time.

    public abstract boolean makeNewDeparture(Plane c, int departureTime);

    // EFFECTS: prints out all the departures.  If the time has not been scheduled, prints "available"
    public void print() {
        for (int i = 5; i < departures.size(); i++) {
            Plane c = departures.get(i);
            if (c != null) {
                System.out.print(i + "hrs: ");
                c.print();
            } else {
                System.out.print(i + "hrs: ");
                System.out.println(" available ");
            }
        }
    }

    //EFFECTS: returns true if a Plane is found at the departure time.
    public boolean verifyDeparture(Plane p, int departureTime) {
        Plane scheduledPlane = departures.get(departureTime);
        if (scheduledPlane == null) {
            System.out.println("There is no plane departing at that time");
            return false;
        }
        if (scheduledPlane.getName().equals(p.getName())) {
            System.out.println("Yes the plane is departing at that time");
            return true;
        }
        return false;
    }

    //EFFECTS: returns true if the plane is scheduled at the departure time
    public boolean confirmScheduledPlane(String planeName, int bookingTime) {
        if (departures.get(bookingTime) != null) {
            Plane scheduledPlane = departures.get(bookingTime);
            String scheduledPlaneName = scheduledPlane.getName();
            return scheduledPlaneName.equals(planeName);
        }
        return false;
    }

}