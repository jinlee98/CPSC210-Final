package model;

public interface ControlTower {
    // getters
    String getName();

    Airport getAirport();

    // setters
    void setDepartureTime(int time);

    void setAirport(Airport a);

    // EFFECTS: returns the departureTime of this plane
    int confirmDeparture();
}
