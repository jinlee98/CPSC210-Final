package model;

public interface ControlTower {
    // getters
    String getName();

    // setters
    void setDepartureTime(int time);

    // EFFECTS: returns the departureTime of this plane
    int confirmDeparture();
}
