package model;

public interface ControlTower {
    // getters
    String getName();

    int getDepartureTime();

    // setters
    void setTime(int time);

    // EFFECTS: returns the departureTime of this plane
    int confirmDeparture();
}
