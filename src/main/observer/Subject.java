package observer;

import model.Plane;
import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<AirportObserver> observers = new ArrayList<>();

    public void addObserver(AirportObserver airportObserver) {
        if (!observers.contains(airportObserver)) {
            observers.add(airportObserver);
        }
    }

    public void notifyObservers(Plane p) {
        for (AirportObserver observer : observers) {
            observer.update(p);
        }
    }
}
