package model;

public class Plane implements Printer {

    private String name;
    private int departureTime;

    public Plane(String name) {
        System.out.println("Scheduling a new Plane called " + name);
        this.name = name;
        this.departureTime = 0;
    }

    // getters
    public String getName() {
        return name;
    }

    // EFFECTS: returns the departureTime of this plane
    @Override
    public int confirmDeparture() {
        System.out.println(name + ": Confirming that we are departing at " + departureTime);
        return departureTime;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    @Override
    public int getDepartureTime() {
        return departureTime;
    }

    // setters
    public void setDepartureTime(int time) {
        departureTime = time;
    }

    // EFFECTS: prints out the name of this plane on the console
    @Override
    public void print() {
        System.out.println(" " + name + " ");
    }

}