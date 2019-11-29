package observer;

import model.Plane;

public interface AirportObserver {

    //EFFECTS: updates the observers
    void update(Plane p);
}
