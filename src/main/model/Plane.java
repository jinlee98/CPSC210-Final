package model;

public class Plane implements ControlTower, Printer {

    private String name;
    private int departureTime;

    public Plane(String name) {
        System.out.println("Scheduling a new Plane called " + name);
        this.name = name;
        this.departureTime = 0;
    }

    // getters
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDepartureTime() {
        return departureTime;
    }

    // setters
    @Override
    public void setDepartureTime(int time) {
        departureTime = time;
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


}