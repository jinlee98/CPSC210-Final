package airport;

public interface BookingService {

    // MODIFIES: this and Plane
    // EFFECTS: books the plane into the requested departure slot if it is available,
    //          and lets the plane know the departure time.
    boolean makeNewDeparture(Plane c, int departureTime);

    //EFFECTS: returns true if a Plane is found at the departure time.
    boolean verifyDeparture(Plane p, int departureTime);

    //EFFECTS: returns true if the plane is scheduled at the departure time
    boolean confirmScheduledPlane(String planeName, int bookingTime);

    //MODIFIES: this and Plane
    //EFFECTS:  changes the plane scheduled in the list, and let's the Plane know the new departure time.
    void changeDeparture(Plane plane, int newTime);

    void print();
}
