package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UrgentDepartureTest extends FlightTest {
    AirportDeparture yvrUrgent;
    Plane delta;

    @BeforeEach
    public void setUp() {
        yvr = new RegularDeparture();
        yvrUrgent = new UrgentDeparture();
        boeing = new Plane("Boeing", yvr);
        delta = new Plane("delta", yvrUrgent);
    }


    @Test
    public void testUrgentOverRegular() {
        yvr.makeNewDeparture(delta, 15);
        assertTrue(yvrUrgent.makeNewDeparture(boeing, 15));
        assertTrue(yvrUrgent.verifyDeparture(boeing, 15));
    }

    @Test
    public void testOutsideTime() {
        assertFalse(yvrUrgent.makeNewDeparture(boeing, 9999));
    }
}