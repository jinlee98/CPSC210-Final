package model;

public interface PlaneControl {
    // getters
    String getName();

    AirportDeparture getAirportDeparture();

    // setters
    void setDepartureTime(int time);

    void setAirportDeparture(AirportDeparture a);

    // EFFECTS: returns the departureTime of this plane
    int confirmDeparture();
}
