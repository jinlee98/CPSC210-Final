package model;

import java.util.HashMap;
import java.util.Map;

public class AirportPrinter {
    public Map<Integer, Plane> departures;

    public AirportPrinter() {
        departures = new HashMap<>();
        for (int i = 0; i <= 23; i++) {
            departures.put(i, null);
        }
    }

    // EFFECTS: prints out all the departures.  If the time has not been scheduled, prints "available"
    public boolean printDepartures() {
        for (int i = 5; i < departures.size(); i++) {
            Plane c = departures.get(i);
            if (c != null) {
                System.out.print(i + "hrs: ");
                c.printName();
            } else {
                System.out.print(i + "hrs: ");
                System.out.println(" available ");
            }
        }
        return true;
    }
}
