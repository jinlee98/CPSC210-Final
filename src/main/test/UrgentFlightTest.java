package test;

import model.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UrgentFlightTest extends FlightTest {
    model.Airport yvrUrgent;
    Plane delta;

    @BeforeEach
    public void setUp() {
        yvr = new model.RegularFlight();
        yvrUrgent = new model.UrgentFlight();
        boeing = new Plane("Boeing");
        delta = new Plane("delta");
    }


    @Test
    public void testUrgentOverRegular() {
        yvr.makeNewDeparture(delta, 15);
        assertTrue(yvrUrgent.makeNewDeparture(boeing, 15));
        assertTrue(yvrUrgent.verifyDeparture(boeing, 15));
    }
}