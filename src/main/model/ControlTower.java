package model;

public interface ControlTower {
    // getters
    String getName();

    int getDepartureTime();

    // setters
    void setDepartureTime(int time);

    // EFFECTS: returns the departureTime of this plane
    void confirmDeparture();
}
