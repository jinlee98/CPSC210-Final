package model;

import java.util.ArrayList;

public abstract class Airport implements BookingService, Printer {

    public ArrayList<Plane> departuresList;

    public Airport() {

        departuresList = new ArrayList<>();
        for (int i = 0; i <= 23; i++) {
            departuresList.add(i, null);
        }
    }

    // MODIFIES: this and Plane
    // EFFECTS: books the plane into the requested departure slot if it is available,
    //          and lets the plane know the departure time.

    public abstract boolean makeNewDeparture(Plane c, int departureTime);

    // EFFECTS: prints out all the departures.  If the time has not been scheduled, prints "available"
    public void print() {
        for (int i = 5; i < departuresList.size(); i++) {
            Plane c = departuresList.get(i);
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
        Plane scheduledPlane = departuresList.get(departureTime);
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
        if (departuresList.get(bookingTime) != null) {
            Plane scheduledPlane = departuresList.get(bookingTime);
            String scheduledPlaneName = scheduledPlane.getName();
            boolean isPlaneScheduled = scheduledPlaneName.equals(planeName);
            return isPlaneScheduled;
        }
        return false;
    }

    //MODIFIES: this and Plane
    //EFFECTS:  changes the plane scheduled in the list, and let's the Plane know the new departure time.
    public void changeDeparture(Plane plane, int newTime) {
        int scheduledTime = plane.getDepartureTime();
        System.out.print(plane.getName() + "'s time is changing from " + scheduledTime);
        System.out.println(" to " + newTime);
        departures.set(scheduledTime, null);
        departures.set(newTime, plane);
        plane.setDepartureTime(newTime);
    }

}