package model;

import java.util.Objects;

public class Plane implements PlaneControl {

    private String name;
    private int departureTime;
    private AirportDeparture airportDeparture;

    public Plane(String name, AirportDeparture airportDeparture) {
        System.out.println("Scheduling a new Plane called " + name);
        this.name = name;
        this.airportDeparture = airportDeparture;
    }

    // getters
    @Override
    public String getName() {
        return name;
    }

    @Override
    public AirportDeparture getAirportDeparture() {
        return airportDeparture;
    }

    // setters
    @Override
    public void setDepartureTime(int time) {
        departureTime = time;
    }

    @Override
    public void setAirportDeparture(AirportDeparture a) {
        airportDeparture = a;
    }

    // EFFECTS: prints out the name of this plane on the console
    public boolean printName() {
        System.out.println(" " + this.getName() + " ");
        return true;
    }

    // EFFECTS: returns the departureTime of this plane
    @Override
    public int confirmDeparture() {
        System.out.println(name + ": Confirming that we are departing at " + departureTime);
        return departureTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Plane plane = (Plane) o;
        return departureTime == plane.departureTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureTime);
    }
}