package model;

public interface BookingService {

    // MODIFIES: this and Plane
    // EFFECTS: books the plane into the requested departure slot if it is available,
    //          and lets the plane know the departure time.
    boolean makeNewDeparture(Plane c, int departureTime);

    //EFFECTS: returns true if a Plane is found at the departure time.
    boolean verifyDeparture(Plane p, int departureTime);

    //EFFECTS: returns true if the plane is scheduled at the departure time
    boolean confirmScheduledPlane(String planeName, int bookingTime);

    void print();
}
