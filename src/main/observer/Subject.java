package observer;

import model.Plane;
import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<AirportObserver> observers = new ArrayList<>();

    //MODIFIES: this
    //EFFECTS: adds observer to the list of observers if they're not already in the list
    public void addObserver(AirportObserver airportObserver) {
        if (!observers.contains(airportObserver)) {
            observers.add(airportObserver);
        }
    }

    //MODIFIES: AirportObserver(s)
    //EFFECTS: notifies observers and updates them
    public void notifyObservers(Plane p) {
        for (AirportObserver observer : observers) {
            observer.update(p);
        }
    }
}
