package model;

import java.util.Objects;

public class Plane implements ControlTower, Printer {

    private String name;
    private int departureTime;
    private Airport airport;

    public Plane(String name, Airport airport) {
        System.out.println("Scheduling a new Plane called " + name);
        this.name = name;
        this.airport = airport;
    }

    // getters
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Airport getAirport() {
        return airport;
    }

    // setters
    @Override
    public void setDepartureTime(int time) {
        departureTime = time;
    }

    @Override
    public void setAirport(Airport a) {
        airport = a;
    }

    // EFFECTS: prints out the name of this plane on the console
    @Override
    public void print() {
        System.out.println(" " + name + " ");
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